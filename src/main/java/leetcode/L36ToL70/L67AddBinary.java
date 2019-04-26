/**
 * 
 */
package leetcode.L36ToL70;

/**
 * @author guizhai
 *
 */
public class L67AddBinary {

	/**

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

	 */
	
	 public String addBinary(String a, String b) {
		 if(a == null) return b;
		 if(b == null) return a;
		 
		 char[] result = new char[a.length()>b.length()?a.length():b.length()];
		 int i = a.length()-1,j = b.length()-1,k=result.length-1;
		 int carry = 0;
		 while( i >=0 || j >=0) {
			 char atmp = i>=0?a.charAt(i):'0';
			 char btmp = j>=0?b.charAt(j):'0';
			 int atmpI = convert(atmp);
			 int btmpI = convert(btmp);
			 
			 int resultI = atmpI+btmpI+carry;
			 if(resultI == 0) {
				 result[k] = '0';
				 carry=0;
			 }
			 if(resultI == 1) {
				 result[k] = '1';
				 carry=0;
			 }
			 if(resultI == 2) {
				 result[k] = '0';
				 carry = 1;
			 }
			 if(resultI == 3) {
				 result[k] = '1';
				 carry = 1;
			 }
			 i--;
			 j--;
			 k--;
		 }
		
		 if(carry > 0) {
			 char[] dest = new char[result.length+1];
			 System.arraycopy(result, 0, dest, 1, result.length);
			 dest[0]='1';
			 return new String(dest);
		 }
	      return new String(result);  
	    }
	private int convert(char atmp) {
		if(atmp == '1') return 1;
		else return 0;
	}
	
	public static void main(String[] args) {
		L67AddBinary test = new L67AddBinary();
		System.out.println(test.addBinary("11", "1"));
		System.out.println(test.addBinary("11", "0"));
		System.out.println(test.addBinary("11", "11"));
		System.out.println(test.addBinary("11", "1111"));
		System.out.println(test.addBinary("1010", "1011"));
	}

}
