package leetcode.L0ToL35;

/**
 * @author zhailz
 *
 * @version 2018年9月27日 下午8:01:30
 */
public class L24SwapNodesinPairs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return head;
		ListNode pre = new ListNode(0);
		pre.next = head;
		int value = 0;
		while (head != null && head.next != null) {
			if (head.next != null) {
				value = head.val;
				head.val = head.next.val;
				head.next.val = value;
			}
			head = head.next.next;
		}
		return pre.next;
	}

}
