/*
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 */
package 二叉树;

public class _938_二叉搜索树的范围和 {
	int sum;

	public int rangeSumBST(TreeNode root, int low, int high) {
		inorder(root, low, high);
		return sum;
	}

	public void inorder(TreeNode root, int low, int high) {
		if (root == null) return;
		
		rangeSumBST(root.left, low, high);

		if (root.val >= low && root.val <= high) {
			sum += root.val;
		}
		rangeSumBST(root.right, low, high);
	}
}
