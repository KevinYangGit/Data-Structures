package 二叉树;
/*
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * 思路：
 * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序；
 * 由于将二叉树展开为链表之后会破坏二叉树的结构，因此在前序遍历结束之后更新每个节点的左右子节点的信息；
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _114_二叉树展开为链表 {
    
	/*
	 * 方法一：前序遍历-递归
	 */
	public void flatten(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        preorderTraversal(root, list);
        for (int i = 1; i < list.size(); i++) {
			TreeNode node1 = list.get(i-1);
			TreeNode node2 = list.get(i);
			node1.right = node2;
			node1.left = null;
		}
    }
	
	public void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        
        list.add(root);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }
	
	/*
	 * 方法一：前序遍历-迭代
	 */
	public void flattenIterative(TreeNode root) {
        
		List<TreeNode> list = new LinkedList<>();
		
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				list.add(node);
				stack.push(node);
				node = node.left;
			}
			node = stack.poll();
			node = node.right;
		}
		for (int i = 1; i < list.size(); i++) {
			TreeNode node1 = list.get(i - 1);
			TreeNode node2 = list.get(i);
			node1.right = node2;
			node1.left = null;
		}
    }
	/*
	 * 方法一：前序遍历-迭代2
	 */
	public void flattenIterative2(TreeNode root) {
        if (root == null) return;
		List<TreeNode> list = new LinkedList<>();
		
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.poll();
			list.add(node);
			
			if (node.right != null) {
				stack.push(node.right);
			}
			
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		for (int i = 1; i < list.size(); i++) {
			TreeNode node1 = list.get(i - 1);
			TreeNode node2 = list.get(i);
			node1.right = node2;
			node1.left = null;
		}
    }
	
	/*
	 * 方法二：前序遍历和展开同步进行
	 */
	public void flattenIterative3(TreeNode root) {
		if (root == null) return;
		
		//前序遍历
		Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode curr = stack.poll();
			if (curr.right != null) {
				stack.push(curr.right);
			}
			if (curr.left != null) {
				stack.push(curr.left);
			}
			
			//展开
			if (prev != null) {
				prev.right = curr;
				prev.left = null;	
			}
			prev = curr;
		}
	}
	
	/*
	 * 方法三：寻找前驱节点
	 */
	public void flattenIteraltive4(TreeNode root) {
		if (root == null) return;
		
		TreeNode curr = root;
		while (curr != null) {
			if (curr.left != null) {
				TreeNode next = curr.left;
				
				//寻找前驱节点
				TreeNode predecessor = curr.left;
				while (predecessor.right != null) {
					predecessor = predecessor.right;
				}
				
				//展开
				predecessor.right = curr.right;
				curr.right = next;
				curr.left = null;
			}
			curr = curr.right; //next
		}
	}
}