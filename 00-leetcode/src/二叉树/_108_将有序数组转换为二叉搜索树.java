/*
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
package 二叉树;

public class _108_将有序数组转换为二叉搜索树 {
	
    public TreeNode sortedArrayToBST(int[] nums) {
    	return helper(nums, 0, nums.length-1);
    }
    
    public TreeNode helper(int[] nums, int left, int right) {
    	if (left > right) return null;
    	
    	int mid = (left + right) / 2;
    	
    	TreeNode root = new TreeNode(nums[mid]);
    	root.left = helper(nums, left, mid-1);
    	root.right = helper(nums, mid+1, right);
    	
    	return root;
    }
}
