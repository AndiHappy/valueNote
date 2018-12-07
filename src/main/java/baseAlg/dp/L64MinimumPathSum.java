package baseAlg.dp;

/**
 * @author zhailz
 * 2018年12月6日 下午11:02:42
 */
public class L64MinimumPathSum {

	/**
	Given a m x n grid filled with non-negative numbers, 
	find a path from top left to bottom right which minimizes the 
	sum of all numbers along its path.
	
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
	 * 
	 * */

	/**
	 * 动态规划：缩小法
	 * */
	public int minPathSum(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (i == 0 && j == 0)
					continue;
				if (i == 0)
					grid[i][j] = grid[i][j] + grid[i][j - 1];
				if (j == 0)
					grid[i][j] = grid[i][j] + grid[i - 1][j];
				if (i != 0 && j != 0)
					grid[i][j] = grid[i - 1][j] > grid[i][j - 1] ? grid[i][j] + grid[i][j - 1]
							: grid[i][j] + grid[i - 1][j];
			}
		}

		return grid[grid.length - 1][grid[0].length - 1];
	}

	public static void main(String[] args) {
		//		[[1,2],[5,6],[1,1]]
		L64MinimumPathSum test = new L64MinimumPathSum();
		int[][] grid = new int[][] { { 1, 2 }, { 5, 6 }, { 1, 1 } };
		System.out.println(test.minPathSum(grid));

		grid = new int[][] { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		System.out.println(test.minPathSum(grid));
	}

}
