package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

// [1,1,1,1,null,null,1,1,null,null,1]
public class _662_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
    	if (root == null) return 0;
    	
    	Node node = new Node(root, 0, 0);
    	Queue<Node> queue = new LinkedList<>();
    	queue.offer(node);
    	int curDepth = 0, left = 0, ans = 0;
    	while (!queue.isEmpty()) {
    		node = queue.poll();
    		
    		if (node.treeNode != null) {
				queue.offer(new Node(node.treeNode.left, node.depth + 1, node.pos * 2));
				queue.offer(new Node(node.treeNode.right, node.depth + 1, node.pos * 2 + 1));
				
				//每开始遍历新的一层，记录最左边的pos
				if (curDepth != node.depth) {
					curDepth = node.depth;
					left = node.pos;
				}
				
				ans = Math.max(ans, node.pos - left + 1);
			}
    	}

    	return ans;
    }
}

//因为左右子树存在null的情况，所以创建新的Node包装TreeNode，这样就可以存储TreeNode==null情况
class Node {
	TreeNode treeNode;
	int depth, pos;
	public Node(TreeNode treeNode, int depth, int pos) {
		this.treeNode = treeNode;
		this.depth = depth;
		this.pos = pos;
	}
}
