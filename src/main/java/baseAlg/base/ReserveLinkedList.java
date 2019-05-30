package baseAlg.base;

public class ReserveLinkedList {

	/**
	 * 反转的逻辑
	 * */
	public static Node<String> reverseOneGroup(Node<String> pre, Node<String> end) {
		Node<String> tmp = pre;
		Node<String> tmpnext = pre.next;
		pre.next = null;
		while(tmpnext != null){
			Node<String> tmpnextnext = tmpnext.next;
			tmpnext.next = tmp;
			tmp = tmpnext;
			tmpnext = tmpnextnext;
		}
		return tmp;
	}

	public static void main(String[] args) {
		Node<String> i = new Node<String>(null, "a", null);
		i.next = new Node<String>(null, "b", null);
		Node<String> j = new Node<String>(null, "c", null);
		i.next.next = j;
		j.next = new Node<String>(null, "d", null);
		Node<String> e = new Node<String>(null, "e", null);
		j.next.next = e;
		Node<String> tmp = reverseOneGroup(i, e);
		System.out.println(tmp.item + " " + tmp.next.item + tmp.next.next.item);

	}

}
