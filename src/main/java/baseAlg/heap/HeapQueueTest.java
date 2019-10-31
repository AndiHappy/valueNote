package baseAlg.heap;

import java.util.Arrays;

/**
 * @author guizhai
 *
 */
public class HeapQueueTest {

 /**
  * @param args
  */
 public static void main(String[] args) {
  HeapQueue<Integer> queue = new HeapQueue<Integer>();
//  3,5,10,7,9,15,11,13,20,12
  queue.add(3);
  queue.add(10);
  queue.add(7);
  queue.add(5);
  queue.add(11);
  queue.add(15);
  queue.add(9);
  queue.add(13);
  queue.add(20);
  queue.add(12);
  queue.add(4);
  
  System.out.println(Arrays.toString(queue.getRealQueue()));
  System.out.println(queue.toString());

 }

}
