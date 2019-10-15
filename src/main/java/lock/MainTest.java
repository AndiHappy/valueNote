package lock;

/**
 * @author guizhai
 *
 */
public class MainTest {

 static final int SHARED_SHIFT   = 16;
 
 static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
 
 static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
 
 static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;
 
 public static void main(String[] args) {
  
  System.out.println(Integer.toBinaryString(SHARED_SHIFT));
  
  System.out.println(Integer.toBinaryString(SHARED_UNIT));
  
  System.out.println(Integer.toBinaryString(MAX_COUNT));
  
  System.out.println(Integer.toBinaryString(EXCLUSIVE_MASK));
  int c = 100;
  System.out.println(Integer.toBinaryString(c));
  
  System.out.println(Integer.toBinaryString(c & EXCLUSIVE_MASK));
  
  System.out.println(Integer.toBinaryString(c >>> SHARED_SHIFT));

  
//  System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
//
//  System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));

 }

}
