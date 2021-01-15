//https://leetcode-cn.com/problems/recover-binary-search-tree/

package 二叉树;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import jdk.internal.module.DefaultRoots;
import sun.tools.tree.ShiftLeftExpression;

public class _99_恢复二叉搜索树 {

	/*
	 * 方法一：显示中序遍历
	 */
	public void recoverTree(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		inorder(root, res);
		int[] swaped = findTwoSwaped(res);
		recover(root, 2, swaped[0], swaped[1]);
    }
	
	public void inorder(TreeNode root, List<Integer> res) {
		if (root == null) return;
		inorder(root.left, res);
		res.add(root.val);
		inorder(root.right, res);
	}
	
	public int[] findTwoSwaped(List<Integer> res) {
		int n = res.size();
		int x = -1, y = -1;
		for (int i = 0; i < n - 1; i++) {
			if (res.get(i) == res.get(i + 1)) { //找到异常节点（前面 > 后面）
				y = res.get(i + 1);//后
				if (x == -1) {
					x = res.get(i);//前
				} else {
					break;	
				}
			}
		}
		return new int[]{x, y};
	}
	
	public void recover(TreeNode root, int count, int x, int y) {
		if (root != null) {
			if (root.val == x || root.val == y) {
				root.val = root.val == x ? y : x;
				if (--count == 0) {
					return;
				}
			}
			recover(root.left, count, x, y);
			recover(root.right, count, x, y);
		}
	}
	
	/*
	 * 方法二：隐式中序遍历
	 */
	public void recoverTree1(TreeNode root) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode x = null, y = null, pred = null;
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			///保存两个被交换的数据
			if (pred != null && pred.val > root.val) { //找到异常节点（前面 > 后面）
				y = root;//后
				if (x == null) {
					x = pred;//前
				} else {
					break;
				}
			}
			pred = root;
			root = root.right;
		}
		
        swap(x, y);
	}
	
	public void swap(TreeNode x, TreeNode y) {
		int temp = x.val;
		x.val = y.val;
		y.val = temp;
	}
	
	/*
	 * 方法三：Morris 中序遍历
	 */
	public void recoverTree2(TreeNode root) {
		TreeNode x = null, y = null, pred = null;
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left != null) {
				/*
				 * 步骤1（寻找前驱节点）
				 * 1>一直向右找到最右端的节点即为前驱节点，停止遍历
				 * 2>如果最右端的节点是 cur，表示上次循环经历过步骤2，停止遍历
				 */
				TreeNode precessoNode = cur.left;
				while (precessoNode.right != null && precessoNode.right != cur) {
					precessoNode = precessoNode.right;
				}
				
				if (precessoNode.right == null) {
					/*
					 * 步骤2
					 * 1>让前驱节点的 right 指向当前节点（实现步骤4 -> 步骤1 -> 步骤3）
					 * 2>继续遍历左子树
					 */
					precessoNode.right = cur;
					cur = cur.left;
				} else {
					/*
					 * 所有右子树的节点（包含 cur）
					 */
					if (pred != null && pred.val > cur.val) { //找到异常节点（前面 > 后面）
						y = cur; //后
						if (x == null) {
							x = pred; //前 
						}
					}
					pred = cur;
					/* 
					 * 步骤3（precessoNode.right == cur，上次循环步骤2处理的结果）
					 * 1>去掉前驱节点和 cur 的关联（right = null）
					 * 2>开始遍历 cur 的右子树
					 */
					precessoNode.right = null;
					cur = cur.right;
				}
			} else {
				/*
				 * 所有左子树的节点
				 */
				if (pred != null && pred.val > cur.val) { //找到异常节点（前面 > 后面）
					y = cur; //后
					if (x == null) {
						x = pred; //前 
					}
				}
				pred = cur;
				/*
				 * 步骤4遍历右子树
				 * 第一种情况：遍历右子树
				 * 第二种情况：回到父节点，去步骤3（呼应步骤2）
				 */
				cur = cur.right;
			}
		}
		
		swap(x, y);
	}
	
	
}
