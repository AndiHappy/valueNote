package io.basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 对于每一个请求都分发给一个线程，每个线程中都独自处理上面的流程。
这种模型由于IO在阻塞时会一直等待，因此在用户负载增加时，性能下降的非常快。 
 * */
public class BasicIoDesignPattern implements Runnable {
  private static final int PORT = 8888;

	public void run() {
    try {
        ServerSocket ss = new ServerSocket(PORT);
        while (!Thread.interrupted())
        new Thread(new Handler(ss.accept())).start(); //创建新线程来handle
        // or, single-threaded, or a thread pool
    } catch (IOException ex) { /* ... */ }
}

static class Handler implements Runnable {
    private static final int MAX_INPUT = 1024;
		final Socket socket;
    Handler(Socket s) { socket = s; }
    public void run() {
        try {
            byte[] input = new byte[MAX_INPUT];
            socket.getInputStream().read(input);
            byte[] output = process(input);
            socket.getOutputStream().write(output);
        } catch (IOException ex) { /* ... */ }
    }       
    private byte[] process(byte[] cmd) {
			return cmd; /* 处理数据 */ }
}
} 

