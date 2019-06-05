package leetcode.L161ToL180;

public class L162FindPeakElement {
	
	/**
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
	 * */
	
	
	/**
	 
I find it useful to reason about binary search problems using invariants. While there are many solutions posted here, neither of them provide (in my opinion) a good explanation about why they work. I just spent some time thinking about this and I thought it might be a good idea to share my thoughts.

Assume we initialize left = 0, right = nums.length - 1. The invariant I'm using is the following:

nums[left - 1] < nums[left] && nums[right] > nums[right + 1]

That basically means that in the current interval we're looking, [left, right] the function started increasing to left and will eventually decrease at right. The behavior between [left, right] falls into the following 3 categories:

nums[left] > nums[left + 1]. From the invariant, nums[left - 1] < nums[left] => left is a peak

The function is increasing from left to right i.e. nums[left] < nums[left + 1] < .. < nums[right - 1] < nums[right]. From the invariant, nums[right] > nums[right + 1] => right is a peak

the function increases for a while and then decreases (in which case the point just before it starts decreasing is a peak) e.g. 2 5 6 3 (6 is the point in question)

As shown, if the invariant above holds, there is at least a peak between [left, right]. Now we need to show 2 things:

I) the invariant is initially true. Since left = 0 and right = nums.length - 1 initially and we know that nums[-1] = nums[nums.length] = -oo, this is obviously true

II) At every step of the loop the invariant gets reestablished. If we consider the code in the loop, we have mid = (left + right) / 2 and the following 2 cases:

a) nums[mid] < nums[mid + 1]. It turns out that the interval [mid + 1, right] respects the invariant (nums[mid] < nums[mid + 1] -> part of the cond + nums[right] > nums[right + 1] -> part of the invariant in the previous loop iteration)

b) nums[mid] > nums[mid + 1]. Similarly, [left, mid] respects the invariant (nums[left - 1] < nums[left] -> part of the invariant in the previous loop iteration and nums[mid] > nums[mid + 1] -> part of the cond)

As a result, the invariant gets reestablished and it will also hold when we exit the loop. In that case we have an interval of length 2 i.e. right = left + 1. If nums[left] > nums[right], using the invariant (nums[left - 1] < nums[left]), we get that left is a peak. Otherwise right is the peak (nums[left] < nums[right] and nums[right] < nums[right + 1] from the invariant).

public int findPeakElement(int[] nums) {
    int N = nums.length;
    if (N == 1) {
        return 0;
    }
   
    int left = 0, right = N - 1;
    while (right - left > 1) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return (left == N - 1 || nums[left] > nums[left + 1]) ? left : right;
} 
	 * 
	 * */
	
	
	/**
	 * 图示：
	  5   5                           5
        / \ / \                         / \
       4   4   4                       4  -∞
      /         \                     /
     3           3           3       3
    /             \         / \     /
   2               2       2   2   2
  /                 \     /     \ /
-∞                   1   1       1
                      \ /
                       0                      
   0 1 2 3 4 5 6 7 8 910111213141516171819 indices
   2,3,4,5,4,5,4,3,2,1,0,1,2,3,2,1,2,3,4,5 (20 nums)
   l                 m                   r l=0, m=9, r=19
   l       m         r                     l=0, m=4, r= 9
             l   m   r                     l=5, m=7, r= 9
             l>m r                         l=5, m=6, r= 7
  return n[l] > n[l + 1])? l : r 
	 * */
	
	public int findPeakElement(int[] nums) {
    int N = nums.length;
    if (N == 1) {
        return 0;
    }
   
    int left = 0, right = N - 1;
    while (right - left > 1) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return (left == N - 1 || nums[left] > nums[left + 1]) ? left : right;
} 
}

