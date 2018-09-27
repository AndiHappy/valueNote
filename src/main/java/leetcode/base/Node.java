package leetcode.base;
public class Node<E> {
    E item;
    public Node<E> next;
    public Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }
    
    
    public boolean equals(Node obj) {
    	return this.item.equals(obj.item);
    }

    @Override
    public String toString() {
      return "Node [item=" + item + ", next=" + next + ", prev=" + prev + "]";
    }
  }