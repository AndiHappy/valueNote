package leetcode.L0ToL35;

import baseAlg.base.LinkedNode;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:09:35
 */
public class L19RemoveNthNodeFromEndofList {
	
	public ListNode removeNthFromEnd_copy(ListNode head, int n) {
		ListNode tmp = new ListNode(-1);
		ListNode first = tmp;ListNode second = tmp;
		tmp.next = head;
		for(int i = 1 ; i<=n+1;i++) {
			first = first.next;
		}
		while(first != null) {
			first = first.next;
			second = second.next;
		}
		
		second.next = second.next.next;
		
		return tmp.next;
	}

public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;
    
    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}
	
	public static LinkedNode<String> removeNthNodeFromEndofList(LinkedNode<String> node, int last) throws Exception {

		LinkedNode<String> first = node, second = node;

		while (last > 0 && second != null) {
			second = second.next;
			last--;
		}

		//说明是第一个几点
		if (0 == last && second == null) {
			return first.next;
		}

		while (second.next != null) {
			first = first.next;
			second = second.next;
		}
		LinkedNode<String> tem = first.next.next;
		first.next = tem;
		return node;
	}

}
