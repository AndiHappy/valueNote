package leetcode.L91ToL111;

import java.util.HashMap;

/**
 * @author guizhai
 *
 */
public class L91DecodeWays {

	/**

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

	 */
	
	public int numDecodings(String s) {
    /*map to avoid repeated calculations*/
    HashMap<String, Integer> dp = new HashMap<>();
    return noDecHelper(s, dp);
}

/*Total number of ways to decode a String s is:
//noOfWays(s) = numOfWays(s-1) + numOfWays(s-2)
//i.e. noOfWays excluding last character + noOfWays excluding last 2 character*/
private int noDecHelper(String s, HashMap<String, Integer> dp) {
	
    if (s.length() == 0) {
        return 1;
    }
    
    /*check we have already done calculations of current string*/
    Integer n = dp.get(s);
    if (n != null) {
        /*if yes, return from map*/
        return n;
    }
    
    int noOfWays = 0;
    /*check if last character is in valid range of 1-9*/
    int no1 = Integer.parseInt(s.substring(s.length() - 1));
    if (no1 >= 1 && no1 <= 9) {
        /*if yes, find number of ways to decode excluding last character*/
        String sub = s.substring(0, s.length() - 1);
        int num = noDecHelper(sub, dp);
        dp.put(sub, num);
        noOfWays += num;
    }
    
    
    /*if length is more than 2*/
    if (s.length() > 1) {
        /*check if last 2 characters are of valid ranger of 10-26*/
        int no2 = Integer.parseInt(s.substring(s.length() - 2));
        if (no2 >= 10 && no2 <= 26) {
            /*if yes, find number of ways to decode excluding last 2 characters*/
            String sub = s.substring(0, s.length() - 2);
            int num = noDecHelper(sub, dp);
            dp.put(sub, num);
            noOfWays += num;
        }
    }
    return noOfWays;
}

public int numDecodings_short(String s) {
  int n = s.length();
  if (n == 0) return 0;
  
  int[] memo = new int[n+1];
  memo[n]  = 1;
  memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
  
  for (int i = n - 2; i >= 0; i--)
      if (s.charAt(i) == '0') continue;
      else memo[i] = (Integer.parseInt(s.substring(i,i+2))<=26) ? memo[i+1]+memo[i+2] : memo[i+1];
  
  return memo[0];
}

	public static void main(String[] args) {
		L91DecodeWays test = new L91DecodeWays();
		test.numDecodings_short("1");

	}

}
