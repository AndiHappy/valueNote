package leetcode.L112ToL132;

import leetcode.base.TreeNode;

/**
 * @author guizhai
 *
 */
public class L114FlattenBinaryTreetoLinkedList {

	/**
	
	Given a binary tree, flatten it to a linked list in-place.
	
	For example, given the following tree:
	
	  1
	 / \
	2   5
	/ \   \
	3   4   6
	The flattened tree should look like:
	
	1
	\
	2
	 \
	  3
	   \
	    4
	     \
	      5
	       \
	        6
	
	
	 */

	public void flatten_pre(TreeNode root) {
    TreeNode cur = root;
    TreeNode prev = null;
    while(cur != null){
        if(cur.left == null) cur = cur.right;
        else {
            prev = cur.left;
            while(prev.right != null) prev = prev.right;
            prev.right = cur.right;
            cur.right = cur.left;
            cur.left = null;
        }
    }
}
	
	/**
	 * 更加的简洁！
	 * 
	 * */
	public void flatten(TreeNode root) {
		while (root != null) {
			if (root.left == null) {
				root = root.right;
			} else {
				TreeNode left = root.left;
				//左节点的最右节点，也就是左节点的最大节点
				while (left.right != null) {
					left = left.right;
				}
				left.right = root.right;
				root.right = root.left;
				root.left = null;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
