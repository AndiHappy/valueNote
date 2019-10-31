package jdk;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author guizhai
 *
 */
public class PriorityQueueTest {
 
 class MedianFinder {
  // max queue is always larger or equal to min queue
  PriorityQueue<Integer> min = new PriorityQueue();
  PriorityQueue<Integer> max = new PriorityQueue(1000, Collections.reverseOrder());
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
   * 
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
  * @param args
  */
 public static void main(String[] args) {
  
//  PriorityQueueTest test = new PriorityQueueTest();
//  test.test();
  
  PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
  /**
   * 
Inserts the specified element into this priority queue.

Overrides: add(...) in AbstractQueue
Parameters:
e the element to add
Returns:
true (as specified by Collection.add)
Throws:
ClassCastException - if the specified element cannot be compared with elements currently in this priority queue according to the priority queue's ordering
NullPointerException - if the specified element is null

   * */
  queue.add(1);
  queue.add(3);
  queue.add(5);
  queue.add(6);
  queue.add(7);
  queue.add(2);
  System.out.println(queue.toString());
 /**
Inserts the specified element into this priority queue.

Specified by: offer(...) in Queue
Parameters:
e the element to add
Returns:
true (as specified by Queue.offer)
Throws:
ClassCastException - if the specified element cannot be compared with elements currently in this priority queue according to the priority queue's ordering
NullPointerException - if the specified element is null
  * */
  queue.offer(9);
  
  System.out.println(queue.peek());
 }


 

}
