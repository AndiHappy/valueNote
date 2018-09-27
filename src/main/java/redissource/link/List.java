package redissource.link;

/**
 * 双端链表
 * */
public class List {

	// 从表头向表尾进行迭代
	public static final int AL_START_HEAD = 0;
	// 从表尾到表头进行迭代
	public static final int AL_START_TAIL = 1;

	// 表头节点
	ListNode head;

	// 表尾节点
	ListNode tail;

	// 节点值复制函数
	//    void *(*dup)(void *ptr);

	// 节点值释放函数
	//    void (*free)(void *ptr);

	// 节点值对比函数
	//    int (*match)(void *ptr, void *key);

	// 链表所包含的节点数量
	long len;

	public static List listCreate() {
		List list = new List();
		list.head = list.tail = null;
		list.len = 0;
		return list;
	}

	public void listRelease(List list) {
		long len;
		ListNode current, next;
		// 指向头指针
		current = list.head;
		// 遍历整个链表
		len = list.len;
		while (len-- > -1) {
			next = current.next;
			// 如果有设置值释放函数，那么调用它
			current.value = null;
			// 释放节点结构
			current.prev = null;
			current.next = null;
			current = next;
		}
	}

	public List listAddNodeHead(List list, Object headValue) {
		ListNode node = new ListNode();
		node.value = headValue;
		// 添加节点到空链表
		if (list.len == 0) {
			list.head = list.tail = node;
		} else {
			//添加到非空的链表
			node.prev = null;
			node.next = list.head;
			list.head.prev = node;
			list.head = node;
		}
		list.len++;
		return list;
	}

	public List listAddNodeTail(List list, Object tailValue) {
		ListNode node = new ListNode();
		node.value = tailValue;
		// 添加节点到空链表
		if (list.len == 0) {
			list.head = list.tail = node;
		} else {
			//添加到非空的链表
			node.next = null;
			node.prev = list.tail;
			list.tail.next = node;
			list.tail = node;
		}
		list.len++;
		return list;
	}

	public List listInsertNode(List list, ListNode old_node, Object value, int after) {
		ListNode node = new ListNode();
		node.value = value;
		// 将新节点添加到给定节点之后
		if (after > 0) {
			node.prev = old_node;
			node.next = old_node.next;
			// 给定节点是原表尾节点
			if (list.tail == old_node) {
				list.tail = node;
			}
			// 将新节点添加到给定节点之前
		} else {
			node.next = old_node;
			node.prev = old_node.prev;
			// 给定节点是原表头节点
			if (list.head == old_node) {
				list.head = node;
			}
		}
		// 更新新节点的前置指针
		if (node.prev != null) {
			node.prev.next = node;
		}
		// 更新新节点的后置指针
		if (node.next != null) {
			node.next.prev = node;
		}

		list.len++;
		return list;
	}

	//	/*
	//	 * 创建一个包含值 value 的新节点，并将它插入到 old_node 的之前或之后
	//	 *
	//	 * 如果 after 为 0 ，将新节点插入到 old_node 之前。
	//	 * 如果 after 为 1 ，将新节点插入到 old_node 之后。
	//	 *
	//	 * T = O(1)
	//	 */
	//	list *listInsertNode(list *list, listNode *old_node, void *value, int after) {
	//	    listNode *node;
	//
	//	    // 创建新节点
	//	    if ((node = zmalloc(sizeof(*node))) == NULL)
	//	        return NULL;
	//
	//	    // 保存值
	//	    node->value = value;
	//
	//	    // 将新节点添加到给定节点之后
	//	    if (after) {
	//	        node->prev = old_node;
	//	        node->next = old_node->next;
	//	        // 给定节点是原表尾节点
	//	        if (list->tail == old_node) {
	//	            list->tail = node;
	//	        }
	//	    // 将新节点添加到给定节点之前
	//	    } else {
	//	        node->next = old_node;
	//	        node->prev = old_node->prev;
	//	        // 给定节点是原表头节点
	//	        if (list->head == old_node) {
	//	            list->head = node;
	//	        }
	//	    }
	//
	//	    // 更新新节点的前置指针
	//	    if (node->prev != NULL) {
	//	        node->prev->next = node;
	//	    }
	//	    // 更新新节点的后置指针
	//	    if (node->next != NULL) {
	//	        node->next->prev = node;
	//	    }
	//
	//	    // 更新链表节点数
	//	    list->len++;
	//
	//	    return list;
	//	}
	//
	//	/* Remove the specified node from the specified list.
	//	 * It's up to the caller to free the private value of the node.
	//	 *
	//	 * This function can't fail. */
	//	/*
	//	 * 从链表 list 中删除给定节点 node 
	//	 * 
	//	 * 对节点私有值(private value of the node)的释放工作由调用者进行。
	//	 *
	//	 * T = O(1)
	//	 */
	//	void listDelNode(list *list, listNode *node)
	//	{
	//	    // 调整前置节点的指针
	//	    if (node->prev)
	//	        node->prev->next = node->next;
	//	    else
	//	        list->head = node->next;
	//
	//	    // 调整后置节点的指针
	//	    if (node->next)
	//	        node->next->prev = node->prev;
	//	    else
	//	        list->tail = node->prev;
	//
	//	    // 释放值
	//	    if (list->free) list->free(node->value);
	//
	//	    // 释放节点
	//	    zfree(node);
	//
	//	    // 链表数减一
	//	    list->len--;
	//	}

	public static void main(String[] args) {
		List tt = listCreate();
		tt.listAddNodeHead(tt, "1");
	}

	/* redis中链表的底层中使用的方法 */
	/*
	list *listCreate(void);
	void listRelease(list *list);
	list *listAddNodeHead(list *list, void *value);
	list *listAddNodeTail(list *list, void *value);
	list *listInsertNode(list *list, listNode *old_node, void *value, int after);
	void listDelNode(list *list, listNode *node);
	listIter *listGetIterator(list *list, int direction);
	listNode *listNext(listIter *iter);
	void listReleaseIterator(listIter *iter);
	list *listDup(list *orig);
	listNode *listSearchKey(list *list, void *key);
	listNode *listIndex(list *list, long index);
	void listRewind(list *list, listIter *li);
	void listRewindTail(list *list, listIter *li);
	void listRotate(list *list);
	 * 
	 */
}

class ListNode {
	// 前置节点
	public ListNode prev;

	// 后置节点
	public ListNode next;

	// 节点的值
	public Object value;
}

/*
 * 双端链表迭代器
 */
class ListIter {
	// 当前迭代到的节点
	ListNode next;

	// 迭代的方向
	int direction;
}