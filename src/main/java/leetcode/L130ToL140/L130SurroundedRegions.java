package leetcode.L130ToL140;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author guizhai
 *
 */
public class L130SurroundedRegions {

	/**


Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, 
which means that any 'O' on the border of the board are not flipped to 'X'. 

Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 

Two cells are connected if they are adjacent cells connected horizontally or vertically.

	 */
	
	/**
	 
	 First, check the four border of the matrix. If there is a element is
'O', alter it and all its neighbor 'O' elements to '1'.

Then ,alter all the 'O' to 'X'

At last,alter all the '1' to 'O'

For example:

         X X X X           X X X X             X X X X
         X X O X  ->       X X O X    ->       X X X X
         X O X X           X 1 X X             X O X X
         X O X X           X 1 X X             X O X X
    

class Solution {
public:
	void solve(vector<vector<char>>& board) {
        int i,j;
        int row=board.size();
        if(!row)
        	return;
        int col=board[0].size();

		for(i=0;i<row;i++){
			check(board,i,0,row,col);
			if(col>1)
				check(board,i,col-1,row,col);
		}
		for(j=1;j+1<col;j++){
			check(board,0,j,row,col);
			if(row>1)
				check(board,row-1,j,row,col);
		}
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				if(board[i][j]=='O')
					board[i][j]='X';
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				if(board[i][j]=='1')
					board[i][j]='O';
    }
	void check(vector<vector<char> >&vec,int i,int j,int row,int col){
		if(vec[i][j]=='O'){
			vec[i][j]='1';
			if(i>1)
				check(vec,i-1,j,row,col);
			if(j>1)
				check(vec,i,j-1,row,col);
			if(i+1<row)
				check(vec,i+1,j,row,col);
			if(j+1<col)
				check(vec,i,j+1,row,col);
		}
	}
};
	 
	 * */
	
	public void solve(char[][] board) {
    if (board.length == 0) return;
    
    int rowN = board.length;
    int colN = board[0].length;
    Queue<Point> queue = new LinkedList<Point>();
   
   //get all 'O's on the edge first
    for (int r = 0; r< rowN; r++){
    	if (board[r][0] == 'O') {
    		board[r][0] = '+';
            queue.add(new Point(r, 0));
    	}
    	if (board[r][colN-1] == 'O') {
    		board[r][colN-1] = '+';
            queue.add(new Point(r, colN-1));
    	}
    	}
    
    for (int c = 0; c< colN; c++){
    	if (board[0][c] == 'O') {
    		board[0][c] = '+';
            queue.add(new Point(0, c));
    	}
    	if (board[rowN-1][c] == 'O') {
    		board[rowN-1][c] = '+';
            queue.add(new Point(rowN-1, c));
    	}
    	}
    

    //BFS for the 'O's, and mark it as '+'
    while (!queue.isEmpty()){
    	Point p = queue.poll();
    	int row = p.x;
    	int col = p.y;
    	if (row - 1 >= 0 && board[row-1][col] == 'O') {board[row-1][col] = '+'; queue.add(new Point(row-1, col));}
    	if (row + 1 < rowN && board[row+1][col] == 'O') {board[row+1][col] = '+'; queue.add(new Point(row+1, col));}
    	if (col - 1 >= 0 && board[row][col - 1] == 'O') {board[row][col-1] = '+'; queue.add(new Point(row, col-1));}
        if (col + 1 < colN && board[row][col + 1] == 'O') {board[row][col+1] = '+'; queue.add(new Point(row, col+1));}        	      
    }
    

    //turn all '+' to 'O', and 'O' to 'X'
    for (int i = 0; i<rowN; i++){
    	for (int j=0; j<colN; j++){
    		if (board[i][j] == 'O') board[i][j] = 'X';
    		if (board[i][j] == '+') board[i][j] = 'O';
    	}
    }
   
    
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
