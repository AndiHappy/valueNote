package lock;

/**
 * @author guizhai
 *
 */
public class MReentrantLock extends AQS {
 private static final long serialVersionUID = 1L;
 /**
  * 根据AQS的注释： Subclasses should be defined as non-public internal helper classes
  * that are used to implement the synchronization properties of their enclosing
  * class. 定义sync的内部类
  */
 private final Sync sync;

 abstract static class Sync extends AQS {
  private static final long serialVersionUID = -5179523762034025860L;

  abstract void lock();
  /**
   * 非公平获取锁 ！！！重要的框架！！！
   */
  final boolean nonfairTryAcquire(int acquires) {
   final Thread current = Thread.currentThread();
   int c = getState();
   if (c == 0) {
    if (compareAndSetState(0, acquires)) {
     setExclusiveOwnerThread(current);
     return true;
    }
   } else if (current == getExclusiveOwnerThread()) {
    int nextc = c + acquires;
    if (nextc < 0) // overflow
     throw new Error("Maximum lock count exceeded");
    setState(nextc);
    return true;
   }
   return false;
  }

  /**
   * 释放的时候，肯定是获得锁的线程进行调用，所以不需要CAS之类的操作，只需要判定状态即可
   */
  protected final boolean tryRelease(int releases) {
   int c = getState() - releases;
   if (Thread.currentThread() != getExclusiveOwnerThread())
    throw new IllegalMonitorStateException();
   boolean free = false;
   if (c == 0) {
    free = true;
    setExclusiveOwnerThread(null);
   }
   setState(c);
   return free;
  }

  protected final boolean isHeldExclusively() {
   // While we must in general read state before owner,
   // we don't need to do so to check if current thread is owner
   return getExclusiveOwnerThread() == Thread.currentThread();
  }

  final ConditionObject newCondition() {
   return new ConditionObject();
  }

  // Methods relayed from outer class
  final Thread getOwner() {
   return getState() == 0 ? null : getExclusiveOwnerThread();
  }

  final int getHoldCount() {
   return isHeldExclusively() ? getState() : 0;
  }

  final boolean isLocked() {
   return getState() != 0;
  }

 }

 static final class NonfairSync extends Sync {
  /**
   * so beautiful ！ 整个操作如此的不见烟火气：
   *
   * ①首先是CAS(state,1) 确定是否已经有线程抢占到锁，然后设置：ExclusiveThread（这个是普通的成员变量）,返回true；
   *  
   * 然后是：Acquire(1),更新state为1，
   * 
   * 具体的逻辑： !tryAcquire(1) => acquireQueued(addWaiter(Node.EXCLUSIVE), 1)) =>selfInterrupt()
   * 
   * 根据getState的状态进行判断： 
   *    如果是0，说明原来的线程已经释放锁，重新的走① 
   *    如果大于零，当前的线程（Thread.concurrent）和 ExclusiveOwnerThread 进行比较 
   *              如果是独占线程为当前线程，则state+1，返回true; 如果不是，则返回false
   * 
   * 只有在返回false的前提下，才会执行：addWaiter(Node.EXCLUSIVE), 1)，
   */
  final void lock() {
   // 只是使用一个CAS的操作，就保证了线程抢占的唯一性
   if (compareAndSetState(0, 1))
    // private transient Thread exclusiveOwnerThread; 可以说只是一个普通的成员变量
    setExclusiveOwnerThread(Thread.currentThread());
   else
    // 如果是同一个线程，则去更新状态为1，如果不是当前的线程，会被阻塞的
    acquire(1);
  }

  protected final boolean tryAcquire(int acquires) {
   return nonfairTryAcquire(acquires);
  }
 }

 public MReentrantLock() {
  sync = new NonfairSync();
 }

 public void lock() {
  sync.lock();
 }

 public void unlock() {
  sync.release(1);
 }

 @Override
 protected boolean tryAcquire(int arg) {
  return super.tryAcquire(arg);
 }

 static class ThreadDemo extends Thread {
  MReentrantLock lock;

  public ThreadDemo(int i, MReentrantLock lock) {
   setName("name" + i);
   this.lock = lock;
  }

  @Override
  public void run() {
   try {
    lock.lock();
    Thread.sleep(1000);
   } catch (InterruptedException e) {
   } finally {
    lock.unlock();
   }
  }
 }

 public static void main(String[] args) {
  MReentrantLock lock = new MReentrantLock();
  ThreadDemo[] threads = new ThreadDemo[10];
  int i = 0;
  for (; i < 10; i++) {
   threads[i] = new ThreadDemo(i, lock);
   threads[i].start();
  }
 }
}
