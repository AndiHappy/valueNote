package leetcode.L161ToL180;

/**
 * @author guizhai
 *
 */
public class L166FractiontoRecurringDecimal {

	/**

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"


	 */
	
	//The important thing is to consider all edge cases while thinking this problem through, 
	//including: negative integer, possible overflow, etc.

//Use HashMap to store a remainder and its associated index while doing the division 
	//so that whenever a same remainder comes up, we know there is a repeating fractional part.


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
