package leetcode.L;

/**
 * @author guizhai
 *
 */
public class L344ReverseString {
 
 /**

Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
  * */
 
 /**
  * 异常处理，然后才是优化处理
  * */
 public void reverseString(char[] s) {
  if (s == null) {
      throw new IllegalArgumentException("char[] cannot be null!");
  }
  if ( s.length <= 1) {
      return ;
  }
  
  for (int i = 0; i < s.length / 2; ++i) {
      char tmp = s[i];
      s[i] = s[s.length - 1 - i];
      s[s.length - 1 - i] = tmp;
  }
}

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub

 }

}
