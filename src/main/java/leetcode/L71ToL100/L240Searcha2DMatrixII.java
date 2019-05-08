package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L240Searcha2DMatrixII {

	/**
	
	Write an efficient algorithm that searches for a value in an m x n matrix. 
	This matrix has the following properties:
	
	Integers in each row are sorted in ascending from left to right.
	Integers in each column are sorted in ascending from top to bottom.
	Example:
	
	Consider the following matrix:
	
	[
	[1,   4,  7, 11, 15],
	[2,   5,  8, 12, 19],
	[3,   6,  9, 16, 22],
	[10, 13, 14, 17, 24],
	[18, 21, 23, 26, 30]
	]
	Given target = 5, return true.
	
	Given target = 20, return false.
	 */

	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0) {
			return false;
		}
		
		int i = 0, left = 0, right = matrix[0].length - 1;

		while (i < matrix.length) {
			if (right < 0)
				return false;
			if (matrix[i][left] == target || matrix[i][right] == target)
				return true;

			else if (matrix[i][left] > target || matrix[i][right] < target) {
				left = 0;
				i++;
			} else {
				left++;
				right--;
			}

		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
