package leetcode.L0;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import baseAlg.base.TreeNode;

/**
 * @author guizhai
 *
 */
public class L297SerializeandDeserializeBinaryTree {

 /**

Serialization is the process of converting a data structure or 
object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or 
another computer environment.

Design an algorithm to serialize and deserialize a binary tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be
 deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. 
You do not necessarily need to follow this format, so please be creative and come up with different 
approaches yourself.

Note: Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.

  */

 class Codec_MedianSort {
  private static final String spliter = ",";
  private static final String NN = "X";

  // 中序遍历的成序列,关键的地方就在于叶子节点表示为X
  public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      buildString(root, sb);
      return sb.toString();
  }

  private void buildString(TreeNode node, StringBuilder sb) {
      if (node == null) {
          sb.append(NN).append(spliter);
      } else {
          sb.append(node.val).append(spliter);
          buildString(node.left, sb);
          buildString(node.right,sb);
      }
  }
  
  // 反序列化，首先是根据间隔符得到节点元素，然后在
  public TreeNode deserialize(String data) {
      Deque<String> nodes = new LinkedList<>();
      nodes.addAll(Arrays.asList(data.split(spliter)));
      return buildTree(nodes);
  }
  
  private TreeNode buildTree(Deque<String> nodes) {
      String val = nodes.remove();
      if (val.equals(NN)) return null;
      else {
          TreeNode node = new TreeNode(Integer.valueOf(val));
          node.left = buildTree(nodes);
          node.right = buildTree(nodes);
          return node;
      }
  }
}
 
 
class Codec_DFS{

//Encodes a tree to a single string.
 public String serialize(TreeNode root) {
     StringBuilder sb=new StringBuilder();
     dfs(root,sb);
     return sb.toString();
 }
 private void dfs(TreeNode x, StringBuilder sb) {
     if (x==null) {
         sb.append("null ");
         return;
     }
     sb.append(String.valueOf(x.val));
     sb.append(' ');
     dfs(x.left,sb);
     dfs(x.right,sb);
 }

 // Decodes your encoded data to tree.
 public TreeNode deserialize(String data) {
     String[] node=data.split(" ");
     int[] d=new int[1];
     return dfs(node,d);
 }
 private TreeNode dfs(String[] node, int[] d) {
     if (node[d[0]].equals("null")) {
         d[0]++;
         return null;
     }
     TreeNode x=new TreeNode(Integer.valueOf(node[d[0]]));
     d[0]++;
     x.left=dfs(node,d);
     x.right=dfs(node,d);
     return x;
 }

}


class Codec_BFS {

 // Encodes a tree to a single string.
 public String serialize(TreeNode root) {
     if (root==null) return "";
     Queue<TreeNode> qu=new LinkedList<>();
     StringBuilder sb=new StringBuilder();
     qu.offer(root);
     sb.append(String.valueOf(root.val));
     sb.append(' ');
     while (!qu.isEmpty()) {
         TreeNode x=qu.poll();
         if (x.left==null) sb.append("null ");
         else {
             qu.offer(x.left);
             sb.append(String.valueOf(x.left.val));
             sb.append(' ');
         }
         if (x.right==null) sb.append("null ");
         else {
             qu.offer(x.right);
             sb.append(String.valueOf(x.right.val));
             sb.append(' ');
         }
     }
     return sb.toString();
 }

 // Decodes your encoded data to tree.
 public TreeNode deserialize(String data) {
     if (data.length()==0) return null;
     String[] node=data.split(" ");
     Queue<TreeNode> qu=new LinkedList<>();
     TreeNode root=new TreeNode(Integer.valueOf(node[0]));
     qu.offer(root);
     int i=1;
     while (!qu.isEmpty()) {
         Queue<TreeNode> nextQu=new LinkedList<>();
         while (!qu.isEmpty()) {
             TreeNode x=qu.poll();
             if (node[i].equals("null")) x.left=null;
             else {
                 x.left=new TreeNode(Integer.valueOf(node[i]));
                 nextQu.offer(x.left);
             }
             i++;
             if (node[i].equals("null")) x.right=null;
             else {
                 x.right=new TreeNode(Integer.valueOf(node[i]));
                 nextQu.offer(x.right);
             }
             i++;
         }
         qu=nextQu;
     }
     return root;
 }
}

 
 /**
  * Definition for a binary tree node.
  * public class TreeNode {
  *     int val;
  *     TreeNode left;
  *     TreeNode right;
  *     TreeNode(int x) { val = x; }
  * }
  */
 class Codec {

     // Encodes a tree to a single string.
     public String serialize(TreeNode root) {
         return null;
     }

     // Decodes your encoded data to tree.
     public TreeNode deserialize(String data) {
      return null;
     }
 }

//  Your Codec object will be instantiated and called as such:
//  Codec codec = new Codec();
//  codec.deserialize(codec.serialize(root));
 public static void main(String[] args) {
  TreeNode root = new TreeNode(1);
  root.left=new TreeNode(7);
  root.right = new TreeNode(9);
  root.left.left=new TreeNode(4);
  root.left.right = new TreeNode(5);
  root.right.left=new TreeNode(8);
  root.right.right = new TreeNode(10);
  
  L297SerializeandDeserializeBinaryTree test = new L297SerializeandDeserializeBinaryTree();
  
  test.test(root);
 }

 private void test(TreeNode root) {
  Codec_DFS dfs = new Codec_DFS();
  String v = dfs.serialize(root);
  System.out.println(v);
  
  Codec_BFS bfs = new Codec_BFS();
  v = bfs.serialize(root);
  System.out.println(v);
 }

}
