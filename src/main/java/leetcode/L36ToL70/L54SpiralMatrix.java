package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhailz
 * 2019年2月26日 下午2:21:46
 */
public class L54SpiralMatrix {

	/**
	 * 
	Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
	
	Example 1:
	
	Input:
	[
	[ 1, 2, 3 ],
	[ 4, 5, 6 ],
	[ 7, 8, 9 ]
	]
	Output: [1,2,3,6,9,8,7,4,5]
	Example 2:
	
	Input:
	[
	[1, 2, 3, 4],
	[5, 6, 7, 8],
	[9,10,11,12]
	]
	Output: [1,2,3,4,8,12,11,10,9,5,6,7] 
	 * */

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		if (matrix[0].length == 1) {
			for (int i = 0; i < matrix.length; i++) {
				result.add(matrix[i][0]);
			}
			return result;
		}

		if (matrix.length == 1) {
			for (int i = 0; i < matrix[0].length; i++) {
				result.add(matrix[0][i]);
			}
			return result;
		}

		int circle = 0;
		for (int i = 0; i < matrix[0].length && result.size() < matrix.length * matrix[0].length; i++) {
			printCircle(matrix, circle, i, result);
			circle++;
		}

		return result;
	}

	private void printCircle(int[][] matrix, int circle, int i, List<Integer> result) {
		// 开始一横
		int j = i;
		for (; j < matrix[0].length - circle; j++) {
			result.add(matrix[i][j]);
		}

		// 然后一竖
		int j2 = i + 1;
		for (; j2 < matrix.length - circle && j - 1 > 0 && result.size() < matrix.length * matrix[0].length; j2++) {
			result.add(matrix[j2][j - 1]);
		}

		//回去一横
		int j3 = j - 2;
		for (; j3 >= circle && result.size() < matrix.length * matrix[0].length; j3--) {
			result.add(matrix[j2 - 1][j3]);
		}

		// 上去一竖
		for (int j4 = j2 - 2; j4 > i && result.size() < matrix.length * matrix[0].length; j4--) {
			result.add(matrix[j4][j3 + 1]);
		}
	}

	public static void main(String[] args) {
		L54SpiralMatrix test = new L54SpiralMatrix();
		//		int[][] matrix = new int[][] { 
		//			{ 1,  2,  3,  4,  5 }, 
		//			{ 7,  8,  9,  10, 11}, 
		//			{ 12, 13, 14, 15, 16},
		//			{ 16, 17, 18, 19, 20},
		//			{ 21, 22, 23, 24, 25}
		//			};

		int[][] matrix = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		List<Integer> res = test.spiralOrder(matrix);
		System.out.println(Arrays.toString(res.toArray()));
		//		int[][] matrix = new int[][] { { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 }, { 7 }, { 8 }, { 9 } };
		//		[[1,11],[2,12],[3,13],[4,14],[5,15],[6,16],[7,17],[8,18],[9,19],[10,20]]
		matrix = new int[][] { { 1, 11 }, { 2, 12 }, { 3, 13 }, { 4, 14 }, { 5, 15 }, { 6, 16 }, { 7, 17 }, { 8, 18 },
				{ 9, 19 } };
		res = test.spiralOrder(matrix);
		System.out.println(Arrays.toString(res.toArray()));

		matrix = new int[][] { { 6, 9, 7 } };
		res = test.spiralOrder(matrix);
		System.out.println(Arrays.toString(res.toArray()));

		matrix = new int[][] { { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
		res = test.spiralOrder(matrix);
		System.out.println(Arrays.toString(res.toArray()));

		matrix = new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		res = test.spiralOrder(matrix);
		System.out.println(Arrays.toString(res.toArray()));

		//		int circle = 0;
		//		for (int i = 0,j=0; i < te.length; i++) {
		//			System.out.println(te[i][j]);
		//		}

	}

}
