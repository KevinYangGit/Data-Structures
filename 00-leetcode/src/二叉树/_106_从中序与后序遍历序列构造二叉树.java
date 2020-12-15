package 二叉树;

import java.util.HashMap;
import java.util.Map;

public class _106_从中序与后序遍历序列构造二叉树 {
	/*
	 * 方法一：递归
	 */
	int post_idx;
	int[] inorder;
	int[] postorder;
	Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
	
	public TreeNode helper(int in_left, int in_right) {
		//没有节点
		if (in_left > in_right) return null;
		
		//后序遍历结果中 post_idx 处的节点作为当前子树的根节点
		int root_val = postorder[post_idx];
		TreeNode root = new TreeNode(root_val);
		
		//根据 root 所在位置分成左右子树
		int index = idx_map.get(root_val);
		
		//下标减1（递归构建完右子树后，post_idx-- < index）
		post_idx--;
		
		//递归-构建右子树
		root.right = helper(index + 1, in_right);
		
		//递归-构建左子树
		root.left = helper(in_left, index - 1);
		
		return root;
	}
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
    	this.inorder = inorder;
    	this.postorder = postorder;
    	
    	post_idx = postorder.length - 1;
    	
    	int idx = 0;
    	for (int val : inorder) {
			idx_map.put(val, idx++);
		}

    	return helper(0, post_idx);
    }
}
