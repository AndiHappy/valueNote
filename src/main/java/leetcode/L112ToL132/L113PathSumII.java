package leetcode.L112ToL132;

import java.util.ArrayList;
import java.util.List;

import leetcode.base.TreeNode;

/**
 * @author guizhai
 *
 */
public class L113PathSumII {

	/**
	
	Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	
	Note: A leaf is a node with no children.
	
	Example:
	
	Given the below binary tree and sum = 22,
	
	    5
	   / \
	  4   8
	 /   / \
	11  13  4
	/  \    / \
	7    2  5   1
	Return:
	
	[
	 [5,4,11,2],
	 [5,8,4,5]
	]
	
	 */

	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> re = new ArrayList<Integer>();
		pathSumHelper(result, re, root, sum);
		return result;

	}

	public void pathSumHelper(List<List<Integer>> result, List<Integer> re, TreeNode root, int sum) {
		if (root == null)
			return;
		re.add(root.val);
		if (root.val == sum && root.left == null && root.right == null) {
			result.add(new ArrayList<Integer>(re));
		}

		if (root.left != null) {
			pathSumHelper(result, re, root.left, sum - root.val);
			re.remove(re.size() - 1);
		}

		if (root.right != null) {
			pathSumHelper(result, re, root.right, sum - root.val);
			re.remove(re.size() - 1);
		}


	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
