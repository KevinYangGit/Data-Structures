/*
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 */
package 二叉树;

public class _530_二叉搜索树的最小绝对差 {
    
	TreeNode prev;
    int ans;
    
	public int getMinimumDifference(TreeNode root) {
		ans = Integer.MAX_VALUE;
		inorder(root);
		return ans;
    }
	
	public void inorder(TreeNode root) {
		if (root == null) return;
		
		inorder(root.left);
		
		if (prev == null) {
			prev = root;
		} else {
			int cha = root.val - prev.val;
			ans = ans > cha ? cha : ans;
			prev = root;
		}
		
		inorder(root.right);
	}
}
