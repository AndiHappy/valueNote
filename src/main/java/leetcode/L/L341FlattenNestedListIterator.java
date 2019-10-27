package leetcode.L;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L341FlattenNestedListIterator {

 /**
 Given a nested list of integers, implement an iterator to flatten it.
 
 Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 
 Example 1:
 
 Input: [[1,1],2,[1,1]]
 Output: [1,1,2,1,1]
 Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
 Example 2:
 
 Input: [1,[4,[6]]]
 Output: [1,4,6]
 Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
             
  */

 /**
  * // This is the interface that allows for creating nested lists.
  * // You should not implement it, or speculate about its implementation
  * public interface NestedInteger {
  *
  *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
  *     public boolean isInteger();
  *
  *     // @return the single integer that this NestedInteger holds, if it holds a single integer
  *     // Return null if this NestedInteger holds a nested list
  *     public Integer getInteger();
  *
  *     // @return the nested list that this NestedInteger holds, if it holds a nested list
  *     // Return null if this NestedInteger holds a single integer
  *     public List<NestedInteger> getList();
  * }
  */

 interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
 }

 class NestedIterator implements Iterator<Integer> {

  public NestedIterator(List<NestedInteger> nestedList) {

  }

  public Integer next() {
   return -1;
  }

  public boolean hasNext() {
   return false;
  }
 }

 /**
  * 
I don't understand why there are so many ppl upvote this solution, but this solution is wrong.
Typical iterator should succeed as well in situation

next() call without hasNext() call
multiple hasNext() calls
This solution basically assume each hasNext() preceed a next() call, which might be the case for leetcode test cases, but not really world iterators.
This solution might cause the interviewee got rejected depends on the interviewer's requirement.

  * */
 class NestedIterator1 implements Iterator<Integer> {
  Stack<NestedInteger> stack = new Stack<>();

  public NestedIterator1(List<NestedInteger> nestedList) {
   for (int i = nestedList.size() - 1; i >= 0; i--) {
    stack.push(nestedList.get(i));
   }
  }

  @Override
  public Integer next() {
   return stack.pop().getInteger();
  }

  @Override
  public boolean hasNext() {
   while (!stack.isEmpty()) {
    NestedInteger curr = stack.peek();
    if (curr.isInteger()) {
     return true;
    }
    stack.pop();
    for (int i = curr.getList().size() - 1; i >= 0; i--) {
     stack.push(curr.getList().get(i));
    }
   }
   return false;
  }
 }

 /**
  * Your NestedIterator object will be instantiated and called as such:
  * NestedIterator i = new NestedIterator(nestedList);
  * while (i.hasNext()) v[f()] = i.next();
  */

 public static void main(String[] args) {
  // TODO Auto-generated method stub

 }
}
