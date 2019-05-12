package leetcode.L71ToL90;

import java.util.ArrayList;
import java.util.List;

public class L77Combinations {

	/**
	 * 
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

	 * */
	

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 1; i <= n ; i++) {
			List<Integer> value = new ArrayList<Integer>();
			value.add(i);
			combine(result,value,n,k);
		}
		return result;
  }

	private void combine(List<List<Integer>> result, List<Integer> value, int n, int k) {
		if(value.size() == k ) {
			result.add(new ArrayList<Integer>(value));
			return;
		}else if (value.size() < k) {
			for (int i = value.get(value.size()-1); i <= n; i++) {
				if(!value.contains(i)) {
					value.add(i);
					combine(result,value,n,k);
					value.remove(value.size() -1 );
				}
			}
		}
	}
	
	
	public List<List<Integer>> combine1(int n, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		backtrack(result, new ArrayList<Integer>(), n, 1,k);
		return result;
  }
	
	private void backtrack(List<List<Integer>> rs,List<Integer> cur, int n, int start,int k) {
		if(cur.size() == k) {
			rs.add(new ArrayList<Integer>(cur));
		}
		
		for (int i = start; i <=n; i++) {
			cur.add(i);
			backtrack(rs,cur, n, i + 1,k);
			cur.remove(cur.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		L77Combinations test = new L77Combinations();
		List<List<Integer>> res = test.combine1(4, 2);
		System.out.println(res);
	}
	
	
}
