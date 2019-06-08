package redissource.lock;

import static redissource.lock.LockConstants.NOT_EXIST;
import static redissource.lock.LockConstants.OK;
import static redissource.lock.LockConstants.SECONDS;

import java.time.LocalTime;

import redis.clients.jedis.Jedis;
public class LockCase1 extends RedisLock {

		public LockCase1(Jedis jedis, String name) {
        super(jedis, name);
    }
		

		
		 @Override
	    public void lock() {
			 
		 }

		/**
		 假设有两个客户端A和B，A获取到分布式的锁。A执行了一会，突然A所在的服务器断电了（或者其他什么的），也就是客户端A挂了。
		 这时出现一个问题，这个锁一直存在，且不会被释放，其他客户端永远获取不到锁。
		 * */
    public void lock1() {
        while(true){
        	 /**
        	  Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1 GB).
Specified by: set(...) in JedisCommands
Parameters:
key
value
params NX|XX, 
NX -- Only set the key if it does not already exist. 
XX -- Only set the key if it already exist. EX|PX, expire time units:
EX = seconds; 
PX = milliseconds
Returns:
Status code reply
        	  * */
            String result = jedis.set(lockKey, "value", NOT_EXIST);
            if(OK.equals(result)){
                System.out.println(Thread.currentThread().getId()+"加锁成功!");
                break;
            }
        }
    }
    

    /**
     客户端A获取锁成功，过期时间30秒。
客户端A在某个操作上阻塞了50秒。
30秒时间到了，锁自动释放了。
客户端B获取到了对应同一个资源的锁。
客户端A从阻塞中恢复过来，释放掉了客户端B持有的锁。
     * */
    public void lock2() {
      while(true){
          String result = jedis.set(lockKey, "value", NOT_EXIST,SECONDS,30);
          if("OK".equals(result)){
              System.out.println(Thread.currentThread().getId()+"加锁成功!");
              break;
          }
      }
  }
    
    /**
     * 防止误删除其他线程的锁
     * lockValue: 为  UUID.randomUUID().toString()+Thread.currentThread().getId()
     * 保持线程的唯一
     * */
    
    public void lock3() {
      while(true){
          String result = jedis.set(lockKey, lockValue, NOT_EXIST,SECONDS,30);
          if(OK.equals(result)){
              System.out.println(Thread.currentThread().getId()+"加锁成功!");
              break;
          }
      }
  }
    
    /**
     * 对应解锁的时候，对照值才进行删除
     * */
    public void unlock3() {
    	/**
       * 此处不具备原子性,三个步骤
       * 获取锁对应的value值
       * 检查是与自身的lockValue是否相等
       * 相等则删除锁（解锁）
       */
      String lockValue = jedis.get(lockKey);
      if (lockValue.equals(this.lockValue)){
          jedis.del(lockKey);
      }
  }
    
    /**
     * 进化解锁4：使用lua脚本保证解锁过程的唯一
     * */
    public void unlock3_1() {
      // 使用lua脚本进行原子删除操作
      String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                  "return redis.call('del', KEYS[1]) " +
                                  "else " +
                                  "return 0 " +
                                  "end";
      jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
  }
    
    /**
     * 解决线程运行锁超时的问题：增加冲刷线程
     * */
    public void lock4(){
      while (true) {
          String result = jedis.set(lockKey, lockValue, NOT_EXIST, SECONDS, 30);
          if (OK.equals(result)) {
              System.out.println("线程id:"+Thread.currentThread().getId() + "加锁成功!时间:"+LocalTime.now());

              //开启定时刷新过期时间
              isOpenExpirationRenewal = true;
              scheduleExpirationRenewal();
              break;
          }
          System.out.println("线程id:"+Thread.currentThread().getId() + "获取锁失败，休眠10秒!时间:"+LocalTime.now());
          //休眠10秒
          sleepBySencond(10);
      }
  }

 /**
  * 对应的解锁的过程中，
  * */
  public void unlock4() {
      System.out.println("线程id:"+Thread.currentThread().getId() + "解锁!时间:"+LocalTime.now());

      String checkAndDelScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                                  "return redis.call('del', KEYS[1]) " +
                                  "else " +
                                  "return 0 " +
                                  "end";
      jedis.eval(checkAndDelScript, 1, lockKey, lockValue);
      isOpenExpirationRenewal = false;

  }

    @Override
    public void unlock() {
        jedis.del(lockKey);
    }
}