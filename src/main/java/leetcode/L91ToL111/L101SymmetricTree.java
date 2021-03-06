package leetcode.L91ToL111;

import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L101SymmetricTree {

	/**
	
	
	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	
	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	
	  1
	 / \
	2   2
	/ \ / \
	3  4 4  3
	
	
	But the following [1,2,2,null,3,null,3] is not:
	
	  1
	 / \
	2   2
	 \   \
	 3    3
	
	
	Note:
	Bonus points if you could solve it both recursively and iteratively.
	
	
	 */

	public boolean isSymmetric(TreeNode root) {
		return root == null || isSymmetricHelp(root.left, root.right);
	}

	private boolean isSymmetricHelp(TreeNode left, TreeNode right) {
		if (left == null || right == null)
			return left == right;
		if (left.val != right.val)
			return false;
		return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
	}
	
	public boolean isSymmetric_NONE_recursive(TreeNode root) {
    if(root==null)  return true;
    
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode left, right;
    if(root.left!=null){
        if(root.right==null) return false;
        stack.push(root.left);
        stack.push(root.right);
    }
    else if(root.right!=null){
        return false;
    }
        
    while(!stack.empty()){
        if(stack.size()%2!=0)   return false;
        right = stack.pop();
        left = stack.pop();
        if(right.val!=left.val) return false;
        
        if(left.left!=null){
            if(right.right==null)   return false;
            stack.push(left.left);
            stack.push(right.right);
        }
        else if(right.right!=null){
            return false;
        }
            
        if(left.right!=null){
            if(right.left==null)   return false;
            stack.push(left.right);
            stack.push(right.left);
        }
        else if(right.left!=null){
            return false;
        }
    }
    
    return true;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
