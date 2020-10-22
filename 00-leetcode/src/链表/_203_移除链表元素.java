package 链表;
/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/submissions/
 * @author yangqi
 *
 */
public class _203_移除链表元素 {
	public ListNode removeElements(ListNode head, int val) {
		if (head == null) return head;
		
		ListNode node = head;
		// 遍历链表
		while (node.next != null) {
			if (node.next.val == val) {
				node.next = node.next.next;
			} else {
				node = node.next;
			}
		}
		// 判断头结点
		if (head.val == val) {
			if (head.next == null) {
				return null;
			} else {
				head = head.next;
			}
		}
		
		return head;
    }
}
