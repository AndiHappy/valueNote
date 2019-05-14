package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L98ValidateBinarySearchTree {

	/**
	
	Given a binary tree, determine if it is a valid binary search tree (BST).
	
	Assume a BST is defined as follows:
	
	The left subtree of a node contains only nodes with keys less than the node's key.
	The right subtree of a node contains only nodes with keys greater than the node's key.
	Both the left and right subtrees must also be binary search trees.
	
	
	Example 1:
	
	  2
	 / \
	1   3
	
	Input: [2,1,3]
	Output: true
	Example 2:
	
	  5
	 / \
	1   4
	   / \
	  3   6
	
	Input: [5,1,4,null,null,3,6]
	Output: false
	Explanation: The root node's value is 5 but its right child's value is 4
	
	 */

	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, null, null);
	}

	public boolean isValidBST(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;
		if ((max != null && max <= root.val) || (min != null && min >= root.val))
			return false;
		return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
	}

	private TreeNode lastNode = null;

	public boolean isValidBST_Inorder(TreeNode root) {
		// base case: an empty tree is trivially a BST
		if (root == null)
			return true;

		// in-order traversal
		if (!isValidBST(root.left))
			return false;

		// compare value of current node with value of last node
		if (lastNode != null && lastNode.val >= root.val)
			return false;

		// set the last node
		lastNode = root;

		return isValidBST(root.right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
