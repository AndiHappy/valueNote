package jdk.selectkey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author guizhai
 *
 */
public class SelectorExample {

 private final Selector selector;
 
 private final SelectionKey sockKey;

 public SelectorExample() throws IOException {
  selector = Selector.open();
  SocketChannel sock = SocketChannel.open();
  sock.configureBlocking(false);
  sock.socket().setSoLinger(false, -1);
  sock.socket().setTcpNoDelay(true);
  sockKey = sock.register(selector, SelectionKey.OP_CONNECT);
 }

 public void testSelector() {
  new Thread(new Runnable() {

   @Override
   public void run() {
    while (true) {
     try {
      System.out.println(System.currentTimeMillis());
      selector.select(10000);
      System.out.println(System.currentTimeMillis());
     } catch (IOException e) {
     }
     Set<SelectionKey> selected;
     synchronized (this) {
      selected = selector.selectedKeys();
     }
     System.out.println("selector.selectedKeys after: " + selected.size());
     for (SelectionKey k : selected) {
      if ((k.readyOps() & SelectionKey.OP_CONNECT) != 0) {
       System.out.println("dele with selectionKey: " + k.readyOps());

      } else if ((k.readyOps() & (SelectionKey.OP_READ | SelectionKey.OP_WRITE)) != 0) {
       System.out.println("dele with selectionKey: " + k.readyOps());
      }
     }
    }

   }
  }).start();

 }

 public static void main(String[] args) throws Exception {

  SelectorExample example = new SelectorExample();

  example.testSelector();

  example.testWaker();

  Thread.currentThread().join();

 }

 private void testWaker() {
  new Thread(new Runnable() {

   @Override
   public void run() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
     try {
      String str = reader.readLine();
      if (str.startsWith("hello")) {
       System.out.println(str);
      } else if (str.startsWith("wake")) {
       System.out.println("selector.wakeup");
       selector.wakeup();
      }
     } catch (IOException e) {
      e.printStackTrace();
     }
    }
   }
  }).start();

 }

}
