package leetcode.L71ToL90;

import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L85MaximalRectangle {

	/**
	
	Given a 2D binary matrix filled with 0's and 1's, 
	find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6

	 */
	
	public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    int max = 0;
    int[] height = new int[n];
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (matrix[i][j] == '1') {
                ++height[j];
            } else {
                height[j] = 0;
            }
        }
        
        Stack<Integer> s = new Stack<>();
        for (int k = 0; k <= n; ++k) {
            int cur = k == n ? -1 : height[k];
            while (!s.isEmpty() && cur < height[s.peek()]) {
                max = Math.max(height[s.pop()] * (s.isEmpty() ? k : k - s.peek() - 1), max);
            }
            s.push(k);
        }
    }
    return max;
}
	public static void main(String[] args) {
		

	}

}
