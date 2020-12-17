package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class _559_N叉树的最大深度 {
    
	/*
	 * 递归
	 */
	public int maxDepth(Node root) {
		if (root == null) return 0;
		
		int result = 1;
		for (Node c : root.children) {
			result = Math.max(result, 1 + maxDepth(c));
		}
		return result;
	}
	
	/*
	 * 迭代
	 */
	public int maxDepthIterative(Node root) {
		int height = 0;
		if (root == null) return height;
        
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int levelSize = 1;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			levelSize--;
			if (node.children != null) {
				for (Node c : node.children) {
					queue.offer(c);
				}
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		return height;
    }

	@SuppressWarnings("unused")
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
