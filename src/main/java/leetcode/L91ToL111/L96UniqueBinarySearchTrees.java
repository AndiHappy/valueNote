package leetcode.L91ToL111;

/**
 * @author guizhai
 *
 */
public class L96UniqueBinarySearchTrees {

	/**
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

	 */
	
	
	
	/**
	 * 
	
First note that dp[k] represents the number of BST trees built from 1....k;

Then assume we have the number of the first 4 trees: 
dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , 

how do we get dp[5] based on these four numbers is the core problem here.

The essential process is: to build a tree, 
we need to pick a root node, then 
we need to know how many possible left sub trees and right sub trees can be held under that node, 
finally multiply them.

To build a tree contains {1,2,3,4,5}. First we pick 1 as root, 
for the left sub tree, there are none; for the right sub tree, 
we need count how many possible trees are there constructed from {2,3,4,5}, 
apparently it's the same number as {1,2,3,4}. 
So the total number of trees under "1" picked as root is 
dp[0] * dp[4] = 14. (assume dp[0] =1). 

Similarly, 
root 2 has dp[1]*dp[3] = 5 trees. 
root 3 has dp[2]*dp[2] = 4, 
root 4 has dp[3]*dp[1]= 5 and 
root 5 has dp[0]*dp[4] = 14. 
Finally sum the up and it's done.

Now, we may have a better understanding of the dp[k], 
which essentially represents the number of BST trees with k consecutive nodes. 
It is used as database when we need to know how many left sub trees are possible for k nodes 
when picking (k+1) as root.
	 * */
	
	public int numTrees(int n) {
		int[] memo = new int[n+1];
		memo[0] = 1;
		return numTrees(n, memo);
	}
	    
	private int numTrees(int n, int[] memo) {
		//出口的选择比较的麻烦
		if(memo[n] != 0) return memo[n];
		for(int i=1; i<=n; i++) {
			memo[n] += numTrees(i-1, memo) * numTrees(n-i, memo);
		}
		return memo[n];
	}
	 
	/**
	 * n 为根节点的情况下：右子树是0位根节点的情况，左子树为1，2，3，4，。。。n-1为根节点的情况
	 * f(n)=f(0)*(f(1)+f(2),....f(n-1))
	 * 
	 * */
	public int numTreesROOTK(int n) {
    int [] dp = new int[n+1];
    dp[0]= 1;
    dp[1] = 1;
     for(int level = 2; level <=n; level++)
        for(int root = 1; root<=level; root++)
            dp[level] += dp[level-root]*dp[root-1];
    return dp[n];
}
	


	public static void main(String[] args) {
		L96UniqueBinarySearchTrees test = new L96UniqueBinarySearchTrees();
		test.numTrees(5);
	}

}
