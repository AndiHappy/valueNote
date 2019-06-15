package leetcode.L161ToL180;

import java.util.HashMap;

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

 /**
  * 算法非常的棒
  * */
	public static String fractionToDecimal(int numerator, int denominator) {
    if (numerator == 0) {
        return "0";
    }
    
    StringBuilder res = new StringBuilder();
    // "+" or "-"
    String symbol = ((numerator > 0) ^ (denominator > 0)) ? "-" : "";
    res.append(symbol);
    
    long num = Math.abs((long)numerator);
    long den = Math.abs((long)denominator);
    
    // integral part
    res.append(num / den);
    num %= den;
    if (num == 0) {
        return res.toString();
    }
    
    // fractional part
    res.append(".");
    HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    map.put(num, res.length());
    while (num != 0) {
        num *= 10;
        res.append(num / den);
        num %= den;
        if (map.containsKey(num)) {
            int index = map.get(num);
            res.insert(index, "(");
            res.append(")");
            break;
        }
        else {
            map.put(num, res.length());
        }
    }
    return res.toString();
}

	public static void main(String[] args) {
		System.out.println(L166FractiontoRecurringDecimal.fractionToDecimal(3, 7));

	}

}
