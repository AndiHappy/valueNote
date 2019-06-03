package leetcode.L141ToL160;

import java.util.Stack;

/**
 * @author guizhai
 *
 */
public class L155MinStack {

	/**
	
	Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	
	push(x) -- Push element x onto stack.
	pop() -- Removes the element on top of the stack.
	top() -- Get the top element.
	getMin() -- Retrieve the minimum element in the stack.
	Example:
	MinStack minStack = new MinStack();
	minStack.push(-2);
	minStack.push(0);
	minStack.push(-3);
	minStack.getMin();   --> Returns -3.
	minStack.pop();
	minStack.top();      --> Returns 0.
	minStack.getMin();   --> Returns -2.
	
	
	 */

	public class MinStack {
		long min = Integer.MAX_VALUE;
		Stack<Long> stack = new Stack<>();

		public MinStack() {

		}

		public void push(int x) {
			stack.push((long) x - min);
			min = Math.min(x, min);
		}

		public void pop() {
			min = Math.max(min - stack.pop(), min);
		}

		public int top() {
			return (int) Math.max(stack.peek() + min, min);
		}

		public int getMin() {
			return (int) min;
		}
	}
	
	class MinStack1 {
    class Node{
        int value;
        int min;
        Node next;
        
        Node(int x, int min){
            this.value=x;
            this.min=min;
            next = null;
        }
    }
    Node head;
    public void push(int x) {
        if(null==head){
            head = new Node(x,x);
        }else{
            Node n = new Node(x, Math.min(x,head.min));
            n.next=head;
            head=n;
        }
    }

    public void pop() {
        if(head!=null)
            head =head.next;
    }

    public int top() {
        if(head!=null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if(null!=head)
            return head.min;
        return -1;
    }
}
	
	class MinStack2 {
    Stack<Integer> min = new Stack<>();
    Stack<Integer> stack = new Stack<>();
    public void push(int x) {
        stack.push(x);
        if (min.isEmpty() || min.peek() >= x) {
            min.push(x);
        }
    }

    public void pop() {
        if (stack.pop().equals(min.peek())) {
            min.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
