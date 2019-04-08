package leetcode.L36ToL70;

/**

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom 
right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7

Explanation: Because the path 1→3→1→1→1 minimizes the sum.

 *
 */
public class L64MinimumPathSum {
	
	public static int minPathSum(int[][] grid) {
		
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		
		int[][] tmp = new int[grid.length][grid[0].length];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if(i==0 && j == 0) {
					tmp[i][j] =grid[i][j];
				}else if(i > 0 && j ==0) {
					tmp[i][j] = tmp[i-1][j]+grid[i][j];
				}else if(i == 0 && j >0) {
					tmp[i][j] = tmp[i][j-1]+grid[i][j];
				}else {
					tmp[i][j] = Math.min(tmp[i-1][j] , tmp[i][j-1])+grid[i][j];
				}
			}
		}
		
		return tmp[grid.length-1][grid[0].length-1];
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] grid = new int[][] {
		                           {1,3,1},
		                           {1,5,1},
		                           {4,2,1}
		};
		
		System.out.println(L64MinimumPathSum.minPathSum(grid));

	}

}
