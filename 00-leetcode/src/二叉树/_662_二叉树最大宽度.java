package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

// [1,1,1,1,null,null,1,1,null,null,1]
public class _662_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
    	int maxWidth = 0;
    	if (root == null) return maxWidth;

    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	int levelSize = 1;
    	boolean hasRight = false;
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		levelSize--;
    		
    		if (node.right != null) {
    			queue.offer(node.right);
    			hasRight = true;
    		} else if (hasRight) {
    			TreeNode newNode = new TreeNode(0);
    			queue.offer(newNode);
    		}
    		
    		if (node.left != null) {
    			queue.offer(node.left);
    		} else if (hasRight) {
    			TreeNode newNode = new TreeNode(0);
    			queue.offer(newNode);
    		}
    		
    		if (levelSize == 0) {
    			hasRight = false;
    			levelSize = queue.size();
    			maxWidth = maxWidth > levelSize ? maxWidth : levelSize;
    		}
    	}

    	return maxWidth;
    }
}
