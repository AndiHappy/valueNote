package leetcode.L71ToL100;

/**
 * @author guizhai
 *
 */
public class L82RemoveDuplicatesfromSortedListII {

	/**
	 
	Given a sorted linked list, delete all nodes that have duplicate numbers, 
	leaving only distinct numbers from the original list.
	
	Example 1:
	
	Input: 1->2->3->3->4->4->5
	Output: 1->2->5
	Example 2:
	
	Input: 1->1->1->2->3
	Output: 2->3
	 */



	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) return head;
		//需要返回head节点的时候，一般的方法是新建一个节点，然后指向head节点，最主要的操作是新建一个节点。
		
    ListNode node = head, result = new ListNode(0), cur = result;
    int last;
    while (node != null) {
    	//如果是不相等的接点
        if (node.next == null || node.val != node.next.val) {
            cur = (cur.next = node);
            node = node.next;
            cur.next = null;
        } else {
        	//如果是相等的节点，然后寻找到不等于下一个节点node节点。
            last = node.val;
            node = node.next;
            while (node != null && node.val == last) node = node.next;
        }
    }
    return result.next;
	}
	
	public ListNode deleteDuplicates_error(ListNode head) {
    if(head == null || head.next == null) return head;
    
    ListNode node = head; ListNode cur = new ListNode(0),result = cur;
    int last;
    
    while(node != null){
        if(node.next == null || node.val != node.next.val){
            cur = (cur.next=node);
            node = node.next;
            cur.next=null;
        }else{
            last = node.val;
            node = node.next;
            while(node != null && node.val == last) node = node.next;       
        }
    }
    return result.next;
}

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		node.next=new ListNode(1);
//		node.next.next=new ListNode(3);
//		node.next.next.next=new ListNode(4);
//		node.next.next.next.next=new ListNode(5);
		
		L82RemoveDuplicatesfromSortedListII test = new L82RemoveDuplicatesfromSortedListII();
		test.deleteDuplicates_error(node);
		
	}
}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int x) {
		val = x;
	}
}
