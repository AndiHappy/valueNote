package leetcode.L71ToL100;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L73SetMatrixZeroes {

	/**
	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
	
	Example 1:
	
	Input: 
	[
	[1,1,1],
	[1,0,1],
	[1,1,1]
	]
	Output: 
	[
	[1,0,1],
	[0,0,0],
	[1,0,1]
	]
	Example 2:
	
	Input: 
	[
	[0,1,2,0],
	[3,4,5,2],
	[1,3,1,5]
	]
	Output: 
	[
	[0,0,0,0],
	[0,4,5,0],
	[0,3,1,0]
	]
	Follow up:
	
	A straight forward solution using O(mn) space is probably a bad idea.
	
	A simple improvement uses O(m + n) space, but still not the best solution.
	
	Could you devise a constant space solution?
	
	 */
	
  //为了满足空间复杂度的要求，借用matrix的空间
	public static void setZeros(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean firstCol = false;
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) firstCol = true;
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    // fill col
    for (int i = 1; i < n; i++) {
        if (matrix[0][i] == 0) {
            for (int j = 0; j < m; j++) {
                matrix[j][i] = 0;
            }
        }
    }
    // fill row
    for (int[] row : matrix) {
        if (row[0] == 0) {
            Arrays.fill(row, 0);
        }
    }
    // fill first col
    if (firstCol) {
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
}

	public void setZeroes_SpaceOmn(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[] isZeroRow = new int[rows];
		int[] isZeroCol = new int[cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 0) {
					isZeroRow[i] = 1;
					isZeroCol[j] = 1;
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			if (isZeroRow[i] == 1) {
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < cols; i++) {
			if (isZeroCol[i] == 1) {
				for (int j = 0; j < rows; j++) {
					matrix[j][i] = 0;
				}
			}
		}
	}


	//straight forward
	public void setZeroes_straight_forward(int[][] matrix) {
		if (matrix == null)
			return;
		boolean[][] matrixtmp = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					if (!matrixtmp[i][j]) {
						for (int j2 = 0; j2 < matrix.length; j2++) {
							if (!matrixtmp[j2][j] && matrix[j2][j] != 0) {
								matrix[j2][j] = 0;
								matrixtmp[j2][j] = true;
							}
						}

						for (int j2 = 0; j2 < matrix[0].length; j2++) {
							if (!matrixtmp[i][j2] && matrix[i][j2] != 0) {
								matrix[i][j2] = 0;
								matrixtmp[i][j2] = true;
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		L73SetMatrixZeroes test = new L73SetMatrixZeroes();
		int[][] matrix = new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		test.setZeros(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		matrix = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
		test.setZeros(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

	}

}
