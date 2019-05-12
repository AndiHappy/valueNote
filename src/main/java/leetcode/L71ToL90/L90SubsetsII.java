package leetcode.L71ToL90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailz
 * 2018年11月19日 下午8:11:48
 */
public class L90SubsetsII {

/**

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

* */
	
	
	public List<List<Integer>> subsetsWithDup(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, 0);
	    return list;
	}

	/**


Input: [1,2,2,3]
正常的情况下：
[[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 2], [1, 2, 3], [1, 3], [2], [2, 2], [2, 2, 3], [2, 3], [2], [2, 3], [3]]

需要的输出：
Output:
[[], [1], [1, 2], [1, 2, 2], [1, 2, 2, 3], [1, 2, 3], [1, 3], [2], [2, 2], [2, 2, 3], [2, 3], [3]]


	 * */
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
	    list.add(new ArrayList<>(tempList));
	    for(int i = start; i < nums.length; i++){
	    		//重复的元素，不在进行分配了,相当于减掉某些“枝干的算法”
	        if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
	        tempList.add(nums[i]);
	        backtrack(list, tempList, nums, i + 1);
	        tempList.remove(tempList.size() - 1);
	    }
	} 
	
	public static void main(String[] args) {
		
		L90SubsetsII test = new L90SubsetsII();
		List<List<Integer>> res = test.subsetsWithDup(new int[]{1,2,2,3});
		System.out.println(res);
	}

}
