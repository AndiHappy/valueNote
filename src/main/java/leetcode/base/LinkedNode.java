package leetcode.base;

public class LinkedNode<E> {
  public E item;
  public LinkedNode<E> next;
  public LinkedNode<E> prev;

  public LinkedNode(LinkedNode<E> prev, E element, LinkedNode<E> next) {
    this.item = element;
    this.next = next;
    this.prev = prev;
  }
  
  public LinkedNode() {
	  }

  @Override
  public String toString() {
    return "Node [ item=" + item + ", next=" + (next != null ? next.item : null) + ",prev="
        + (prev != null ? prev.item : prev) + "]";
  }
  
  public String toAllAfterString(){
	  if(item != null && next != null){
		  return  item.toString() +" " + next.toAllAfterString();
	  }
	  
	  if(item != null){
		  return  item.toString() +" ";
	  }
	  
	  return "";
  }
  
  
  public String toAllBeforeString(){
	  if(item != null && prev != null){
		  return  item.toString() +" " + prev.toAllBeforeString();
	  }
	  
	  if(item != null){
		  return  item.toString() +" ";
	  }
	  
	  return "";
  }
}