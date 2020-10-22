package 链表;
/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/submissions/
 * @author yangqi
 *
 */
public class _83_删除排序链表中的重复元素 {
	
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) return head;
		
		ListNode node = head;
		// 遍历链表
		while (node != null) {
			
			ListNode targetNode = node;
			// 从 node 开始遍历链表
			while (targetNode.next != null) {
				if (targetNode.next.val == node.val) {
					targetNode.next = targetNode.next.next;
				} else {
					targetNode = targetNode.next;	
				}
			}
			
			node = node.next;
		}
		
		return head;
    }
}
