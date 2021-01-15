/*
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 */
package 二叉树;

public class _783_二叉搜索树节点最小距离 {
	int preVal;
	int ans;
    public int minDiffInBST(TreeNode root) {
    	preVal = -1;
    	ans = Integer.MAX_VALUE;
    	inorder(root);
    	return ans;
    }
    
    public void inorder(TreeNode root) {
    	if (root == null) return;
    	
    	inorder(root.left);
    	
    	if (preVal != -1) {
    		ans = Math.min(ans, root.val - preVal);
		}
    	preVal = root.val;
    	
    	inorder(root.right);
    }
}
