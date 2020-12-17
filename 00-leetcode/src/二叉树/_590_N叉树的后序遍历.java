package 二叉树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 */
public class _590_N叉树的后序遍历 {

	/*
	 * 递归
	 */
	public List<Integer> postorder(Node root) {
		List<Integer> res = new LinkedList<>();
		postorderTraversal(root, res);
        return res;
    }
	
	public void postorderTraversal(Node root, List<Integer> res) {
		if (root == null) return;
		
		if (root.children != null) {
			for (Node c : root.children) {
				postorderTraversal(c, res);
			}
		}
		
		res.add(root.val);
        return;
    }
	
	/*
	 * 迭代
	 */
	public List<Integer> postorderIterative(Node root) {
		List<Integer> res = new LinkedList<>();
		if (root == null) return res;
		
		Deque<Node> stack = new LinkedList<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.poll();
			res.add(0, node.val);
			for (Node c : node.children) {
				stack.push(c);
			}
		}
		
        return res;
    }
	
	private static class Node {
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
}
