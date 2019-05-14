package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L111MinimumDepthofBinaryTree {

	/**
	
	
	Given a binary tree, find its minimum depth.
	
	The minimum depth is the number of nodes along the shortest path 
	from the root node down to the nearest leaf node.
	
	Note: A leaf is a node with no children.
	
	Example:
	
	Given binary tree [3,9,20,null,null,15,7],
	
	  3
	 / \
	9  20
	  /  \
	 15   7
	return its minimum depth = 2.
	
	 */
	
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		return helper(root, 0);
	}

	public int helper(TreeNode root, int height) {
		if (root == null)
			return height;
		int lh = helper(root.left, height + 1);
		int rh = helper(root.right, height + 1);
		if (root.left == null)
			return rh;
		else if (root.right == null)
			return lh;
		else
			return Math.min(lh, rh);
	}

	public static void main(String[] args) {
		

	}

}
