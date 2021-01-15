/*
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
package 二叉树;

import java.util.LinkedList;
import java.util.List;

public class _235_二叉搜索树的最近公共祖先 {
	/*
	 * 方法一：两次遍历
	 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	List<TreeNode> path_p = path(root, p);
    	List<TreeNode> path_q = path(root, q);
    	
    	TreeNode node = null;
    	for (int i = 0; i < path_p.size() && i < path_q.size(); i++) {
			if (path_p.get(i) == path_q.get(i)) {
				node = path_p.get(i);
			}
		}
    	return node;
    }
    
    public List<TreeNode> path(TreeNode root, TreeNode target) {
    	List<TreeNode> path = new LinkedList<>();
    	TreeNode node = root;
    	while (node != target) {
			path.add(node);
			if (node.val > target.val) {
				node = node.left;
			} else if (node.val < target.val) {
				node = node.right;
			}
		}
		path.add(node);
    	return path;
    }
    
    /*
	 * 方法一：一次遍历
	 */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    	TreeNode node = root;
    	
    	while (node != null) {
			if (node.val > p.val && node.val > q.val) {
				node = node.left;
			} else if (node.val < p.val && node.val < q.val) {
				node = node.right;
			} else {
				break;
			}
		}
    	
    	return node;
    }
}
