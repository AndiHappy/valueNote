package leetcode.L71ToL90;

/**
 * @author guizhai
 *
 */
public class L74Searcha2DMatrix {

	/**
	 
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false

	 */
	
	 public boolean searchMatrix(int[][] matrix, int target) {
		 if(matrix == null || matrix.length == 0) return false;
		 for (int[] is : matrix) {
			if(is.length>0 && is[is.length-1] >= target && is[0] <= target) {
				//二分法查找更快一点
//				return Arrays.binarySearch(is, target) >= 0 ;
			  	return sortArraySearchKey(is,target);
			}
		}
     return false;
   }
	
	private boolean sortArraySearchKey(int[] is, int target) {
		if(is == null || is.length == 0) return false;
		 int from = 0; int to = is.length-1;
		 while(from <= to) {
			 if(is[from] == target || is[to] == target) {
				 return true;
			 }
			 int mid = (from + to)/2;
			 if(is[mid] == target) return true;
			 if(is[mid] > target) to = mid-1;
			 if(is[mid] < target) from = mid+1;
		 }
		return false;
	}

	public static void main(String[] args) {
		L74Searcha2DMatrix test = new L74Searcha2DMatrix();
		
		int[][] matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
//		int[][] matrix = new int[][] {{}};
		System.out.println(test.searchMatrix(matrix , 3));
		System.out.println(test.searchMatrix(matrix , 15));

	}

}
