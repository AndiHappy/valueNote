package valuenotes;

/**
 * @author guizhai
 *
 */
public class LinkedTestValue {

	/**
	 * @param args
	 */
	class Node{
		public Node(String v) {
			this.v = v;
		}
		String v;
		Node next;
	}
	
	public static void main(String[] args) {
		LinkedTestValue test = new LinkedTestValue();
		test.t1();

	}
	
	private void t1() {
		Node a =new Node("a");
		Node b =new Node("b");
		Node c =new Node("c");
		Node d =new Node("d");
		Node e =new Node("e");
		Node f =new Node("f");
		Node g =new Node("g");
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;
//		Node head = convertLinkdList(b, c);
		Node head = convert(a);
		print(head);
	}
	
	public Node convert(Node head){
		Node first = head;
		Node cur = head;
		Node curn = head.next;
		while(curn != null){
			cur.next = curn.next;
			curn.next = first;
			first = curn;
			curn = cur.next;
		}
		return first;
		}

	private void print(Node a) {
		Node head =a;
		while(head != null) {
			System.out.print(head.v + "  ");
			head = head.next;
		}
		
	}

	public Node convertLinkdList(Node from,Node to){
		Node head = from;
		Node tail = to.next;
		Node p = head.next;
		Node first = head;
		while(p != null && p!=tail){
			head.next=p.next;
			p.next=first;
			first=p;
			p = head.next;
		}
		return first;
	}

}
