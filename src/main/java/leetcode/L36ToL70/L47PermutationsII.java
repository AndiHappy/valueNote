package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailz
 * 2018年11月19日 下午8:25:17
 */
public class L47PermutationsII {

	/**
	Given a collection of numbers that might contain duplicates, return all possible unique permutations.
	
	Example:
	
	Input: [1,1,2]
	Output:
	[
	[1,1,2],
	[1,2,1],
	[2,1,1]
	]
	 * */
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
					continue;
				used[i] = true;
				tempList.add(nums[i]);
				backtrack(list, tempList, nums, used);
				used[i] = false;
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		L47PermutationsII test = new L47PermutationsII();
		System.out.println(test.permuteUnique(new int[]{1,1,2}));

	}

}
