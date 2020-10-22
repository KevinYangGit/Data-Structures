package 链表;
/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author yangqi
 *
 */
public class _206_反转链表 {

	public ListNode reverseList(ListNode head) {
		// 添加边界，避免死循环
		// head == null 表示没有元素
		// head.next == null 表示只有一个元素
		if (head == null || head.next == null) return head;
		
		ListNode newHead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode newHead = null;
		while (head != null) {
			ListNode tmp = head.next;
			head.next = newHead;
			newHead = head;
			head = tmp;
		}
		return newHead;
	}
}
