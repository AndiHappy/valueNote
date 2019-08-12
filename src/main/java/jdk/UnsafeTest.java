package jdk;

import java.lang.reflect.Field;

/**
 * @author guizhai
 * 
 * Unsafe: This class provides us with low-level mechanisms that were designed to be used only by the core Java library 
 * and not by standard users.
 */
public class UnsafeTest {
	
	static class InitializationOrdering {
    private long a;
 
    public InitializationOrdering() {
        this.a = 1;
    }
 
    public long getA() {
        return this.a;
    }
}
	
	class CASCounter {
    private sun.misc.Unsafe unsafe;
    private volatile long counter = 0;
    private long offset;
 
    private sun.misc.Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (sun.misc.Unsafe) f.get(null);
    }
 
    public CASCounter() throws Exception {
        unsafe = getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }
 
    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }
 
    public long getCounter() {
        return counter;
    }
}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws NoSuchFieldException, Exception {
		Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
		System.out.println(unsafe);
		String[] value = new String[20];
		for (int i = 0; i < value.length; i++) {
			value[i] = "k"+i;
		}
		
		Class<?> ak = String[].class;
		int ABASE = unsafe.arrayBaseOffset(ak);
		
		int scale = unsafe.arrayIndexScale(ak);
		if ((scale & (scale - 1)) != 0)
			throw new Error("data type scale not a power of two");
		
		int ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
		
		System.out.println("ABASE: "+ ABASE + "  ASHIFT: "+ASHIFT );
		
		//拿到数组的下标的的元素，为什么这么做尼？
		String vstring = (String) unsafe.getObjectVolatile(value, ((long)5 << ASHIFT) + ABASE);
		System.out.println(vstring);
		
		/*
		InitializationOrdering o1 = new InitializationOrdering();
		assertEquals(o1.getA(), 1);
		*/
		
		/**
		 * allocateInstance() method using Unsafe. It will only allocate the memory for our class, 
		 * and will not invoke a constructor
		 * */
		/*
		InitializationOrdering o3  = (InitializationOrdering) unsafe.allocateInstance(InitializationOrdering.class);
	  assertEquals(o3.getA(), 0);
	  
	  
	  Field fa = o1.getClass().getDeclaredField("a");
	  unsafe.putInt(o1, unsafe.objectFieldOffset(fa), 1000);
	  assertEquals(o1.getA(), 1000);
	  */
	  
	  /**
	   * The allocateMemory() method from the Unsafe class gives us the ability 
	   * 			to allocate huge objects off the heap, 
	   * meaning that this memory will not be seen and taken into account by the GC and the JVM.
	   * */
	  /*
	  int BYTE =1;
	  // 内存的地址
		long address = unsafe.allocateMemory(1024 * BYTE );
	  System.out.println(address);
	  long begine = address;
	  String value = "address begine";
	  byte[] arrays = value.getBytes();
	  for (byte b : arrays) {
	  	unsafe.putByte(begine + 1 * BYTE, b);
	  	begine = begine+1*BYTE;
		}
	  
	  byte[] readValue = new byte[arrays.length];
	  long read = address;
	  for (int i = 1; i <= readValue.length; i++) {
	  	readValue[i-1] =unsafe.getByte(read + i * BYTE);
		}
	  
	  for (int i = 0; i < readValue.length; i++) {
			if(readValue[i] != arrays[i]) {
				System.out.println("NOT Same");
			}
		}
	  
	  //TODO 确定释放了2014 ，还是只释放个这个内存地址？
	  unsafe.freeMemory(address);
	  */
	  
	  /**
	   * It is very similar to the Object.wait() method, but it is calling the native OS code,
	   *  thus taking advantage of some architecture specifics to get the best performance.
	   *  
	   * When the thread is blocked and needs to be made runnable again, the JVM uses the unpark() method. 
	   * We’ll often see those method invocations in thread dumps, especially in the applications 
	   * which use thread pools.
	   * 
	   unsafe.park(arg0, arg1);
	   unsafe.unpark(arg0);
	   * */

	  /**
		 * throw Exception in thread "main" java.lang.SecurityException: Unsafe
		
		sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
		System.out.println(U);
		
		 * */
	

	}
	


}
