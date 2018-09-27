package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 201512310:15:21
 * 
 Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
             
 */
public class L08StringToInteger {

	public static void main(String[] args) {
		L08StringToInteger inoc = new L08StringToInteger();
//		System.out.println(inoc.myAtoi("words and 987"));
//		System.out.println(inoc.myAtoi("   -987"));
		System.out.println(inoc.myAtoi("   ++-987"));

		
//		System.out.println(Integer.parseInt("234234325325325235252"));

	}

	public int myAtoi(String str) {
		//异常情况判断
		if (str == null || str.isEmpty()) {
			return 0;
		}
		str = str.trim();
		int i = 0;
		int fh = 1;
		long value = 0;
		int length = str.length();
		while(i < length){
			char temp = str.charAt(i);
			if (str.charAt(i) == '-' && i == 0) {
				fh = -1;
				i++;
				continue;
			}else if (str.charAt(i) == '+' && i == 0) {
        fh = 1;
				i++;
				continue;
			}
      if (temp >= '0' && temp <= '9') {
				value = Character.digit(str.charAt(i), 10) + value * 10;
				if (fh * value > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				if (fh * value < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			}else{
        break;
      }
			i++;
		}
		return (int) (fh * value);
	}
	
	
	/**
	 * 数字前的字符的个数 ++---2
	 * */
	public int myAtoi3(String str) {
		// 异常情况判断
		if (str == null || str.isEmpty()) {
			return 0;
		}
		str = str.trim();
		int i = 0;
		int fh = 1;
		long value = 0;
		int length = str.length();
		while (i < length) {
			char temp = str.charAt(i);
			if (str.charAt(i) == '-') {
				fh = -1;
				i++;
				continue;
			}else if (str.charAt(i) == '+') {
				fh = 1;
				i++;
				continue;
			}
			if (temp >= '0' && temp <= '9') {
				value = Character.digit(str.charAt(i), 10) + value * 10;
				if (fh * value > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				if (fh * value < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			}else{
				break;
			}
			i++;
		}
		return (int) (fh * value);
	}
	
	public int myAtoi1(String str) {
		if (str == null || str.isEmpty()) {
			return 0;
		}
		str = str.trim();
		char fhao = str.charAt(0);
		int fh = fhao == '-' ? -1 : 1;
		long value = 0;
		int i = (fhao == '-' | fhao == '+') ? 1 : 0;
		while (i < str.length()) {
			char temp = str.charAt(i);
			if (temp >= '0' && temp <= '9') {
				value = Character.digit(str.charAt(i), 10) + value * 10;
				if (fh * value > Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				if (fh * value < Integer.MIN_VALUE) {
					return Integer.MIN_VALUE;
				}
			} else {
				return (int) (fh * value);
			}
			i++;
		}
		return (int) (fh * value);
	}

}
