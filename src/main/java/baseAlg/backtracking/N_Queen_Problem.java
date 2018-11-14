package baseAlg.backtracking;

/**
 * @author zhailz
 * 2018年11月14日 上午11:41:19
 */
public class N_Queen_Problem {

	public static final int N = 4;
	
	public static void printSolution(int sol[][]) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++){
				System.out.print(" " + sol[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//任两个皇后都不能处于同一条横行、纵行或斜线上
	boolean isMeet(int[][] chess,int x,int y){
		//横线和纵行
		for (int i = 0; i< chess.length ;i++) {
			if(chess[x][i] == 1) return false;
			if(chess[i][y] == 1) return false;
		}
		return true;
	}
	
	private static void solveNQueue(int[][] chess, int n2) {
		
		
	}
	
	public static void main(String[] args) {
		int[][] chess = new int[N][N];
		printSolution(chess);
		
		solveNQueue(chess,N);
	}

	

}
