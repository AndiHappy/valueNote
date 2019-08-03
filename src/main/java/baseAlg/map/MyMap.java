package baseAlg.map;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author guizhai
 * 
 */
public class MyMap<K,V> {
	
	
	/**
   * The default initial capacity - MUST be a power of two.
   */
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

  /**
   * The maximum capacity, used if a higher value is implicitly specified
   * by either of the constructors with arguments.
   * MUST be a power of two <= 1<<30.
   */
  static final int MAXIMUM_CAPACITY = 1 << 30;

  /**
   * The load factor used when none specified in constructor.
   */
  static final float DEFAULT_LOAD_FACTOR = 0.75f;

		
	 // hashtable 中的table[] 链表的数据结构转化为 treeNode的数据结构的限制性数量
	//  6个及以下为链表的数据结构，7个为临界数据，组装为链表之后，马上开始变化数据结构。
	 static final int TREEIFY_THRESHOLD = 8;
	 
	 //TODO  链表的数据结构转化为 treeNode的数据结构 的hash的length最小也要64个？
	 static final int MIN_TREEIFY_CAPACITY = 64;
	 
   static final int UNTREEIFY_THRESHOLD = 6;


   transient Set<Map.Entry<K,V>> entrySet;
   transient int size;
   transient int modCount;
   int threshold;
   final float loadFactor=0;
	// >>> 无符号右移，忽略符号位，空位都以0补齐
	// h 为key的值hashcode，因为table是2的幂次方，
	
	/**
   * 3288498===> 3288448
   * 
   * 1100100010110110110010
   * 0000000000000000110010
   * 1100100010110110000000
   * 
   * 高中低位的字符全部的参与进来，把key的hash值打散
   */
	 static final int hash(Object key) {
		
//     int h;
//     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		 
     /**
		  * 为了测试，hash值一律的返回0
		  * */
     return 0;
 }
	 
	 // Callbacks to allow LinkedHashMap post-actions
   void afterNodeAccess(Node<K,V> p) { }
   void afterNodeInsertion(boolean evict) { }
   void afterNodeRemoval(Node<K,V> p) { }

	 
	 
	 /**
	  * Node 为节点
	  * Map.Entry<K,V> 为节点需要满足的条件：getKey getValue setValue equals等
	  * */
	public static class Node<K,V>  {
     public final int hash; // 包含一个hash值
     public final K key;
     public V value;
     public Node<K,V> next; // 链表的结构

     Node(int hash, K key, V value, Node<K,V> next) {
         this.hash = hash;
         this.key = key;
         this.value = value;
         this.next = next;
     }

     public final K getKey()        { return key; }
     public final V getValue()      { return value; }
     
     public final Node<K,V> geNext()        { return next; }
     
     public String toString() { return key + "=" + value; }

     // 这个hashcode是做什么的？
     public final int hashCode() {
         return Objects.hashCode(key) ^ Objects.hashCode(value);
     }

     public final V setValue(V newValue) {
         V oldValue = value;
         value = newValue;
         return oldValue;
     }

     // 比对的时候，采用的是：Objects.equals 方法，比对的是key 和 value
     public final boolean equals(Object o) {
         if (o == this)
             return true;
         if (o instanceof Map.Entry) {
             Map.Entry<?,?> e = (Map.Entry<?,?>)o;
             if (Objects.equals(key, e.getKey()) &&
                 Objects.equals(value, e.getValue()))
                 return true;
         }
         return false;
     }
 }
	 
// Create a regular (non-tree) node
   Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
       return new Node<>(hash, key, value, next);
   }
	////////////////////////////////////// 	以上为辅助的数据结构	////////////////////////////////////// 
 
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
               ((Comparable<Object>)k).compareTo(x));
   }
   
   // Create a tree bin node
   TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
       return new TreeNode<>(hash, key, value, next);
   }
   
