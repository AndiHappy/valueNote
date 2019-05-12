package leetcode.L71ToL90;

/**
 * @author guizhai
 *
 */
public class L86PartitionList {

	/**
Given a linked list and a value x, partition it such that all nodes less than x come 
before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5

	 */
	
	/**
	 * 直接的截成两节，进行拼接 
	 * */
	
	public static ListNode partition(ListNode head, int x) {
		ListNode leftHead = null, rightHead = null, leftNode= null, rightNode = null;
    while (head != null) {
        if (head.val < x) {
            if (leftNode == null) leftHead = leftNode = head;
            else leftNode = (leftNode.next = head);
        } else {
            if (rightNode == null) rightHead = rightNode = head;
            else rightNode = (rightNode.next = head);
        }
        head = head.next;
    }
    if (rightNode != null) {
        rightNode.next = null;
    }
    if (leftNode != null) {
        leftNode.next = rightHead;
    }
    return leftHead == null ? rightHead : leftHead;
}
	
	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next=new ListNode(1);
		node.next.next=new ListNode(3);
		node.next.next.next=new ListNode(4);
		node.next.next.next.next=new ListNode(5);
		
		L86PartitionList test = new L86PartitionList();
		test.partition(node, 3);

	}

}
