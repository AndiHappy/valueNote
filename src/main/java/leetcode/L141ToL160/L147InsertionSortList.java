package leetcode.L141ToL160;

/**
 * @author guizhai
 *
 */
public class L147InsertionSortList {

	/**
	Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains 
only the first element in the list.
With each iteration one element (red) is removed from the input data and 
inserted in-place into the sorted list
 

Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition,
 and growing a sorted output list.
 
At each iteration, insertion sort removes one element from the input data, 
finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
	 */
	
	public ListNode insertionSortList(ListNode head) {
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		
		ListNode cur = head; //the node will be inserted
		
		ListNode pre = helper; //insert node between pre and pre.next 
		
		ListNode next = null; //the next node will be inserted
		
//		4->2->1->3
//		helper => 0
//		cur => 4->2->1->3
		
		//not the end of input list
		while( cur != null ){
			
			next = cur.next;
			
			//find the right place to insert
			//第一次的时候，pre.next == null;
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			
			//insert between pre and pre.next
			/**
			 * 第一句：cur.next = pre.next
			 * cur.next >> next
			 * pre.next >> next
			 *   cur和pre 指向了同一个next
			 * 第二句：pre.next = cur;
			 * pre.next >> pre
			 * 	pre的next 指向了cur
			 * 这也就完成了 cur 元素，插入了pre 和 pre.next 之间。
			 * */
			
			cur.next = pre.next;
			pre.next = cur;
			
			
			/**
			 * pre 依然是helper节点
			 * */
			pre = helper;
			
			/**
			 * cur 一次的遍历
			 * */
			cur = next;
		}
		
		return helper.next;
	}
	
	public static void main(String[] args) {
		L147InsertionSortList test = new L147InsertionSortList();
		
		ListNode head = new ListNode(4);
		head.next = new ListNode(2);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(3);
		test.insertionSortList(head);
	}

}
