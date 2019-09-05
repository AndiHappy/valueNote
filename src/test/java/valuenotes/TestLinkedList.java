package valuenotes;

import java.util.Stack;

public class TestLinkedList {

	class Node{
		public Node(int v2) {
			this.v = v2;
		}
		int v;
		Node next;
	}
	
	 void print(Node a) {
		Node head =a;
		while(head != null) {
			System.out.print(head.v + "  ");
			head = head.next;
		}
		System.out.println();
	}
	 Node add(Node h1,Node h2) {
			Node f1 = h1;
			Node f2 = h2;
			Node cur = null;
			Node tmp = null;
			int j =0;
			while(f1 != null || f2 != null) {
				int value = (f1==null?0:f1.v) + (f2==null?0:f2.v) +j;
				int v = value%10;
				j = value/10;
				if(cur == null) {
					cur = new Node(v);
					tmp = cur;
				}else {
					cur.next = new Node(v);
					cur = cur.next;
				}
				f1 = f1==null?null:f1.next;
				f2 = f2==null?null:f2.next;
			}
			
			if(j!=0) {
				cur.next = new Node(j);
			}
			return tmp;
		}
	 
	 Node addResert(Node h1,Node h2){
			Stack<Integer> f1 = new Stack<Integer>();
			while(h1 != null){
			f1.push(h1.v);
			h1 = h1.next;
			}
			
			Stack<Integer> f2 = new Stack<Integer>();
			while(h2 != null){
			f2.push(h2.v);
			h2 = h2.next;
			}
			
			Node tmp = null;
			Node first = null;
			int j = 0;
			while(!f1.isEmpty() || !f2.isEmpty()){
				int value = (f1.isEmpty()?0:f1.pop().intValue())+(f2.isEmpty()?0:f2.pop().intValue())+j;
				int v = value%10;
				j = value/10;
				if(first == null) {
					first = new Node(v);
				}else {
					tmp = new Node(v);
					tmp.next = first;
					first = tmp;
				}
			}
			if(j != 0) {
				tmp = new Node(j);
				tmp.next = first;
			}
			return tmp;
		}
	 
	 public static void main(String[] args) {
		 TestLinkedList test = new TestLinkedList();
		 test.t3();
	 }
	 
	 
	 private void t3() {
			Node a = new Node(1);
			Node b = new Node(2);
			Node c = new Node(3);
			Node d = new Node(4);
			Node e = new Node(5);
			Node f = new Node(6);
			Node g = new Node(7);
			a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;
			
			Node a1 = new Node(1);
			Node b2 = new Node(2);
			Node c3 = new Node(3);
			Node d4 = new Node(4);
			a1.next=b2;b2.next=c3;c3.next=d4;d4.next=e;
			
			print(a1);
			print(a);
			print(addResert(a1, a));
		}

	public void t2() {
		Node a = new Node(1);
		Node b = new Node(2);
		Node c = new Node(3);
		Node d = new Node(4);
		Node e = new Node(5);
		Node f = new Node(6);
		Node g = new Node(7);
		a.next=b;b.next=c;c.next=d;d.next=e;e.next=f;f.next=g;
		
		Node a1 = new Node(1);
		Node b2 = new Node(2);
		Node c3 = new Node(3);
		Node d4 = new Node(4);
		a1.next=b2;b2.next=c3;c3.next=d4;d4.next=e;
		
		print(a1);
		print(a);
		print(add(a1, a));
	}
}
