package valuenotes;

import java.nio.ByteBuffer;

import io.netty.util.internal.PlatformDependent;

/**
 * @author guizhai
 *
 */
public class PlatformDependentTest {
	
	/**
	 
	  Operating systems perform I/O operations on memory areas. These memory areas, as far as the operating system is concerned, are contiguous sequences of bytes. It's no surprise then that only byte buffers are eligible to participate in I/O operations. Also recall that the operating system will directly access the address space of the process, in this case the JVM process, to transfer the data. This means that memory areas that are targets of I/O perations must be contiguous sequences of bytes. In the JVM, an array of bytes may not be stored contiguously in memory, or the Garbage Collector could move it at any time. Arrays are objects in Java, and the way data is stored inside that object could vary from one JVM implementation to another.

For this reason, the notion of a direct buffer was introduced. Direct buffers are intended for interaction with channels and native I/O routines. They make a best effort to store the byte elements in a memory area that a channel can use for direct, or raw, access by using native code to tell the operating system to drain or fill the memory area directly.

Direct byte buffers are usually the best choice for I/O operations. By design, they support the most efficient I/O mechanism available to the JVM. Nondirect byte buffers can be passed to channels, but doing so may incur a performance penalty. It's usually not possible for a nondirect buffer to be the target of a native I/O operation. If you pass a nondirect ByteBufferTest object to a channel for write, the channel may implicitly do the following on each call:

Create a temporary direct ByteBufferTest object.
Copy the content of the nondirect buffer to the temporary buffer.
Perform the low-level I/O operation using the temporary buffer.
The temporary buffer object goes out of scope and is eventually garbage collected.
This can potentially result in buffer copying and object churn on every I/O, which are exactly the sorts of things we'd like to avoid. However, depending on the implementation, things may not be this bad. The runtime will likely cache and reuse direct buffers or perform other clever tricks to boost throughput. If you're simply creating a buffer for one-time use, the difference is not significant. On the other hand, if you will be using the buffer repeatedly in a high-performance scenario, you're better off allocating direct buffers and reusing them.

Direct buffers are optimal for I/O, but they may be more expensive to create than nondirect byte buffers. The memory used by direct buffers is allocated by calling through to native, operating system-specific code, bypassing the standard JVM heap. Setting up and tearing down direct buffers could be significantly more expensive than heap-resident buffers, depending on the host operating system and JVM implementation. The memory-storage areas of direct buffers are not subject to garbage collection because they are outside the standard JVM heap.

The performance tradeoffs of using direct versus nondirect buffers can vary widely by JVM, operating system, and code design. By allocating memory outside the heap, you may subject your application to additional forces of which the JVM is unaware. When bringing additional moving parts into play, make sure that you're achieving the desired effect. I recommend the old software maxim: first make it work, then make it fast. Don't worry too much about optimization up front; concentrate first on correctness. The JVM implementation may be able to perform buffer caching or other optimizations that will give you the performance you need without a lot of unnecessary effort on your part.
	 
	 * */
	
	
	/**
	I don't like that quote because it contains too much guessing. Also, the JVM certainly does not need to allocate a direct ByteBufferTest when doing IO for a non direct ByteBufferTest: it's sufficient to malloc a sequence of bytes on the heap, do the IO, copy from the bytes to the ByteBufferTest and release the bytes. Those areas could even be cached. But it is totally unnecessary to allocate a Java object for this. Real answers will only be obtained from measuring. Last time I did measurements there was no significant difference. I would have to redo tests to come up with all the specific details. – Robert Klemme  
	 * */

	/**
	 It's a well known fact that direct bytebuffer allocation is much slower than non direct byte buffers.

If you think about it when you allocate a non direct byte buffer then it basically just needs 
to dereference a pointer to some memory on the Java heap, which is very quick. But for a direct buffer, 
it has to malloc real memory from the OS, and do a bunch of other house keeping.

So yes, if you're using direct byte buffers it pays to re-use them.

You should speak to Trustin about this. I believe Netty uses non direct buffers for exactly this reason.
	 */


	public static void main(String[] args) {
		//		PlatformDependent dent = new PlatformDependent();

		/**
		 Allocates a new byte buffer.

		 The new buffer's position will be zero, 
		 its limit will be its capacity, 
		 its mark will be undefined, 
		 and each of its elements will be initialized to zero. 
		 It will have a backing array, and its array offset will be zero.
		 * */
		// 分配的是：HeapByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		/**
		 Allocates a new direct byte buffer.
		The new buffer's position will be zero, 
		its limit will be its capacity, 
		its mark will be undefined, 
		and each of its elements will be initialized to zero. 
		Whether or not it has a backing array is unspecified.
		 * */
		// 分配的是：DirectByteBuffer
		ByteBuffer buffer1 = ByteBuffer.allocateDirect(1024);

		long address = PlatformDependent.directBufferAddress(buffer);

		System.out.println("buffer: " + address);


		address = PlatformDependent.directBufferAddress(buffer1);

		System.out.println("buffer1: " + address);


	}

}
