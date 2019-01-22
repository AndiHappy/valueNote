package leetcode.L36ToL70;

/**
 * @author zhailz
 * 2019年1月21日 下午2:39:31
 */
public class L48RotateImage {

	/***
	
	You are given an n x n 2D matrix representing an image.
	
	Rotate the image by 90 degrees (clockwise).
	
	Note:
	
	You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
	DO NOT allocate another 2D matrix and do the rotation.
	
	Example 1:
	
	Given input matrix = 
	[
	[1,2,3],
	[4,5,6],
	[7,8,9]
	],
	
	rotate the input matrix in-place such that it becomes:
	[
	[7,4,1],
	[8,5,2],
	[9,6,3]
	]
	Example 2:
	
	Given input matrix =
	[
	[ 5, 1, 9,11],
	[ 2, 4, 8,10],
	[13, 3, 6, 7],
	[15,14,12,16]
	], 
	
	rotate the input matrix in-place such that it becomes:
	[
	[15,13, 2, 5],
	[14, 3, 4, 1],
	[12, 6, 8, 9],
	[16, 7,10,11]
	] 
	
	 * */

	public static void rotate(int[][] matrix) {
		/*1. 先交换行（先转置再交换列也行，但交换列对java不友好）
		   2. 再转置
		 */
		if (matrix.length <= 1)
			return;
		int n = matrix.length;
		/*  1,2,3
		    4,5,6
		    7,8,9
		*/
		for (int i = 0; i < n / 2; i++) {
			int[] temp = matrix[i];
			matrix[i] = matrix[n - 1 - i];
			matrix[n - 1 - i] = temp;
		}
		/*  7,8,9
		    4,5,6
		    1,2,3
		*/

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int temp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = temp;

			}
		}
		/*  7,4,1
		    8,5,2
		    9,6,3
		*/
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		for (int[] is : matrix) {
			for (int i : is) {
				System.out.print(i + "    ");
			}
			System.out.println();
		}
		
		rotate(matrix);

		for (int[] is : matrix) {
			for (int i : is) {
				System.out.print(i + "    ");
			}
			System.out.println();
		}
	}

}
