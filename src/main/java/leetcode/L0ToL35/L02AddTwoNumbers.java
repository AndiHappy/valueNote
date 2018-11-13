package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 201511132:41:50
 * 
 *       TagsLinkedList,level1
 *       链表操作，不进位
 * 
 */
public class L02AddTwoNumbers {

	public static void main(String[] args) {
		L02AddTwoNumbers num = new L02AddTwoNumbers();
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(8);
		l1.next.next = new ListNode(9);
		System.out.println(l1.toString());

		ListNode l2 = new ListNode(1);
		 l2.next = new ListNode(6);
		 l2.next.next = new ListNode(4);
		System.out.println(l2.toString());
		ListNode l3 = num.addTwoNumbers(l1, l2);
		System.out.println(l3);

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
