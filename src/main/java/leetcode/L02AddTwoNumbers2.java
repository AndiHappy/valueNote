package leetcode;

/**
 * @author zhailzh
 * 
 * @Date 201511132:41:50
 * 
 *       TagsLinkedList,level1
 * 
 */
public class L02AddTwoNumbers2 {

	public static void main(String[] args) {
		L02AddTwoNumbers2 num = new L02AddTwoNumbers2();
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

	//简练，但是还能够优化，但是循环非常的优雅
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int d1 = l1 == null ? 0 : l1.val;
            int d2 = l2 == null ? 0 : l2.val;
            int sum = d1 + d2 + carry;
            carry = sum >= 10 ? 1 : 0;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) cur.next = new ListNode(1);
        return dummy.next;
    }
}