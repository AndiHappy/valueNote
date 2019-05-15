package leetcode.base;


public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	public TreeNode(int[] x) {
		this.val = x[0];
		TreeNode[] tmp = new TreeNode[x.length];
		tmp[0] = this;
		for (int i = 1; i < x.length; i++) {
			if (-100 != x[i]) {
				tmp[i] = new TreeNode(x[i]);
			} else {
				tmp[i] = null;
			}

		}

		for (int i = 0; i < tmp.length / 2; i++) {
			if (2 * i + 1 < tmp.length) {
				tmp[i].left = tmp[2 * i + 1];
			}

			if (2 * i + 2 < tmp.length) {
				tmp[i].right = tmp[2 * i + 2];
			}
		}
	}

}