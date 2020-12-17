package 二叉树;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.sql.rowset.JoinRowSet;

public class _105_从前序与中序遍历序列构造二叉树 {
	/*
	 * 方法一：递归
	 * 
	 * preorder = [3, 9, 15, 10, 20, 7, 5, 8, 4]
	 * inorder = [15, 9, 10, 3, 20, 5, 7, 8, 4]
	 */
	int preorderIndex;
	int[] preorder;
	int[] inorder;
	Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
	public TreeNode helper(int in_left, int in_right) {
		
		if (in_left > in_right) {
			return null;
		}
		
		TreeNode root = new TreeNode(preorder[preorderIndex]);
		int index = idx_map.get(root.val);
		
		preorderIndex++;
		
		//遍历创建左子节点
		root.left = helper(in_left, index - 1);
		//遍历创建右子节点
		root.right = helper(index + 1, in_right);
		
		return root;
	}
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;
		
		for (int i = 0; i < inorder.length; i++) {
			idx_map.put(inorder[i], i);
		}
		
		return helper(0, preorder.length);
	}
	
	/*
	 * 方法二：迭代
	 * 
	 * preorder = [3, 9, 15, 10, 20, 7, 5, 8, 4]
	 * inorder = [15, 9, 10, 3, 20, 5, 7, 8, 4]
	 */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
    	if (preorder == null || preorder.length == 0) {
			return null;
		}
    	//前序遍历的第一个节点就是root节点
    	TreeNode root = new TreeNode(preorder[0]);
    	//中序遍历的索引
    	int inorderIndex = 0;

    	Deque<TreeNode> stack = new LinkedList<>();
    	stack.push(root);
    	
    	//正序遍历 preorder
    	for (int i = 1; i < preorder.length; i++) {
			int preorderVal = preorder[i];
			TreeNode node = stack.peek();
			if (node.val != inorder[inorderIndex]) {
				// node 不是最左边的节点，那么 postorderVal 是 node 的左节点
				node.left = new TreeNode(preorderVal);
				stack.push(node.left);
			} else {
				// node 是最左边的节点，那么 postorderVal 是 node 的右节点
				while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
					node = stack.pop();
					inorderIndex++;
				}
				node.right = new TreeNode(preorderVal);
				stack.push(node.right);
			}
		}
    	
    	return root;
    }
}
