package 二叉树;

import java.awt.TexturePaint;
import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class _104_二叉树的最大深度 {
	/*
	 * 递归
	 */
	public int maxDepth1(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
	}
	
	/*
	 * 迭代
	 */
    public int maxDepth(TreeNode root) {
    	int height = 0;
    	if (root == null) return height;
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	int levelSize = 1;
    	while (!queue.isEmpty()) {
 			TreeNode node = queue.poll();
 			levelSize--;
 			
 			if (node.left != null) {
				queue.add(node.left);
			}
 			
 			if (node.right != null) {
				queue.add(node.right);
			}
 			
 			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
    	
    	return height;
    }
}
