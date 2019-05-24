package leetcode.L112ToL129;

/**
 * @author guizhai
 *
 */
public class L116PopulatingNextRightPointersinEachNode {

	/**
	
	You are given a perfect binary tree where all leaves are on the same level, 
	and every parent has two children. The binary tree has the following definition:
	
	struct Node {
	int val;
	Node *left;
	Node *right;
	Node *next;
	}
	Populate each next pointer to point to its next right node. 
	If there is no next right node, the next pointer should be set to NULL.
	
	Initially, all next pointers are set to NULL.
	
	
	Example:



Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
	 */

	public void connect(Node root) {
		//初始化       
		Node parent = null;
		makeNext(root, parent);
	}

	/**
	 * 两种类型的赋值
	 * */
	private void makeNext(Node node, Node parent) {
		if (node != null) {
			if (parent == null) {
				node.next = null;
			} else {
				if (node != parent.right) {
					node.next = parent.right;
				} else {
					Node temp = getNext(parent);
					if (temp != null) {
						node.next = temp;
					}
				}
			}
			makeNext(node.left, node);
			makeNext(node.right, node);
		}
	}

	private  Node getNext(Node parent) {
		if (parent != null && parent.next != null) {
			return parent.next.left;
		}
		return null;
	}

	
	public static Node connect_s(Node root) {
    if (root == null) return null;
    connect_s(root.left);
    connect_s(root.right);
    con(root.left, root.right);
    return root;
}
private static void con(Node left, Node right) {
    if (left == null || right == null) return;
    left.next = right;
    con(left.right, right.left);
}

/*
void connect(TreeLinkNode *root) {
  if (root == NULL) return;
  TreeLinkNode *pre = root;
  TreeLinkNode *cur = NULL;
  while(pre->left) {
      cur = pre;
      while(cur) {
          cur->left->next = cur->right;
          if(cur->next) cur->right->next = cur->next->left;
          cur = cur->next;
      }
      pre = pre->left;
  }
}
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}

class Node {
	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node() {
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
	
	@Override
	public String toString() {
		return this.val+" ";
	}
};