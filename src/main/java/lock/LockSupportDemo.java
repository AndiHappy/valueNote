package lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

 public static void main(String[] args) throws InterruptedException {

  Thread test1 = new Thread(new Runnable() {
   @Override
   public void run() {

    try {
     Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    System.out.println("park before !");
    LockSupport.park("");
    System.out.println("park after !");
   }
  });
  
  test1.start();

  LockSupport.unpark(test1);
  System.out.println("Call unpark first");

  Thread.sleep(1000);
  System.out.println("Thread.state: " + test1.getState().name());

 }
}
