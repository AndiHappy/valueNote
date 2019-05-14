package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L106ConstructBinaryTreefromInorderandPostorderTraversal {

	/**

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
   
	 */
	
	// 后续遍历，中最后一个节点为根节点，然后拿着根节点，去到中序遍历中寻找
	// 对应的索引，确定左子树和右子树
	 public TreeNode buildTree(int[] inorder, int[] postorder) {
	    return helper(postorder.length-1, 0, inorder.length - 1, inorder, postorder);
   }
	 
	 
	private TreeNode helper(int postend, int inStart, int inEnd, int[] inorder, int[] postorder) {
		if(postend  < 0 || inStart > inEnd ) {
			return null;
		}
		
	//根节点为前序遍历的第一个节点
    TreeNode root = new TreeNode(postorder[postend]);
    
    //寻找到根节点在中序遍历中的
    int inIndex = 0; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            inIndex = i;
        }
    }
    // 拆分为了两段，一段是左节点，一段是右节点，使用递归遍历
    root.left = helper(postend -( inEnd - inIndex + 1), inStart, inIndex - 1, inorder,postorder);
    
    root.right = helper(postend-1, inIndex + 1, inEnd, inorder,postorder);
    
    return root;
    
	}


	public static void main(String[] args) {
		L106ConstructBinaryTreefromInorderandPostorderTraversal test = new L106ConstructBinaryTreefromInorderandPostorderTraversal();
		test.buildTree(new int[] {9,3,15,20,7}, new int[] {9,15,7,20,3});

	}

}
