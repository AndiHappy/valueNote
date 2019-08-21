package valuenotes;

import java.nio.ByteBuffer;

public class ByteBufferTest {
	
	public static void main(String[] args) {
		
		ByteBuffer test = ByteBuffer.allocateDirect(1024);
		int post = test.position();
		System.out.println("post1: "+ post);
		//2
		ByteBuffer putpost = test.putChar('a');
		
		System.out.println(putpost.toString() +"    "+test.toString());
		
		post = putpost.position();
		
		
		System.out.println("post2: "+ post);
		
		 putpost = test.putChar('a');
			
			System.out.println(putpost.toString() +"    "+test.toString());
			
			post = putpost.position();
			
			
			System.out.println("post2: "+ post);
		
	}

}
