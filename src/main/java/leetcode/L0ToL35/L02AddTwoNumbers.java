package leetcode.L0ToL35;

/*
 * NOTE:
 * 链表的循环操作，代码的整洁。
 * note: 再循环的过程移动表头的过程中，返回链表的头部的一种方法就是新建一个冗余的结点，
 */
/**
 * @author zhailzh
 * 
 * @Date 201511132:41:50
 * 

You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 * 
 */
public class L02AddTwoNumbers {

	public static void main(String[] args) {
		L02AddTwoNumbers num = new L02AddTwoNumbers();
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(9);
//		l1.next.next = new ListNode(9);
		System.out.println(l1.toString());

		ListNode l2 = new ListNode(1);
//		 l2.next = new ListNode(6);
//		 l2.next.next = new ListNode(4);
		System.out.println(l2.toString());
		ListNode l3 = num.addTwoNumbers_simple(l1, l2);
		System.out.println(l3);

	}
	
	public ListNode addTwoNumbers_simple(ListNode l1, ListNode l2) {
		int carry=0;ListNode current = new ListNode(-1);ListNode head = current;
		while(l1 != null || l2 != null) {
			int d1 = l1 == null?0:l1.val;
			int d2 = l2 == null?0:l2.val;
			int sum = d1+d2+carry;
			current.next = new ListNode(sum%10);
			carry = sum/10;
			current = current.next;
			
			if(l1 != null) l1=l1.next;
			if(l2 != null) l2=l2.next;
		}
		if(carry >0) current.next = new ListNode(carry);
		return head.next;
	}
	

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode sum = null, l3 = null;
		if (l1 != null && l2 != null) {
			int value = l1.val + l2.val;
			int numc = value % 10;
			sum = new ListNode(numc);
			l3 = sum;
		}else if(l1 ==null){
			l3 = sum = l2;
		}else{
			l3 = sum = l1;
		}

		while (l1.next != null && l2.next != null) {
			int tv = (l1.next.val + l2.next.val) % 10;
			ListNode node = new ListNode(tv);
			sum.next = node;
			sum = node;
			l1 = l1.next;
			l2 = l2.next;
		}

		if (l1.next != null && l2.next == null) {
			sum.next = l1.next;
		}

		if (l2.next != null && l1.next == null) {
			while (l2.next != null) {
				sum.next = l2.next;
			}

			return l3;
		}
		return l3;
	}
}
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	
	@Override
	public String toString() {
		String value  = val+"";
		ListNode temp = next;
		while (temp != null) {
			value  = value +" "+ temp.val;
			temp = temp.next;
		}
		return value;
	}
	
	public boolean equals(ListNode obj) {
		// TODO Auto-generated method stub
		return this.val == obj.val;
	}
}
