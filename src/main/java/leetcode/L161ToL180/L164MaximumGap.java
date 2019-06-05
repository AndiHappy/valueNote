package leetcode.L161ToL180;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L164MaximumGap {

	/**

 Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.


	 */
	
/*	
	public class Solution {
		public int maximumGap(int[] nums) {
		    if (nums == null || nums.length < 2) {
		        return 0;
		    }
		    
		    // m is the maximal number in nums
		    int m = nums[0];
		    for (int i = 1; i < nums.length; i++) {
		        m = Math.max(m, nums[i]);
		    }
		    
		    int exp = 1; // 1, 10, 100, 1000 ...
		    int R = 10; // 10 digits

		    int[] aux = new int[nums.length];
		    
		    while (m / exp > 0) { // Go through all digits from LSB to MSB
		        int[] count = new int[R];
		        
		        for (int i = 0; i < nums.length; i++) {
		            count[(nums[i] / exp) % 10]++;
		        }
		        
		        for (int i = 1; i < count.length; i++) {
		            count[i] += count[i - 1];
		        }
		        
		        for (int i = nums.length - 1; i >= 0; i--) {
		            aux[--count[(nums[i] / exp) % 10]] = nums[i];
		        }
		        
		        for (int i = 0; i < nums.length; i++) {
		            nums[i] = aux[i];
		        }
		        exp *= 10;
		    }
		    
		    int max = 0;
		    for (int i = 1; i < aux.length; i++) {
		        max = Math.max(max, aux[i] - aux[i - 1]);
		    }
		     
		    return max;
		}
		}

		The first step is to find the maximum value in nums array, it will
		be the threshold to end while loop.
		Then use the radix sort algorithm to sort based on each digit from Least Significant Bit
		(LSB) to Most Significant Bit (MSB), that's exactly what's showing
		in the link.
		(nums[i] / exp) % 10 is used to get the digit, for each digit, basically the digit itself serves as the index to
		access the count array. Count array stores the index to access aux
		array which stores the numbers after sorting based on the current
		digit.
		Finally, find the maximum gap from sorted array.
		Time and space complexities are both O(n). (Actually time is O(10n) at worst case for Integer.MAX_VALUE 2147483647)
	*/
	
	/**
	 * 桶排序Bucket Sort
	 * 首先找出数组的最大值和最小值，然后要确定每个桶的容量，
	 * (最大值 - 最小值) / 个数 + 1，在确定桶的个数，即为(最大值 - 最小值) / 桶的容量 + 1，

	 * 然后需要在每个桶中找出局部最大值和最小值，
	 * 而最大间距的两个数不会在同一个桶中，
	 * 而是一个桶的最小值和另一个桶的最大值之间的间距
	 * */
	public int maximumGap(int[] num) {
    if (num == null || num.length < 2)
        return 0;
    
    // get the max and min value of the array
    // 最大值和最小值
    int min = num[0];
    int max = num[0];
    for (int i:num) {
        min = Math.min(min, i);
        max = Math.max(max, i);
    }
    
    // 每一个桶里面的数值的范围
    // the minimum possibale gap, ceiling of the integer division
    int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
    
    // 最小值的桶
    int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
    
    //最大值的桶
    int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
    
    //最小值的数组填的是最大值
    Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
    
    //最大值的数组填填的是最小值
    Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
    
    // put numbers into buckets
    // 把数组填进去，按照gap的间隔,bucketsMIN 维护的是在gap的范围内的最小的值，bucketsMAX维护的是在gap的范围内的最大的值
    for (int i:num) {
        if (i == min || i == max)
            continue;
        int idx = (i - min) / gap; // index of the right position in the buckets
        bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
        
        bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
    }
    
    // scan the buckets for the max gap
    //我们遍历数组，对每个桶计算其最大值和最小值，最后遍历一遍桶，因为最大的gap不会出现在桶内部，
   //而在桶之间，所以获得“后一个非空桶的最小值减前一个非空桶的最大值的最大值”，
//    即为我们的题目所求。该思路的时间复杂度和空间复杂度都是O(n)。
    int maxGap = Integer.MIN_VALUE;
    int previous = min;
    for (int i = 0; i < num.length - 1; i++) {
        if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
            // empty bucket
            continue;
        // min value minus the previous value is the current gap
        maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
        // update previous bucket value
        previous = bucketsMAX[i];
    }
    maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
    return maxGap;
}
		public static void main(String[] args) {
		
			L164MaximumGap test = new L164MaximumGap();
			test.maximumGap(new int[] {1,1,1,1,1});
	}

}
