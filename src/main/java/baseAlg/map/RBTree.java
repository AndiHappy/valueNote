package baseAlg.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 * @param <T>
 *
 */
public class RBTree<T extends Comparable<T>> {

	Node root = null;

	/**
	 *  1. 任何一个节点都有颜色，黑色或者红色 
	 *  2. 根节点是黑色的 
	 *  3. 父子节点之间不能出现两个连续的红节点 
	 *  4. 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等 
	 *  5. 空节点被认为是黑色的
	 */

	public  class Node {
		public Node(T i) {
			this.value = i;
		}
		
		public T value;
		public Node parent;
		public boolean red;
		public Node left;
		public Node right;
		
		@Override
		public String toString() {
			return "value: " +value.toString();
		}
		
		void print(){
			System.out.println("value: "+ this.value + " left: " + (this.left == null?"null":this.left.value) + " right: " + (this.right == null?"null":this.right.value));
			if(this.left != null) {
				this.left.print();
			}
			
			if(this.right != null) {
				this.right.print();
			}
		}
	}

	public void treeify(List<RBTree<Integer>.Node> tab) {
		Node root = this.root;
		for (int i = 0; i < tab.size(); i++) {
			Node current = (RBTree<T>.Node) tab.get(i);
			// 如果根节点为null的时候，直接设置root节点，并且x节点为黑节点
			if (root == null) {
				current.parent = null;
				current.red = false;
				root = current;
				this.root = root;
			}else {
				T tv = current.value;
				for (Node node=root ;;) {
					int dir = root.value.compareTo(tv);
					 Node nodep = node;
           if ((node = (dir <= 0) ? node.left : node.right) == null) {
          	 current.parent = nodep;
          	 if(dir <=0) 
          		 nodep.left = current;
          	 else
          		 nodep.right = current;
          	 //检查插入的是否破坏了规则
          	 System.out.println("---------------------------------------------------------------------------------------------");

          	 root.print();
          	 
             root = balanceInsertion(root, current);
          	 System.out.println("---------------------------------------------------------------------------------------------");

             root.print();
             break;
           }
				}
			}
		}
	}


	private RBTree<T>.Node balanceInsertion(RBTree<T>.Node root, RBTree<T>.Node x) {
	// 插入的节点为红色的节点
    x.red = true;
    // x ，xp(x parent) xpp（x parent parent） xppl （x parent parent left） xppr （x parent parent right）
    for (Node xp, xpp, xppl, xppr;;) {
   	 	//如果x为根节点
        if ((xp = x.parent) == null) {
            x.red = false;
            return x;
        }// 父节点为黑色的节点 或者 父节点的父节点即爷爷节点为null，也就是两层的数据结构
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
           	 // TODO 右孩子使用左旋
                if (x == xp.right) {
                    root = rotateLeft(root, x = xp);
                    xpp = (xp = x.parent) == null ? null : xp.parent;
                }
                //配合着一系列的变色的操作，已知的叶子节点为红色，则父节点设置为黑色，爷爷节点设置为红色
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
   Node rotateLeft(Node root,Node p) {
      Node r, pp, rl;
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
    Node rotateRight(Node root,Node p) {
      Node l, pp, lr;
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

	public static void main(String[] args) {
		RBTree<Integer> tree = new RBTree<Integer>();
		tree.treeifyTest();

	}


	private void treeifyTest() {
		List<RBTree<Integer>.Node> table = new ArrayList<RBTree<Integer>.Node>();
		for (int i = 0; i < 20; i++) {
			RBTree<Integer>.Node e = new RBTree.Node(i);
			table.add(e);
		}
		
		treeify(table);
		
		
	}

}
