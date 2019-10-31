package leetcode.L;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class L324WiggleSortII {

 /**

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

*/
 
 /**
  * 我们采用控制窗口的形式来进行判断，可以试一下，或者在脑中过一下，采用这种办法的依据是：
  * 
  * You may assume all input has valid answer.
  * 采用这种方式，编码了一段时间之后，没有完成，再次梳理
  * */
 public void wiggleSort_wrong(int[] nums) {
  int from = 0, to = 0;
  for (int i = 0; i < nums.length-1; i++) {
   if(i % 2 == 0) {
    // 这个时候应该是nums[i] < nums[i+1]
    // 如果开始的位置
    if(nums[i] < nums[i+1] && from ==i) {
     from = i+1;
     to = i+1;
    }else {
//     nums[i] >  nums[i+1]
     
    }
   }else {
    if(nums[i] > nums[i+1]) {
     
    }
   }
  }
 }
 
 public void wiggleSort(int[] nums) {
  int n = nums.length, m = (n + 1) >> 1;
  int[] copy = Arrays.copyOf(nums, n);
  Arrays.sort(copy);
  // 首先是从中间位置开始，然后开始设置
  for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
  // 从后面开始，然后开始设置，
  for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
}

public void wiggleSort_On(int[] nums) {
  // 来源于：https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/
  //划定好分区以后
  int median=findKthLargest(nums,(nums.length+1)/2);

  int odd=1;
  int even=nums.length%2==0?nums.length-2:nums.length-1;
  // 申请了O(n)的空间，放置数据
  int[] tmpArr=new int[nums.length];
  // 把大于中间值的数量全部放在了奇数位，把小于中间值的全部放在了偶数位。
  for(int i=0;i<nums.length;i++){
      if(nums[i]>median){
          tmpArr[odd]=nums[i];
          odd+=2;
          continue;
      }
      if(nums[i]<median){
          tmpArr[even]=nums[i];
          even-=2;
          continue;
      }
  }
//然后在放置中间值
  while(odd<nums.length){
      tmpArr[odd]=median;
      odd+=2;
  }
  while(even>=0){
      tmpArr[even]=median;
      even-=2;
  }
  for(int i=0;i<nums.length;i++){
      nums[i]=tmpArr[i];
  }
}
 
 
 private int findKthLargest(int[] nums, int i) {
 // TODO Auto-generated method stub
 return 0;
}

 public static void main(String[] args) {
  L324WiggleSortII test = new L324WiggleSortII();
  int[] value = new int[] {1,2,3,4,5,6,7};
  System.out.println(Arrays.toString(value));
  test.wiggleSort(value);
  System.out.println(Arrays.toString(value));

 }

}
