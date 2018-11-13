package leetcode.L0ToL35;

import leetcode.base.LinkedNode;

/**
 * @author zhailz
 *
 * @version 2018年7月11日 上午11:09:35
 */
public class L19RemoveNthNodeFromEndofList {

	public static LinkedNode<String> removeNthNodeFromEndofList(LinkedNode<String> node, int last) throws Exception {

		LinkedNode<String> first = node, second = node;

		while (last > 0 && second != null) {
			second = second.next;
			last--;
		}

		//说明是第一个几点
		if (0 == last && second == null) {
			return first.next;
		}

		while (second.next != null) {
			first = first.next;
			second = second.next;
		}
		LinkedNode<String> tem = first.next.next;
		first.next = tem;
		return node;
	}

}
