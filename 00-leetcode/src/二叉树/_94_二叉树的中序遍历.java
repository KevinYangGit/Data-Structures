package 二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class _94_二叉树的中序遍历 {
	
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorderTraversalRecursive(root, res);
        return res;
    }
    /*
     * 递归
     */
    public void inorderTraversalRecursive(TreeNode root, List<Integer> res) {
    	if (root == null) return;
    	
    	inorderTraversalRecursive(root.left, res);
    	res.add(root.val);
    	inorderTraversalRecursive(root.right, res);
    }
    
    /*
     * 迭代
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
        	while (node != null) {
            	stack.push(node);
    			node = node.left;
    		}
        	node = stack.pop();
        	res.add(node.val);
        	node = node.right;
		}
        return res;
    }
}
