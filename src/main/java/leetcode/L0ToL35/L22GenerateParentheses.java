package leetcode.L0ToL35;

import java.util.ArrayList;
import java.util.List;

/**
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 * */
public class L22GenerateParentheses {

	public static void main(String[] args) {
		System.out.println(generateParenthesis(2));
	}

	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		getRes(n, 0, 0, res, "", 0);
		return res;
	}

	/**
	 * 递归的解决方案:思路非常的重要，首先找出所有的配置，然后过滤不合格的 
	 * 中间的①就是过滤的条件
	 * */
	public static void getRes(int n, int cur, int len, List<String> res, String s, int balance) {
		if (cur > n || len > 2 * n)
			return;
		//①
		if (cur == n && s.length() == 2 * n && balance == 0) {
			res.add(s);
			return;
		}
		getRes(n, cur + 1, len + 1, res, s + "(", balance + 1);
		if (balance > 0){
			getRes(n, cur, len + 1, res, s + ")", balance - 1);
		}
	}

	
	public static List<String> generateParenthesis1(int n) {
		List<String> res = new ArrayList<String>();
		String key = "";
		for (int i = 0; i < n; i++) {
			key=key+"()";
		}
		res.add(key);
		
		
		return res ;
	}
}
