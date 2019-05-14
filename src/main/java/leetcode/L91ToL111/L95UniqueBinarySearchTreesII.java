package leetcode.L91ToL111;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L95UniqueBinarySearchTreesII {

	/**
	 * 
	
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

	 */
	
	
	/**
	 * 关于树的操作，大多树都要考虑递归的实现。针对这个我们需要生成不同的搜索二叉树，
	 * 我们生成的依据首先就是  根节点，这个根节点，我们可以使用遍历来进行解决。
	 * 然后就是左右子节点生成，因为左节点的值要小于根节点，右节点的值要大于根节点
	 * 然后就是构建一棵树，然后再去重复的构建这个树。
	 * */
	public static List<TreeNode> generateTrees(int n) {
    if (n <= 0) return new ArrayList<>();
    //开始index=1，结束为end=n
    return generate(1, n);
}
private static List<TreeNode> generate(int start, int end) {
	
    if (start > end) return null;
    
    List<TreeNode> result = new ArrayList<>();
    
    for (int i = start; i <= end; i++) {
    		//非常精彩的代码
        List<TreeNode> left = generate(start, i-1);
        List<TreeNode> right = generate(i+1, end);
        
        //非常精彩的迭代
        if (left != null && right != null) {
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        } else if (left != null) {
            for (TreeNode l : left) {
                TreeNode root = new TreeNode(i);
                root.left = l;
                result.add(root);
            }
        } else if (right != null) {
            for (TreeNode r : right) {
                TreeNode root = new TreeNode(i);
                root.right = r;
                result.add(root);
            }
        } else {
            result.add(new TreeNode(i));
        }
    }
    return result;
}

public List<TreeNode> generateTrees1(int n) {
  return n!=0?helper(1,n):new ArrayList<TreeNode>();
}
private List<TreeNode> helper(int x,int y){
  List<TreeNode> res = new ArrayList<>();
  //相比较上一个算法来说，这个添加，使得逻辑更加的清楚了
  if(x>y)
      res.add(null);
  else if(x==y)
      res.add(new TreeNode(x));
  else
      for(int i=x;i<=y;i++){
          List<TreeNode> lefts = helper(x,i-1);
          List<TreeNode> rights = helper(i+1,y);
          for(TreeNode l:lefts)
              for(TreeNode r:rights){
                  TreeNode root = new TreeNode(i);
                  root.left = l;
                  root.right = r;
                  res.add(root);
              }
      }
  return res;
}
	public static void main(String[] args) {
		L95UniqueBinarySearchTreesII test = new L95UniqueBinarySearchTreesII();
		test.generateTrees1(4);
	}

}
