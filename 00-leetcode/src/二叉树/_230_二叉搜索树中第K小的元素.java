//https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/

package 二叉树;

import java.util.Deque;
import java.util.LinkedList;

public class _230_二叉搜索树中第K小的元素 {
	TreeNode targeTreeNode = null;
	int index = 0;
	/*
	 * 迭代
	 */
	public int kthSmallest(TreeNode root, int k) {

		if (root == null) return root.val;
		
		///递归
		//return inorder(root, k);
		
		///迭代
		index = k;
		helper(root);
		return targeTreeNode.val;
    }
	
	public int inorder(TreeNode root, int k) {
		
		TreeNode targeTreeNode = root;
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			k--;
			if (k == 0) {
				targeTreeNode = node;
				break;
			}
			node = node.right;
		}
		
		return targeTreeNode.val;
	}
	
	/*
	 * 递归
	 */
	public void helper(TreeNode root) {
		if (root == null || targeTreeNode != null) return;
		
		helper(root.left);
		index--;
		if (index == 0 && targeTreeNode == null) {
			targeTreeNode = root;
			return;
		}
		helper(root.right);
	}
}
