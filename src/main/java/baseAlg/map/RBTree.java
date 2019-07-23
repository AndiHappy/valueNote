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
	}

	public void treeify(Node[] tab) {
		Node root = this.root;
		for (int i = 0; i < tab.length; i++) {
			Node current = tab[i];
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
             root = balanceInsertion(root, current);
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
//           	 // TODO 关键就在于什么时间使用左旋，什么时间使用右旋了
//                if (x == xp.right) {
//                    root = rotateLeft(root, x = xp);
//                    xpp = (xp = x.parent) == null ? null : xp.parent;
//                }
//                //TODO 父节点不为null，左旋之后右旋的可能？？
//                if (xp != null) {
//                    xp.red = false;
//                    if (xpp != null) {
//                        xpp.red = true;
//                        root = rotateRight(root, xpp);
//                    }
//                }
//            }
//        } else {
//       	 // 奖项类型的操作
//            if (xppl != null && xppl.red) {
//                xppl.red = false;
//                xp.red = false;
//                xpp.red = true;
//                x = xpp;
//            }
//            else {
//                if (x == xp.left) {
//                    root = rotateRight(root, x = xp);
//                    xpp = (xp = x.parent) == null ? null : xp.parent;
//                }
//                if (xp != null) {
//                    xp.red = false;
//                    if (xpp != null) {
//                        xpp.red = true;
//                        root = rotateLeft(root, xpp);
//                    }
//                }
            }
        }
    }
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
		
		
	}

}
