package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L79WordSearch {

	/**

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. T
he same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

	 */
	
	/**
	 * 二位数组的搜索：确定了头位置以后，其余的元素依次的相连接
	 * */
	public boolean exist(char[][] board, String word) {
    if (board == null || word == null)
        return false;
    if (word.length() == 0)
        return true;
    
    char[] w = word.toCharArray();
    for (int i = 0; i < board.length; i++) {
        for(int j = 0; j < board[i].length; j++) {
            if (exist(board, i, j, w, 0))
                   return true;
        }
    }
    
    return false;
}

private boolean exist(char[][] board, int r, int c, char[] word, int index) {       
    if (board[r][c] == word[index]) {
        char save = board[r][c];
        board[r][c] = 0;
        if ((index == word.length - 1) ||
            (r > 0 && exist(board, r-1, c, word, index + 1)) ||
            (c > 0 && exist(board, r, c-1, word, index + 1)) ||
            (r < board.length - 1 && exist(board, r+1, c, word, index + 1)) ||
            (c < board[r].length - 1 && exist(board, r, c+1, word, index + 1)))
             return true;
        board[r][c] = save;
    }
    return false;
}
	
	public static void main(String[] args) {
		

	}

}