// For conversion from TreeNodes to plain nodes
   Node<K,V> replacementNode(Node<K,V> p, Node<K,V> next) {
       return new Node<>(p.hash, p.key, p.value, next);
   }
   
   /**
    * HashMap.Node subclass for normal LinkedHashMap entries.
    */
   public static class Entry<K,V> extends Node<K,V> {
       Entry<K,V> before, after;
       Entry(int hash, K key, V value, Node<K,V> next) {
           super(hash, key, value, next);
       }
   }
   
	 // 非序列化
	 transient Node<K,V>[] table;
	 
	 public V put(K key, V value) {
     return putVal(hash(key), key, value, false, true);
 }
	 
	 
	 /**
		 *  1. 任何一个节点都有颜色，黑色或者红色 
		 *  2. 根节点是黑色的 
		 *  3. 父子节点之间不能出现两个连续的红节点 
		 *  4. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等 
		 *  5. 空节点被认为是黑色的
		 */
	public static class TreeNode<K,V> extends Entry<K,V> {
     public TreeNode<K,V> parent;  // red-black tree links
     public TreeNode<K,V> left;
     public TreeNode<K,V> right;
     public TreeNode<K,V> prev;    // needed to unlink next upon deletion
     public boolean red;
     TreeNode(int hash, K key, V val, Node<K,V> next) {
         super(hash, key, val, next);
     }
     
     /**
      * Returns root of tree containing this node.
      */
     final TreeNode<K,V> root() {
         for (TreeNode<K,V> r = this, p;;) {
             if ((p = r.parent) == null)
                 return r;
             r = p;
         }
     }

     /**
      * Ensures that the given root is the first node of its bin.
      * 给定的root节点为table的链表的第一个节点
      * root的next原来的节点为first节点
      * root节点在原来的链表的序列中被删除。
      * 链表中的节点
      */
     static <K,V> void moveRootToFront(Node<K,V>[] tab, TreeNode<K,V> root) {
         int n;
         if (root != null && tab != null && (n = tab.length) > 0) {
             int index = (n - 1) & root.hash;
             //原来的first节点
             TreeNode<K,V> first = (TreeNode<K,V>)tab[index];
             if (root != first) {
                 Node<K,V> rn;
                 // 设置root接点为tab[index]，既是第一个节点
                 tab[index] = root;
                 TreeNode<K,V> rp = root.prev;
                 
                 /**
                  *  rp-> root -> rn 跳过了root节点：rn.prev = rp 
                  * */
                 if ((rn = root.next) != null)
                     ((TreeNode<K,V>)rn).prev = rp;
                 if (rp != null)
                     rp.next = rn;
                 
                 if (first != null)
                     first.prev = root;
                 root.next = first;
                 root.prev = null;
             }
             assert checkInvariants(root);
         }
     }

     /**
      * Finds the node starting at root p with the given hash and key.
      * The kc argument caches comparableClassFor(key) upon first use
      * comparing keys.
      */
     final TreeNode<K,V> find(int h, Object k, Class<?> kc) {
         TreeNode<K,V> p = this;
         do {
             int ph, dir; K pk;
             TreeNode<K,V> pl = p.left, pr = p.right, q;
             if ((ph = p.hash) > h)
                 p = pl;
             else if (ph < h)
                 p = pr;
             else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                 return p;
             else if (pl == null)
                 p = pr;
             else if (pr == null)
                 p = pl;
             else if ((kc != null ||
                       (kc = comparableClassFor(k)) != null) &&
                      (dir = compareComparables(kc, k, pk)) != 0)
                 p = (dir < 0) ? pl : pr;
             else if ((q = pr.find(h, k, kc)) != null)
                 return q;
             else
                 p = pl;
         } while (p != null);
         return null;
     }

     /**
      * Calls find for root node.
      */
     final TreeNode<K,V> getTreeNode(int h, Object k) {
         return ((parent != null) ? root() : this).find(h, k, null);
     }

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
      * Forms tree of the nodes linked from this node.
      */
     final void treeify(Node<K,V>[] tab) {
    	 	// 根节点
         TreeNode<K,V> root = null;
         for (TreeNode<K,V> current = this, next; current != null; current = next) {
             next = (TreeNode<K,V>)current.next;
             current.left = current.right = null;
             // 如果根节点为null的时候，直接设置root节点，并且x节点为黑节点
             if (root == null) {
                 current.parent = null;
                 current.red = false;
                 root = current;
             }
             else {
                 K k = current.key;
                 int h = current.hash;
                 Class<?> kc = null;
                 for (TreeNode<K,V> p = root;;) {
                	 
                	 	 //根据hash值的大小，计算dir的值，如果hash值相同的情况下，再次通过一些列复杂的计算
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
                     
                     //寻找到插入的父节点，dir小于0为左节点，大于零是右节点
                     TreeNode<K,V> xp = p;
                     if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    	 	 // 为当前的节点，设置父节点，然后根据dir设置在xp的孩子节点上
                         current.parent = xp;
                         if (dir <= 0)
                             xp.left = current;
                         else
                             xp.right = current;
                         //检查插入的是否破坏了规则
                         root = balanceInsertion(root, current);
                         break;
                     }
                 }
             }
         }
         moveRootToFront(tab, root);
     }

     /**
      * Returns a list of non-TreeNodes replacing those linked from
      * this node.
      */
     final Node<K,V> untreeify(MyMap<K,V> map) {
         Node<K,V> hd = null, tl = null;
         for (Node<K,V> q = this; q != null; q = q.next) {
             Node<K,V> p = map.replacementNode(q, null);
             if (tl == null)
                 hd = p;
             else
                 tl.next = p;
             tl = p;
         }
         return hd;
     }

     /**
      * Tree version of putVal.
      */
     final TreeNode<K,V> putTreeVal(MyMap<K,V> map, Node<K,V>[] tab,int h, K k, V v) {
         Class<?> kc = null;
         boolean searched = false;
         TreeNode<K,V> root = (parent != null) ? root() : this;
         for (TreeNode<K,V> p = root;;) {
             int dir, ph; K pk;
             if ((ph = p.hash) > h)
                 dir = -1;
             else if (ph < h)
                 dir = 1;
             else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                 return p;
             else if ((kc == null &&
                       (kc = comparableClassFor(k)) == null) ||
                      (dir = compareComparables(kc, k, pk)) == 0) {
                 if (!searched) {
                     TreeNode<K,V> q, ch;
                     searched = true;
                     if (((ch = p.left) != null &&
                          (q = ch.find(h, k, kc)) != null) ||
                         ((ch = p.right) != null &&
                          (q = ch.find(h, k, kc)) != null))
                         return q;
                 }
                 dir = tieBreakOrder(k, pk);
             }
             
             /**
              * p当前的节点，插入的节点，if语句中p为null
              * */
             TreeNode<K,V> xp = p;
             if ((p = (dir <= 0) ? p.left : p.right) == null) {
            	 	// 新节点的next为原来节点的next，这个比较的有意思，即使在treeNode的节点里面还保持着next的有效性
                 Node<K,V> xpn = xp.next;
                 TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);//③
                 if (dir <= 0)
                     xp.left = x;
                 else
                     xp.right = x;
                 
                 // xp的next为x，原来的xp的next在建立的新的节点，设置为了next 对应的语句是③
                 xp.next = x;
                 // x的pre为parent 可以理解，顺着x的pre可以找到根节点
                 x.parent = x.prev = xp;
                 
                 if (xpn != null)
                     ((TreeNode<K,V>)xpn).prev = x;
                 
                 // 确定table的第一个节点为root节点
                 moveRootToFront(tab, balanceInsertion(root, x));
                 
                 return null;
             }
         }
     }

     /**
      * Removes the given node, that must be present before this call.
      * This is messier than typical red-black deletion code because we
      * cannot swap the contents of an interior node with a leaf
      * successor that is pinned by "next" pointers that are accessible
      * independently during traversal. So instead we swap the tree
      * linkages. If the current tree appears to have too few nodes,
      * the bin is converted back to a plain bin. (The test triggers
      * somewhere between 2 and 6 nodes, depending on tree structure).
      
      
				删除在此调用之前必须存在的给定节点。
				
				这比典型的红黑删除代码更加混乱，因为我们无法将内部节点的内容与叶子后继交换，
				后者由“下一个”指针固定，这些指针可以在此期间独立访问，遍历。
				所以我们交换树链接。
				
				如果当前树似乎有太少的节点，则bin将转换回普通bin。 （测试会在2到6个节点之间触发，具体取决于树结构）。
      
      */
     final void removeTreeNode(MyMap<K,V> map, Node<K,V>[] tab,boolean movable) {
         int n;
         if (tab == null || (n = tab.length) == 0)
             return;
         
         // 所在的位置
         int index = (n - 1) & hash;
         
         //root节点 tab[index]
         TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
         
         // 链表数据结构的节点
         TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
         
         /**
          * 如果pred为null，说明当前的节点为tab[index]的第一个节点，也就是root节点。
          * 如果不是，则pred.next = succ，现在链表中删除当前的节点
          * */
         if (pred == null)
             tab[index] = first = succ;
         else
             pred.next = succ;
         if (succ != null)
             succ.prev = pred;
         
         // 如果first为null的情况下，这个链表或者root已经为null，直接的返回了
         if (first == null)
             return;
         
         if (root.parent != null)
             root = root.root();
         
         if (root == null || (movable&& (root.right == null|| (rl = root.left) == null || rl.left == null))) {
        	 	 // 由树转化为链表的数据结构
             tab[index] = first.untreeify(map);  // too small
             return;
         }
         
         TreeNode<K,V> p = this, pl = left, pr = right, replacement;
         
         /**
          * 需要寻找替代的节点，删除节点的过程就是，让原有的节点替换掉现在的节点即可
          * 如果是叶子节点，即是：pr==null && pl == null  replacement就是p
          * 如果是左节点或者右节点不为null，但是有一个为null，即是：pr==null&&pl != null || pl == null && pr != null replacement就是p的不空的那个孩子
          * 
          * 如果左右节点均不为null，即是：pl != null && pr != null 替换节点首先确定是右子树的的最左孩子，
          * 可以保证该孩子在右子树中最小
          * 
          * */
         /////// /////// /////// /////// 寻找 replacement 元素 begine  /////// /////// /////// ///////
         if (pl != null && pr != null) {
             TreeNode<K,V> s = pr, sl;
             while ((sl = s.left) != null) // find successor 继任者
                 s = sl;
             boolean c = s.red; s.red = p.red; p.red = c; // swap colors
             
             TreeNode<K,V> sr = s.right;
             TreeNode<K,V> pp = p.parent;
             if (s == pr) { // p was s's direct parent
            	 	//s==pr 说ming pr没有左节点，可能存在右节点，然后直接的交换位置
                 p.parent = s;
                 s.right = p;
             }else {
            	 	// 此时的else逻辑下：
            	  // s存在左节点,把对应的s和p节点互换位置，
            	 // 设置p对应的parent，对应的子节点，
            	 // 设置s对应的右节点，对应的父节点
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
             
             //此时的p的位置为原来S的位置，left设置为null，属于正常的操作
             p.left = null;
             
             if ((p.right = sr) != null)
                 sr.parent = p;
             if ((s.left = pl) != null)
                 pl.parent = s;
             if ((s.parent = pp) == null)
                 root = s;
             else if (p == pp.left)
                 pp.left = s;
             else
                 pp.right = s;
             
             // 没有看明白
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
         
         /////// /////// /////// /////// 寻找 replacement 元素 end  /////// /////// /////// ///////
         
         // 如果replace不等于p，下面的逻辑就是：使用replace元素替换p元素的过程
         if (replacement != p) {
             TreeNode<K,V> pp = replacement.parent = p.parent;
             if (pp == null)
                 root = replacement;
             else if (p == pp.left)
                 pp.left = replacement;
             else
                 pp.right = replacement;
             
             // 全部值null
             p.left = p.right = p.parent = null;
         }

         TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);
         
         // 如果替代品，或者说可以删除着为p，说明p为叶子节点，那么只需要设置p.parent 以pp的指向即可
         if (replacement == p) {  // detach
             TreeNode<K,V> pp = p.parent;
             p.parent = null;
             if (pp != null) {
                 if (p == pp.left)
                     pp.left = null;
                 else if (p == pp.right)
                     pp.right = null;
             }
         }
         if (movable)
             moveRootToFront(tab, r);
     }

    private void print(int i) {
    List<TreeNode> queue = new ArrayList<TreeNode>();
    queue.add(this);
    while(!queue.isEmpty()) {
    	
    }
			
		}

		/**
      * Splits nodes in a tree bin into lower and upper tree bins,
      * or untreeifies if now too small. Called only from resize;
      * see above discussion about split bits and indices.
      *
      * @param baseAlg.map the baseAlg.map
      * @param tab the table for recording bin heads
      * @param index the index of the table being split
      * @param bit the bit of hash to split on
      */
     final void split(MyMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
         TreeNode<K,V> b = this;
         // Relink into lo and hi lists, preserving order
         TreeNode<K,V> loHead = null, loTail = null;
         TreeNode<K,V> hiHead = null, hiTail = null;
         int lc = 0, hc = 0;
         for (TreeNode<K,V> e = b, next; e != null; e = next) {
             next = (TreeNode<K,V>)e.next;
             e.next = null;
             if ((e.hash & bit) == 0) {
                 if ((e.prev = loTail) == null)
                     loHead = e;
                 else
                     loTail.next = e;
                 loTail = e;
                 ++lc;
             }
             else {
                 if ((e.prev = hiTail) == null)
                     hiHead = e;
                 else
                     hiTail.next = e;
                 hiTail = e;
                 ++hc;
             }
         }

         if (loHead != null) {
             if (lc <= UNTREEIFY_THRESHOLD)
                 tab[index] = loHead.untreeify(map);
             else {
                 tab[index] = loHead;
                 if (hiHead != null) // (else is already treeified)
                     loHead.treeify(tab);
             }
         }
         if (hiHead != null) {
             if (hc <= UNTREEIFY_THRESHOLD)
                 tab[index + bit] = hiHead.untreeify(map);
             else {
                 tab[index + bit] = hiHead;
                 if (loHead != null)
                     hiHead.treeify(tab);
             }
         }
     }

     /* ------------------------------------------------------------ */
     // Red-black tree methods, all adapted from CLR
     /* 
      *
      * 左旋示意图(对节点P进行左旋)：
      *      px                              px
      *     /                               /
      *    p                               y                
      *   /  \      --(左旋)-.            /   \                #
      *  lx   pr                          p    ry     
      *     /   \                       /  \
      *    ly   ry                     lx  ly  
      *
      *
      */
     static <K,V> TreeNode<K,V> rotateLeft(TreeNode<K,V> root,TreeNode<K,V> p) {
         TreeNode<K,V> r, pp, rl;
         if (p != null && (r = p.right) != null) {
             if ((rl = p.right = r.left) != null) 
                 rl.parent = p;// p.right 更改为r.left 也即是（p.right.left）,并且把p.right.left的parent指向p
             if ((pp = r.parent = p.parent) == null) //判定特殊的情况，置黑根节点
                 (root = r).red = false;
             else if (pp.left == p) // 旋转上去，r替换p的位置
                 pp.left = r;
             else
                 pp.right = r;
             r.left = p;// 左旋的标志动作，旋上去的左孩子为原来的节点p
             p.parent = r;
         }
         return root;
     }

     /* 
      *
      * 右旋示意图(对节点p进行右旋)：
      *      px                              px
      *       \                                \
      *        p                                y                
      *      /   \                             /   \                #
      *     y     pr                          ly    p     
      *   /   \                                    /  \
      *  ly   ry                                  ry  pr  
      *
      *
      */
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

     /**
      * 平衡插入：
      * 左子树插入操作-case 1
         case 1的操作是将父节点和叔叔节点与祖父节点的颜色互换，这样就符合了RBTRee的定义。即维持了高度的平衡，
         修复后颜色也符合RBTree定义的第三条和第四条。如下图： 
             root                              
            /    \                             
          rootx  rootr                                
          /  \                           
         x   y 
        /                           
       i 
         case 2的操作是将B节点进行右旋操作，并且和父节点A互换颜色。通过该修复操作RBTRee的高度和颜色都符合红黑树的定义。
         如果B和C节点都是右节点的话，只要将操作变成左旋就可以了。
         
         case 3的操作是将C节点进行左旋，这样就从case 3转换成case 2了，然后针对case 2进行操作处理就行了。
         case 2操作做了一个右旋操作和颜色互换来达到目的。如果树的结构是下图的镜像结构，则只需要将对应的左旋变成右旋，右旋变成左旋即可。
         
         插入操作的总结
插入后的修复操作是一个向root节点回溯的操作，一旦牵涉的节点都符合了红黑树的定义，修复操作结束。之所以会向上回溯是由于case 1操作会将父节点，叔叔节点和祖父节点进行换颜色，有可能会导致祖父节点不平衡(红黑树定义3)。这个时候需要对祖父节点为起点进行调节（向上回溯）。
祖父节点调节后如果还是遇到它的祖父颜色问题，操作就会继续向上回溯，直到root节点为止，根据定义root节点永远是黑色的。在向上的追溯的过程中，针对插入的3中情况进行调节。直到符合红黑树的定义为止。直到牵涉的节点都符合了红黑树的定义，修复操作结束。
      * */
     static <K,V> TreeNode<K,V> balanceInsertion(TreeNode<K,V> root,TreeNode<K,V> x) {
    	 // 插入的节点为红色的节点
         x.red = true;
         // x ，xp(x parent) xpp（x parent parent） xppl （x parent parent left） xppr （x parent parent right）
         for (TreeNode<K,V> xp, xpp, xppl, xppr;;) {
        	 	//如果x为根节点
             if ((xp = x.parent) == null) {
                 x.red = false;
                 return x;
             }// x为红色节点，并且挂在root节点下
             else if (!xp.red || (xpp = xp.parent) == null) {
            	 return root;
             }
             /*
              
             				     xpp
             				  /      \
             		xppl(xp)      xppr
             			/   \       /   \
             		xpr(x)          
             		
               */
             
             // 确定x的父节点是左孩子还是右孩子
             if (xp == (xppl = xpp.left)) {
            	   //变色的情况是：当前节点为红色，父节点处于左节点 爷爷节的右节点存在且为红色节点
            	 	 //变色完成后的情况是：当前节点为爷爷节点
                 if ((xppr = xpp.right) != null && xppr.red) {
                     xppr.red = false;
                     xp.red = false;
                     xpp.red = true;
                     x = xpp;
                 }else {
                	 // TODO 关键就在于什么时间使用左旋，什么时间使用右旋了
                     if (x == xp.right) {
                         root = rotateLeft(root, x = xp);
                         xpp = (xp = x.parent) == null ? null : xp.parent;
                     }
                     //TODO 父节点不为null，左旋之后右旋的可能？？
                     if (xp != null) {
                         xp.red = false;
                         if (xpp != null) {
                             xpp.red = true;
                             root = rotateRight(root, xpp);
                         }
                     }
                 }
             } else {
            	 // 奖项类型的操作
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

     /**
      * 删除之后的平衡操作
      * */
     static <K,V> TreeNode<K,V> balanceDeletion(TreeNode<K,V> root,TreeNode<K,V> x) {
         for (TreeNode<K,V> xp, xpl, xpr;;) {
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
 }

// For treeifyBin
   TreeNode<K,V> replacementTreeNode(Node<K,V> p, Node<K,V> next) {
       return new TreeNode<>(p.hash, p.key, p.value, next);
   }
	 /**
    * Replaces all linked nodes in bin at index for given hash unless
    * table is too small, in which case resizes instead.
    */
   public final void treeifyBin(Node<K,V>[] tab, int hash) {
  	 	this.table = tab;
       int n, index; Node<K,V> e;
//       if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
       if (tab == null || (n = tab.length) < 1)
           resize();
       else if ((e = tab[index = (n - 1) & hash]) != null) {
           TreeNode<K,V> hd = null, tl = null;
           // 先把Node节点全部的转化为TreeNode节点
           do {
               TreeNode<K,V> p = replacementTreeNode(e, null);
               if (tl == null)
                   hd = p;
               else {
                   p.prev = tl;
                   tl.next = p;
               }
               tl = p;
           } while ((e = e.next) != null);
           
           // 此时的hd为1->2->3->...->end 为链表的数据结构
           if ((tab[index] = hd) != null)
          	 	 // 链状的TreeNode节点转为为树状
               hd.treeify(tab);
           		 System.out.println(tab[index]);
           		 System.out.println(hd);
       }
   }
	 
	 /**
    * Implements Map.put and related methods.
    * put逻辑：
    * 	1. 检查hashmap中的table是否需要扩容
    * 	2.1 确定放入的值的位置：(n - 1) & hash，hash的计算方式：(h = key.hashCode()) ^ (h >>> 16)
    * 	2.2 如果该位置没有值，直接的新建Node，设置key，value值，放入table
    * 	2.3 如果该位置有Node值，判断key值有否相同
    * 		2.3.1  相同则是替换value值
    *     2.3.2  不相同则是判断Node值的类型
    *     		2.3.2.1 如果Node值的类性是：TreeNode，标识为一颗红黑树的类型，调用putTreeVal
    */
   final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) {
  	 
       Node<K,V>[] tab; 
       Node<K,V> p; 
       int n,/* n 代表的是原始的hashmap中 table 的 length值*/ i;
       
       if ((tab = table) == null || (n = tab.length) == 0)
      	 	//扩容
           n = (tab = resize()).length;
       // & 值的运算：得到的值只可能比n-1小,如果table[i]为null,直接的赋值即可。
       if ((p = tab[i = (n - 1) & hash]) == null)
           tab[i] = newNode(hash, key, value, null);
       else {
      	 
           Node<K,V> e; 
           K k;
           // p=table[i]; hash值相同，k值和p的key值一样，直接的替换
           if (p.hash == hash &&((k = p.key) == key || (key != null && key.equals(k))))
          	 	//替换value值
          	 	e = p;
           else if (p instanceof TreeNode)
          	 	// 如果有值，并且Node的类型是TreeNode类型，调用TreeNode的putTreeVal
          	  // TODO 暂时的放弃这个分支的分析
               e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
           else {
          	 // 如果不是TreeNode，那就是一个链表的类型
               for (int binCount = 0; ; ++binCount) {
                   if ((e = p.next) == null) {
                       p.next = newNode(hash, key, value, null);
                       // 如果达到了7个，开始变换数据结构
                       if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                      	 	 //传入的参数竟然是table和hash值
                           treeifyBin(tab, hash);
                       break;
                   }
                   if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
                       break;
                   p = e;
               }
           }
           
           if (e != null) { // existing mapping for key
               V oldValue = e.value;
               if (!onlyIfAbsent || oldValue == null)
                   e.value = value;
//               afterNodeAccess(e);
               return oldValue;
           }
       }
       ++modCount;
       if (++size > threshold)
           resize();
       afterNodeInsertion(evict);
       return null;
   }

	 
   /**
    * TODO 梳理扩容的逻辑
    * */
   final Node<K,V>[] resize() {
     Node<K,V>[] oldTab = table;
     int oldCap = (oldTab == null) ? 0 : oldTab.length;
     int oldThr = threshold;
     int newCap, newThr = 0;
     if (oldCap > 0) {
         if (oldCap >= MAXIMUM_CAPACITY) {
             threshold = Integer.MAX_VALUE;
             return oldTab;
         }
         else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                  oldCap >= DEFAULT_INITIAL_CAPACITY)
             newThr = oldThr << 1; // double threshold
     }
     else if (oldThr > 0) // initial capacity was placed in threshold
         newCap = oldThr;
     else {               // zero initial threshold signifies using defaults
         newCap = DEFAULT_INITIAL_CAPACITY;
         newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
     }
     if (newThr == 0) {
         float ft = (float)newCap * loadFactor;
         newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                   (int)ft : Integer.MAX_VALUE);
     }
     threshold = newThr;
     @SuppressWarnings({"rawtypes","unchecked"})
     Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
     table = newTab;
     if (oldTab != null) {
         for (int j = 0; j < oldCap; ++j) {
             Node<K,V> e;
             if ((e = oldTab[j]) != null) {
                 oldTab[j] = null;
                 if (e.next == null)
                     newTab[e.hash & (newCap - 1)] = e;
                 else if (e instanceof TreeNode)
                     ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                 else { // preserve order
                     Node<K,V> loHead = null, loTail = null;
                     Node<K,V> hiHead = null, hiTail = null;
                     Node<K,V> next;
                     do {
                         next = e.next;
                         if ((e.hash & oldCap) == 0) {
                             if (loTail == null)
                                 loHead = e;
                             else
                                 loTail.next = e;
                             loTail = e;
                         }
                         else {
                             if (hiTail == null)
                                 hiHead = e;
                             else
                                 hiTail.next = e;
                             hiTail = e;
                         }
                     } while ((e = next) != null);
                     if (loTail != null) {
                         loTail.next = null;
                         newTab[j] = loHead;
                     }
                     if (hiTail != null) {
                         hiTail.next = null;
                         newTab[j + oldCap] = hiHead;
                     }
                 }
             }
         }
     }
     return newTab;
 }
   
   // 删除的逻辑
   public V remove(Object key) {
     Node<K,V> e;
     return (e = removeNode(hash(key), key, null, false, true)) == null ?
         null : e.value;
 }
   
   /**
    * Implements Map.remove and related methods.
    *
    * @param hash hash for key
    * @param key the key
    * @param value the value to match if matchValue, else ignored
    * @param matchValue if true only remove if value is equal
    * @param movable if false do not move other nodes while removing
    * @return the node, or null if none
    */
   final Node<K,V> removeNode(int hash, Object key, Object value, boolean matchValue, boolean movable) {
       Node<K,V>[] tab; Node<K,V> p; int n, index;
       
       if ((tab = table) != null && (n = tab.length) > 0 && (p = tab[index = (n - 1) & hash]) != null) {
      	 
           Node<K,V> node = null, e; K k; V v;
           // finde node value 
           if (p.hash == hash &&((k = p.key) == key || (key != null && key.equals(k))))
               node = p;
           else if ((e = p.next) != null) {
               if (p instanceof TreeNode)
                   node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
               else {
                   do {
                       if (e.hash == hash &&((k = e.key) == key ||(key != null && key.equals(k)))) {
                           node = e;
                           break;
                       }
                       p = e;
                   } while ((e = e.next) != null);
               }
           }
           
           // !matchValue || (v = node.value) == value
           // node为key值对应的节点，p为key的前一个节点
           if (node != null && (!matchValue || (v = node.value) == value ||(value != null && value.equals(v)))) {
               if (node instanceof TreeNode)
              	 	// node 为当前的节点
                   ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
               else if (node == p)
                   tab[index] = node.next;
               else
                   p.next = node.next;
               
               ++modCount;
               --size;
//               afterNodeRemoval(node);
               return node;
           }
       }
       return null;
   }
   
   
   
	/**
	 * @param args
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static void main(String[] args) {
//		Node[] tab = new Node[20];
//		int hash = 11;
//		Node<String, String> next15= new Node<String, String>(hash,"11","15",null);
//		Node<String, String> next14= new Node<String, String>(hash,"11","14",next15);
//		Node<String, String> next13= new Node<String, String>(hash,"11","13",next14);
//		Node<String, String> next12 = new Node<String, String>(hash,"11","12",next13);
//		Node<String, String> next11 = new Node<String, String>(hash,"11","11",next12);
//		Node<String, String> next10= new Node<String, String>(hash,"11","10",next11);
//		Node<String, String> next9 = new Node<String, String>(hash,"11","9",next10);
//		Node<String, String> next8 = new Node<String, String>(hash,"11","8",next9);
//		Node<String, String> next7= new Node<String, String>(hash,"11","7",next8);
//		Node<String, String> next6 = new Node<String, String>(hash,"11","6",next7);
//		Node<String, String> next5 = new Node<String, String>(hash,"11","5",next6);
//		Node<String, String> next4 = new Node<String, String>(hash,"11","4",next5);
//		Node<String, String> next3 = new Node<String, String>(hash,"11","3",next4);
//		Node<String, String> next2 = new Node<String, String>(hash,"11","3",next3);
//		Node<String, String> next = new Node<String, String>(hash,"11","2",next2);
//		tab[3] = new Node<String, String>(hash, "11", "1", next);
//		TreeNode node = new TreeNode<String, String>(hash, "11", "0", next);
//		MyMap<String, String> mymap = new MyMap<String, String>();
//		mymap.treeifyBin(tab, hash);
		
		//正经的测试
		MyMap<String, String> mymap = new MyMap<String, String>();
		for (int i = 0; i < 30; i++) {
			mymap.put("k"+i, "v"+i);
		}
		System.out.println(mymap.table.length);
		
		mymap.remove("k5");
		
		for (int i = 15; i < 20; i++) {
			if(i == 16) {
				mymap.remove("k"+i);
			}else {
				mymap.remove("k"+i);
			}
			
		}
	}

}