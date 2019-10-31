package leetcode.L;

/**
 * @author guizhai
 *
 */
public class L328OddEvenLinkedList {

 class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   @Override
    public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(val);
    ListNode clone = next;
     while(clone != null) {
      builder.append("->");
      builder.append(clone.val);
      clone = clone.next;
     }
     return builder.toString();
    }
 }
 
 /**
Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. 
The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...

  */

 /**
  * 完成自己AC出，关键在于什么地方？为什么不能一遍的梳理出来？
  * */
 public ListNode oddEvenList(ListNode head) {
  if(head == null || head.next == null) return head;
  
  ListNode cur = head;
  ListNode curafter = head.next;
  ListNode next = head.next.next;
  
  while(next!= null) {
   curafter.next = next.next;
   next.next = cur.next;
   curafter = curafter.next;
   cur.next = next;
   cur = cur.next;
   if(curafter != null) {
    next = curafter.next;
   }else {
    next = null;
   }
  }
  return head;
 }
 public static void main(String[] args) {
  L328OddEvenLinkedList test = new L328OddEvenLinkedList();
  test.test();

 }
 private void test() {
  ListNode head1 = new ListNode(1);
  ListNode head2 = new ListNode(2);
  ListNode head3 = new ListNode(3);
  ListNode head4 = new ListNode(4);
  ListNode head5 = new ListNode(5);
  ListNode head6 = new ListNode(6);
  ListNode head7 = new ListNode(7);

  head1.next=head2;head2.next=head3;head3.next=head4;head4.next=head5;head5.next=head6;
  head6.next=head7;
  
  head1 = oddEvenList(head1);
  System.out.println(head1);
 }

}
