package 二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class _45_二叉树的后序遍历 {
	
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        postorderTraversalRecursive(root, res);
		return res; 
    }
	
	/*
	 * 递归
	 */
	public void postorderTraversalRecursive(TreeNode root, List<Integer> res) {
        if (root == null) return;
        
		postorderTraversalRecursive(root.left, res);
		postorderTraversalRecursive(root.right, res);
		res.add(root.val);
    }
	
	/*
	 * 迭代
	 */
	public List<Integer> postorderTraversalIterative(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
        if (root == null) return null;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        TreeNode prev = null;
        while (!stack.isEmpty() || node != null) {
        	while (node != null) {
            	stack.push(node);
    			node = node.left;
    		}
        	node = stack.pop();
        	
        	if (node.right == null || node.right == prev) {
				res.add(node.val);
				prev = node;
				node = null;
			} else {
				stack.push(node);
				node = node.right;
			}
		}
        
        return res;
    }
	
}
