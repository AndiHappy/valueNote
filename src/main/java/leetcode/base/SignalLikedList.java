package leetcode.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author zhailzh 
 */
public class SignalLikedList<E> {

  transient int size = 0;
  transient LinkedNode<E> first = null;
  transient LinkedNode<E> last = null;

  public SignalLikedList(Collection<? extends E> c) {
    this();
    addAll(c);
  }

  public SignalLikedList() {}

  private boolean addAll(Collection<? extends E> c) {
    Object[] a = c.toArray();
    int numNew = a.length;
    if (numNew == 0)
      return true;

    for (Object o : a) {
      @SuppressWarnings("unchecked")
      E e = (E) o;
      if (first == null) { // null linkedlist
        LinkedNode<E> newLinkedNode = new LinkedNode<E>(null, e, null);
        first = newLinkedNode;
        last = first;
      } else {
        LinkedNode<E> newLinkedNode = new LinkedNode<E>(null, e, first);
        first = newLinkedNode;
      }
    }
    size += numNew;
    return true;
  }

  /**
   * index 
   */
  private boolean addAll(int index, Collection<? extends E> c) {
    checkPositionIndex(index);
    Object[] a = c.toArray();
    int numNew = a.length;
    if (numNew == 0)
      return false;
    LinkedNode<E> pred, succ;
    // ȷindex  index ǰһλ
    if (index == size) {
      succ = null;
      pred = last;
    } else {
      succ = node(index);
      pred = node(index - 1);
    }

    for (Object o : a) {
      @SuppressWarnings("unchecked")
      E e = (E) o;
      LinkedNode<E> newNode = new LinkedNode<E>(pred, e, null);
      if (pred == null) {
        first = newNode;
      } else {
        pred.next = newNode;
      }
      pred = newNode;
    }

    if (succ == null) {
      last = pred;
    } else {
      pred.next = succ;
      succ.prev = pred;
    }

    size += numNew;
    return true;
  }

  // ҵnڵ
  public LinkedNode<E> node(int index) {
    if (index > -1) {
      LinkedNode<E> x = first;
      for (int i = 0; i < index; i++)
        x = x.next;
      return x;
    } else {
      return null;
    }

  }

  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index))
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  public boolean add(E e) {

    if (first == null && size == 0) {
      LinkedNode<E> newLinkedNode = new LinkedNode<E>(null, e, null);
      first = newLinkedNode;
    } else {
      // Ԫ,ͷ巨
      LinkedNode<E> newLinkedNode = new LinkedNode<E>(null, e, first);
      first = newLinkedNode;
    }
    size = size + 1;
    return true;
  }

  public E get() {
    if (first != null) {
      return first.item;
    } else {
      return null;
    }
  }

  public List<E> toArray() {
    if (first == null) {
      return null;
    } else {
      List<E> arraylist = new ArrayList<E>();
      LinkedNode<E> fiLinkedNode = first;
      while (fiLinkedNode != null) {
        arraylist.add(fiLinkedNode.item);
        fiLinkedNode = fiLinkedNode.next;
      }
      return arraylist;
    }

  }

  private SignalLikedList<E> reversal() {
    if (first != null && first.next != null) {
      LinkedNode<E> pre = null;
      LinkedNode<E> next = first.next;
      while (next != null) {
        first.next = pre;
        pre = first;
        first = next;
        next = next.next;
      }
      first.next = pre;
    }
    return this;
  }

  @Override
  public String toString() {
    return toArray().toString();
  }

  public static void main(String[] args) {

    Collection<Integer> nums = new ArrayList<Integer>();
    nums.add(12);
    nums.add(13);
    nums.add(14);
    nums.add(15);
    nums.add(16);
    nums.add(17);
    nums.add(18);
    nums.add(19);
    nums.add(27);
    nums.add(37);
    nums.add(47);
    nums.add(57);
    SignalLikedList<Integer> cycle = new SignalLikedList<Integer>(nums);
    System.out.println(cycle.toArray().toString());

    System.out.println(cycle.reversal().toString());

    Collection<Integer> nudms = new ArrayList<Integer>();
    nudms.add(112);
    nudms.add(113);
    nudms.add(114);
    cycle.addAll(0, nudms);
    System.out.println(cycle.toArray().toString());

  }

}
