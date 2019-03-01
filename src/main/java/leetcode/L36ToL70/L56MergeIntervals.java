package leetcode.L36ToL70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author zhailz 2019年2月27日 上午11:23:35
 */
public class L56MergeIntervals {

	/**
	 * 
	 * Given a collection of intervals, merge all overlapping intervals.
	 * 
	 * Example 1:
	 * 
	 * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
	 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into
	 * [1,6]. Example 2:
	 * 
	 * Input: [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and
	 * [4,5] are considered overlapping.
	 * 
	 */

	public List<Interval> merge2(List<Interval> intervals) {
		if (intervals.size() == 0)
			return intervals;

		PriorityQueue<Interval> pq = new PriorityQueue<Interval>(new MyComparator());

		for (Interval i : intervals) {
			pq.add(i);
		}

		List<Interval> ans = new ArrayList<Interval>();
		ans.add(pq.poll());

		while (pq.size() != 0) {
			Interval temp = pq.poll();
			Interval compare = ans.get(ans.size() - 1);

			if (compare.end >= temp.start) {
				ans.remove(ans.size() - 1);
				ans.add(new Interval(compare.start, Math.max(compare.end, temp.end)));
			} else {
				ans.add(new Interval(temp.start, temp.end));
			}
		}

		return ans;
	}

	public class MyComparator implements Comparator<Interval> {
		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
	}

	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1)
			return intervals;
		Comparator<Interval> c = new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		};
		Collections.sort(intervals, c);
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;

		List<Interval> result = new ArrayList<>();
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
		List<Interval> v = new ArrayList<>();
		v.add(new Interval(1, 4));
		v.add(new Interval(0, 4));
		L56MergeIntervals test = new L56MergeIntervals();
		List<Interval> result = test.merge(v);
		System.out.println(Arrays.toString(result.toArray()));
	}
	
	private static void test2() {
		List<Interval> v = new ArrayList<>();
		v.add(new Interval(1, 4));
		v.add(new Interval(4, 5));
		L56MergeIntervals test = new L56MergeIntervals();
		List<Interval> result = test.merge(v);
		System.out.println(Arrays.toString(result.toArray()));
	}

	private static void test1() {
		List<Interval> v = new ArrayList<>();
		v.add(new Interval(1, 3));
		v.add(new Interval(2, 6));
		v.add(new Interval(8, 10));
		v.add(new Interval(15, 18));
		L56MergeIntervals test = new L56MergeIntervals();
		List<Interval> result = test.merge(v);
		System.out.println(Arrays.toString(result.toArray()));
	}

}

/**
 * Definition for an interval. public class Interval { int start; int end;
 * Interval() { start = 0; end = 0; } Interval(int s, int e) { start = s; end =
 * e; } }
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

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
