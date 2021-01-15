//https://leetcode-cn.com/problems/binary-search-tree-iterator/
package 二叉树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _173_二叉搜索树迭代器 {
	
}
/*
 * 迭代
 */
class BSTIterator1 {
	Deque<TreeNode> stack;
	
    public BSTIterator1(TreeNode root) {
    	stack = new LinkedList<>();
    	leftmostInorder(root);
    }
    
    public void leftmostInorder(TreeNode root) {
    	while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}
    
    public int next() {
    	TreeNode node = stack.poll();
    	
    	if (node.right != null) {
    		leftmostInorder(node.right);
		}
    	
    	return node.val;
    }
    
    public boolean hasNext() {
    	return !stack.isEmpty();
    }
}

/*
 * 递归
 */
class BSTIterator {
	List<Integer> res;
	int index;
	
    public BSTIterator(TreeNode root) {
    	res = new LinkedList<>();
    	index = 0;
    	inorder(root);
    }
    
    public void inorder(TreeNode root) {
    	if (root == null) return;
		inorder(root.left);
		res.add(root.val);
		inorder(root.right);
	}
    
    public int next() {
    	index++;
    	return res.get(index - 1);
    }
    
    public boolean hasNext() {
    	return res.size() > index;
    }
}

/*
 * 迭代2
 */
class BSTIterator2 {
	Queue<Integer> queue;
	
    public BSTIterator2(TreeNode root) {
    	queue = new LinkedList<>();
    	inorder(root);
    }
    
    public void inorder(TreeNode root) {
    	if (root == null) return;
		inorder(root.left);
		queue.offer(root.val);
		inorder(root.right);
	}
    
    public int next() {
    	return queue.poll();
    }
    
    public boolean hasNext() {
    	return !queue.isEmpty();
    }
}


