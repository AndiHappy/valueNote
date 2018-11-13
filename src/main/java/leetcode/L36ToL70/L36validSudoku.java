package leetcode.L36ToL70;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhailz
 * 2018年11月13日 下午4:17:46
 */
public class L36validSudoku {

	/**
	 * 
	Determine if a 9x9 Sudoku board is valid. 
	Only the filled cells need to be validated according to the following rules:
	
	Each row must contain the digits 1-9 without repetition.
	Each column must contain the digits 1-9 without repetition.
	Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
	
	A partially filled sudoku which is valid.
	
	The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
	
	Example 1:
	
	Input:
	[
	  ["5","3",".",".","7",".",".",".","."],
	  ["6",".",".","1","9","5",".",".","."],
	  [".","9","8",".",".",".",".","6","."],
	  ["8",".",".",".","6",".",".",".","3"],
	  ["4",".",".","8",".","3",".",".","1"],
	  ["7",".",".",".","2",".",".",".","6"],
	  [".","6",".",".",".",".","2","8","."],
	  [".",".",".","4","1","9",".",".","5"],
	  [".",".",".",".","8",".",".","7","9"]
	]
	Output: true
	Example 2:
	
	Input:
	[
	  ["8","3",".",".","7",".",".",".","."],
	  ["6",".",".","1","9","5",".",".","."],
	  [".","9","8",".",".",".",".","6","."],
	  ["8",".",".",".","6",".",".",".","3"],
	  ["4",".",".","8",".","3",".",".","1"],
	  ["7",".",".",".","2",".",".",".","6"],
	  [".","6",".",".",".",".","2","8","."],
	  [".",".",".","4","1","9",".",".","5"],
	  [".",".",".",".","8",".",".","7","9"]
	]
	Output: false
	Explanation: Same as Example 1, except with the 5 in the top left corner being 
	modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
	Note:
	
	A Sudoku board (partially filled) could be valid but is not necessarily solvable.
	Only the filled cells need to be validated according to the mentioned rules.
	The given board contain only digits 1-9 and the character '.'.
	The given board size is always 9x9.
	* 
	* */

	/**
	 * 1. 想声明三个hashMap值来进行判断，结果Set更加的理想
	 * 2. 3乘3的小块的表示，比较的有意思 i/3,j/3
	 * */
	public static boolean isValidSudoku(char[][] board) {
	    Set<String> seen = new HashSet<String>();
	    for (int i=0; i<9; ++i) {
	        for (int j=0; j<9; ++j) {
	            char number = board[i][j];
	            if (number != '.')
	                if (!seen.add(number + " in row " + i) ||
	                    !seen.add(number + " in column " + j) ||
	                    !seen.add(number + " in block " + i/3 + "-" + j/3))
	                    return false;
	        }
	    }
	    return true;
	}
	
	public static void main(String[] args){
		char[][] board = new char[][]{
			  {'8','3','.','.','7','.','.','.','.'},
			  {'6','.','.','1','9','5','.','.','.'},
			  {'.','9','5','.','.','.','.','6','.'},
			  {'8','.','.','.','6','.','.','.','3'},
			  {'4','.','.','8','.','3','.','.','1'},
			  {'7','.','.','.','2','.','.','.','6'},
			  {'.','6','.','.','.','.','2','8','.'},
			  {'.','.','.','4','1','9','.','.','5'},
			  {'.','.','.','.','8','.','.','7','9'}
		};
		
		System.out.println(isValidSudoku(board));
	}
}
