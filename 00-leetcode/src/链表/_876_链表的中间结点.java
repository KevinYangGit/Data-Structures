package 链表;
/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author yangqi
 *
 */
public class _876_链表的中间结点 {
	
    public ListNode middleNode(ListNode head) {
    	int count = 0; // 标记链表长度
    	ListNode node = head;
    	// 遍历链表
    	while (node != null) {
    		count++;
			node = node.next;
		}
    	
    	count = count >> 1;
    	
    	while (node != null) {
    		if (count > 0) {
    			count--;
			} else {
				break; // 此时 node 为中间节点
			}
			node = node.next;
		}
    	
    	return node;
    }
}
