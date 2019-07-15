package io.netty;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelException;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;

/**
 
简单的拆分Netty的主要的响应流程

1. NioEventLoop 为单线程的无线循环处理 IO事件。
2. 主要的事件触发，是有handler来进行触发的，哪怕是注册事件之类的，accept之类的，handler应用的非常的彻底。
3. 还不是非常的清楚SelectedSelectionKeySet 这个的响应的机制，要在研究一下。
 * */

class NioEventLoopGroup {

	SelectorProvider provider = SelectorProvider.provider();
	Object threadFactory = new Object();
	NioEventLoop[] children = new NioEventLoop[] { new NioEventLoop(this.provider, this.threadFactory, this),
			new NioEventLoop(this.provider, this.threadFactory, this) };

}

//netty里面NioEventLoop 继承的有一个类名称：SingleThreadEventLoop
class NioEventLoop implements Runnable {

	private static Logger log = LoggerFactory.getLogger(NioEventLoop.class);
	SelectorProvider provider = null;
	Object threadFactory = null;
	NioEventLoopGroup parent = null;
	Selector selector;
	SelectedSelectionKeySet selectedKeys;
	private static final boolean DISABLE_KEYSET_OPTIMIZATION = SystemPropertyUtil
			.getBoolean("io.netty.noKeySetOptimization", false);

	public NioEventLoop(SelectorProvider provider, Object threadFactory, NioEventLoopGroup parent) {
		this.parent = parent;
		this.provider = provider;
		this.threadFactory = threadFactory;
		this.selector = openSelectoer();
	}

	private Selector openSelectoer() {
		final Selector selector;
		try {
			selector = provider.openSelector();
		} catch (IOException e) {
			throw new ChannelException("failed to open a new selector", e);
		}

		if (DISABLE_KEYSET_OPTIMIZATION) {
			return selector;
		}
		try {
			SelectedSelectionKeySet selectedKeySet = new SelectedSelectionKeySet();

			Class<?> selectorImplClass = Class.forName("sun.nio.ch.SelectorImpl", false,
					PlatformDependent.getSystemClassLoader());

			// Ensure the current selector implementation is what we can instrument.
			if (!selectorImplClass.isAssignableFrom(selector.getClass())) {
				return selector;
			}

			Field selectedKeysField = selectorImplClass.getDeclaredField("selectedKeys");
			Field publicSelectedKeysField = selectorImplClass.getDeclaredField("publicSelectedKeys");

			selectedKeysField.setAccessible(true);
			publicSelectedKeysField.setAccessible(true);

			//注入自定义的SelectedSelectionKeySet
			selectedKeysField.set(selector, selectedKeySet);
			publicSelectedKeysField.set(selector, selectedKeySet);
			
			selectedKeys = selectedKeySet;
		} catch (Throwable t) {
			selectedKeys = null;
		}
		return selector;
	}

	////////////////register 可以通过基类来进行统一为一个方法解决 -- begine ////////////////////////////////
	public void register(NioServerSocketChannel channel) {
		System.out.println(this.toString());
		channel.doregister(this);
	}
	public void register(NioSocketChannel niochannel) {
		System.out.println(this.toString());
		niochannel.doregister(this);
	}
	////////////////register 可以通过基类来进行统一为一个方法解决 -- end ////////////////////////////////


	//下面是整个死循环配套的东西
	private final AtomicBoolean wakenUp = new AtomicBoolean();
	private Queue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

	@Override
	public void run() {
		for (;;) {
			try {
				boolean oldWakenUp = wakenUp.getAndSet(false);
				if (!taskQueue.isEmpty()) { // hasTask()
					//////////// selectNow() --begine /////////////////
					try {
						selector.selectNow();
					} finally {
						// restore wakup state if needed
						if (wakenUp.get())
							selector.wakeup();
					}
					////////////selectNow() --end /////////////////

				} else {
					//没有任务的情况下
					select(oldWakenUp);//没有任务的情况
					if (wakenUp.get()) {
						selector.wakeup();
					}
				}
				processSelectedKeys();
				runAllTasks();
			} catch (Throwable t) {
			}
		}

	}

	private void runAllTasks() {
	}

	private void processSelectedKeys() {
		if (selectedKeys != null) {
			processSelectedKeysOptimized(selectedKeys.flip());
		}
	}

	private void processSelectedKeysOptimized(SelectionKey[] selectionKeys) {
		for (int i = 0;; i++) {
			SelectionKey k = selectionKeys[i];
			if (k == null) {
				break;
			}
			// null out entry in the array to allow to have it GC'ed once the Channel close
			// See https://github.com/netty/netty/issues/2363
			selectionKeys[i] = null;
			final Object a = k.attachment();
			if (a instanceof NioServerSocketChannel) {
				log.info("事件处理,事件的Int值:" + k.interestOps() + ",attachment:" + a.toString() + ".");
				processSelectedKey(k, (NioServerSocketChannel) a);
			} else if (a instanceof NioSocketChannel) {
				processSelectedKey(k, (NioSocketChannel) a);
			}
		}

	}

