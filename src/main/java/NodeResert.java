
/**
 * @author guizhai
 *
 */
public class NodeResert {

	class Node {
		public Node(String string) {
			this.v = string;
		}
		String v;
		Node next;
	}

	public Node find(Node head, int index) {
		int tmpo = 0;
		Node tmp = head;
		while (tmpo < index) {
			tmp = tmp.next;
			tmpo++;
		}
		return tmp;
	}

	public Node resert(Node head, int a, int b) {

		Node head1 = head;
		Node nap = find(head, a-1);
		Node na = nap.next;
		Node napn = nap.next;
		int limit = 0;
		while(limit < (b-a)) {
			Node tmp = na.next;
			na.next = tmp.next;
			tmp.next = napn;
			nap.next = tmp;
			
			napn = nap.next;
			limit++;
			print(head);
		}
		
		return head1;

	}


	private void print(Node head) {
		Node head1 = head;
		while(head1 != null) {
			System.out.print(head1.v+" ");
			head1 = head1.next;
		}
		
	}

	public void test() {
		Node a = new Node("a");
		Node b= new Node("b");
		Node c = new Node("c");
		Node d= new Node("d");
		Node e = new Node("e");
		Node f= new Node("f");
		Node g = new Node("g");
		Node h= new Node("h");
		
		a.next=b;
		b.next=c;
		c.next=d;
		d.next=e;
		e.next=f;
		f.next = g;
		g.next=h;
		
		resert(a, 1, 4);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NodeResert test = new NodeResert();
		test.test();

	}

}
