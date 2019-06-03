package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L157ReadNCharactersGivenRead4 {

	/**
	
	The API: int read4(char *buf) reads 4 characters at a time from a file.
	The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
	By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
	Note:
	The read function will only be called once for each test case.
	Understand the problem:
	This seemingly easy coding question has some tricky edge cases. When read4 returns
	less than 4, we know it must reached the end of file. However, take note that read4
	returning 4 could mean the last 4 bytes of the file.
	
	To make sure that the buffer is not copied more than n bytes, copy the remaining bytes
	(n – readBytes) or the number of bytes read, whichever is smaller.
	
	
	
	 */

	/* The read4 API is defined in the parent class Reader4.
	int read4(char[] buf); */


//	public class Solution extends Reader4 {
//		/**
//		 * @param buf Destination buffer
//		 * @param n   Maximum number of characters to read
//		 * @return    The number of characters read
//		 */
//		public int read(char[] buf, int n) {
//			boolean eof = false;
//			int charsRead = 0;
//			char[] buf4 = new char[4];
//
//			while (!eof && charsRead < n) {
//				int size = read4(buf4);
//				if (size < 4) {
//					eof = true;
//				}
//
//				if (charsRead + size > n) {
//					size = n - charsRead;
//				}
//
//				System.arraycopy(buf4, 0, buf, charsRead, size);
//				charsRead += size;
//			}
//
//			return charsRead;
//		}
//	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
