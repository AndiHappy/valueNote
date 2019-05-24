package leetcode.L112ToL129;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L118PascalsTriangle {

	/**

Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

	 */
	
	/**
	 * 第i行，就是在i+1的基础上面，前面添加1，然后相邻的两个元素相加即可得到
	 * */
	public static List<List<Integer>> generate(int numRows)
	{
		List<List<Integer>> allrows = new ArrayList<List<Integer>>();
		//最牛的地方在于只使用了一个；临时的队列
		ArrayList<Integer> row = new ArrayList<Integer>();
		
		for(int i=0;i<numRows;i++)
		{
			row.add(0, 1);
			for(int j=1;j<row.size()-1;j++)
				row.set(j, row.get(j)+row.get(j+1));
			
			allrows.add(new ArrayList<Integer>(row));
		}
		return allrows;
		
	}
	
	public static void main(String[] args) {
		L118PascalsTriangle.generate(5);

	}

}
