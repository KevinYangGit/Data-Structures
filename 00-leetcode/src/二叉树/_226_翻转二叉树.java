package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class _226_翻转二叉树 {
	
	/*
	 * 前序遍历
	 */
	public TreeNode invertTree(TreeNode root) {
    	if (root == null) return root;
    	
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
		
		invertTree(root.left);
		invertTree(root.right);
		return root;
    }
	
	/*
	 * 后序遍历
	 */
	public TreeNode invertTree2(TreeNode root) {
    	if (root == null) return root;
		
		invertTree(root.left);
		invertTree(root.right);
		
		TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
		return root;
    }
	
	/*
	 * 中序遍历
	 */
	public TreeNode invertTree3(TreeNode root) {
    	if (root == null) return root;
    	
    	invertTree(root.left);
    	
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
		
		invertTree(root.left);
		return root;
    }
	
	/*
	 * 层序遍历
	 */
	public TreeNode invertTree4(TreeNode root) {
    	if (root == null) return root;
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	while (en.hasMoreElements()) {
			type type = (type) en.nextElement();
			
		}
    	
		return root;
    }
}