	/**
	 * 可以统一处理的事件处理SelectionKey
	 * */
	private void processSelectedKey(SelectionKey k, NioSocketChannel a) {
		
	}
	private void processSelectedKey(SelectionKey k, NioServerSocketChannel a) {
		try {
			if (!k.isValid()) {
				// close the channel if the key is not valid anymore
				a.ch.close();
				return;
			}
			
			 int readyOps = k.readyOps();
			 // Also check for readOps of 0 to workaround possible JDK bug which may otherwise lead to a spin loop
			 //read 和 accept事件
			 if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0) {
			     EventHandler.accept(a);
			     if (!a.ch.isOpen()) {
			         // Connection already closed - no need to handle write.
			         return;
			     }
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void select(boolean oldWakenUp) throws IOException {
		Selector selector = this.selector;
		try {
			int selectCnt = 0;
			long currentTimeNanos = System.nanoTime();
			long selectDeadLineNanos = currentTimeNanos + TimeUnit.SECONDS.toNanos(1);// delayNanos(currentTimeNanos);
			for (;;) {
				//timeoutMillis 基本上就是1秒钟，1000ms的时间
				long timeoutMillis = (selectDeadLineNanos - currentTimeNanos + 500000L) / 1000000L;
				if (timeoutMillis <= 0) {
					if (selectCnt == 0) {
						selector.selectNow();
						selectCnt = 1;
					}
					break;
				}
				//            log.info("空转的执行的第一步:selectDeadLineNanos: {}, currentTimeNanos:{},timeoutMillis :{} ",selectDeadLineNanos
				//            		,currentTimeNanos,timeoutMillis);

				int selectedKeys = selector.select(timeoutMillis);
				selectCnt++;
				if (selectedKeys != 0 || oldWakenUp || wakenUp.get() || !taskQueue.isEmpty() /*|| hasScheduledTasks()*/) {
					// - Selected something,
					// - waken up by user, or
					// - the task queue has a pending task.
					// - a scheduled task is ready for processing
					break;
				}
				if (Thread.interrupted()) {
					// Thread was interrupted so reset selected keys and break so we not run into a busy loop.
					// As this is most likely a bug in the handler of the user or it's client library we will
					// also log it.
					//
					// See https://github.com/netty/netty/issues/2426
					selectCnt = 1;
					break;
				}

				long time = System.nanoTime();
				if (time - TimeUnit.MILLISECONDS.toNanos(timeoutMillis) >= currentTimeNanos) {
					// timeoutMillis elapsed without anything selected.
					selectCnt = 1;
				}
				currentTimeNanos = time;
			}

		} catch (CancelledKeyException e) {
		}
	}

	

}

class NioServerSocketChannel {
	static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
	final SelectableChannel ch;
	final int readInterestOp;
	volatile NioEventLoop eventloop;
	Reactor reactor;
	
	public NioServerSocketChannel(Reactor reactor) throws IOException {
		this.readInterestOp = SelectionKey.OP_ACCEPT;
		this.ch = DEFAULT_SELECTOR_PROVIDER.openServerSocketChannel();
		this.ch.configureBlocking(false);
		this.reactor = reactor;
	}

	public void doregister(NioEventLoop nioEventLoop) {
		this.eventloop = nioEventLoop;
		try {
			System.out.println(this.ch);
			SelectionKey sk = this.ch.register(this.eventloop.selector, 0,this);
      
			//注册完成以后，有handler 触发 SelectionKey 注册 感兴趣的时间类型
			 final int interestOps = sk.interestOps();
       if ((interestOps & readInterestOp) == 0) {
       	//readInterestOp=16，这个应该是 int OP_ACCEPT = 1 << 4
       	//这个值是如何初始化的
      	sk.interestOps(interestOps | readInterestOp);
       }
			this.eventloop.run();
		} catch (ClosedChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void bind(InetSocketAddress add, Object promise) {
		try {
			((ServerSocketChannel) this.ch).socket().bind(add, 100);
		} catch (IOException e) {
		}
	}


}

public class Reactor {

	NioEventLoopGroup bossGroup = new NioEventLoopGroup();
	NioEventLoopGroup workerGroup = new NioEventLoopGroup();

	Reactor(InetSocketAddress add) throws Exception { //Reactor初始化
		// ini channel and register,
		// 构建函数中添加启动配置类的原因是，在处理完accept事件之后，需要找到workergroup,
		// 而workergroup的成员变量，只有在Reactor中定义
		NioServerSocketChannel channel = new NioServerSocketChannel(this);
		//绑定逻辑
		Object promise = new Object();
		channel.bind(add, promise);

		int index = new Random().nextInt(bossGroup.children.length);
		bossGroup.children[index].register(channel);
		Thread.currentThread().join();
	}

	public static void main(String[] args) throws Exception {
		new Reactor(new InetSocketAddress(8000));
	}
}

