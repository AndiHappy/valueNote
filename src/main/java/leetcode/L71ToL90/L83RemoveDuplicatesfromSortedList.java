package leetcode.L71ToL90;

/**
 * @author guizhai
 *
 */
public class L83RemoveDuplicatesfromSortedList {

	/**

Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3


	 */
	
	public ListNode deleteDuplicates(ListNode head) {
    if(head == null || head.next == null) return head;
    ListNode node = head;
    ListNode nextnode = head.next;
    ListNode result = new ListNode(-1),res=result;
    while(node != null){
        if(node.next == null || node.val != nextnode.val){
            result = (result.next=node);
        }
            node = node.next;
            if(nextnode != null){
                nextnode= nextnode.next;
            }
    }
    return res.next;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
