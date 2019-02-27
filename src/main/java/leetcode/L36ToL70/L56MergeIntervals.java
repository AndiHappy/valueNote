package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhailz
 * 2019年2月27日 上午11:23:35
 */
public class L56MergeIntervals {

	/**
	
	Given a collection of intervals, merge all overlapping intervals.
	
	Example 1:
	
	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
	Example 2:
	
	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considered overlapping.
	
	 * */

	public List<Interval> merge(List<Interval> intervals) {
		if(intervals == null || intervals.size() <= 1) return intervals;
		int start = intervals.get(0).start; int end = intervals.get(0).end;
		
		List<Interval> result = new ArrayList<>();
		for (int i = 1; i < intervals.size(); i++) {
			int tmps = intervals.get(i).start;
//			if(tmps = > start &&  tmps < = end){
				
//			}
		}
		
		return intervals;

	}
	
	public static void main(String[] args){
		List<Interval>  v = new ArrayList<>();
		v.add(new Interval(1,3));
		v.add(new Interval(2,6));
		v.add(new Interval(8,10));
		v.add(new Interval(15,18));
		
	}

}

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
