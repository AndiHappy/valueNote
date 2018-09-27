package leetcode;

import java.util.ArrayList;
import java.util.Collection;

import leetcode.base.SignalLikedList;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:51:21
 */
public class L26RemoveDuplicatesfromSortedArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<Integer> nums = new ArrayList<Integer>();
	    nums.add(12);
	    nums.add(13);
	    nums.add(14);
	    nums.add(15);
	    nums.add(16);
	    nums.add(17);
	    nums.add(18);
	    nums.add(19);
	    nums.add(27);
	    nums.add(37);
	    nums.add(47);
	    nums.add(57);
	    SignalLikedList<Integer> cycle = new SignalLikedList<Integer>(nums);
	    System.out.println(cycle.toArray().toString());

	}

}
