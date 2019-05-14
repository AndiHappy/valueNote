package leetcode.L91ToL105;

/**
 * @author guizhai
 *
 */
public class L105ConstructBinaryTreefromPreorderandInorderTraversal {

	/**


Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

	 */
	
	/*

	The basic idea is here:
	Say we have 2 arrays, PRE and IN.
	
	Preorder traversing implies that PRE[0] is the root node.
	
	Then we can find this PRE[0] in IN, say it's IN[5].
	
	Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, 
	IN[6] to the end is on the right side.
	
	Recursively doing this on subarrays, we can build a tree out of it :)

	Hope this helps.

*/
	
	/**
	 * 根据先续和中序的数组，构建一个树
	 * 先续的第一个节点为根节点，然后拿到这个节点在中序的数组中确定了 前面属于左子树，后面属于右子树
	 * */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
	    return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		  // 序列的变化
	    if (preStart > preorder.length - 1 || inStart > inEnd) {
	        return null;
	    }
	    //根节点为前序遍历的第一个节点
	    TreeNode root = new TreeNode(preorder[preStart]);
	    
	    //寻找到根节点在中序遍历中的
	    int inIndex = 0; // Index of current root in inorder
	    for (int i = inStart; i <= inEnd; i++) {
	        if (inorder[i] == root.val) {
	            inIndex = i;
	        }
	    }
	    // 拆分为了两段，一段是左节点，一段是右节点，使用递归遍历
	    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
	    
	    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
	    
	    return root;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
