package leetcode.L0ToL35;

import java.util.Stack;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:10:47
 * 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

 */
public class L20ValidParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		L20ValidParentheses test = new L20ValidParentheses();
//		System.out.println(test.isValid("()"));
//		System.out.println(test.isValid("()[]{}"));
//		System.out.println(test.isValid("(]"));
//		System.out.println(test.isValid("([)]"));
//		System.out.println(test.isValid("{[]}"));
		System.out.println(test.isValid("]"));
	}

	public boolean isValid(String s) {
		if(s == null || s.length() <= 0){
    		return true;
    	}
    	Stack<Character> stack = new Stack<Character>();
    	for (int i = 0; i < s.length(); i++) {
    		char cha = s.charAt(i);
			switch (cha) {
			case '(':
			case '{':
			case '[':
				stack.push(cha);
				break;
			case ')':
				if(stack.isEmpty() || stack.pop() != '(' ){
					return false;
				}
				break;
			case '}':
				if(stack.isEmpty() || stack.pop() != '{' ){
					return false;
				}
				break;
			case ']':
				if(stack.isEmpty() || stack.pop() != '[' ){
					return false;
				}
				break;
			default:
				break;
			}
		}
    	return stack.isEmpty();
	}
}
