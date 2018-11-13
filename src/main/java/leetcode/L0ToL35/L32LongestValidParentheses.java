package leetcode.L0ToL35;

import java.util.Stack;

/**
 * @author zhailz
 *
 */
public class L32LongestValidParentheses {

	/**
	Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
	
	Example 1:
	
	Input: "(()"
	Output: 2
	Explanation: The longest valid parentheses substring is "()"
	Example 2:
	
	Input: ")()())"
	Output: 4
	Explanation: The longest valid parentheses substring is "()()"
	 */

	/**
	 * 思考简单了，the length of the longest valid (well-formed) parentheses substring
	 * 不是寻找有多少可用的
	 * */
	public static int longestValidParentheses(String s) {
		Stack<Character> tmp = new Stack<Character>();
		int res = 0,i = 0;
		while(i < s.length()){
			char tmpc = s.charAt(i);
			i++;
			if(tmpc == '('){
				tmp.push(tmpc);
			}
			
            if(tmpc == ')'){
				if(tmp.isEmpty()) continue;
				if(tmp.peek() == '(') {
					tmp.pop();
					res = res+2;
				}
			}
		}
		return res;
	}

	/**
	 * 1. 首先理解题意是关键的步骤：挑选可以使用的括号规则，如下的规则
	 * 
	 * ()(() 计算值为2
	 * ()()) 计算值为4
	 * 
	 * 2. 理解了题意之后，就是梳理具体的逻辑
	 * 
	 * 采用堆栈的的数据结构，来匹配右括号与左括号的匹配的规则
	 * 左括号则入栈，右括号需要处理的逻辑是：
	 * 匹配或者不匹配的情况
	 * 			如果是匹配的情况，那么需要把匹配的左括号出栈，然后根据出栈后的情况，如果还有内容，没有匹配完呢，直接的更新数据，如果栈内没有了内容，需要
	 * 计算最大的长度。
	 * 
	 * 			如果是不匹配的情况，右括号不匹配的情况，就是直接的丢弃。判断这个时候的栈内元素为空，并且更新最大长度的开始的值。
	 * 			
	 * */
	public static int longestValidParentheses1(String s) {
        // the position of the last ')'
        int maxLen = 0, last = -1;
        // keep track of the positions of non-matching '('s
        Stack<Integer> lefts = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) =='(') {
                lefts.push(i);
            } else {
                if (lefts.empty()) {
                    // no matching left
                    last = i;
                } else {
                    // find a matching pair
                    lefts.pop();
                    if (lefts.empty()) {
                        maxLen = Math.max(maxLen, i-last);
                    } else {
                        maxLen = Math.max(maxLen, i-lefts.peek());
                    }
                }
            }
        }
        return maxLen;
    }
	public static void main(String[] args) {
		System.out.println(longestValidParentheses("()(()")); //2
		System.out.println(longestValidParentheses(")()())"));
		System.out.println(longestValidParentheses1("()(()")); //2
		System.out.println(longestValidParentheses1(")()())"));
	}

}
