package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhailz
 * 2018年11月19日 下午8:19:12
 */
public class L46Permutations {

	/**
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

使用的回溯的算法，最主要的是回溯路径的写法

	 * 
	 * */
	
	public List<List<Integer>> permute(int[] nums) {
		   List<List<Integer>> list = new ArrayList<>();
		   backtrack(list, new ArrayList<>(), nums);
		   return list;
		}

		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
		   if(tempList.size() == nums.length){
		      list.add(new ArrayList<>(tempList));
		   } else{
		      for(int i = 0; i < nums.length; i++){ 
		         if(tempList.contains(nums[i])) continue; // element already exists, skip
		         tempList.add(nums[i]);
		         backtrack(list, tempList, nums);
		         tempList.remove(tempList.size() - 1);
		      }
		   }
		} 
		
	public static void main(String[] args) {
		L46Permutations test = new L46Permutations();
		System.out.println(test.permute(new int[]{1,2,3}));
	}

}
