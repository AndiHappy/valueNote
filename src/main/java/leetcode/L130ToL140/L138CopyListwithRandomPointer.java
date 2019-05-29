package leetcode.L130ToL140;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guizhai
 *
 */
public class L138CopyListwithRandomPointer {

	/**

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

 

Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 
 Note:

You must return the copy of the given head as a reference to the cloned list.

	 */
	
	/*
//Definition for a Node.

*/
	
	public Node copy(Node head) {
	  if (head == null) return null;
	  
	  Map<Node, Node> map = new HashMap<Node, Node>();
	  
	  // loop 1. copy all the nodes
	  Node node = head;
	  while (node != null) {
	  	Node tmp = new Node();
	  	tmp.val = node.val;
	    map.put(node, tmp);
	    node = node.next;
	  }
	  
	  // loop 2. assign next and random pointers
	  node = head;
	  while (node != null) {
	    map.get(node).next = map.get(node.next);
	    map.get(node).random = map.get(node.random);
	    node = node.next;
	  }
	  
	  return map.get(head);
	}
	
	/**
	 * 
An intuitive solution is to keep a hash table for each node in the list, 
via which we just need to iterate the list in 2 rounds respectively to create nodes 
and assign the values for their random pointers. As a result, the space complexity of this solution is O(N), 
although with a linear time complexity.

Note: if we do not consider the space reversed for the output, 
then we could say that the algorithm does not consume any additional memory during the processing, 
i.e. O(1) space complexity

As an optimised solution, we could reduce the space complexity into constant. The idea is to associate the original node with its copy node in a single linked list. In this way, we don't need extra space to keep track of the new nodes.

The algorithm is composed of the follow three steps which are also 3 iteration rounds.

Iterate the original list and duplicate each node. The duplicate
of each node follows its original immediately.
Iterate the new list and assign the random pointer for each
duplicated node.
Restore the original list and extract the duplicated nodes.
The algorithm is implemented as follows:

public RandomListNode copyRandomList(RandomListNode head) {
  RandomListNode iter = head, next;

  // First round: make copy of each node,
  // and link them together side-by-side in a single list.
  while (iter != null) {
    next = iter.next;

    RandomListNode copy = new RandomListNode(iter.label);
    iter.next = copy;
    copy.next = next;

    iter = next;
  }

  // Second round: assign random pointers for the copy nodes.
  iter = head;
  while (iter != null) {
    if (iter.random != null) {
      iter.next.random = iter.random.next;
    }
    iter = iter.next.next;
  }

  // Third round: restore the original list, and extract the copy list.
  iter = head;
  RandomListNode pseudoHead = new RandomListNode(0);
  RandomListNode copy, copyIter = pseudoHead;

  while (iter != null) {
    next = iter.next.next;

    // extract the copy
    copy = iter.next;
    copyIter.next = copy;
    copyIter = copy;

    // restore the original list
    iter.next = next;

    iter = next;
  }

  return pseudoHead.next;
}
ja 
	 * */

   public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class Node {
  public int val;
  public Node next;
  public Node random;

  public Node() {}

  public Node(int _val,Node _next,Node _random) {
      val = _val;
      next = _next;
      random = _random;
  }
};
