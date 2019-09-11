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
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
		
		ListNode dd = 	L24SwapNodesinPairs.swapPairs(head.next,1);
		System.out.println(head);
		System.out.println(dd);
	}

//替换两个节点的值
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;
		
		ListNode pre = new ListNode(0);
		ListNode tmp = null;
		ListNode currentPre = pre;
		
		while (head != null && head.next != null) {
			//next 插入到 head 之前即可 
			tmp = head.next;
		  head.next = tmp.next;
			tmp.next = head;
			currentPre.next = tmp;
			if(pre.next == null) pre.next = tmp;
			currentPre = head;
			head = head.next;
		}
		
		return pre.next;
	}
	
	
	
  /***
   * 此方法的内容是翻转从head开始，后面的K个元素，如果不足K个元素，则不翻转
   * @param head 开始的节点
   * @param k 后续翻转元素的个数
   * */
	public static ListNode swapPairs(ListNode head,int k) {
		ListNode returntmp = new ListNode(-1);
		returntmp.next = head;
		ListNode currentPre = returntmp;
		ListNode tmp = head;
		int i = 0;
		while(tmp.next != null) {
			i++;
			tmp = tmp.next;
		}
		
		if(i<k) return head;
		
		ListNode tmp2 = head;int j = 0;
		
		while(tmp2 != null && tmp2.next != null && j < k) {
			ListNode tmp3 = tmp2.next;
			tmp2.next = tmp3.next;
			tmp3.next = tmp2;
			currentPre.next = tmp3;
			tmp2= tmp2.next;
		}
		
		return returntmp.next;
		
	}
	
	// 替换两个节点的值
	public ListNode swapPairs_changeValue(ListNode head) {
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
