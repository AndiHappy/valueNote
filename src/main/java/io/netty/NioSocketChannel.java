package io.netty;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NioSocketChannel {

	NioServerSocketChannel parent;

	SocketChannel ch;

	NioEventLoop eventloop;

	private int readInterestOp;

	public NioSocketChannel(NioServerSocketChannel a, SocketChannel ch, int readInterestOp) throws Exception {
		this.parent = a;
		this.ch = ch;
		this.ch.configureBlocking(false);
		this.readInterestOp = readInterestOp;
	}

	public void doregister(NioEventLoop nioEventLoop) {
		this.eventloop = nioEventLoop;
		try {
			System.out.println(this.ch.toString());
			SelectionKey sk = this.ch.register(this.eventloop.selector, 0, this);

			// 注册自己感兴趣的事件，都是handler在实现，这个次是：fireChannelActive 触发的
			//注册完成以后，有handler 触发 SelectionKey 注册 感兴趣的时间类型
			final int interestOps = sk.interestOps();
			if ((interestOps & readInterestOp) == 0) {
				sk.interestOps(interestOps | readInterestOp);
			}

			this.eventloop.run();
		} catch (ClosedChannelException e) {
		}

	}

}
