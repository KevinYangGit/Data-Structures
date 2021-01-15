/*
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 */
package 二叉树;

public class _701_二叉搜索树中的插入操作 {
	
    public TreeNode insertIntoBST(TreeNode root, int val) {
    	if (root == null) return new TreeNode(val);
    	
    	TreeNode node = root;
    	TreeNode parent = root;
    	int cmp = 0;
    	while (node != null) {
    		parent = node;
			if (val > node.val) {
				cmp = 1;
				node = node.right;
			} else {
				cmp = -1;
				node = node.left;
			}
		}
    	
    	if (cmp > 0) {
    		parent.right = new TreeNode(val);
    	} else {
    		parent.left = new TreeNode(val);
    	}
    	
    	return root;
    }
}
