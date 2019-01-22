package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhailz
 * 2019年1月21日 下午5:47:29
 */
public class L51NQueens {
	/**
	 
	 The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
	 such that no two queens attack each other.
	
	
	
	Given an integer n, return all distinct solutions to the n-queens puzzle.
	
	Each solution contains a distinct board configuration of the n-queens' placement, 
	where 'Q' and '.' both indicate a queen and an empty space respectively.
	
	Example:
	
	Input: 4
	Output: [
	[".Q..",  // Solution 1
	"...Q",
	"Q...",
	"..Q."],
	
	["..Q.",  // Solution 2
	"Q...",
	"...Q",
	".Q.."]
	]
	Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
	 * */

	public List<List<String>> solveNQueens(int n) {

		List<List<String>> result = new ArrayList<>();
		int[][] tmpValue = new int[n][n];
		for (int i = 0; i < tmpValue.length; i++) {
			
		}
		return result;
		
	}

	public static void main(String[] args) {

	}
}
