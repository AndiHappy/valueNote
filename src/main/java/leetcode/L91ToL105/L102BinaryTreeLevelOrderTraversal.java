package leetcode.L91ToL105;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L102BinaryTreeLevelOrderTraversal {

	/**
	
	Given a binary tree, return the level order traversal of its nodes' values. 
	(ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
	
	 */

	/**
	 * 层次遍历：
	 * 首先由一个双端的队列，把root装进入，初始化
	 * 然后维护这个双端队列：每次全部的出入到一个list中，这个list为一个结果，一个阶层
	 * 然后遍历这个list的数据，把这个list中包含的节点的左节点和右节点，全部的加入维护的双端队列中
	 * 再次出队列的时候，就相当于在遍历第二个阶层了，一次循环下去，一直到双端队列中没有元素为止。
	 * */
public List<List<Integer>> levelOrder(TreeNode root) {
    
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
        return result;
    }
    
    ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    
    while (queue.size() > 0) {
        
        List<TreeNode> nodes = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        while (queue.size() > 0) {
            TreeNode node = queue.pop();
            nodes.add(node);
            level.add(node.val);
        }
        result.add(level);
        
        for (TreeNode n : nodes) {
            if (n.left != null) {
                queue.add(n.left);
            }
            
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }
    
    return result;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
