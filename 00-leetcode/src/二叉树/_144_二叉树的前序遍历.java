package 二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
	
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class _144_二叉树的前序遍历 {
	
	/*
	 * 递归
	 */
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	preorderTraversalRecursive(root, res);
        return res;
    }
    
    public void preorderTraversalRecursive(TreeNode root, List<Integer> res) {
    	if (root == null) return;
    	
    	res.add(root.val);
    	preorderTraversalRecursive(root.left, res);
    	preorderTraversalRecursive(root.right, res);
    }
    
    /*
     * 迭代
     */
    public List<Integer> preorderTraversalIterative(TreeNode root) {
    	List<Integer> res = new ArrayList<Integer>();
    	if (root == null) return res;
    	
    	Deque<TreeNode> stack = new LinkedList<>();
    	TreeNode node = root;
    	while (!stack.isEmpty() || node != null) {
    		while (node != null) {
    			res.add(node.val);
        		stack.push(node);
    			node = node.left;
    		}
    		node = stack.pop();
    		node = node.right;
		}
        return res;
    }
    
    public List<Integer> preorderTraversalIterative2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(Integer.valueOf(node.val));
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }
}
