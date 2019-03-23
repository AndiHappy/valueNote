package leetcode.L36ToL70;

public class L61RotateList {

	/***
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL 
	 * */
	
	public ListNode rotateRight(ListNode head, int n) {
	    if (head==null||head.next==null) return head;
	    ListNode dummy=new ListNode(0);
	    
	    dummy.next=head;
	    
	    ListNode fast=dummy,slow=dummy;

	    int i;
	    for (i=0;fast.next!=null;i++)//Get the total length 
	    	fast=fast.next;
	    
	    for (int j=i-n%i;j>0;j--) //Get the i-n%i th node
	    	slow=slow.next;
	    
	    fast.next=dummy.next; //Do the rotation, 原来的尾节点指向原来的头结点
	    dummy.next=slow.next; // 变换头结点，设置dummy的next一直指向头结点
	    slow.next=null; // 完成头结点变换以后，成为尾节点
	    
	    return dummy.next;
	}


public static void main(String[] args) {
	ListNode head = new ListNode(1);
	head.next = new ListNode(2);
	head.next.next = new ListNode(3);
	head.next.next.next = new ListNode(4);
	head.next.next.next.next= new ListNode(5);
	
	L61RotateList test = new L61RotateList();
	test.rotateRight(head, 2);
}
}
class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
