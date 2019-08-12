package baseAlg.map;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.LockSupport;

/**
 * @author guizhai
 *
 */
@SuppressWarnings("restriction")
public class ConcurrentMapImpl<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {

	private static final long serialVersionUID = 1L;

	static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

	transient volatile Node<K, V>[] table;

	/**
	 * A padded cell for distributing counts.  Adapted from LongAdder
	 * and Striped64.  See their internal docs for explanation.
	 */
	@sun.misc.Contended
	static final class CounterCell {
		volatile long value;

		CounterCell(long x) {
			value = x;
		}
	}

	// Unsafe mechanics
	private static final sun.misc.Unsafe U;
	
	//内存的地址
	private static final long SIZECTL;
	private static final long TRANSFERINDEX;
	private static final long BASECOUNT;
	private static final long CELLSBUSY;
	private static final long CELLVALUE;
	private static final long ABASE;
	private static final int ASHIFT;

	static {
		try {
			Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
//			sun.misc.Unsafe unsafe = 
			U = (sun.misc.Unsafe) f.get(null);
			Class<?> k = ConcurrentHashMap.class;
			SIZECTL = U.objectFieldOffset(k.getDeclaredField("sizeCtl"));
			TRANSFERINDEX = U.objectFieldOffset(k.getDeclaredField("transferIndex"));
			BASECOUNT = U.objectFieldOffset(k.getDeclaredField("baseCount"));
			CELLSBUSY = U.objectFieldOffset(k.getDeclaredField("cellsBusy"));
			Class<?> ck = CounterCell.class;
			CELLVALUE = U.objectFieldOffset(ck.getDeclaredField("value"));
			
			Class<?> ak = Node[].class;
			ABASE = U.arrayBaseOffset(ak);
			
			int scale = U.arrayIndexScale(ak);
			if ((scale & (scale - 1)) != 0)
				throw new Error("data type scale not a power of two");
			ASHIFT = 31 - Integer.numberOfLeadingZeros(scale);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	/**
	 * 为什么直接是：U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE) ？
	 * 而不是直接是：tab[i]
	 * 
	 * */
	 @SuppressWarnings("unchecked")
   static final <K,V> Node<K,V> tabAt(Node<K,V>[] tab, int i) {
       return (Node<K,V>)U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
//       return tab[i];
   }

	 // c：现有的值，老值。 v：新的值，期望的值
   static final <K,V> boolean casTabAt(Node<K,V>[] tab, int i,Node<K,V> c, Node<K,V> v) {
       return U.compareAndSwapObject(tab, ((long)i << ASHIFT) + ABASE, c, v);
   }

   static final <K,V> void setTabAt(Node<K,V>[] tab, int i, Node<K,V> v) {
       U.putObjectVolatile(tab, ((long)i << ASHIFT) + ABASE, v);
   }

	/**
	 * Table initialization and resizing control.  When negative, the
	 * table is being initialized or resized: -1 for initialization,
	 * else -(1 + the number of active resizing threads).  Otherwise,
	 * when table is null, holds the initial table size to use upon
	 * creation, or 0 for default. After initialization, holds the
	 * next element count value upon which to resize the table.
	 * 
	 * table 初始化和重新分配大小的控制位。 
	 * -1 标识初始化，
	 * -(1+重置table的线程数)
	 * 默认为0
	 * 初始化以后，n代表下载重新分配table的数量
	 */
	private transient volatile int sizeCtl;

	/**
	 * Node 的数据结构
	 * 
	 * */
	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		volatile V val;
		volatile Node<K, V> next;

		Node(int hash, K key, V val, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.val = val;
			this.next = next;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return val;
		}

		public final int hashCode() {
			return key.hashCode() ^ val.hashCode();
		}

		public final String toString() {
			return key + "=" + val;
		}

		public final V setValue(V value) {
			throw new UnsupportedOperationException();
		}

		public final boolean equals(Object o) {
			Object k, v, u;
			Map.Entry<?, ?> e;
			return ((o instanceof Map.Entry) && (k = (e = (Map.Entry<?, ?>) o).getKey()) != null && (v = e.getValue()) != null
					&& (k == key || k.equals(key)) && (v == (u = val) || v.equals(u)));
		}

		/**
		 * Virtualized support for map.get(); overridden in subclasses.
		 */
		Node<K, V> find(int h, Object k) {
			Node<K, V> e = this;
			if (k != null) {
				do {
					K ek;
					if (e.hash == h && ((ek = e.key) == k || (ek != null && k.equals(ek))))
						return e;
				} while ((e = e.next) != null);
			}
			return null;
		}
	}

	/**
	 * Spreads (XORs) higher bits of hash to lower and also forces top
	 * bit to 0. Because the table uses power-of-two masking, sets of
	 * hashes that vary only in bits above the current mask will
	 * always collide. (Among known examples are sets of Float keys
	 * holding consecutive whole numbers in small tables.)  So we
	 * apply a transform that spreads the impact of higher bits
	 * downward. There is a tradeoff between speed, utility, and
	 * quality of bit-spreading. Because many common sets of hashes
	 * are already reasonably distributed (so don't benefit from
	 * spreading), and because we use trees to handle large sets of
	 * collisions in bins, we just XOR some shifted bits in the
	 * cheapest possible way to reduce systematic lossage, as well as
	 * to incorporate impact of the highest bits that would otherwise
	 * never be used in index calculations because of table bounds.
	 */
	static final int spread(int h) {
		return (h ^ (h >>> 16)) & HASH_BITS;
	}

	/**
	 * Returns a power of two table size for the given desired capacity.
	 * See Hackers Delight, sec 3.2
	 */
	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	private static final int MAXIMUM_CAPACITY = 1 << 30;

	
	public ConcurrentMapImpl() {
	}
	
	public ConcurrentMapImpl(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException();
		int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ? MAXIMUM_CAPACITY
				: tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
		this.sizeCtl = cap;

	}

	public V put(K key, V value) {
		return putVal(key, value, false);
	}
  /**
   * Returns x's Class if it is of the form "class C implements
   * Comparable<C>", else null.
   */
  static Class<?> comparableClassFor(Object x) {
      if (x instanceof Comparable) {
          Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
          if ((c = x.getClass()) == String.class) // bypass checks
              return c;
          if ((ts = c.getGenericInterfaces()) != null) {
              for (int i = 0; i < ts.length; ++i) {
                  if (((t = ts[i]) instanceof ParameterizedType) &&
                      ((p = (ParameterizedType)t).getRawType() ==
                       Comparable.class) &&
                      (as = p.getActualTypeArguments()) != null &&
                      as.length == 1 && as[0] == c) // type arg is c
                      return c;
              }
          }
      }
      return null;
  }

  /**
   * Returns k.compareTo(x) if x matches kc (k's screened comparable
   * class), else 0.
   */
  @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
  static int compareComparables(Class<?> kc, Object k, Object x) {
      return (x == null || x.getClass() != kc ? 0 :
              ((Comparable)k).compareTo(x));
  }
  
  /**
   * Nodes for use in TreeBins
   */
  static final class TreeNode<K,V> extends Node<K,V> {
      TreeNode<K,V> parent;  // red-black tree links
      TreeNode<K,V> left;
      TreeNode<K,V> right;
      TreeNode<K,V> prev;    // needed to unlink next upon deletion
      boolean red;

      TreeNode(int hash, K key, V val, Node<K,V> next,
               TreeNode<K,V> parent) {
          super(hash, key, val, next);
          this.parent = parent;
      }

      Node<K,V> find(int h, Object k) {
          return findTreeNode(h, k, null);
      }

      /**
       * Returns the TreeNode (or null if not found) for the given key
       * starting at given root.
       */
      final TreeNode<K,V> findTreeNode(int h, Object k, Class<?> kc) {
          if (k != null) {
              TreeNode<K,V> p = this;
              do  {
                  int ph, dir; K pk; TreeNode<K,V> q;
                  TreeNode<K,V> pl = p.left, pr = p.right;
                  if ((ph = p.hash) > h)
                      p = pl;
                  else if (ph < h)
                      p = pr;
                  else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
                      return p;
                  else if (pl == null)
                      p = pr;
                  else if (pr == null)
                      p = pl;
                  else if ((kc != null ||
                            (kc = comparableClassFor(k)) != null) &&
                           (dir = compareComparables(kc, k, pk)) != 0)
                      p = (dir < 0) ? pl : pr;
                  else if ((q = pr.findTreeNode(h, k, kc)) != null)
                      return q;
                  else
                      p = pl;
              } while (p != null);
          }
          return null;
      }
  }
	
  /* ---------------- TreeBins -------------- */

  /**
   * TreeNodes used at the heads of bins. TreeBins do not hold user
   * keys or values, but instead point to list of TreeNodes and
   * their root. They also maintain a parasitic read-write lock
   * forcing writers (who hold bin lock) to wait for readers (who do
   * not) to complete before tree restructuring operations.
   */
  static final class TreeBin<K,V> extends Node<K,V> {
      TreeNode<K,V> root;
      volatile TreeNode<K,V> first;
      volatile Thread waiter;
      volatile int lockState;
      // values for lockState
      static final int WRITER = 1; // set while holding write lock
      static final int WAITER = 2; // set when waiting for write lock
      static final int READER = 4; // increment value for setting read lock

      /**
       * Tie-breaking utility for ordering insertions when equal
       * hashCodes and non-comparable. We don't require a total
       * order, just a consistent insertion rule to maintain
       * equivalence across rebalancings. Tie-breaking further than
       * necessary simplifies testing a bit.
       */
      static int tieBreakOrder(Object a, Object b) {
          int d;
          if (a == null || b == null ||
              (d = a.getClass().getName().
               compareTo(b.getClass().getName())) == 0)
              d = (System.identityHashCode(a) <= System.identityHashCode(b) ?
                   -1 : 1);
          return d;
      }

      /**
       * Creates bin with initial set of nodes headed by b.
       */
      TreeBin(TreeNode<K,V> b) {
          super(TREEBIN, null, null, null);
          this.first = b;
          TreeNode<K,V> r = null;
          for (TreeNode<K,V> x = b, next; x != null; x = next) {
              next = (TreeNode<K,V>)x.next;
              x.left = x.right = null;
              if (r == null) {
                  x.parent = null;
                  x.red = false;
                  r = x;
              }
              else {
                  K k = x.key;
                  int h = x.hash;
                  Class<?> kc = null;
                  for (TreeNode<K,V> p = r;;) {
                      int dir, ph;
                      K pk = p.key;
                      if ((ph = p.hash) > h)
                          dir = -1;
                      else if (ph < h)
                          dir = 1;
                      else if ((kc == null &&
                                (kc = comparableClassFor(k)) == null) ||
                               (dir = compareComparables(kc, k, pk)) == 0)
                          dir = tieBreakOrder(k, pk);
                          TreeNode<K,V> xp = p;
                      if ((p = (dir <= 0) ? p.left : p.right) == null) {
                          x.parent = xp;
                          if (dir <= 0)
                              xp.left = x;
                          else
                              xp.right = x;
                          r = balanceInsertion(r, x);
                          break;
                      }
                  }
              }
          }
          this.root = r;
          assert checkInvariants(root);
      }

      /**
       * Acquires write lock for tree restructuring.
       */
      private final void lockRoot() {
          if (!U.compareAndSwapInt(this, LOCKSTATE, 0, WRITER))
              contendedLock(); // offload to separate method
      }

      /**
       * Releases write lock for tree restructuring.
       */
      private final void unlockRoot() {
          lockState = 0;
      }

      /**
       * Possibly blocks awaiting root lock.
       */
      private final void contendedLock() {
          boolean waiting = false;
          for (int s;;) {
              if (((s = lockState) & ~WAITER) == 0) {
                  if (U.compareAndSwapInt(this, LOCKSTATE, s, WRITER)) {
                      if (waiting)
                          waiter = null;
                      return;
                  }
              }
              else if ((s & WAITER) == 0) {
                  if (U.compareAndSwapInt(this, LOCKSTATE, s, s | WAITER)) {
                      waiting = true;
                      waiter = Thread.currentThread();
                  }
              }
              else if (waiting)
                  LockSupport.park(this);
          }
      }

      /**
       * Returns matching node or null if none. Tries to search
       * using tree comparisons from root, but continues linear
       * search when lock not available.
       */
      final Node<K,V> find(int h, Object k) {
          if (k != null) {
              for (Node<K,V> e = first; e != null; ) {
                  int s; K ek;
                  if (((s = lockState) & (WAITER|WRITER)) != 0) {
                      if (e.hash == h &&
                          ((ek = e.key) == k || (ek != null && k.equals(ek))))
                          return e;
                      e = e.next;
                  }
                  else if (U.compareAndSwapInt(this, LOCKSTATE, s,
                                               s + READER)) {
                      TreeNode<K,V> r, p;
                      try {
                          p = ((r = root) == null ? null :
                               r.findTreeNode(h, k, null));
                      } finally {
                          Thread w;
                          if (U.getAndAddInt(this, LOCKSTATE, -READER) ==
                              (READER|WAITER) && (w = waiter) != null)
                              LockSupport.unpark(w);
                      }
                      return p;
                  }
              }
          }
          return null;
      }

      /**
       * Finds or adds a node.
       * @return null if added
       */
      final TreeNode<K,V> putTreeVal(int h, K k, V v) {
          Class<?> kc = null;
          boolean searched = false;
          for (TreeNode<K,V> p = root;;) {
              int dir, ph; K pk;
              if (p == null) {
                  first = root = new TreeNode<K,V>(h, k, v, null, null);
                  break;
              }
              else if ((ph = p.hash) > h)
                  dir = -1;
              else if (ph < h)
                  dir = 1;
              else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
                  return p;
              else if ((kc == null &&
                        (kc = comparableClassFor(k)) == null) ||
                       (dir = compareComparables(kc, k, pk)) == 0) {
                  if (!searched) {
                      TreeNode<K,V> q, ch;
                      searched = true;
                      if (((ch = p.left) != null &&
                           (q = ch.findTreeNode(h, k, kc)) != null) ||
                          ((ch = p.right) != null &&
                           (q = ch.findTreeNode(h, k, kc)) != null))
                          return q;
                  }
                  dir = tieBreakOrder(k, pk);
              }

              TreeNode<K,V> xp = p;
              if ((p = (dir <= 0) ? p.left : p.right) == null) {
                  TreeNode<K,V> x, f = first;
                  first = x = new TreeNode<K,V>(h, k, v, f, xp);
                  if (f != null)
                      f.prev = x;
                  if (dir <= 0)
                      xp.left = x;
                  else
                      xp.right = x;
                  if (!xp.red)
                      x.red = true;
                  else {
                      lockRoot();
                      try {
                          root = balanceInsertion(root, x);
                      } finally {
                          unlockRoot();
                      }
                  }
                  break;
              }
          }
          assert checkInvariants(root);
          return null;
      }

      /**
       * Removes the given node, that must be present before this
       * call.  This is messier than typical red-black deletion code
       * because we cannot swap the contents of an interior node
       * with a leaf successor that is pinned by "next" pointers
       * that are accessible independently of lock. So instead we
       * swap the tree linkages.
       *
       * @return true if now too small, so should be untreeified
       */
      final boolean removeTreeNode(TreeNode<K,V> p) {
          TreeNode<K,V> next = (TreeNode<K,V>)p.next;
          TreeNode<K,V> pred = p.prev;  // unlink traversal pointers
          TreeNode<K,V> r, rl;
          if (pred == null)
              first = next;
          else
              pred.next = next;
          if (next != null)
              next.prev = pred;
          if (first == null) {
              root = null;
              return true;
          }
          if ((r = root) == null || r.right == null || // too small
              (rl = r.left) == null || rl.left == null)
              return true;
          lockRoot();
          try {
              TreeNode<K,V> replacement;
              TreeNode<K,V> pl = p.left;
              TreeNode<K,V> pr = p.right;
              if (pl != null && pr != null) {
                  TreeNode<K,V> s = pr, sl;
                  while ((sl = s.left) != null) // find successor
                      s = sl;
                  boolean c = s.red; s.red = p.red; p.red = c; // swap colors
                  TreeNode<K,V> sr = s.right;
                  TreeNode<K,V> pp = p.parent;
                  if (s == pr) { // p was s's direct parent
                      p.parent = s;
                      s.right = p;
                  }
                  else {
                      TreeNode<K,V> sp = s.parent;
                      if ((p.parent = sp) != null) {
                          if (s == sp.left)
                              sp.left = p;
                          else
                              sp.right = p;
                      }
                      if ((s.right = pr) != null)
                          pr.parent = s;
                  }
                  p.left = null;
                  if ((p.right = sr) != null)
                      sr.parent = p;
                  if ((s.left = pl) != null)
                      pl.parent = s;
                  if ((s.parent = pp) == null)
                      r = s;
                  else if (p == pp.left)
                      pp.left = s;
                  else
                      pp.right = s;
                  if (sr != null)
                      replacement = sr;
                  else
                      replacement = p;
              }
              else if (pl != null)
                  replacement = pl;
              else if (pr != null)
                  replacement = pr;
              else
                  replacement = p;
              if (replacement != p) {
                  TreeNode<K,V> pp = replacement.parent = p.parent;
                  if (pp == null)
                      r = replacement;
                  else if (p == pp.left)
                      pp.left = replacement;
                  else
                      pp.right = replacement;
                  p.left = p.right = p.parent = null;
              }

              root = (p.red) ? r : balanceDeletion(r, replacement);

              if (p == replacement) {  // detach pointers
                  TreeNode<K,V> pp;
                  if ((pp = p.parent) != null) {
                      if (p == pp.left)
                          pp.left = null;
                      else if (p == pp.right)
                          pp.right = null;
                      p.parent = null;
                  }
              }
          } finally {
              unlockRoot();
          }
          assert checkInvariants(root);
          return false;
      }

      /* ------------------------------------------------------------ */
      // Red-black tree methods, all adapted from CLR

      static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,
                                            TreeNode<K,V> p) {
          TreeNode<K,V> r, pp, rl;
          if (p != null && (r = p.right) != null) {
              if ((rl = p.right = r.left) != null)
                  rl.parent = p;
              if ((pp = r.parent = p.parent) == null)
                  (root = r).red = false;
              else if (pp.left == p)
                  pp.left = r;
              else
                  pp.right = r;
              r.left = p;
              p.parent = r;
          }
          return root;
      }

      static <K,V> TreeNode<K,V> rotateRight(TreeNode<K,V> root,
                                             TreeNode<K,V> p) {
          TreeNode<K,V> l, pp, lr;
          if (p != null && (l = p.left) != null) {
              if ((lr = p.left = l.right) != null)
                  lr.parent = p;
              if ((pp = l.parent = p.parent) == null)
                  (root = l).red = false;
              else if (pp.right == p)
                  pp.right = l;
              else
                  pp.left = l;
              l.right = p;
              p.parent = l;
          }
          return root;
      }

      static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,
                                                  TreeNode<K,V> x) {
          x.red = true;
          for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
              if ((xp = x.parent) == null) {
                  x.red = false;
                  return x;
              }
              else if (!xp.red || (xpp = xp.parent) == null)
                  return root;
              if (xp == (xppl = xpp.left)) {
                  if ((xppr = xpp.right) != null && xppr.red) {
                      xppr.red = false;
                      xp.red = false;
                      xpp.red = true;
                      x = xpp;
                  }
                  else {
                      if (x == xp.right) {
                          root = rotateLeft(root, x = xp);
                          xpp = (xp = x.parent) == null ? null : xp.parent;
                      }
                      if (xp != null) {
                          xp.red = false;
                          if (xpp != null) {
                              xpp.red = true;
                              root = rotateRight(root, xpp);
                          }
                      }
                  }
              }
              else {
                  if (xppl != null && xppl.red) {
                      xppl.red = false;
                      xp.red = false;
                      xpp.red = true;
                      x = xpp;
                  }
                  else {
                      if (x == xp.left) {
                          root = rotateRight(root, x = xp);
                          xpp = (xp = x.parent) == null ? null : xp.parent;
                      }
                      if (xp != null) {
                          xp.red = false;
                          if (xpp != null) {
                              xpp.red = true;
                              root = rotateLeft(root, xpp);
                          }
                      }
                  }
              }
          }
      }

      static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,
                                                 TreeNode<K,V> x) {
          for (TreeNode<K,V> xp, xpl, xpr;;)  {
              if (x == null || x == root)
                  return root;
              else if ((xp = x.parent) == null) {
                  x.red = false;
                  return x;
              }
              else if (x.red) {
                  x.red = false;
                  return root;
              }
              else if ((xpl = xp.left) == x) {
                  if ((xpr = xp.right) != null && xpr.red) {
                      xpr.red = false;
                      xp.red = true;
                      root = rotateLeft(root, xp);
                      xpr = (xp = x.parent) == null ? null : xp.right;
                  }
                  if (xpr == null)
                      x = xp;
                  else {
                      TreeNode<K,V> sl = xpr.left, sr = xpr.right;
                      if ((sr == null || !sr.red) &&
                          (sl == null || !sl.red)) {
                          xpr.red = true;
                          x = xp;
                      }
                      else {
                          if (sr == null || !sr.red) {
                              if (sl != null)
                                  sl.red = false;
                              xpr.red = true;
                              root = rotateRight(root, xpr);
                              xpr = (xp = x.parent) == null ?
                                  null : xp.right;
                          }
                          if (xpr != null) {
                              xpr.red = (xp == null) ? false : xp.red;
                              if ((sr = xpr.right) != null)
                                  sr.red = false;
                          }
                          if (xp != null) {
                              xp.red = false;
                              root = rotateLeft(root, xp);
                          }
                          x = root;
                      }
                  }
              }
              else { // symmetric
                  if (xpl != null && xpl.red) {
                      xpl.red = false;
                      xp.red = true;
                      root = rotateRight(root, xp);
                      xpl = (xp = x.parent) == null ? null : xp.left;
                  }
                  if (xpl == null)
                      x = xp;
                  else {
                      TreeNode<K,V> sl = xpl.left, sr = xpl.right;
                      if ((sl == null || !sl.red) &&
                          (sr == null || !sr.red)) {
                          xpl.red = true;
                          x = xp;
                      }
                      else {
                          if (sl == null || !sl.red) {
                              if (sr != null)
                                  sr.red = false;
                              xpl.red = true;
                              root = rotateLeft(root, xpl);
                              xpl = (xp = x.parent) == null ?
                                  null : xp.left;
                          }
                          if (xpl != null) {
                              xpl.red = (xp == null) ? false : xp.red;
                              if ((sl = xpl.left) != null)
                                  sl.red = false;
                          }
                          if (xp != null) {
                              xp.red = false;
                              root = rotateRight(root, xp);
                          }
                          x = root;
                      }
                  }
              }
          }
      }

      /**
       * Recursive invariant check
       */
      static <K,V> boolean checkInvariants(TreeNode<K,V> t) {
          TreeNode<K,V> tp = t.parent, tl = t.left, tr = t.right,
              tb = t.prev, tn = (TreeNode<K,V>)t.next;
          if (tb != null && tb.next != t)
              return false;
          if (tn != null && tn.prev != t)
              return false;
          if (tp != null && t != tp.left && t != tp.right)
              return false;
          if (tl != null && (tl.parent != t || tl.hash > t.hash))
              return false;
          if (tr != null && (tr.parent != t || tr.hash < t.hash))
              return false;
          if (t.red && tl != null && tl.red && tr != null && tr.red)
              return false;
          if (tl != null && !checkInvariants(tl))
              return false;
          if (tr != null && !checkInvariants(tr))
              return false;
          return true;
      }

      private static final sun.misc.Unsafe U;
      private static final long LOCKSTATE;
      static {
          try {
              U = sun.misc.Unsafe.getUnsafe();
              Class<?> k = TreeBin.class;
              LOCKSTATE = U.objectFieldOffset
                  (k.getDeclaredField("lockState"));
          } catch (Exception e) {
              throw new Error(e);
          }
      }
  }
  
  
  
  /**
   * Replaces all linked nodes in bin at given index unless table is
   * too small, in which case resizes instead.
   */
  private final void treeifyBin(Node<K,V>[] tab, int index) {
      Node<K,V> b; int n, sc;
      if (tab != null) {
          if ((n = tab.length) < 64)//MIN_TREEIFY_CAPACITY)
              System.out.println("tryPresize(n << 1)");
          else if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
              synchronized (b) {
                  if (tabAt(tab, index) == b) {
                      TreeNode<K,V> hd = null, tl = null;
                      for (Node<K,V> e = b; e != null; e = e.next) {
                          TreeNode<K,V> p =
                              new TreeNode<K,V>(e.hash, e.key, e.val,
                                                null, null);
                          if ((p.prev = tl) == null)
                              hd = p;
                          else
                              tl.next = p;
                          tl = p;
                      }
                      setTabAt(tab, index, new TreeBin<K,V>(hd));
                  }
              }
          }
      }
  }
  
  /* ---------------- Special Nodes -------------- */

  /**
   * A node inserted at head of bins during transfer operations.
   */
  static final class ForwardingNode<K,V> extends Node<K,V> {
      final Node<K,V>[] nextTable;
      ForwardingNode(Node<K,V>[] tab) {
          super(MOVED, null, null, null);
          this.nextTable = tab;
      }

      Node<K,V> find(int h, Object k) {
          // loop to avoid arbitrarily deep recursion on forwarding nodes
          outer: for (Node<K,V>[] tab = nextTable;;) {
              Node<K,V> e; int n;
              if (k == null || tab == null || (n = tab.length) == 0 ||
                  (e = tabAt(tab, (n - 1) & h)) == null)
                  return null;
              for (;;) {
                  int eh; K ek;
                  if ((eh = e.hash) == h &&
                      ((ek = e.key) == k || (ek != null && k.equals(ek))))
                      return e;
                  if (eh < 0) {
                      if (e instanceof ForwardingNode) {
                          tab = ((ForwardingNode<K,V>)e).nextTable;
                          continue outer;
                      }
                      else
                          return e.find(h, k);
                  }
                  if ((e = e.next) == null)
                      return null;
              }
          }
      }
  }
  
  private static int RESIZE_STAMP_BITS = 16;

  /**
   * Returns the stamp bits for resizing a table of size n.
   * Must be negative when shifted left by RESIZE_STAMP_SHIFT.
   */
  static final int resizeStamp(int n) {
      return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
  }

  /**
   * The next table to use; non-null only while resizing.
   */
  private transient volatile Node<K,V>[] nextTable;
  private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
  private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;
  private transient volatile int transferIndex;
  static final int UNTREEIFY_THRESHOLD = 6;

  /** Number of CPUS, to place bounds on some sizings */
  static final int NCPU = Runtime.getRuntime().availableProcessors();
  private static final int MIN_TRANSFER_STRIDE = 16;

  /**
   * Moves and/or copies the nodes in each bin to new table. See
   * above for explanation.
   */
  private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
      int n = tab.length, stride;
      if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
          stride = MIN_TRANSFER_STRIDE; // subdivide range
      if (nextTab == null) {            // initiating
          try {
              @SuppressWarnings("unchecked")
              Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1];
              nextTab = nt;
          } catch (Throwable ex) {      // try to cope with OOME
              sizeCtl = Integer.MAX_VALUE;
              return;
          }
          nextTable = nextTab;
          transferIndex = n;
      }
      int nextn = nextTab.length;
      ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
      boolean advance = true;
      boolean finishing = false; // to ensure sweep before committing nextTab
      for (int i = 0, bound = 0;;) {
          Node<K,V> f; int fh;
          while (advance) {
              int nextIndex, nextBound;
              if (--i >= bound || finishing)
                  advance = false;
              else if ((nextIndex = transferIndex) <= 0) {
                  i = -1;
                  advance = false;
              }
              else if (U.compareAndSwapInt
                       (this, TRANSFERINDEX, nextIndex,
                        nextBound = (nextIndex > stride ?
                                     nextIndex - stride : 0))) {
                  bound = nextBound;
                  i = nextIndex - 1;
                  advance = false;
              }
          }
          if (i < 0 || i >= n || i + n >= nextn) {
              int sc;
              if (finishing) {
                  nextTable = null;
                  table = nextTab;
                  sizeCtl = (n << 1) - (n >>> 1);
                  return;
              }
              if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
                  if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
                      return;
                  finishing = advance = true;
                  i = n; // recheck before commit
              }
          }
          else if ((f = tabAt(tab, i)) == null)
              advance = casTabAt(tab, i, null, fwd);
          else if ((fh = f.hash) == MOVED)
              advance = true; // already processed
          else {
              synchronized (f) {
                  if (tabAt(tab, i) == f) {
                      Node<K,V> ln, hn;
                      if (fh >= 0) {
                          int runBit = fh & n;
                          Node<K,V> lastRun = f;
                          for (Node<K,V> p = f.next; p != null; p = p.next) {
                              int b = p.hash & n;
                              if (b != runBit) {
                                  runBit = b;
                                  lastRun = p;
                              }
                          }
                          if (runBit == 0) {
                              ln = lastRun;
                              hn = null;
                          }
                          else {
                              hn = lastRun;
                              ln = null;
                          }
                          for (Node<K,V> p = f; p != lastRun; p = p.next) {
                              int ph = p.hash; K pk = p.key; V pv = p.val;
                              if ((ph & n) == 0)
                                  ln = new Node<K,V>(ph, pk, pv, ln);
                              else
                                  hn = new Node<K,V>(ph, pk, pv, hn);
                          }
                          setTabAt(nextTab, i, ln);
                          setTabAt(nextTab, i + n, hn);
                          setTabAt(tab, i, fwd);
                          advance = true;
                      }
                      else if (f instanceof TreeBin) {
                          TreeBin<K,V> t = (TreeBin<K,V>)f;
                          TreeNode<K,V> lo = null, loTail = null;
                          TreeNode<K,V> hi = null, hiTail = null;
                          int lc = 0, hc = 0;
                          for (Node<K,V> e = t.first; e != null; e = e.next) {
                              int h = e.hash;
                              TreeNode<K,V> p = new TreeNode<K,V>
                                  (h, e.key, e.val, null, null);
                              if ((h & n) == 0) {
                                  if ((p.prev = loTail) == null)
                                      lo = p;
                                  else
                                      loTail.next = p;
                                  loTail = p;
                                  ++lc;
                              }
                              else {
                                  if ((p.prev = hiTail) == null)
                                      hi = p;
                                  else
                                      hiTail.next = p;
                                  hiTail = p;
                                  ++hc;
                              }
                          }
                          ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :
                              (hc != 0) ? new TreeBin<K,V>(lo) : t;
                          hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
                              (lc != 0) ? new TreeBin<K,V>(hi) : t;
                          setTabAt(nextTab, i, ln);
                          setTabAt(nextTab, i + n, hn);
                          setTabAt(tab, i, fwd);
                          advance = true;
                      }
                  }
              }
          }
      }
  }
  /**
   * Returns a list on non-TreeNodes replacing those in given list.
   */
  static <K,V> Node<K,V> untreeify(Node<K,V> b) {
      Node<K,V> hd = null, tl = null;
      for (Node<K,V> q = b; q != null; q = q.next) {
          Node<K,V> p = new Node<K,V>(q.hash, q.key, q.val, null);
          if (tl == null)
              hd = p;
          else
              tl.next = p;
          tl = p;
      }
      return hd;
  }
  /**
   * Helps transfer if a resize is in progress.
   */
  final Node<K,V>[] helpTransfer(Node<K,V>[] tab, Node<K,V> f) {
      Node<K,V>[] nextTab; int sc;
      if (tab != null && (f instanceof ForwardingNode) && (nextTab = ((ForwardingNode<K,V>)f).nextTable) != null) {
          int rs = resizeStamp(tab.length);
          while (nextTab == nextTable && table == tab &&
                 (sc = sizeCtl) < 0) {
              if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
                  sc == rs + MAX_RESIZERS || transferIndex <= 0)
                  break;
              if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1)) {
                  transfer(tab, nextTab);
                  break;
              }
          }
          return nextTab;
      }
      return table;
  }


	private static final int DEFAULT_CAPACITY = 16;

	 /*
   * Encodings for Node hash fields. See above for explanation.
   */
  static final int MOVED     = -1; // hash for forwarding nodes
  static final int TREEBIN   = -2; // hash for roots of trees
  static final int RESERVED  = -3; // hash for transient reservations

  
	/**
	 * compareAndSwapInt 和 Thread.yield()
	 * 保证操作table的唯一性
	 * */
	@SuppressWarnings("unchecked")
	private final Node<K, V>[] initTable() {
		Node<K, V>[] tab;
		int sc;
		while ((tab = table) == null || tab.length == 0) {
			// 默认的情况下sizeCtl=0
			if ((sc = sizeCtl) < 0)
				Thread.yield(); // lost initialization race; just spin
			else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
				try {
					if ((tab = table) == null || tab.length == 0) {
						int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
						Node<K, V>[] nt = (Node<K, V>[]) new Node<?, ?>[n];
						table = tab = nt;
						sc = n - (n >>> 2);
					}
				} finally {
					sizeCtl = sc;
				}
				break;
			}
		}
		return tab;
	}

	/** Implementation for put and putIfAbsent */
	final V putVal(K key, V value, boolean onlyIfAbsent) {
		if (key == null || value == null)
			throw new NullPointerException();
		int hash = spread(key.hashCode());
		int binCount = 0;

		for (Node<K, V>[] tab = table;;) {
			Node<K, V> f;
			int n, i, fh;
			if (tab == null || (n = tab.length) == 0)
				// 初始化table
				tab = initTable();
			else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
				//原子性的确定，通过CAS的操作
				if (casTabAt(tab, i, null, new Node<K, V>(hash, key, value, null)))
					break; // no lock when adding to empty bin
			} else if ((fh = f.hash) == MOVED)
				tab = helpTransfer(tab, f);
			else {
				V oldVal = null;
				//锁建立在f即是某一个节点上面，只是针对该插入的节点
				synchronized (f) {
					if (tabAt(tab, i) == f) {
						if (fh >= 0) {
							binCount = 1;
							for (Node<K, V> e = f;; ++binCount) {
								K ek;
								// 替换，key值直接判断相同
								if (e.hash == hash && ((ek = e.key) == key || (ek != null && key.equals(ek)))) {
									oldVal = e.val;
									if (!onlyIfAbsent)
										e.val = value;
									break;
								}
								//插入到链表的尾部
								Node<K, V> pred = e;
								if ((e = e.next) == null) {
									pred.next = new Node<K, V>(hash, key, value, null);
									break;
								}
							}
						} else if (f instanceof TreeBin) {
							Node<K, V> p;
							binCount = 2;
							if ((p = ((TreeBin<K, V>) f).putTreeVal(hash, key, value)) != null) {
								oldVal = p.val;
								if (!onlyIfAbsent)
									p.val = value;
							}
						}
					}
				}
				if (binCount != 0) {
					if (binCount >= 8)//TREEIFY_THRESHOLD)
						treeifyBin(tab, i);
					if (oldVal != null)
						return oldVal;
					break;
				}
			}
		}
//		addCount(1L, binCount);
		return null;
	}

	public static void main(String[] args) {
		
		ConcurrentMapImpl<String, String> map = new ConcurrentMapImpl<String, String>();
		map.put("k1", "v1");

	}

	@Override
	public V putIfAbsent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean replace(K key, V oldValue, V newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V replace(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
