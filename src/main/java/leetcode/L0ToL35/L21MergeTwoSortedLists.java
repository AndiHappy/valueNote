package leetcode.L0ToL35;

public class L21MergeTwoSortedLists {
	public static void main(String[] args) {
		//1,2,3,4,5,6,7
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);

		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(3);
		head.next.next = new ListNode(4);

		ListNode node = mergeTwoLists(head, head1);
		System.out.println(node.toString());
	}


	public ListNode mergeTwoLists_copy(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(-1);
		ListNode tmp = head;

		while (l1 != null || l2 != null) {
			int l1v = l1 != null ? l1.val : Integer.MAX_VALUE;
			int l2v = l2 != null ? l2.val : Integer.MAX_VALUE;
			if (l1v < l2v) {
				tmp.next = new ListNode(l1v);
				l1 = l1.next;
			} else {
				tmp.next = new ListNode(l2v);
				l2 = l2.next;
			}
			tmp = tmp.next;
		}
		return head.next;
	}



	/**
	 * very good recursion
	 * */
	public ListNode mergeTwoListsrecursion(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	/**
	 * 循环遍历，关键在于：
	 * ListNode head = new ListNode(-1);
	 * */
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode head = new ListNode(-1);
		ListNode result = head;
		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		if (l1 != null) {
			head.next = l1;
		}
		if (l2 != null) {
			head.next = l2;
		}

		return result.next;
	}
}
