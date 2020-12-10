package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class _102_二叉树的层序遍历 {
    
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int levelSize = 1;
		List<Integer> list = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			levelSize--;
			list.add(node.val);
			
			if (node.left != null) {
				queue.add(node.left);
			}
			
			if (node.right != null) {
				queue.add(node.right);
			}
			
			if (levelSize == 0) {
				res.add(list);
				levelSize = queue.size();
				list = new ArrayList<Integer>();
			}
		}
		return res;
    }
}
