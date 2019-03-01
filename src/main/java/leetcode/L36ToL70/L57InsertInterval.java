package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L57InsertInterval {

	/**
	 * 
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 * 
	 * Example 1:
	 * 
	 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5] Output:
	 * [[1,5],[6,9]] Example 2:
	 * 
	 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval =
	 * [4,8] Output: [[1,2],[3,10],[12,16]] Explanation: Because the new
	 * interval [4,8] overlaps with [3,5],[6,7],[8,10].
	 * 
	 * 
	 */

	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<>();
		if (intervals == null || intervals.isEmpty()) {
			result.add(newInterval);
			return result;
		}
		int flag = -1;
		for (int i = 0; i < intervals.size(); i++) {
			Interval tmp = intervals.get(i);
			if( newInterval.start < tmp.start){
				flag = i;
				break;
			}
		}
		if(flag >= 0){
			intervals.add(flag, newInterval);
		}else{
			intervals.add(newInterval);
		}
		
		
		
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		
		for (int i = 1; i < intervals.size(); i++) {
			int tmps = intervals.get(i).start;
			int tmpe = intervals.get(i).end;
			if (tmps >= start && tmps <= end) {
				if (tmpe >= end) {
					end = tmpe;
				}
			} else if (tmps > end) {
				result.add(new Interval(start, end));
				start = tmps;
				end = tmpe;
			} else {
				start = tmps;
				if (tmpe >= end) {
					end = tmpe;
				}
			}
		}
		result.add(new Interval(start, end));
		return result;
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
	
	private static void test3() {
		L57InsertInterval test = new L57InsertInterval();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 5));
		Interval newInterval = new Interval(6, 8);
		
		List<Interval>  reusult = test.insert(intervals, newInterval);
		
		System.out.println(Arrays.toString(reusult.toArray()));
	}
	
	private static void test2() {
		L57InsertInterval test = new L57InsertInterval();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 2));
		intervals.add(new Interval(3, 5));
		intervals.add(new Interval(6,7));
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(12,16));
		Interval newInterval = new Interval(4,8);
		
		List<Interval>  reusult = test.insert(intervals, newInterval);
		
		System.out.println(Arrays.toString(reusult.toArray()));
	}

	private static void test1() {
		L57InsertInterval test = new L57InsertInterval();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1, 3));
		intervals.add(new Interval(6, 9));
		Interval newInterval = new Interval(2, 5);
		
		List<Interval>  reusult = test.insert(intervals, newInterval);
		
		System.out.println(Arrays.toString(reusult.toArray()));
	}

}
