package leetcode;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:11:44
 */
public class L25ReverseNodesinkGroup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1,2,3,4,5,6,7
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		//		
		//		ListNode tmp2 = reverseLinked(head.next,head.next.next.next.next);
		//        System.out.println(tmp2.toString());

		//		ListNode tmp = reverseKGroup(head, 3);
		//		System.out.println(tmp);
	}

	public ListNode[] reverseKGroupHelper(ListNode head, int limitk) {
		ListNode pre = head;
		ListNode result = head;
		ListNode next = null;
		int count = 1;

		while (result.next != null && count < limitk) {
			next = result.next;
			result.next = next.next;

			next.next = pre;
			pre = next;
			++count;
		}

		return new ListNode[] { pre, result };
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode result = head;
		int count = 0;
		ListNode returnVal = null;
		ListNode prevToSet = null;
		while (result != null) {
			result = result.next;
			++count;
		}
		
		if (k > count)
			return head;

		result = head;
		while (result != null && count >= k) {
			ListNode[] tmp = reverseKGroupHelper(result, k);
			result = tmp[1].next;
			count = count - k;
			if (prevToSet != null) {
				prevToSet.next = tmp[0];
				prevToSet = tmp[1];
			}

			if (returnVal == null) {
				returnVal = tmp[0];
				prevToSet = tmp[1];
			}
		}

		return returnVal;
	}

	/**
	 * 1. 堆栈可以很轻易的解决
	 * 2. 交换节点的值
	 * 3. 移动引用，类似指针
	 * */
	private static ListNode reverseLinked(ListNode cuuurent, ListNode next) {
		if (cuuurent == null || next == null) {
			return cuuurent;
		}

		ListNode cur = cuuurent;
		ListNode curnext = cuuurent.next;
		ListNode tmp = cuuurent.next.next;
		cur.next = null;

		while (curnext != null && !curnext.equals(next)) {
			curnext.next = cur;
			cur = curnext;
			curnext = tmp;
			tmp = tmp.next;
		}

		next.next = cur;
		cuuurent.next = tmp;

		return next;
	}

	/**
	 * 每次三个元素变换一次
	 * */
	public static ListNode reverseKGroup_wrong(ListNode head, int k) {//1，2，3，4，5，6，7,8k=3
		ListNode result2 = null;
		ListNode head1 = head;

		while (head != null) {
			int tmp = k;
			while (tmp > 1 && head1 != null) {
				head1 = head1.next;
				tmp--;
			}
			if (tmp > 1) {
				break;
			} else {
				//变换head 和 head1 之间的值
				ListNode next = change(head, head1);
				if (result2 == null) {
					result2 = next;
				}
				head = head.next;

			}
		}
		return result2;
	}

	/**
	 * @param head
	 * @param head1
	 * @return
	 */
	private static ListNode change(ListNode head, ListNode head1) {
		//next为后面的数据，不收影响
		ListNode next = head1.next;

		ListNode cur = head;
		ListNode curnext = head.next;
		cur.next = null;
		ListNode tm = null;
		while (cur != head1) {
			tm = curnext.next;
			curnext.next = cur;
			cur = curnext;
			curnext = tm;
		}
		head.next = next;
		return cur;
	}

	public static ListNode reverseOneGroup(ListNode pre, ListNode end) {
		ListNode tmp = pre;
		ListNode tmpnext = pre.next;
		pre.next = null;
		while (tmpnext != null && tmpnext != end) {
			ListNode tmpnextnext = tmpnext.next;
			tmpnext.next = tmp;
			tmp = tmpnext;
			tmpnext = tmpnextnext;
		}
		return tmp;
	}
}
