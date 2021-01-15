/*
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 */
package 二叉树;

public class _700_二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
    	if (root == null) return null;
    	while (root != null) {
			if (val > root.val) {
				root = root.right;
			} else if (val < root.val) {
				root = root.left;
			} else {
				return root;
			}
		}
    	return root;
    }
}
