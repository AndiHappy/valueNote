package thread;

public class ThreadLocalExample {

 public static class MyRunnable implements Runnable {
  ThreadLocal<String> threadLocal = null;
  ThreadLocal<String> threadLocal2 = new ThreadLocal<String>();
  ThreadLocal<String> threadLocal3 = new ThreadLocal<String>();
  ThreadLocal<String> threadLocal4 = new ThreadLocal<String>();
  public MyRunnable(ThreadLocal<String> threadLocal) {
   this.threadLocal = threadLocal;
  }

  @Override
  public void run() {
   threadLocal.set(Thread.currentThread().getName());
   threadLocal2.set(Thread.currentThread().getName());
   threadLocal3.set(Thread.currentThread().getName());
   threadLocal4.set(Thread.currentThread().getName());
   try {
    Thread.sleep((int) (Math.random() * 100D));
   } catch (InterruptedException e) {
   }

   sysout();
  }

  private void sysout() {
   System.out.println(threadLocal.get());
  }
 }

 public static void main(String[] args) throws InterruptedException {
  ThreadLocal<String> threadLocal = new ThreadLocal<String>();
  MyRunnable sharedRunnableInstance = new MyRunnable(threadLocal);

  Thread thread1 = new Thread(sharedRunnableInstance);
  Thread thread2 = new Thread(sharedRunnableInstance);

  thread1.start();
  thread2.start();
  
  thread1.join(); //wait for thread 1 to terminate
  thread2.join(); //wait for thread 2 to terminate
  
  System.out.println(threadLocal.get().charAt(1));
 }

}