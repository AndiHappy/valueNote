/**
 * 
 */
package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L72EditDistance {

	/**

Given two words word1 and word2, find the minimum number of operations 
required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

	 *
	 *
	 */
	
	/**
	  * DP, O(nm) Time, O(nm) Space 
	  * Searching for a path (sequence of edits) from the start string to the 
	  * final string 
	  * For two strings, X of length n, Y of length m 
	  * Define D(i,j): the edit distance between X[1..i] and Y[1..j] 
	  * The edit distance between X and Y is thus D(n,m) 
	  * Initialization: 
	  * D(i,0) = i, D(0,j) = j * 1. 
	  * D(i, j) = min(D(i - 1, j) + 1, D(i, j - 1) + 1, D(i - 1, j - 1) + 0 * or 1), 
	  * 0 is X(i) = Y(j), 
	  * 1 if X(i) != Y(j) * D(N, M) is distance 
	  * 
	  *  Note that f[i][j] only depends on f[i-1][j-1], f[i-1][j] and f[i][j-1], 
	  *  therefore we can reduce the space to O(n) by using only the (i-1)th  
	  *   array and previous updated element(f[i][j-1]). 
	  *   
	  *   */

	 public int minDistance(String word1, String word2) {
		 if(word1 == null && word2 == null) return 0;
       int M = word1.length(), N = word2.length();
//       matrix[i][j],代表的是word1的0到i，转化成word2的0到j的步骤数目
       int[][] matrix = new int[M + 1][N + 1];
       
       //matrix[i][0],代表的是word1的0到i，转化成word2的0到0的步骤数目
       for (int i = 0; i <= M; i++) {
           matrix[i][0] = i;
       }
       for (int j = 0; j <= N; j++) {
           matrix[0][j] = j;
       }
       
       for (int i = 1; i <= M; i++) {
           for (int j = 1; j <= N; j++) {
               int cost = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
               matrix[i][j] = Math.min(
              		 
              		 Math.min(1 + matrix[i][j - 1], 1 + matrix[i - 1][j]),
              		 
              		 cost + matrix[i - 1][j - 1]);
           }
       }
       
       return matrix[M][N];
   }
	
	public static void main(String[] args) {
		int[][] value = new int [2][5];
		for (int i = 0; i < value.length; i++) {
			for (int j = 0; j < value[0].length; j++) {
				System.out.print(value[i][j]+" ");
			}
			System.out.println();
		}

	}

}
