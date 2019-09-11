package leetcode.L0ToL35;

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
//		ListNode tmp = reverseKGroup(head, 2);
		ListNode[] tmp1 =  reverseKGroupHelper(head, 3);
		System.out.println(tmp1);
	}

	public ListNode reverseKGroup_copy(ListNode head, int k) {
    int n = 0;
    for (ListNode i = head; i != null; n++, i = i.next);
    
    ListNode dmy = new ListNode(0);
    dmy.next = head;
//    翻转的实在让人感慨，看着简单，自己去写就是写的没有那么好
    for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
    	
        for (int i = 1; i < k; i++) {
            ListNode next = tail.next.next;
            tail.next.next = prev.next;
            prev.next = tail.next;
            tail.next = next;
        }
        
        prev = tail;
        tail = tail.next;
    }
    return dmy.next;
}


	public static ListNode reverseKGroup(ListNode head, int k) {
		
		ListNode returnVal = null;
		ListNode prevToSet = null;
		int count = count(head);
		if (k > count) return head;

		ListNode result = head;
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
	 * @param head
	 * @param count
	 * @return
	 */
	private static int count(ListNode head) {
		int count = 0;
		ListNode result = head;
		while (result != null) {
			result = result.next;
			++count;
		}
		return count;
	}
	
	/**
	 * 一段的反转的过程，具体的逻辑可以描述为：把元素轮流的插入链表的头部
	 * */
	public static ListNode[] reverseKGroupHelper(ListNode head, int limitk) {
		ListNode pre = head;
		ListNode current = head;
		//当前元素的下一个元素
		ListNode next = null;
		int count = 1;
		while (current.next != null && count < limitk) {
			//下一个元素
			next = current.next;
			//把当前元素的下一个元素指向下一个元素的next，相当于是跳过了next所指向的元素
			current.next = next.next;
			//然后把next指向的元素的下一个元素设置为前一个元素，完成把头元素的后面的元素挪到前面的位置
			next.next = pre;
			pre = next;
			++count;
		}
		return new ListNode[] { pre, current };
	}
	
	public static ListNode reverseHeadAfterToHead(ListNode head, int limitk) {
		ListNode pre = head;
		ListNode next = head.next;
		head.next = next.next;
		pre.next = head;
		return pre;
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
