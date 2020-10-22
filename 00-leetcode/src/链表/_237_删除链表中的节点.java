package 链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author yangqi
 *
 */
public class _237_删除链表中的节点 {
	/**
	 * 解题思路：删除指定节点 node，可以将 node 下一个节点的内容覆盖掉 node 的内容，让后将 node 的下一个节点删除掉 `node.next = node.next.next`
	 * @param node
	 */
	public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
