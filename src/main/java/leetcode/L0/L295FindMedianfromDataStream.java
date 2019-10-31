package leetcode.L0;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author guizhai
 *
 */
public class L295FindMedianfromDataStream {

 /**

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
  */
 
 class MedianFinder {
  // max queue is always larger or equal to min queue
  PriorityQueue<Integer> min = new PriorityQueue<Integer>();
  PriorityQueue<Integer> max = new PriorityQueue<Integer>(1000, Collections.reverseOrder());
  // Adds a number into the data structure.
  public void addNum(int num) {
      max.offer(num);
      min.offer(max.poll());
      if (max.size() < min.size()){
          max.offer(min.poll());
      }
  }

  /**
   * 首先是理解中位数的概念：中位数
   *        如果是偶数: 1，2，3，4 那就是 (a[n/2] + a[n/2+1]) /2
   *        如果是奇数：1，2，3 那就是 a[(n+1)/2] 的值
   * 这样话，我们首先要对输入的值进行排序，然后需要把队列拆成两节，分别记录这两节的最大值和最小值
   * */
  // Returns the median of current data stream
  public double findMedian() {
      if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
      else return max.peek();
  }
}
 
 private void test() {
  MedianFinder value = new MedianFinder();
  value.addNum(1);
  value.addNum(2);
  value.addNum(3);
  value.addNum(100);
  System.out.println(value.findMedian());
  
 }

/**
* Your MedianFinder object will be instantiated and called as such:
* MedianFinder obj = new MedianFinder();
* obj.addNum(num);
* double param_2 = obj.findMedian();
*/
 
 public static void main(String[] args) {
  // TODO Auto-generated method stub

 }

}
