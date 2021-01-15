/*
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 */
package 二叉树;

public class _450_删除二叉搜索树中的节点 {
    
	public int successor(TreeNode root) {
		root = root.right;
		while (root.left != null) {
			root = root.left;
		}
		return root.val;
	}
	
	public int predecessor(TreeNode root) {
		root = root.left;
		while (root.right != null) {
			root = root.right;
		}
		return root.val;
	}
 	
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) return null;
		
		if (key > root.val) {
			//向又查找
			root.right = deleteNode(root.right, key);
		} else if (key < root.val) {
			//向左查找
			root.left = deleteNode(root.left, key);
		} else {
			if (root.left == null && root.right == null) { //删除叶子节点
 				root = null;
			} else if (root.right != null) { //删除度为1的节点
				root.val = successor(root);
				root.right = deleteNode(root.right, root.val);
			} else { //删除度为1的节点
				root.val = predecessor(root);
				root.left = deleteNode(root.left, root.val);
			}
		}
		return root;
	}
}
