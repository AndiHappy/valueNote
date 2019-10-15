package leetcode.L36ToL70;

import java.util.Arrays;

/**
 * @author zhailz
 * 2018年11月22日 下午10:08:56
 */
public class L42TrappingRainWater {

 /**
 
 Given n non-negative integers representing an elevation baseAlg.map where 
 the width of each bar is 1, compute how much water it is able to trap after raining.
 
 The above elevation baseAlg.map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
 In this case, 6 units of rain water (blue section) are being trapped.
 Thanks Marcos for contributing this image!
 
 Example:
 
 Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 * 
 * */

 /**
 * 错误的一种思路：寻找对勾样式的数据格式，但是这样的话，会不能够处理，大对勾里面包含小对勾的样式，遂放弃。 
 * */
 public int trap_error(int[] height) {
  if (height == null || height.length <= 2)
   return 0;
  int result = 0;
  //上升的
  boolean rising = height[0] < height[1];
  boolean down = !rising;
  int from = 0;
  int end = 0;
  //0,1,0,2,1,0,1,3,2,1,2,1
  //2，0，2
  for (int i = 1; i < height.length; i++) {
   int valuei = height[i];
   int valuei_1 = height[i - 1];
   if (valuei > valuei_1 && rising) { //继续的上升趋势
    end = end + 1;
   } else if (valuei > valuei_1 && down) { //下降趋势被断
    end = end + 1;
    down = false;
    rising = true;
   } else if (valuei < valuei_1 && rising) {//上升的趋势被打断
    result = result + caucle(from, i - 1, height);//需要计算是否能够剩水
    from = i - 1;//更新from和end
    rising = false;//更新趋势
    down = true;
   } else if (valuei < valuei_1 && down) {
    end = end + 1;
   }

  }
  return result;
 }

 //[0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 public int caucle(int from, int end, int[] height) {
  int small = height[from];
  int smallindex = from;
  for (int i = from + 1; i <= end; i++) {
   if (height[i] < small) {
    small = height[i];
    smallindex = i;
   }
  }

  int res = 0;
  int key = height[from] < height[end] ? height[from] : height[end];
  int i = smallindex;
  for (; i >= from; i--) {
   if (height[i] >= key) {
    break;
   }
  }

  int j = smallindex;
  for (; j <= end; j++) {
   if (height[j] >= key) {
    break;
   }
  }

  for (int ii = i; ii < j; ii++) {
   if (height[ii] <= height[from]) {
    res = res + height[ii] - small;
   } else {
    res = res + height[from] - small;
   }
  }

  return res;
 }

 /**
 * 这种解法主要是，理解：从左边设置依次的设置最大的值。从右边一次的设置最大值的意义是什么？
 * 恩，水灌满以后的状态！！！！
 * 这个非常的重要，从后面开始考虑，考虑水灌满了以后是一个什么情况
 * 
 * 如果标识水管满以后的数组呢，那就是左右的两个支柱的数准,就有了下面的程序
 * 
 * */
 public int trap(int[] height) {

  int n = height.length;

  int[] left = new int[n];
  int[] right = new int[n];

  int leftMax = Integer.MIN_VALUE;
  int rightMax = Integer.MIN_VALUE;
  // construct left max (including self) array
  for (int i = 0; i < n; i++) {
   if (height[i] > leftMax) {
    leftMax = height[i];
   }
   left[i] = leftMax;
  }

  // construct right max (including self) array 
  for (int i = n - 1; i >= 0; i--) {
   if (height[i] > rightMax) {
    rightMax = height[i];
   }
   right[i] = rightMax;
  }

  int water = 0;

  // water trapped  
  for (int i = 0; i < n; i++) {

   int min = Math.min(left[i], right[i]);

   water += (min - height[i]);
  }

  return water;
 }

 public static void main(String[] args) {
  L42TrappingRainWater water = new L42TrappingRainWater();
  int[] height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
  //		System.out.println(water.caucle(1, 3, height));
  //		System.out.println(water.caucle(3, 7, height));
  //		System.out.println(water.caucle(7, 10, height));
  //		System.out.println(water.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
  height = new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
  System.out.println(Arrays.toString(height));
  System.out.println(water.trap(height));
 }

}
