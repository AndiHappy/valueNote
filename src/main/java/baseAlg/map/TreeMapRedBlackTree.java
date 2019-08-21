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
    	 // p的右子树最左节点，如果右子树没有左节点，就是右子树
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
        if (p.color == BLACK) {
        	 fixAfterDeletion(replacement);
        }
           
    } else if (p.parent == null) { // return if we are the only node.
        root = null;
    } else { //  No children. Use self as phantom replacement and unlink.
    	  // replacement 为null的情况下，
        if (p.color == BLACK) {
        	fixAfterDeletion(p);
        }
        //直接的删除了p 说明这个时候p已经是叶子节点
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
				Node uncle = rightOf(parentOf(x));

				if (colorOf(uncle) == RED) {
					setColor(uncle, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					uncle = rightOf(parentOf(x));
				}

				if (colorOf(leftOf(uncle)) == BLACK && colorOf(rightOf(uncle)) == BLACK) {
					setColor(uncle, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(uncle)) == BLACK) {
						setColor(leftOf(uncle), BLACK);
						setColor(uncle, RED);
						rotateRight(uncle);
						uncle = rightOf(parentOf(x));
					}
					setColor(uncle, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(uncle), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			} else { // symmetric
				assert x == rightOf((parentOf(x)));
				Node brother = leftOf(parentOf(x));

				if (colorOf(brother) == RED) {
					setColor(brother, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					brother = leftOf(parentOf(x));
				}

				if (colorOf(rightOf(brother)) == BLACK && colorOf(leftOf(brother)) == BLACK) {
					setColor(brother, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(brother)) == BLACK) {
						setColor(rightOf(brother), BLACK);
						setColor(brother, RED);
						rotateLeft(brother);
						brother = leftOf(parentOf(x));
					}
					setColor(brother, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(brother), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}

		setColor(x, BLACK);
	}

	private void fixAfterInsertion(Node x) {
		x.color = RED;
		// x 为null，x为root的第一个节点，直接的跳走
		/**
		 * 如果x parent 节点为黑，那么x 为红，直接的跳过即可。
		 * */
		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Node uncle = rightOf(parentOf(parentOf(x)));
				if (colorOf(uncle) == RED) {
					// 图三
					setColor(parentOf(x), BLACK);
					setColor(uncle, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					// uncle 节点为黑或者uncle 节点为null
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						/**
						 * 这个操作，非常的有意思，按照左旋方法的定义，参数应该是旋转子树的根节点，但是这个传入的是旋转节点
						 * 然后，就变成了：x右节点和x交换位置，并且在交换位置的过程中，x有右节点变为了左节点。
						 * 图②
						 * */
						rotateLeft(x);
					}
					// 调整颜色，当前节点为红色节点，是定死的。所以把父节点设为黑，爷节节点设置为红
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					// x 为left节点，进行右旋
					// 图①
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Node uncle = leftOf(parentOf(parentOf(x)));
				if (colorOf(uncle) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(uncle, BLACK);
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

	/** From CLR 
	 *  旋转节点：为 p.right
	 *  方法的输入的参数为 旋转子树的节点
	 * */
	private void rotateLeft(Node p) {
		if (p != null) {
			// p的右节点即是旋转上升的节点，然后旋转上升后，该节点的左节点为P，原来的左节点，这是为p的右节点
			Node r = p.right;
			p.right = r.left;
			if (r.left != null)
				r.left.parent = p;
			
			// 设置循转节点的父节点，以及P原来父节点的指向的设置
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			
			//旋转节点为右节点，原来的左孩子设置为P
			r.left = p;
			p.parent = r;
		}
	}

	/** From CLR 
	 * 该方法的参数，并不是被旋转的节点，而是调整子树的根节点
	 * */
	private void rotateRight(Node p) {
		if (p != null) {
			// p的左节点设置为 原来左节点的右子树
			Node l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			
			// 旋转过程中父节点的设置，上升节点的父节点以及原来父节点的指向的设置
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			
			// 最后右节点和p旋转后父节点的设置
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
			
		tree.put(15);
		
		tree.put(10);
		
		tree.put(17);
//		
//		tree.put(9);
			
			/**
			 * 从大到小的构建，即可调试：从左子树开始构建
			 * */
			for (int i = 15; i >=0; i--) {
				tree.put(i);
			}
			
			/**
			 * 从小到大的构建，即可调试：从右子树开始构建
			 * */
//			for (int i = 0; i < 15; i++) {
//				tree.put(i);
//			}
			
			tree.remove(3);

	}

}
