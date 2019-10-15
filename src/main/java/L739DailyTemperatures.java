import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L739DailyTemperatures {

 /**
  
 Given a list of daily temperatures T, return a list such that, for each day in the input, 
 tells you how many days you would have to wait until a warmer temperature. 
 If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], 
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. 
Each temperature will be an integer in the range [30, 100].
  */
 
 public static void main(String[] args) {
  int[] tmp = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
  L739DailyTemperatures test = new L739DailyTemperatures();
  test.dailyTemperatures_array(tmp);
 }

 /**
  * 非常的巧妙，分析题意可以得出的，我们需要找到index后面的比index大的值，然后再去更新index对应的result值，
  * 这样分析的前提下，我们才能够想到栈这个数据结构。
  * */
 public int[] dailyTemperatures(int[] temperatures) {
  Stack<Integer> stack = new Stack<>();
  int[] ret = new int[temperatures.length];
  for(int i = 0; i < temperatures.length; i++) {
      while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
          int idx = stack.pop();
          ret[idx] = i - idx;
      }
      stack.push(i);
  }
  return ret;
}
 
 
 /**
  * 梳理清楚逻辑之后，我们也能够使用数组的方式，来模拟栈的数据结构，其中最关键的点就在于如果碰到一个比较大的值，怎么
  * 去更新前面的数据，这个需要一个类似于窗口范围的值，这个值就是top
  * 
  * 在遍历的时候，一开始的时候top为-1，也可以为0只不过是为了计算的方便。
  * 然后如果index值，小于前面的值，这个之后，top++，说明窗口在增大
  * 如果index值，大于前面的值，那么我们就根据top的值，进行向前的更新，一直到top设置为-1
  * 我们在计算的时候，我们知道当前的index值，这个是for循环中的参数，还需要知道top对应的索引，这个时候需要一个
  * 临时的数组，存储 数组[top] 对应的数组索引
  * 
  * */
 public int[] dailyTemperatures_array(int[] temperatures) {
  int[] stack = new int[temperatures.length];
  int top = -1;
  int[] ret = new int[temperatures.length];
  for(int i = 0; i < temperatures.length; i++) {
      while(top > -1 && temperatures[i] > temperatures[stack[top]]) {
          int idx = stack[top--];
          ret[idx] = i - idx;
      }
      stack[++top] = i;
  }
  return ret;
}

}
