package baseAlg.map;

import java.util.Comparator;

/**
 * @author guizhai
 *
 */
public class TreeMapRedBlackTree<K> implements Cloneable, java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Red-black mechanics
	private static final boolean RED = false;
	private static final boolean BLACK = true;


	//The number of entries in the tree
	private transient int size = 0;


	//The number of structural modifications to the tree.
	private transient int modCount = 0;

	private transient Node root;
	private final Comparator<? super K> comparator;

	public TreeMapRedBlackTree() {
		comparator = null;
	}

	public TreeMapRedBlackTree(Comparator<? super K> comparator) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	final int compare(Object k1, Object k2) {
		return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2) : comparator.compare((K) k1, (K) k2);
	}

	class Node {
		public Node(K key, Node parent) {
			this.key = key;
			this.parent = parent;
		}

		K key;
		Node left;
		Node right;
		Node parent;
		boolean color = BLACK;

		@Override
		public String toString() {
			return key.toString();
		}
	}

	/**
	 * 1 代表保存成功
	 * -1 标识红黑树中已经存在该节点
	 * */
	@SuppressWarnings("unchecked")
	public int put(K key) {
		Node t = root;
		if (t == null) {
			compare(key, key); // type (and possibly null) check
			root = new Node(key, null);
			size = 1;
			modCount++;
			return 1;
		}

		Node parent = findParent(key);
		if (parent != null) {
			int cmp = this.comparator != null ? this.comparator.compare(key, parent.key)
					: ((Comparable<? super K>) key).compareTo(parent.key);
			Node e = new Node(key, parent);
			if (cmp < 0)
				parent.left = e;
			else
				parent.right = e;
			fixAfterInsertion(e);
			size++;
			modCount++;
			return 1;
		} else {
			return -1;
		}
	}

	public void remove(K key) {
		Node p = getNode(key);
		if (p != null) {
			deleteNode(p);
		}
	}
	
  /**
   * Returns the successor of the specified Entry, or null if no such.
   */
  public Node successor(Node t) {
      if (t == null)
          return null;
      else if (t.right != null) {
      	Node p = t.right;
          while (p.left != null)
              p = p.left;
          return p;
      } else {
      	Node p = t.parent;
      	Node ch = t;
          while (p != null && ch == p.right) {
              ch = p;
              p = p.parent;
          }
          return p;
      }
  }

	private void deleteNode(TreeMapRedBlackTree<K>.Node p) {
		modCount++;
    size--;

    // If strictly internal, copy successor's element to p and then make p
    // point to successor.
    if (p.left != null && p.right != null) {
    	 // 右子树最左节点
        Node s = successor(p);
        p.key = s.key;
        p = s;
    } // p has 2 children

    // Start fixup at replacement node, if it exists.
    Node replacement = (p.left != null ? p.left : p.right);

    if (replacement != null) {
        // Link replacement to parent
        replacement.parent = p.parent;
        if (p.parent == null)
            root = replacement;
        else if (p == p.parent.left)
            p.parent.left  = replacement;
        else
            p.parent.right = replacement;

        // Null out links so they are OK to use by fixAfterDeletion.
        p.left = p.right = p.parent = null;

        // Fix replacement
        if (p.color == BLACK)
            fixAfterDeletion(replacement);
    } else if (p.parent == null) { // return if we are the only node.
        root = null;
    } else { //  No children. Use self as phantom replacement and unlink.
        if (p.color == BLACK)
            fixAfterDeletion(p);

        if (p.parent != null) {
            if (p == p.parent.left)
                p.parent.left = null;
            else if (p == p.parent.right)
                p.parent.right = null;
            p.parent = null;
        }
    }
		
	}

	@SuppressWarnings("unchecked")
	public Node getNode(K key) {
		if (key == null)
			throw new NullPointerException();
		Node p = root;
		while (p != null) {
			int cmp = this.comparator != null ? this.comparator.compare(key, p.key)
					: ((Comparable<? super K>) key).compareTo(p.key);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	/** From CLR */
	private void fixAfterDeletion(Node x) {
		while (x != root && colorOf(x) == BLACK) {
			if (x == leftOf(parentOf(x))) {
				Node sib = rightOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}

				if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			} else { // symmetric
				Node sib = leftOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}

				if (colorOf(rightOf(sib)) == BLACK && colorOf(leftOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}

		setColor(x, BLACK);
	}

	private void fixAfterInsertion(Node x) {
		x.color = RED;
		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Node uncle = rightOf(parentOf(parentOf(x)));
				if (colorOf(uncle) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(uncle, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Node y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}

	/** From CLR */
	private void rotateLeft(Node p) {
		if (p != null) {
			Node r = p.right;
			p.right = r.left;
			if (r.left != null)
				r.left.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}

	/** From CLR */
	private void rotateRight(Node p) {
		if (p != null) {
			Node l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			l.right = p;
			p.parent = l;
		}
	}


	private boolean colorOf(TreeMapRedBlackTree<K>.Node p) {
		return (p == null ? BLACK : p.color);
	}

	private void setColor(TreeMapRedBlackTree<K>.Node p, boolean clo) {
		if (p != null)
			p.color = clo;
	}

	private TreeMapRedBlackTree<K>.Node rightOf(TreeMapRedBlackTree<K>.Node p) {
		return (p == null ? null : p.right);
	}

	private TreeMapRedBlackTree<K>.Node leftOf(TreeMapRedBlackTree<K>.Node p) {
		return (p == null ? null : p.left);
	}

	private TreeMapRedBlackTree<K>.Node parentOf(TreeMapRedBlackTree<K>.Node p) {
		return (p == null ? null : p.parent);
	}

	/**
	 * find Key 对应的Node的Parent节点，如果存在Key对应的Node，直接返回NULL
	 * */
	private Node findParent(K key) {
		Node parent;
		Node t = root;
		Comparator<? super K> cpr = comparator;
		if (cpr != null) {
			do {
				parent = t;
				int cmp = cpr.compare(key, t.key);
				if (cmp < 0)
					t = t.left;
				else if (cmp > 0)
					t = t.right;
				else
					return null;
			} while (t != null);
		} else {
			if (key == null)
				throw new NullPointerException();
			@SuppressWarnings("unchecked")
			Comparable<? super K> k = (Comparable<? super K>) key;
			do {
				parent = t;
				int cmp = k.compareTo(t.key);
				if (cmp < 0)
					t = t.left;
				else if (cmp > 0)
					t = t.right;
				else
					return null;
			} while (t != null);
		}
		return parent;
	}


	public static void main(String[] args) {
		
			TreeMapRedBlackTree<Integer> tree = new TreeMapRedBlackTree<Integer>();
			for (int i = 0; i < 15; i++) {
				tree.put(i);
			}
			
			tree.remove(5);

	}

}
