package leetcode.L91ToL105;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

 * */
public class L94BinaryTreeInorderTraversal {

	List<Integer> l = new ArrayList<Integer>();
	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null) {
			return l;
		} else {
			//recur on the left side
			inorderTraversal(root.left);
			//add root
			l.add(root.val);
			//recur on the right side
			inorderTraversal(root.right);
			return l;
		}
	}

	//TODO 没有理解明白
	public List<Integer> inorderTraversal_While(TreeNode root) {
		List<Integer> r = new ArrayList<>();
		if (root == null)
			return r;

		// Start from root
		TreeNode cur = root;

		TreeNode traverseCursor = null;
		while (cur != null) {
			if (cur.left != null) {
				
				traverseCursor = cur.left;
				while (traverseCursor.right != null && traverseCursor.right != cur) {
					traverseCursor = traverseCursor.right;
				}

				if (traverseCursor.right == null) {
					traverseCursor.right = cur;
					cur = cur.left;
				}

				if (traverseCursor.right == cur) {
					traverseCursor.right = null;
					r.add(cur.val);
					cur = cur.right;
				}
				
			} else {
				r.add(cur.val);
				cur = cur.right;
			}
		}

		return r;
	}
	
	public List<Integer> inorderTraversal_stack(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }

    return list;
}
	
	public static void main(String[] args) {
		
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
	
	TreeNode(int[] x) {
		this.val = x[0];
		TreeNode[] tmp = new TreeNode[x.length];
		tmp[0]=this;
		for (int i = 1; i < x.length; i++) {
			if(-100 != x[i]) {
				tmp[i] = new TreeNode(x[i]);
			}else {
				tmp[i] = null;
			}
			
		}
		
		for (int i = 0; i < tmp.length/2; i++) {
			if(2*i+1 < tmp.length) {
				tmp[i].left = tmp[2*i+1];
			}
		
			if(2*i+2 < tmp.length) {
				tmp[i].right = tmp[2*i+2];
			}
		}
	}
	
}