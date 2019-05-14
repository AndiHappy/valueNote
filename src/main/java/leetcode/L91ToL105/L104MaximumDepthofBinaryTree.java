package leetcode.L91ToL105;

/**
 * @author guizhai
 *
 */
public class L104MaximumDepthofBinaryTree {

	/**
	
	
	Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path 
from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
	
	 */
	
	 public int maxDepth(TreeNode root) {
     return maxDepthHelper(root,1);
   }
	
	private int maxDepthHelper(TreeNode root, int i) {
		if(root == null) return i;
		
		 int leve =i,right=i;
		 if(root.left != null) {
			 leve = maxDepthHelper(root.left, i+1);
		 }
		 
		 if(root.right != null) {
			 right = maxDepthHelper(root.right, i+1);
		 }
		
		return Math.max(leve, right);
	}

	public static void main(String[] args) {
		L104MaximumDepthofBinaryTree test = new L104MaximumDepthofBinaryTree();
		System.out.println(test.maxDepth(new TreeNode(new int[] {1,2,3,-100,-100,4,-100})));
	}

}
