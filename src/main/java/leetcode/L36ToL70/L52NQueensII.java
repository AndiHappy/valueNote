package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhailz
 * 2019年2月25日 下午7:21:28
 */
public class L52NQueensII {

	public int totalNQueens(int n) {
		int[][] na = new int[n][n];
		List<String> value = new ArrayList<String>();
		sortNQueens(na, 0, value);
		return value.size();
	}

	public void sortNQueens(int[][] n, int col, List<String> value) {
		if (col >= n.length) {
			value.add(convert(n));
			return;
		}
		for (int i = 0; i < n.length; i++) {
			if (meetCondition(n, i, col)) {
				n[i][col] = 1;
				sortNQueens(n, col + 1, value);
				n[i][col] = 0;
			}
		}
	}

	private String convert(int[][] n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length; j++) {
				builder.append(n[i][j] + "  ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	private boolean meetCondition(int[][] n, int i, int j) {
		// 上列
		for (int x = j; x >= 0; x--) {
			if (n[i][x] == 1)
				return false;
		}

		//左行
		for (int x = i; x >= 0; x--) {
			if (n[x][j] == 1)
				return false;
		}

		//左斜线
		for (int x = i, y = j; y >= 0 && x >= 0; x--, y--) {
			if (n[x][y] == 1)
				return false;
		}

		// up left
		for (int x = i, y = j; y >= 0 && x < n.length; ++x, --y) {
			if (n[x][y] == 1)
				return false;
		}

		return true;
	}

	public static void main(String[] args) {

		int[][] n = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length; j++) {
				builder.append(n[i][j] + "  ");
			}
			builder.append("\n");
		}
		System.out.println(builder.toString());

		L52NQueensII test = new L52NQueensII();
		List<String> value = new ArrayList<String>();
		test.sortNQueens(n, 0, value);
		for (String string : value) {
			System.out.println(string);
		}

	}

}
