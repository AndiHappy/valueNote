package leetcode.L91ToL120;

/**
 * @author guizhai
 *
 */
public class L92ReverseLinkedListII {

	/**
	
	Reverse a linked list from position m to n. Do it in one-pass.
	
	Note: 1 ≤ m ≤ n ≤ length of list.
	
	Example:
	
	Input: 1->2->3->4->5->NULL, m = 2, n = 4
	Output: 1->4->3->2->5->NULL
	
	
	 */

	 public ListNode reverseBetween(ListNode head, int m, int n) {
     // 特殊情况处理
		 if (head == null || head.next == null) return head;
     if (n == m)  return head;
     
     
     //寻找m对应的节点。
     int i = 1;
     ListNode prev = null;
     ListNode current = head;
     while (current != null && i < m) {            
         prev = current;
         current = current.next;
         i++;
     }
     
     // current真好是m数的node，pre为前一个节点

     ListNode tmp = null;
     ListNode tail1 = prev;
     ListNode tail2 = current;

     // 翻转链表节点
     prev = current;
     current = current.next;
     i = 1;
     while (current != null && i <= n - m) {
         tmp = current.next;
         current.next = prev;
         prev = current;
         current = tmp;
         i++;
     }
     
     //处理断掉的部分
     if (tail1 != null ) {
         tail1.next = prev;
     } else {
    	 //特殊情况的考虑
         head = prev;
     }
     tail2.next = tmp;
     
     return head;
     
 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}