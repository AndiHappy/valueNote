package leetcode.L36ToL70;

public class L59SpiralMatrixII {

	/***
	 * 
	 * Given a positive integer n, generate a square matrix filled with elements
	 * from 1 to n2 in spiral order.
	 * 
	 * Example:
	 * 
	 * Input: 3 Output: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
	 * 
	 */

	public int[][] generateMatrix(int n) {
		int[][] result = new int[n][n];
		int limit = 1;
		for (int i = 0; i < n && limit <= n * n; i++) {
			limit = setValue(result, limit, i);
		}

		return result;
	}

	private int setValue(int[][] result, int limit, int i) {
		// 首先一横
		for (int j = i; j < result.length - i; j++) {
			result[i][j] = limit;
			limit++;
		}
		// 然后一竖
		for (int j2 = i + 1; j2 < result.length - i; j2++) {
			result[j2][result.length - 1 - i] = limit;
			limit++;
		}

		// 回去一横
		for (int j3 = result.length - 2 - i; j3 >= i; j3--) {
			result[result.length - 1 - i][j3] = limit;
			limit++;
		}
		
		// 上去一竖
		for (int j4 = result.length - 2 - i; j4 > i; j4--) {
			result[j4][i] = limit;
			limit++;
		}

		return limit;
	}

	public static void main(String[] args) {
		L59SpiralMatrixII test = new L59SpiralMatrixII();
		int[][] result = test.generateMatrix(4);
		print(result);
		
		result = test.generateMatrix(1);
		print(result);
		
		result = test.generateMatrix(2);
		print(result);
		
		result = test.generateMatrix(3);
		print(result);
		
		
	}

	private static void print(int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				System.out.print(result[i][j]+ " ");
			}
			
			System.out.println();
		}
	}

}
