package io.basicreactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
WorkerThreadReactor：负责响应IO事件，当检测到一个新的事件，将其发送给相应的Handler去处理。
Handler：负责处理非阻塞的行为，标识系统管理的资源；同时将handler与事件绑定。
Reactor为单个线程，需要处理accept连接，同时发送请求到处理器中
 * */
public class Reactor implements Runnable {
 Selector selector;
 ServerSocketChannel serverSocket;

 Reactor(int port) throws IOException { //Reactor初始化
  selector = Selector.open();
  serverSocket = ServerSocketChannel.open();
  serverSocket.socket().bind(new InetSocketAddress(port));
  serverSocket.configureBlocking(false); //非阻塞
  SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT); //分步处理,第一步,接收accept事件
  sk.attach(new Acceptor()); //attach callback object, Acceptor
 }

 /*
  * WorkerThreadReactor 的一直运行的单线程，处理第一步的事件：SelectionKey.OP_ACCEPT
  * 处理事件的逻辑就是：(Runnable)(k.attachment()).run(),其实这里的runnable 就是Acceptor
  * */
 public void run() {
  try {
   while (!Thread.interrupted()) {
    System.out.println("selector select begine!");
    selector.select();
    System.out.println("selector select after!");
    Set<?> selected = selector.selectedKeys();
    Iterator<?> it = selected.iterator();
    while (it.hasNext())
     dispatch((SelectionKey) (it.next())); //Reactor负责dispatch收到的事件
     selected.clear();
   }
  } catch (IOException ex) {
  }
 }

 void dispatch(SelectionKey k) {
  Runnable r = (Runnable) (k.attachment()); //调用之前注册的callback对象
  if (r != null)
   r.run();
 }

 /*
  * 处理事件的第二部：交给handler处理
  * */
 class Acceptor implements Runnable { // inner
  public void run() {
   try {
    SocketChannel c = serverSocket.accept();
    if (c != null)
     new Handler(selector, c);
   } catch (IOException ex) {
   }
  }
 }

 public static void main(String[] args) throws IOException {
  System.out.println("WorkerThreadReactor");
  Reactor test = new Reactor(8000);
  test.run();
 }
}

final class Handler implements Runnable {
 private static final int MAXIN = 256;
 private static final int MAXOUT = 1024;
 final SocketChannel socket;
 final SelectionKey sk;
 ByteBuffer input = ByteBuffer.allocate(MAXIN);
 ByteBuffer output = ByteBuffer.allocate(MAXOUT);
 static final int READING = 0, SENDING = 1;
 int state = READING;

 Handler(Selector sel, SocketChannel c) throws IOException {
  socket = c;
  c.configureBlocking(false);

  // Optionally try first read now
  sk = socket.register(sel, READING);
  sk.attach(this); //将Handler作为callback对象
  sk.interestOps(SelectionKey.OP_READ); //第二步,接收Read事件
  sel.wakeup();
 }

 boolean inputIsComplete() {
  return true;
 }

 boolean outputIsComplete() {
  return true;
 }

 void process() {
  String read = new String(input.array());
  System.out.println(read);
  //    	input.clear();
 }

 public void run() {
  try {
   if (state == READING)
    read();
   else if (state == SENDING)
    send();
  } catch (IOException ex) {
  }
 }

 void read() throws IOException {
  socket.read(input);
  if (inputIsComplete()) {
   process();
   state = SENDING;
   // Normally also do first write now
   sk.interestOps(SelectionKey.OP_WRITE); //第三步,接收write事件
  }
 }

 void send() throws IOException {
  //    	output.put("response write".getBytes());
  //    	output.flip();
  System.out.println("循环");
  socket.write(input);
  state = READING;
  // Normally also do first write now
  sk.interestOps(SelectionKey.OP_READ); //第三步,接收write事件
  //      if (outputIsComplete()) sk.cancel(); //write完就结束了, 关闭select key
 }
}
