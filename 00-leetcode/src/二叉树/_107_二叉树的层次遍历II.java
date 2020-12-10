package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_二叉树的层次遍历II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<>();
    	if (root == null) return res;
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	int levelSize = 1;
    	List<Integer> list = new ArrayList<>();
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
				levelSize = queue.size();
				res.add(0, list);
				list = new ArrayList<>();
			}
		}
    	
    	return res;
    }
}
