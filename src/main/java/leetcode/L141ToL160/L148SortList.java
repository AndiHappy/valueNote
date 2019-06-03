package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L148SortList {

	/**
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5

	 */
	
public ListNode sortList(ListNode head) {
  if (head == null || head.next == null)
    return head;
      
  // step 1. cut the list to two halves
  
  // 关注prev的设置 4-2-1-3
  ListNode prev = null, slow = head, fast = head;
  
  while (fast != null && fast.next != null) {
    prev = slow;
    slow = slow.next;
    fast = fast.next.next;
  }
  // 执行结束以后 fast 为null，slow为中间值1，pre为slow的前一个节点，然后prev.next = null，就把链条分为了两节
  prev.next = null;
  
  // step 2. sort each half
  ListNode l1 = sortList(head);
  ListNode l2 = sortList(slow);
  
  // step 3. merge l1 and l2
  return merge(l1, l2);
}

ListNode merge(ListNode l1, ListNode l2) {
  ListNode l = new ListNode(0), p = l;
  
  while (l1 != null && l2 != null) {
    if (l1.val < l2.val) {
      p.next = l1;
      l1 = l1.next;
    } else {
      p.next = l2;
      l2 = l2.next;
    }
    p = p.next;
  }
  
  if (l1 != null)
    p.next = l1;
  
  if (l2 != null)
    p.next = l2;
  
  return l.next;
}


	public static void main(String[] args) {
		L148SortList test = new L148SortList();
		ListNode head = new ListNode(4);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(3);
		test.sortList(head);

	}

}
