package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L110BalancedBinaryTree {

	/**
	
	Given a binary tree, determine if it is height-balanced.
	
	For this problem, a height-balanced binary tree is defined as:
	
	a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
	
	Example 1:
	
	Given the following tree [3,9,20,null,null,15,7]:
	
	  3
	 / \
	9  20
	  /  \
	 15   7
	Return true.
	
	Example 2:
	
	Given the following tree [1,2,2,3,3,null,null,4,4]:
	
	     1
	    / \
	   2   2
	  / \
	 3   3
	/ \
	4   4
	
	Return false.
	
	 */


	public boolean isBalanced(TreeNode root) {
		int rootVal = helper(root);
		return rootVal >= 0;
	}

	/** This helper method returns "-1" if not balanced, 
	else return the true depth of this node.
	*/
	private int helper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = helper(node.left);
		int right = helper(node.right);
		if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}

	private int depth(TreeNode root) {
		if(root == null) return 0;
		int leftdepth = depth(root.left);
		int rightdepth = depth(root.left);
		return Math.max(leftdepth,rightdepth)+1;
	}

	public static void main(String[] args) {
		L110BalancedBinaryTree test = new L110BalancedBinaryTree();
		TreeNode root = new  TreeNode(new int[] {1,2,2,3,3,-100,-100,4,4});
		System.out.println(test.depth(root));
		System.out.println(test.depth(root.left));
		System.out.println(test.depth(root.right));
		System.out.println(test.depth(root.left.left));

	}

}
