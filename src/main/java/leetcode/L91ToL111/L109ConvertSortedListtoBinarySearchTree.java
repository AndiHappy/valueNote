package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L109ConvertSortedListtoBinarySearchTree {

	/**
	
	Given a singly linked list where elements are sorted in ascending order, 
	convert it to a height balanced BST.
	
	For this problem, a height-balanced binary tree is defined as a binary tree 
	in which the depth of the two subtrees of every node never differ by more than 1.
	
	Example:
	
	Given the sorted linked list: [-10,-3,0,5,9],
	
	One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
	
	    0
	   / \
	 -3   9
	 /   /
	-10  5
	
	 */

	private ListNode node;

	public TreeNode sortedListToBST(ListNode head) {
		if(head == null){
			return null;
		}
		
		int size = 0;
		ListNode runner = head;
		
		node = head;
		
		while(runner != null){
			runner = runner.next;
			size ++;
		}
		
		return inorderHelper(0, size - 1);
	}

	public TreeNode inorderHelper(int start, int end){
		if(start > end){
			return null;
		}
		
		int mid = start + (end - start) / 2;
		TreeNode left = inorderHelper(start, mid - 1);
		
		TreeNode treenode = new TreeNode(node.val);
		treenode.left = left;
		node = node.next;

		TreeNode right = inorderHelper(mid + 1, end);
		treenode.right = right;
		
		return treenode;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

