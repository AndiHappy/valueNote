package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L108ConvertSortedArraytoBinarySearchTree {

	/**
	
	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
	
	For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
	the two subtrees of every node never differ by more than 1.
	
	Example:
	
	Given the sorted array: [-10,-3,0,5,9],
	
	One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
	
	    0
	   / \
	 -3   9
	 /   /
	-10  5
	
	 */

	public TreeNode sortedArrayToBST(int[] nums) {
		return getBST(nums, 0, nums.length - 1);
	}

	public TreeNode getBST(int[] nums, int start, int end) {
		if (nums.length == 1) {
			TreeNode treeNode = new TreeNode(nums[start]);
			return treeNode;
		} else {
			while (start <= end) {
				int mid = (start + end) / 2;
				TreeNode treeNode = new TreeNode(nums[mid]);
				treeNode.left = getBST(nums, start, mid - 1);
				treeNode.right = getBST(nums, mid + 1, end);
				return treeNode;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
