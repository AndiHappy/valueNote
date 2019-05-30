package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L144BinaryTreePreorderTraversal {

	/**
	 * 二叉树 前序遍历
	 */
	
	/*
		public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> pre = new LinkedList<Integer>();
		if(root==null) return pre;
		pre.add(root.val);
		pre.addAll(preorderTraversal(root.left));
		pre.addAll(preorderTraversal(root.right));
		return pre;
	}
Recursive method with Helper method to have a List as paramater, so we can modify the parameter and don't have to instantiate a new List at each recursive call:

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> pre = new LinkedList<Integer>();
		preHelper(root,pre);
		return pre;
	}
	public void preHelper(TreeNode root, List<Integer> pre) {
		if(root==null) return;
		pre.add(root.val);
		preHelper(root.left,pre);
		preHelper(root.right,pre);
	}
Iterative method with Stack:

	public List<Integer> preorderIt(TreeNode root) {
		List<Integer> pre = new LinkedList<Integer>();
		if(root==null) return pre;
		Stack<TreeNode> tovisit = new Stack<TreeNode>();
		tovisit.push(root);
		while(!tovisit.empty()) {
			TreeNode visiting = tovisit.pop();
			pre.add(visiting.val);
			if(visiting.right!=null) tovisit.push(visiting.right);
			if(visiting.left!=null) tovisit.push(visiting.left);
		}
		return pre;
	}
	 * */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
