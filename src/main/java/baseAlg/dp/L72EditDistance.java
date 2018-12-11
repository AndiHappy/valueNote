package baseAlg.dp;

/**
 * @author zhailz
 * 2018年12月7日 上午10:14:11
 */
public class L72EditDistance {
	/***
	
	Given two words word1 and word2, 
	find the minimum number of operations required 
	to convert word1 to word2.
	
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
	 * */

	/**
	 已经确定的使用DP，三种操作，插入一个字符，删除一个字符，替换一个字符
	 问题的解决是 word1 转化为 word2 ，以word2 为模板
	 关键在于怎么转化？
	 
	Let following be the function definition :-
	
	f(i, j) := minimum cost (or steps) required to convert first i 
	characters of word1 to first j characters of word2
	
	Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.
	
	f(i, j) = f(i - 1, j - 1)
	
	Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper
	
	f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
	
	f(i, j - 1) represents insert operation
	f(i - 1, j) represents delete operation
	f(i - 1, j - 1) represents replace operation
	
	Here, we consider any operation from word1 to word2. 
	It means, when we say insert operation, we insert a new character 
	after word1 that matches the jth character of word2. 
	So, now have to match i characters of word1 to j - 1 characters of word2. 
	Same goes for other 2 operations as well.
	
	Note that the problem is symmetric. 
	The insert operation in one direction 
	(i.e. from word1 to word2) is same as delete operation in other. 
	So, we could choose any direction.
	
	Above equations become the recursive definitions for DP.
	
	Base Case:
	
	f(0, k) = f(k, 0) = k
	
	Below is the direct bottom-up translation of this recurrent relation. 
	It is only important to take care of 0-based index with actual code :-
	 
	 * **/

	/**
	 * 首先定义子问题：f(i, j) := minimum cost (or steps) required to convert 
	 * first i characters of word1 to first j characters of word2
	 * 
	 * 定义第i个字符和第j个字符转化为相等，花费的步骤
	 * 
	 * */
	public int minDistance_DP(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[][] cost = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			cost[i][0] = i;
		for (int i = 1; i <= n; i++)
			cost[0][i] = i;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (word1.charAt(i) == word2.charAt(j))
					cost[i + 1][j + 1] = cost[i][j];
				else {
					int a = cost[i][j];
					int b = cost[i][j + 1];
					int c = cost[i + 1][j];
					cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
					cost[i + 1][j + 1]++;
				}
			}
		}
		return cost[m][n];
	}

	// - Exmaple:
	// 1. From 'abcx' to 'edfx', we don't need to do anything after change 'abc' to 'edf', since 'x' == 'x'.
	// 2. From 'abcx' to 'edfz', we need to change 'x' to 'z', after we change 'abc' to 'edf', since 'x' != 'z'.
	// 3. From 'abcx' to 'edf', we need to delete 'x', after we change 'abc' to 'edf', since 'x' != null.
	// 4. From 'abc' to 'edfz', we need to add a z, after we change 'abc' to 'edf', sunce null != 'z'.

	// - Design of Algorithms:
	// Use dynamic programming approach:
	// edDpm[i+1, j+1] means the edit distance to convert word1[0:i] to word2[0:j], there are two cases:
	// 1. If word[i+1] == word[j+1], then edDpm[i+1, j+1] == min(edDpm[i,j], edDpm[i, j+1]+1, edDpm[i+1, j]+1);
	// 2. If word[i+1] != word[j+1], then edDpm[i+1, j+1] == min(edDpm[i,j]+1, edDpm[i, j+1]+1, edDpm[i+1, j]+1);
	
	public int minDistance(String word1, String word2) {
		//声明的时候都在原来的基础上增加了一行
		//edDpm[i+1, j+1] means the edit distance to convert word1[0:i] to word2[0:j]
		int[][] edDpm = new int[word1.length() + 1][word2.length() + 1];
		
		char[] charArr1 = word1.toCharArray();
		char[] charArr2 = word2.toCharArray();
		
		// edDpm[i][0] 标识，word1[0:i-1] 到 word2[0:0] 
		for (int i = 0; i <= word1.length(); i++) {
			edDpm[i][0] = i;
		}
		
		// edDpm[i][0] 标识，word1[0:0] 到 word2[0:j-1] 
		for (int j = 0; j <= word2.length(); j++) {
			edDpm[0][j] = j;
		}
		for (int i = 0; i < word1.length(); i++) {
			for (int j = 0; j < word2.length(); j++) {
				if (charArr1[i] == charArr2[j]) {
					edDpm[i + 1][j + 1] = edDpm[i][j];
				} else {
					edDpm[i + 1][j + 1] = edDpm[i][j] + 1;
				}
				edDpm[i + 1][j + 1] = Math.min(edDpm[i + 1][j + 1], edDpm[i][j + 1] + 1);
				edDpm[i + 1][j + 1] = Math.min(edDpm[i + 1][j + 1], edDpm[i + 1][j] + 1);
			}
		}
		return edDpm[word1.length()][word2.length()];
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
