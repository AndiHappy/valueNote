package leetcode.L;

/**
 * @author guizhai
 *
 */
public class L326PowerofThree {

 /**
 Given an integer, write a function to determine if it is a power of three.
 
 Example 1:
 
 Input: 27
 Output: true
 Example 2:
 
 Input: 0
 Output: false
 Example 3:
 
 Input: 9
 Output: true
 Example 4:
 
 Input: 45
 Output: false
 Follow up:
 Could you do it without using any loop / recursion?
 
  */

 public boolean isPowerOfThree(int n) {
  // 1162261467 is 3^19,  3^20 is bigger than int  
  return (n > 0 && 1162261467 % n == 0);
 }
 
 /**
  * 数学的方法
  * */
 public boolean isPowerOfThree_Persue(int n) {
  int maxPow = (int)(Math.pow(3, (int)(Math.log(Integer.MAX_VALUE) / Math.log(3))));
  return (n > 0 && maxPow % n == 0);
}

 public static void main(String[] args) {
  // TODO Auto-generated method stub

 }

}
