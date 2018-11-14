/**
 * @author zhailz
 * 2018年11月14日 上午11:08:58
 */
package baseAlg.backtracking;

/**

Backtracking is an algorithmic paradigm that tries different solutions until finds a solution that “works”. 
Problems which are typically solved using backtracking technique have following property in common. 

These problems can only be solved by trying every possible configuration and each configuration is tried only once. 
A Naive solution for these problems is to try all configurations and output a configuration that follows given problem constraints.

Backtracking works in incremental way and is an optimization over the Naive solution
 where all possible configurations are generated and tried.

For example, consider the following Knight’s Tour problem.
The knight is placed on the first block of an empty board and, 
moving according to the rules of chess, must visit each square exactly once.

backtracking也是一种编程思想，使用到了递归。

backtracking要解决的问题大致具有这样的特征，
为了得到问题的解，需要进行若干步骤，
每一步的抉择都是相同的，
每一步都是在上一步的基础上完成的，
需要记录之前的轨迹，直到终点情况，

不过有可能是正确也有可能是错误。比如最典型的N皇后问题。需要部署N个皇后，每一次部署都有N种可能。

其程序在实现上满足下列特征：

（1）每一步的处理，先check特殊情况，即return case；这里必须有returncase。

（2）再使用一个for循环，尝试每一种选择，在for循环内，先检测该种选择是否正确，
然后如果正确，就在轨迹上记录，然后递归地处理下一步，处理完以后，再把轨迹恢复，供下一中选择进行。这里恢复轨迹是很重要的。

可以分为两类，一种是寻找全部的解，一种是找到一个解。

一个解:递归函数需要返回值，在for循环里面尝试每一种可能时，如果该选择返回true，
那么就返回。否则for结束的时候（执行到这里说明所有尝试都失败了）要返回false。利用返回值可以让程序提前返回，只找到一个解。

多个解：这时递归函数可以是void类型，这样就可以搜全部的解，for循环结束也不需要处理。



和dp的比较：这里是指up到bottom递归地dp，二者都涉及了递归，但是有差别，
dp的递归是小规模的递归，即解决一个子问题。但是回溯的递归是每一步的选择，可以看成是并列的，
都是在一次完整的搜索中的一步。而且dp是从高到低递归，而回溯是从开始到结束，有点从低到高的感觉。

目前为止，涉及到递归地思路有分治法，子问题，dp和回溯。很相近，不过还是有差别。

分治法的子问题不重合，子问题是不带memo的dp，回溯需要记录轨迹。这些事他们的特征。

回溯法用于搜索解，dp找最优解。



下面上一个N皇后的代码，回溯的思路体现的很清晰。包括轨迹和恢复。

public List<List<String>> solveNQueens(int n) {
	    List<List<String>> result = new ArrayList<>();
	    char[][] cash = new char[n][n];
	    for(int i = 0; i < n; i++){
	    	for(int j = 0; j < n; j++){
	    		cash[i][j] = '.';
	    	}
	    }
	    solveQ(result, cash, 0, n);
	    return result;
	}
	public void solveQ(List<List<String>> result, char[][] cash, int row, int n){
		if(row == n){
			List<String> r = new ArrayList<>();
			for(int i = 0; i < n; i++){
				String s = "";
				for(int j = 0; j < n; j++){
					s += cash[i][j];
				}
				r.add(s);
			}
			result.add(r);
			return;
		}
		for(int i = 0; i < n; i++){
			if(isright(cash, row, i, n)){
				cash[row][i] = 'Q';
				solveQ(result, cash, row + 1, n);
				cash[row][i] = '.';
			}
		}
	}
	private boolean isright(char[][] cash, int row, int col, int n){
		for(int i = 0; i <= row; i++){
			if(cash[i][col] == 'Q')
				return false;
		}
		int x = row, y = col;
		while(x >= 0 && y >= 0){
			if(cash[x--][y--] == 'Q')
				return false;
		}
		x = row;y = col;
		while(x >= 0 && y < n){
			if(cash[x--][y++] == 'Q')
				return false;
		}
		return true;
	}

 * 
 * */
