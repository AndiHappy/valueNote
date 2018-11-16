package baseAlg.backtracking;

/**
 * @author zhailz
 * 2018年11月14日 上午11:16:56
 */
public class Rat_in_a_Maze {

	/**
	算法描述：
	while there are untried paths
	{
	generate the next path
	if this path has all blocks as 1
	{
	  print this path;
	}
	}
	
	If destination is reached
	print the solution matrix
	Else
	a) Mark current cell in solution matrix as 1. 
	b) Move forward in the horizontal direction and recursively check if this 
	   move leads to a solution. 
	c) If the move chosen in the above step doesn't lead to a solution
	   then move down and check if this move leads to a solution. 
	d) If none of the above solutions works then unmark this cell as 0 
	   (BACKTRACK) and return false.
	
	 * 
	 * */
	public static final int N = 4;

	/**
	 * 打印迷宫的数据化表达
	 * */
	public static void printSolution(int sol[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(" " + sol[i][j] + " ");
			System.out.println();
		}
	}

	/**
	 * x,y 节点是否可以通行
	 * */
	public static boolean isSafe(int maze[][], int x, int y) {
		// if (x,y outside maze) return false 
		return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
	}

	public static boolean solveMaze(int maze[][]) {
		int sol[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		if (solveMazeUtil(maze, 0, 0, sol) == false) {
			System.out.print("Solution doesn't exist");
			return false;
		}

		printSolution(sol);
		return true;
	}

	/* A recursive utility function to solve Maze 
	problem */
	public static boolean solveMazeUtil(int maze[][], int x, int y, int sol[][]) {
		// if (x,y is goal) return true 
		if (x == N - 1 && y == N - 1) {
			sol[x][y] = 1;
			return true;
		}
		
		if (isSafe(maze, x, y) == true) {
			sol[x][y] = 1;
			if (solveMazeUtil(maze, x + 1, y, sol))
				return true;
			if (solveMazeUtil(maze, x, y + 1, sol))
				return true;
			sol[x][y] = 0;
			return false;
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] array = new int[][] { 
			{ 1, 0, 1, 1 }, 
			{ 1, 1, 1, 1 }, 
			{ 0, 1, 0, 0 }, 
			{ 1, 1, 1, 1 } };
		int[][] array1 = new int[][] { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1}, { 1, 1, 1, 1 } };

		solveMaze(array1);

	}

}
