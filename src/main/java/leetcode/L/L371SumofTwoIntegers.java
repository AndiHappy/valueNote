package leetcode.L;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L371SumofTwoIntegers {

 /**
 
 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 
 Example 1:
 
 Input: a = 1, b = 2
 Output: 3
 Example 2:
 
 Input: a = -2, b = 3
 Output: 1
  */

 public int getSum(int a, int b) {
  return 0;
 }

 public static String toBinaryString(int a, int alignment) {
  String value = Integer.toBinaryString(a);
  int vl = value.length();
  if (value.length() <= alignment) {
   StringBuilder builder = new StringBuilder();
   for (int i = 0; i < alignment; i++) {
    if (i < alignment - vl) {
     builder.append("0");
    } else {
     builder.append(value.charAt(i - (alignment - vl)));
    }
    if ((i + 1) % 4 == 0) {
     builder.append(" ");
    }
   }
   return builder.toString();
  }
  return value;
 }

 // public static int getNumsOfOneByMove(int n) {
 //  int count = 0;
 //  while(n > 0) {
 //   if((n&1) > 0) count++;
 //    n = n>>1;
 //  }
 //  return count;
 // }

 public static int getNumsOfOneByMove(int n) {
  int count = 0;
  for (; n > 0; n >>= 1) {
   count += n & 1;
  }
  return count;
 }

 /**
  * 对于n来说，只要每次消除n中存在的1即可。那么n &= (n - 1) 为什么能够保证可以消除一个1尼？
  * 
  * 为什么n &= (n – 1)能清除最右边的1呢？因为从二进制的角度讲，n相当于在n - 1的最低位加上1
  * n(00**11***01) = n-1(00**11***00)+1
  * 
  * */
 public static int getNumsOfOne(int n) {
  int count = 0;
  for (; n > 0; ++count) {
   n &= (n - 1); // 清除最低位的1
  }
  return count;
 }
 
 public static void swap(int[] a,int i , int j) {
  a[i] = a[i]^a[j];
  a[j] = a[i]^a[j];
  a[i] = a[i]^a[j];
 }
 
 

 public static void main(String[] args) {
  int[] a = new int[] {1,2};
  System.out.println(Arrays.toString(a));
  swap(a,0,1);
  System.out.println(Arrays.toString(a));
  
  System.out.println(0^0);
  System.out.println(0^1);
  
  System.out.println(1^1);
  System.out.println(1^0);


  
  
    int aa = 434;
    int b = 436346;
    System.out.println("a:   " + toBinaryString(aa,32));
    System.out.println("b:   " + toBinaryString(b,32));
    System.out.println("~a:  " + toBinaryString(~aa,32));
  
  
    System.out.println("a&b: " + toBinaryString(aa&b,32));
  
    System.out.println("a|b: " + toBinaryString(aa|b,32));
  
    System.out.println("a^b: " + toBinaryString(aa^b,32));
  
    System.out.println("a>>1:" + toBinaryString(aa>>1,32));
  
    System.out.println("a<<1:" + toBinaryString(aa<<1,32));

  System.out.println(getNumsOfOne(0) + "  " + getNumsOfOneByMove(0));
  System.out.println(getNumsOfOne(1) + "  " + getNumsOfOneByMove(1));
  System.out.println(getNumsOfOne(2) + "  " + getNumsOfOneByMove(2));
  System.out.println(getNumsOfOne(3) + "  " + getNumsOfOneByMove(3));
  System.out.println(getNumsOfOne(8) + "  " + getNumsOfOneByMove(8));
  System.out.println(getNumsOfOne(9) + "  " + getNumsOfOneByMove(9));
  System.out.println(getNumsOfOne(1000000) + "  " + getNumsOfOneByMove(1000000));

  System.out.println(getNumsOfOne(Integer.MAX_VALUE) + "  " + getNumsOfOneByMove(Integer.MAX_VALUE));

 }

}
