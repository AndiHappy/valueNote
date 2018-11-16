package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2018年11月14日 上午10:59:29
 */
public class L37SudokuSolver {

/**
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

 * */
	
	public static void solveSudoku(char[][] board) {
		if (board == null || board.length == 0)
			return;
		solve(board);
	}

	public static boolean solve(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {//不在进行循环的计算的依据
					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board[i][j] = c; 
							if (solve(board))//计算下一步
								return true;
							else
								board[i][j] = '.'; //如果下一步不行，直接的回退
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			if (board[i][col] != '.' && board[i][col] == c)
				return false; //check row
			if (board[row][i] != '.' && board[row][i] == c)
				return false; //check column
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
					&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
				return false; //check 3*3 block
		}
		return true;
	}
	
	public static void printSolution(char sol[][]) {
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < sol[0].length; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}
	
	/**
	 * 使用程序解决数独的问题
	 * */
	public static void main(String[] args) {
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
		boolean va = solve(board);
		System.out.println(va);
		printSolution(board);
	}

}
