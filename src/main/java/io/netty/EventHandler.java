package io.netty;

import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guizhai
 *
 */
public class EventHandler {

	public static Logger log = LoggerFactory.getLogger(EventHandler.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 在实际的netty的框架中，下面的这些的阶段性的操作都是通过handler来进行处理的。
	 * */
	public static void accept(NioServerSocketChannel a) {
		 try {
			 // 接受客户端的链接
			SocketChannel ch =((ServerSocketChannel) a.ch).accept();
			//封装对应的channel,指的是和客户端连接的channel
			NioSocketChannel niochannel = new NioSocketChannel(a, ch,SelectionKey.OP_READ);
			
			// 选择child的eventLoop
			int radom = new Random().nextInt(a.reactor.workerGroup.children.length);
			// 触发workerGroup里面的NioEventLoop逻辑，这句就相当于连接了
			a.reactor.workerGroup.children[radom].register(niochannel);
			
		} catch (Exception e) {
			log.info("Accept Error:{}",e);
		}
	}

}
