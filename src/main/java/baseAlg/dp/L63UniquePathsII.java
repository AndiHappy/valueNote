package baseAlg.dp;

/**
 * @author zhailz
 * 2018年11月29日 下午8:51:59
 */
public class L63UniquePathsII {
	
	/**

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach 
the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
	 * **/
	
    /**
     * i 和 j 来说，挪动的步骤有两个(i+1,j) 和 (i,j+1)
     * 子问题定义的好，可以省去很多的事情，这里定义的子问题，就是[0][0] 到[i][j] 到底有多少条路。
     * 这个算是在原来问题的基础上，直接的缩小，简称缩小法
     * 原来的问题是求到最后一个节点[m][n]的路径数
     * 现在子问题是：从[0][0] 到 [i][j] 总共有多少条路径呢，
     * 子问题的i,j 和子问题i-1,j 以及 i,j-1 之间的关系。这个算是递推的关系，然后填上[i][j] 这张表
     * */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        for (int i=0;i<obstacleGrid.length;i++){
            for (int j=0;j<obstacleGrid[i].length;j++){
                if (obstacleGrid[i][j]==1) obstacleGrid[i][j]=0;
                else if (i==0 && j==0) obstacleGrid[i][j]=1;
                else if (i==0) obstacleGrid[i][j]=obstacleGrid[i][j-1];
                else if (j==0) obstacleGrid[i][j]=obstacleGrid[i-1][j];
                else obstacleGrid[i][j]=obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
            }
        }
     return obstacleGrid[obstacleGrid.length-1][obstacleGrid[obstacleGrid.length-1].length-1];
 }

	public static void main(String[] args) {
		
		L63UniquePathsII test = new L63UniquePathsII();
		int[][] obstacleGrid = new int[3][4];
		test.uniquePathsWithObstacles(obstacleGrid);

	}

}
