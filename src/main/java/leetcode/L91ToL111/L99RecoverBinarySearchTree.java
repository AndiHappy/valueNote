package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L99RecoverBinarySearchTree {

	/**


Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?



	 */
	
	private static TreeNode first, second, pre;
	
	public static void recoverTree(TreeNode root) {
	    first = second = pre = null;
	    traversal(root);
	    int val = first.val;
	    first.val = second.val;
	    second.val = val;
	}
	
	/**
	 * 中序遍历，建立在只有一个错误的前提下
	 * */
	private static void traversal(TreeNode node) {
	    if (node == null) return;
	    traversal(node.left);
	    if (pre != null && pre.val > node.val) {
	        if (first == null) first = pre;
	        second = node;
	    } else if (first != null && first.val < node.val) {
	        return;
	    }
	    pre = node;
	    traversal(node.right);
	}
	
	public static void main(String[] args) {
		int[] x = new int[] {3,1,4,-100,-100,2};
		TreeNode root = new TreeNode(x);
		recoverTree(root);
	}

}
