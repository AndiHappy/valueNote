package baseAlg.base;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * tree base code
 * */
public class TreeNode {
	
	public int val;
	public TreeNode left;
	public TreeNode right;
	
	public static final TreeNode NULLNODE = new TreeNode(Integer.MIN_VALUE);

	public TreeNode(int x) {
		val = x;
	}

	/**
	 * int array construct tree
	 * -100 stand for null
	 * */
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
	
	@Override
	public String toString() {
		return "Node: "+ this.val;
	}
	
	public static void main(String[] args) {
		TreeNode node = new TreeNode(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17});
		node.print();
	}
	
	
	private void print() {
		List<List<TreeNode>>  result = printleveTraverselResult();
		
		TreeNode[][] array = new TreeNode[result.size()][result.get(result.size()-1).size()*2];
		for (int j = 0; j < result.get(result.size()-1).size(); j++) {
			array[result.size()-1][2*j]=result.get(result.size()-1).get(j);
		}
		
		for (int i = result.size()-2; i >=0; i--) {
			caclu(array,i,result.get(i));
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j]!=null&& TreeNode.NULLNODE !=array[i][j] ?array[i][j].val:"    ");
			}
			System.out.println();
		}
	}
	
	private void caclu(TreeNode[][] treeNodes, int setIndex,List<TreeNode> list) {
		List<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < treeNodes[setIndex+1].length; i++) {
			if(treeNodes[setIndex+1][i] != null) index.add(i);
		}
		
		for (int i = 0; i < index.size() && (i+1) < index.size(); i=i+2) {
			int caclu = (index.get(i) + index.get(i+1))/2;
			treeNodes[setIndex][caclu] = list.get(i/2);
		}
	}

	/**
	 * level Traversel
	 * */
	public List<List<TreeNode>>  printleveTraverselResult() {
		List<List<TreeNode>> levelres = new ArrayList<List<TreeNode>>();
		ArrayDeque<TreeNode> roottmp = new ArrayDeque<TreeNode>();
		printlevelTraverse(levelres,roottmp,this);
		return levelres;
	}

	private void printlevelTraverse(List<List<TreeNode>> levelres,ArrayDeque<TreeNode> roottmp, TreeNode treeNode) {
		roottmp.push(this);
		while(!roottmp.isEmpty()) {
			//all pop
			List<TreeNode> level = new ArrayList<TreeNode>();
			while(!roottmp.isEmpty()) {
				TreeNode tmp = roottmp.pop();
				level.add(tmp);
			}
			
			boolean breakflag = true;
			for (TreeNode treeNode2 : level) {
				if(treeNode2 != TreeNode.NULLNODE) {
					breakflag = false;
				}
			}
			if(breakflag) {
				break;
			}else {
				levelres.add(level);
			}

			// next turn
			for (TreeNode node : levelres.get(levelres.size()-1)) {
				if(node.left != null) {
					roottmp.add(node.left);
				}else {
					roottmp.add(NULLNODE);
				}
				if(node.right != null) {
					roottmp.add(node.right);
				}else {
					roottmp.add(NULLNODE);
				}
			}
			
		}
	}

	private int depth(TreeNode treeNode) {
		return depthHelp(treeNode,1);
	}

	private int depthHelp(TreeNode treeNode, int i) {
		if(treeNode == null) return i;
		int le = i,rh = i;
		if(treeNode.left != null) {
			 le = depthHelp(treeNode.left,i+1);
		}
		if(treeNode.right != null) {
			rh = depthHelp(treeNode.right,i+1);
		}
		return Math.max(le, rh);
	}

	/**
	 * level Traversel
	 * */
	public List<List<TreeNode>>  leveTraverselResult() {
		List<List<TreeNode>> levelres = new ArrayList<List<TreeNode>>();
		ArrayDeque<TreeNode> roottmp = new ArrayDeque<TreeNode>();
		levelTraverse(levelres,roottmp,this);
		return levelres;
	}

	private void levelTraverse(List<List<TreeNode>> levelres,ArrayDeque<TreeNode> roottmp, TreeNode treeNode) {
		roottmp.push(this);
		
		while(!roottmp.isEmpty()) {
			
			//all pop
			List<TreeNode> level = new ArrayList<TreeNode>();
			while(!roottmp.isEmpty()) {
				TreeNode tmp = roottmp.pop();
				level.add(tmp);
			}
			levelres.add(level);
			
			// next turn
			for (TreeNode node : levelres.get(levelres.size()-1)) {
				if(node.left != null) roottmp.add(node.left);
				if(node.right != null) roottmp.add(node.right);
			}
			
		}
	}

}