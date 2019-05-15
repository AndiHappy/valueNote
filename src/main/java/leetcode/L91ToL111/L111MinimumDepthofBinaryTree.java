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
		if (root.left == null)
			return helper(root.right, height + 1);
		else if (root.right == null)
			return helper(root.left, height + 1);
		else
			return Math.min(helper(root.left, height + 1), helper(root.right, height + 1));
	}

	public static void main(String[] args) {
		
		L111MinimumDepthofBinaryTree test = new L111MinimumDepthofBinaryTree();
		test.minDepth(new TreeNode(new int[] {3,9,20,-100,-100,15,7}));
		
		
	}

}
