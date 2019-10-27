package leetcode.L;

/**
 * @author guizhai
 *
 */
public class L329LongestIncreasingPathinaMatrix {

 /**
 
 Given an integer matrix, find the length of the longest increasing path.
 
 From each cell, you can either move to four directions: left, right, up or down. 
 You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 
 Example 1:
 
 Input: nums = 
 [
  [9,9,4],
  [6,6,8],
  [2,1,1]
 ] 
 Output: 4 
 Explanation: The longest increasing path is [1, 2, 6, 9].
 
 Example 2:
 Input: nums = 
 [
  [3,4,5],
  [3,2,6],
  [2,2,1]
 ] 
 Output: 4 
 Explanation: The longest increasing path is [3, 4, 5, 6].
 Moving diagonally is not allowed.
 
  */

 /**
 自我分析：
 left, right, up or down
 四个动作，代表了是四个动作
 i,j 到 下一步的四个步骤，可以想到的是动态规划，然后就是如何确定子问题的难点
 如果只是遍历分析，针对每一个的元素进行分析，是不是可以写出来？
 
 写出来了，但是算法已经：Time Limit Exceeded
 
 然后增加了cache的判断
  * 
  * */
 public int longestIncreasingPath(int[][] matrix) {
  int length = 1;
  int[][] cache = new int[matrix.length][matrix[0].length];
  if (matrix == null || matrix.length == 0)
   return 0;
  for (int i = 0; i < matrix.length; i++) {
   for (int j = 0; j < matrix[0].length; j++) {
    length = Math.max(length, find(i, j, matrix, cache));
   }
  }
  return length;
 }

 private int find(int i, int j, int[][] matrix, int[][] cache) {
  if (cache[i][j] != 0)
   return cache[i][j];
  int result = 1;
  int up = 0, down = 0, left = 0, right = 0;
  if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
   down = result + find(i + 1, j, matrix, cache);
  }
  if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
   right = result + find(i, j + 1, matrix, cache);
  }
  if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
   up = result + find(i - 1, j, matrix, cache);
  }

  if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
   left = result + find(i, j - 1, matrix, cache);
  }
  cache[i][j] = Math.max(Math.max(Math.max(up, down), Math.max(left, right)), result);
  return cache[i][j];
 }

 public static void main(String[] args) {
  L329LongestIncreasingPathinaMatrix test = new L329LongestIncreasingPathinaMatrix();
  int[][] matrix = new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
  test.longestIncreasingPath(matrix);

 }

}
