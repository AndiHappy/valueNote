package baseAlg;

/**
 * @author guizhai
 *
 */
// TODO 等待实现linkednode 的基础函数
public class LinkedNodeUtil {

	public static void insertBeforeNode(ListNode pre, ListNode value) {

	}

	public static void insertAfterNode(ListNode pre, ListNode value) {

	}

	public static ListNode mergetLinkedNode(ListNode l1, ListNode l2) {
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}
