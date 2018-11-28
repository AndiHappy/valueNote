package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author zhailz
 * 2018年11月27日 下午8:17:45
 */
public class L43MultiplyStrings {

	/**
	Given two non-negative integers num1 and num2 represented as strings, 
	return the product of num1 and num2, also represented as a string.
	
	Example 1:
	
	Input: num1 = "2", num2 = "3"
	Output: "6"
	Example 2:
	
	Input: num1 = "123", num2 = "456"
	Output: "56088"
	Note:
	The length of both num1 and num2 is < 110.
	Both num1 and num2 contain only digits 0-9.
	Both num1 and num2 do not contain any leading zero, except the number 0 itself.
	You must not use any built-in BigInteger library or convert the inputs to integer directly.
	
	* */
	
	public String multiply(String num1, String num2) {
		if (num1 == null || num2 == null) {
			return null;
		}

		if (num1.equals("0") || num2.equalsIgnoreCase("0")) {
			return "0";
		}
		String result = null;
		String longString = num1.length() > num2.length()?num1:num2;
		String shortString = num1.length() > num2.length()?num2:num1;
        
		for (int i = 0; i < shortString.length(); i++) {
			char tmp = shortString.charAt(shortString.length()-1-i);
			String tp = multiply(tmp,longString,i);
			result = addStringInter(result,tp);
		}
		
		return result;
		
	}

	private String addStringInter(String result, String tp) {
		if(result == null || result.equals("0")) return tp;
		String base = result.length() > tp.length() ? result : tp;
		String base2 = result.length() > tp.length() ? tp:result;	
		int i = 0;int jinwei = 0;
		StringBuilder builder = new StringBuilder();
		for (; i < base.length(); i++) {
			int multipyvalue = (base.charAt(base.length() - 1 - i)-'0') + 
					((base2.length() - 1 - i) >= 0?(base2.charAt(base2.length() - 1 - i)-'0'):0) + jinwei;
			builder.append(multipyvalue % 10);
			jinwei = multipyvalue/10;
		}
		
		if (jinwei > 0) {
			builder.append(jinwei);
		}
		return builder.reverse().toString();
	}

	private String multiply(char tmpchar, String longString,int beishu) {
		int key = tmpchar - '0';
		int jinwei = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = longString.length() - 1; i >= 0; i--) {
			int value = longString.charAt(i) - '0';
			int multipyvalue = value * key;
			builder.append((multipyvalue + jinwei) % 10);
			jinwei = (multipyvalue + jinwei) / 10;
		}
		if (jinwei > 0) {
			builder.append(jinwei);
		}
		
		if(beishu >0){
			for (int i =0; i < beishu; i++) {
				builder.insert(0,0);
			}
		}
		return builder.reverse().toString();
	}

	public String multiply_error(String num1, String num2) {
		if (num1 == null || num2 == null) {
			return null;
		}

		if (num1.equals("0") || num2.equalsIgnoreCase("0")) {
			return "0";
		}

		char[] num1charlong = num1.toCharArray().length > num2.toCharArray().length ? num1.toCharArray()
				: num2.toCharArray();
		char[] num2charshort = num1.toCharArray().length > num2.toCharArray().length ? num2.toCharArray()
				: num1.toCharArray();

		char[] tmp = null;
		for (int i = 0; i < num2charshort.length; i++) {
			char tmpchar = num2charshort[num2charshort.length-1-i];
			char[] mul = multiply(tmpchar, num1charlong,i);
			tmp = addChar(tmp,mul);
		}

		return new String(tmp);
	}


	private char[] addChar(char[] tmp, char[] multiply) {
		if (tmp == null)
			return multiply;
		char[] base = tmp.length > multiply.length ? tmp : multiply;
		char[] base2 = tmp.length > multiply.length ? multiply : tmp;
		int jinwei = 0;
		int i = 0;
		for (; i < base.length; i++) {
			char fortmp = base[base.length - 1 - i];
			int value = fortmp - '0';
			int multipyvalue = value + ((base2.length - 1 - i) >= 0?base2[base2.length - 1 - i]:'0') + jinwei-'0';
			base[base.length - 1 - i] = (char) ('0' + multipyvalue % 10);
			jinwei = multipyvalue/10;
			if(jinwei == 0 && base2.length - 1 - i < 0){
				return base;
			}
		}
		
		if (jinwei > 0) {
			char[] result = new char[base.length + 1];
			System.arraycopy(base, 0, result, 1, base.length);
			result[0] = (char) ('0' + jinwei);
			return result;
		}
		

		return base;
	}

	private char[] multiply(char tmpchar, char[] num1charlong,int beishu) {
		int key = tmpchar - '0';
		int jinwei = 0;
		for (int i = num1charlong.length - 1; i >= 0; i--) {
			char fortmp = num1charlong[i];
			int value = fortmp - '0';
			int multipyvalue = value * key;
			num1charlong[i] = (char) ('0' + (multipyvalue + jinwei) % 10);
			jinwei = (multipyvalue + jinwei) / 10;
		}
		if (jinwei > 0) {
			char[] result = new char[num1charlong.length + 1];
			System.arraycopy(num1charlong, 0, result, 1, num1charlong.length);
			result[0] = (char) ('0' + jinwei);
			num1charlong = result;
		}
		
		if(beishu >0){
			char[] result = new char[num1charlong.length + beishu];
			System.arraycopy(num1charlong, 0, result, 0, num1charlong.length);
			for (int i = num1charlong.length; i < result.length; i++) {
				result[i]='0';
			}
			return result;
		}
		return num1charlong;
	}

	public static void main(String[] args) {
		L43MultiplyStrings test = new L43MultiplyStrings();
		System.out.println(Arrays.toString(test.multiply('9', new char[] { '9', '2', '1', '9' },2)));
		System.out.println(test.multiply('9', "9219",2));

		System.out.println(
				Arrays.toString(test.addChar(new char[] { '9', '9', '9' }, new char[] { '9','9','9', '2', '1', '6' })));
		System.out.println(test.addStringInter("999", "999216"));
		System.out.println(test.addStringInter("11", "110"));

		System.out.println(test.multiply("11", "11"));

		System.out.println(test.multiply("100", "100"));
		
		System.out.println(test.multiply("140", "721"));
	}

}
