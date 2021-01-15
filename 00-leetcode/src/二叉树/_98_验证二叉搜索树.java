/*
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
package 二叉树;

import java.util.Deque;
import java.util.LinkedList;

public class _98_验证二叉搜索树 {

	/*
	 * 递归
	 *
	 * 左子树不为空，则左子树上所有节点的值均小于它的根节点的值；
	 * 右子树不空，则右子树上所有节点的值均大于它的根节点的值；
	 * 它的左右子树也为二叉搜索树。
	 */
	public boolean isValidBST(TreeNode root) {
		return preorder(root, null, null);
	}

	public boolean preorder(TreeNode root, Integer lower, Integer upper) {
		if (root == null) return true;
		
		if (lower != null && lower >= root.val) return false;
		
		if (upper != null && upper <= root.val) return false;
		
		if (!preorder(root.left, lower, root.val)) return false;
		
		if (!preorder(root.right, root.val, upper)) return false;
		
		return true;
	}


	/*
	 * 迭代
	 * 
	 * 「中序遍历」得到的值构成的序列一定是升序的
	 */
	public boolean isValidBST2(TreeNode root) {
		return inorder(root);
	}

	public boolean inorder(TreeNode root) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;
		TreeNode prev = null;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (prev != null && prev.val >= node.val) {
				return false;
			}
			prev = node;
			node = node.right;
		}
		return true;
	}
}
