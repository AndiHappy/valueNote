package leetcode.L0ToL35;

/**
 * @author zhailz
 *
 * @version 2018年9月27日 下午7:59:43
 * 
 * 主要有L21MergeTwoSortedLists 变化而来，现在Merge的是K个已经排序的list,
 * ①：两两的进行合并，直接的就把这个问题降级
 * ②：直接的循环合并
 */
public class L23MergekSortedLists {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//		  1->4->5,
		//		  1->3->4,
		//		  2->6
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(5);

		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(3);
		head.next.next = new ListNode(4);

		ListNode head2 = new ListNode(2);
		head2.next = new ListNode(6);
		
		ListNode[] lists = new ListNode[3];
		lists[0]=head;
		lists[1]=head1;
		lists[2]=head2;
		
		L23MergekSortedLists test = new L23MergekSortedLists();
		
		ListNode res = test.mergeKListsCirclulation(lists);
		System.out.println(res);
	}

	public ListNode mergeKListsCirclulation(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		if (lists != null && lists.length == 1) {
			return lists[0];
		}

		ListNode head = new ListNode(-1);
		ListNode res =head;
		while (!isEmpty(lists)) {
			head.next = selectMin(lists);
			head = head.next;
		}

		return res.next;
	}

	private ListNode selectMin(ListNode[] lists) {
		int key = 0;
		for (int i = 1; i < lists.length; i++) {
			if(lists[key] == null || (lists[i] != null && lists[i].val < lists[key].val )){
				key = i;
			}
		}
		
		ListNode tmp = lists[key];
		lists[key] = lists[key].next;
		return tmp;
	}

	private boolean isEmpty(ListNode[] lists) {
		if(lists == null || lists.length == 0) return true;
		for (ListNode listNode : lists) {
			if(listNode != null){
				return false;
			}
		}
		return true;
	}

	/**
	 * 第一种方案
	 * */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		if (lists != null && lists.length == 1) {
			return lists[0];
		}
		return mergeKLists1fromAndTo(lists, 0, lists.length - 1);
	}

	private ListNode mergeKLists1fromAndTo(ListNode[] lists, int i, int j) {
		if (i + 1 == j) {
			return mergeKLists1fromAndTo(lists[i], lists[j]);
		} else if (i == j) {
			return lists[i];
		} else {
			int midle = (i + j) / 2;
			ListNode h1 = mergeKLists1fromAndTo(lists, i, midle);
			ListNode h2 = mergeKLists1fromAndTo(lists, midle + 1, j);
			return mergeKLists1fromAndTo(h1, h2);
		}
	}

	// 操作的动作的，操作的是新建的节点
	public ListNode mergeKLists1fromAndTo(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(0);
		ListNode first = l3;
		while (l1 != null || l2 != null) {
			if (l1 != null && l2 != null) {
				if (l1.val < l2.val) {
					l3.next = l1;
					l1 = l1.next;
				} else {
					l3.next = l2;
					l2 = l2.next;
				}
			} else if (l1 == null && l2 != null) {
				l3.next = l2;
				l2 = l2.next;
			} else if (l2 == null && l1 != null) {
				l3.next = l1;
				l1 = l1.next;
			}
			l3 = l3.next;
		}
		return first.next;
	}

}
