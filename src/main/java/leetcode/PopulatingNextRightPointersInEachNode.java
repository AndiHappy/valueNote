package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 2015111311:58:10
 * 
 * TagsTree,level0
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class PopulatingNextRightPointersInEachNode {

	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(0);
		root.left = new TreeLinkNode(1);
		root.left.left = new TreeLinkNode(3);
		root.left.right = new TreeLinkNode(4);
		root.right = new TreeLinkNode(2);
		root.right.left = new TreeLinkNode(5);
		root.right.right = new TreeLinkNode(6);
		connect(root);
		System.out.println(root.toString());

	}

	public static void connect(TreeLinkNode root) {
		// ʼ
		TreeLinkNode parent = null;
		makeNext(root, parent);
	}

	private static void makeNext(TreeLinkNode node, TreeLinkNode parent) {
		if (node != null) {
			if (parent == null) {
				node.next = null;
			} else {
				if (node != parent.right) {
					node.next = parent.right;
				} else {
					TreeLinkNode temp = getNext(parent);
					if (temp != null) {
						node.next = temp;
					}
				}
			}
			makeNext(node.left, node);
			makeNext(node.right, node);
		}

	}

	private static TreeLinkNode getNext(TreeLinkNode parent) {
		if (parent != null && parent.next != null) {
			return parent.next.left;
		}
		return null;
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}
