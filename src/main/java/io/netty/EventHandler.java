package io.netty;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

/**
 * @author guizhai
 *
 */
public class EventHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 在实际的netty的框架中，下面的这些的阶段性的操作都是通过handler来进行处理的。
	 * */
	public static void read(NioServerSocketChannel a) {
		final int maxMessagesPerRead = 16;
		 try {
			 // 接受客户端的链接
			SocketChannel ch =((ServerSocketChannel) a.ch).accept();
			
			//封装对应的channel
			NioSocketChannel niochannel = new NioSocketChannel(a, ch,SelectionKey.OP_READ);
			
			// 触发第二个逻辑，注册到work的eventLoop
			int radom = new Random().nextInt(a.reactor.workerGroup.children.length);
			a.reactor.workerGroup.children[radom].register(niochannel);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
