package baseAlg.base;

/**
 * 
 * 主要讲述：Morris遍历的方法
 * 
 * */
class tNode {
	int data;
	tNode left, right;

	tNode(int item) {
		data = item;
		left = right = null;
	}
}

class BinaryTree {
	tNode root;

	void MorrisTraversal(tNode root) {
		tNode current, pre;

		if (root == null)
			return;

		current = root;
		while (current != null) {
			// 如果左节点为null，则直接的输出，或者访问
			if (current.left == null) {
				System.out.print(current.data + " ");
				//然后轮到了右节点
				current = current.right;
			} else {

				// 步骤①和步骤② 都是在创建构建”可能断掉的循环“
				
				/* 步骤① Find the inorder predecessor of current */
				pre = current.left;
				while (pre.right != null && pre.right != current)
					pre = pre.right;

				if (pre.right == null) {
					/* 步骤② Make current as right child of its inorder predecessor */
					pre.right = current;
					current = current.left;
				}else {
					/* Revert the changes made in the 'if' part to restore the 
					original tree i.e., fix the right child of predecessor*/
					pre.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				} /* End of if condition pre->right == NULL */

			} /* End of if condition current->left == NULL*/

		} /* End of while */
	}

	public static void main(String args[]) {
		/* Constructed binary tree is 
			1 
			/ \ 
			2	 3 
		/ \ 
		4	 5 
		*/
		BinaryTree tree = new BinaryTree();
		tree.root = new tNode(1);
		tree.root.left = new tNode(2);
		tree.root.right = new tNode(3);
		tree.root.left.left = new tNode(4);
		tree.root.left.right = new tNode(5);

		tree.MorrisTraversal(tree.root);
	}
}