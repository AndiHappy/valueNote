package leetcode.L36ToL70;

import java.util.LinkedList;
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
        boolean[][] board = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                board[i][j] = false;
                // not visited.
            }
        }
    
        List<List<String>> result = new LinkedList<>();
        queenUtil(0, board, result);
        return result;
    }
    
    private boolean queenUtil(int col, boolean[][] solution, 
                              List<List<String>> results) {
        
        // we already exhausted this board.
        if (col >= solution.length) {
            results.add(convertToSolution(solution));
            return true;
        }
        
        // in this column, try placing the queen in this row.
        for (int i = 0; i < solution.length; ++i) {
            if (isOkay(i, col, solution)) {
                solution[i][col] = true;
                // recurse and try,Very Clever
                queenUtil(col + 1, solution, results);
                // try other solutions for this column
                solution[i][col] = false;
            }
        }
        return false;
    }
    
    private List<String> convertToSolution(boolean[][] solution) {
        List<String> converted = new LinkedList<>();
        
        for (int i = 0; i < solution.length; ++i) {
            String row = "";
            for (int j = 0; j < solution.length; ++j) {
                if (solution[i][j] == false) {
                    row += ".";
                } else {
                    row += "Q";
                }
            }
            converted.add(row);
        }
        return converted;
    }
    
    private boolean isOkay(int x, int y, boolean[][] state) {
        if (x >= state.length || y >= state.length || x < 0 || y < 0) {
            return false;
        }
        
        // to the left
        for (int j = y; j >= 0; --j) {
            if (state[x][j] == true) 
                return false; 
        }
        for (int i = x; i >= 0; --i) {
            if (state[i][y] == true) {
                return false;
            }
        }
        
        // low left    
        for (int i = x, j = y; i >= 0 && j >= 0; --i, --j)                 {
            if (state[i][j] == true) {
                return false;
            }
        }
        
        // up left
        for (int i = x, j = y; j >= 0 && i < state.length; ++i, --j)                 {
            if (state[i][j] == true) {
                return false;
            }
        }
        
        return true;
    }

}
