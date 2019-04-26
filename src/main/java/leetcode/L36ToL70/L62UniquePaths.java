package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L62UniquePaths {

	/**
	A robot is located at the top-left corner of 
	a m x n grid (marked 'Start' in the diagram below).
	
	The robot can only move either down or right at any point in time. 
	The robot is trying to reach the bottom-right corner of the grid 
	(marked 'Finish' in the diagram below).
	
	How many possible unique paths are there?
	
	
	Above is a 7 x 3 grid. How many possible unique paths are there?
	
	Note: m and n will be at most 100.
	
	Example 1:
	
	Input: m = 3, n = 2
	Output: 3
	Explanation:
	From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
	1. Right -> Right -> Down
	2. Right -> Down -> Right
	3. Down -> Right -> Right
	Example 2:
	
	Input: m = 7, n = 3
	Output: 28
	
	 */

	
	/**
	 * 这种形式下，更容易理解
	 * */
	 public int uniquePaths_easyUnderstanding(int m, int n) {
	        int[][] dp = new int[n][m];
	        dp[0][0] = 1;
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < m; j++) {
	                if (i == 0 || j == 0) {
	                    dp[i][j] = 1;
	                } else {
	                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
	                }
	            }
	        }
	        return dp[n - 1][m - 1];
	    }
	
	
	public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }
	
	public int uniquePaths_wrong(int m, int n) {
		int[][] tmparray = new int[m][n];
		List<Object> result = new  ArrayList<Object>();
		start(tmparray,0,0,result);
		return result.size();
	}

	
	private void start(int[][] tmparray, int i, int j,List<Object> result) {
		//这个判断是不对的，因为统计的是路径的条数
		if(i == tmparray.length-1 && j == tmparray[0].length-1) {
			 result.add(new Object());
		}else {
			if(i+1 < tmparray.length) {
				 start(tmparray, i+1, j, result);
				 i = i-1;
			}
			if(j+1 < tmparray.length) {
				start(tmparray, i, j+1, result);
				j = j-1;
			}
		}
	}

	public static void main(String[] args) {
		L62UniquePaths test = new L62UniquePaths();
		int value = test.uniquePaths(3, 2);
		System.out.println(value);
		int value1 = test.uniquePaths(7, 3);
		System.out.println(value1);

	}

}
