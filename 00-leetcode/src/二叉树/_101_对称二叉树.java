package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class _101_对称二叉树 {
	/*
	 * 方法一：递归
	 */
    public boolean isSymmetric(TreeNode root) {
    	return check(root, root);
    }
    
    public boolean check(TreeNode p, TreeNode q) {
    	
    	if (p == null && q == null) return true;
    	
    	if (p == null || q == null) return false;
    	
    	return (p.val == q.val && check(p.left, q.right) && check(p.right, q.left));
    }
    
    /*
     * 方法二：迭代
     */
    public boolean isSymmetric2(TreeNode root) {
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	queue.offer(root);
    	
    	while (!queue.isEmpty()) {
			TreeNode p = queue.poll();
			TreeNode q = queue.poll();
			if (p == null && q == null) {
				continue;
			}
			
			if (p == null || q == null || p.val != q.val) {
				return false;
			}
			
			queue.offer(p.left);
			queue.offer(q.right);
			
			queue.offer(p.right);
			queue.offer(q.left);
		}
    	
    	return true;
    }
}
