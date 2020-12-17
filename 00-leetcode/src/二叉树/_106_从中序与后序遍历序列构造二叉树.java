package 二叉树;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import sun.security.krb5.internal.rcache.AuthTimeWithHash;

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
    
    /*
     * 方法二：迭代
     * 
     * inorder = [15, 9, 10, 3, 20, 5, 7, 8, 4]
     * postorder = [15, 10, 9, 5, 4, 8, 7, 20, 3]
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
    	if (postorder == null || postorder.length == 0) {
			return null;
		}
    	
    	//后序遍历的最后一个节点就是 root 节点
    	//这些节点的顺序和它们在反向中序遍历（反向遍历inorder）中出现的顺序一定是相反的
    	TreeNode root = new TreeNode(postorder[postorder.length - 1]);
    	//使用栈存放后序遍历中的节点
    	Deque<TreeNode> stack = new LinkedList<>();
    	stack.push(root);
    	
    	//用来记录子树里最右边的节点
    	int inorderIndex = inorder.length - 1;
    	//从后序遍历的倒数第二个几点开始，反向遍历 postorder
    	for (int i = postorder.length - 2; i >= 0; i--) {
    		int postorderVal = postorder[i];
    		//栈顶节点
			TreeNode node = stack.peek();
			if (node.val != inorder[inorderIndex]) {
				//node 不是最右边的节点，那么 postorderVal 是 node 的右节点
				node.right = new TreeNode(postorderVal);
				stack.push(node.right);
			} else {
				//node 是最右边的节点，那么 postorderVal 是 node 的左节点
				//栈里的这些节点（右节点）的顺序和它们在反向中序遍历中出现的顺序一定是相反的
				while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
					node = stack.pop();
					inorderIndex--; //反向遍历 inorder
				}
				//因为 postorderVal 出现在了 node 与 node 在栈中的下一个节点的反向中序遍历之间，因此 postorderVal 就是 node 的左儿子。
				node.left = new TreeNode(postorderVal);
				stack.push(node.left);
			}
		}
    	
    	return root;
    }
}
