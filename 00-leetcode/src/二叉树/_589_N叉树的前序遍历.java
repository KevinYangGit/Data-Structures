package 二叉树;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 */
public class _589_N叉树的前序遍历 {

	/*
	 * 递归
	 */
	public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
		preorderTraversal(root, res);
		return res;
    }
	
	public void preorderTraversal(Node root, List<Integer> res) {
		if (root == null) return;
		
		res.add(root.val);
		if (root.children != null) {
			for (Node c : root.children) {
				preorderTraversal(c, res);
			}
		}
		return;
    }
	
	/*
	 * 迭代
	 */
	public List<Integer> preorderIterative(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        
        Deque<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
        	Node node = stack.pop();
        	res.add(node.val);
        	//反转一个 List（[v1, v2, v3] -> [v3, v2, v1]）
        	Collections.reverse(node.children);
        	for (Node c : node.children) {
				stack.push(c);
			}
		}
		return res;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};