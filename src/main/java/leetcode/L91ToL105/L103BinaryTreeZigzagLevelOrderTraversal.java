package leetcode.L91ToL105;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L103BinaryTreeZigzagLevelOrderTraversal {

	/**
	
	
	Given a binary tree, return the zigzag level order traversal of its nodes' values.
	(ie, from left to right, then right to left for the next level and alternate between).
	
	For example:
	Given binary tree [3,9,20,null,null,15,7],
	  3
	 / \
	9  20
	  /  \
	 15   7
	return its zigzag level order traversal as:
	[
	[3],
	[20,9],
	[15,7]
	]
	 */

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();

		if (root == null)
			return list;
		
		ArrayDeque<TreeNode> dqueue = new ArrayDeque<TreeNode>();
		
		dqueue.add(root);
		int counter = 1;
		
		while (!dqueue.isEmpty()) {
			
			List<Integer> listbyLevel = new ArrayList<Integer>();
			 List<TreeNode> nodes = new ArrayList<>();
			 
			while (!dqueue.isEmpty()) {
				TreeNode node = dqueue.pop();
				listbyLevel.add(node.val);
				nodes.add(node);
			}
			
			list.add(listbyLevel);
			
			for (TreeNode node : nodes) {
				TreeNode first = counter % 2 != 0 ? node.left : node.right;
				TreeNode second = counter % 2 != 0 ? node.right : node.left;
				if (first != null)
					dqueue.push(first);
				if (second != null)
					dqueue.push(second);
			}
			counter++;
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
