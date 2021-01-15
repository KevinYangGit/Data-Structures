package com.yq.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return 0;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	/*
	 * 前序遍历 - 递归
	 */
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;

		visitor.isStop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}
	/*
	 * 前序遍历 - 迭代
	 */
	public void preorder2(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder2(root, visitor);
	}
	private void preorder2(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;

		Deque<Node<E>> stack = new LinkedList<>();
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				if (visitor.isStop) return;
				visitor.isStop = visitor.visit(node.element);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			node = node.right;
		}
	}
	/*
	 * 中序遍历 - 递归
	 */
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;
		
		inorder(node.left, visitor);
		if (visitor.isStop) return;
		visitor.isStop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	/*
	 * 中序遍历 - 迭代
	 */
	public void inorder2(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder2(root, visitor);
	}
	private void inorder2(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		
		Deque<Node<E>> stack = new LinkedList<>();
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (visitor.isStop) return;
			visitor.isStop = visitor.visit(node.element);
			node = node.right;
		}
	}
	/*
	 * 后序遍历 - 递归
	 */
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;
		
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.isStop) return;
		visitor.isStop = visitor.visit(node.element);
	}
	/*
	 * 后序遍历 - 迭代
	 */
	public void postorder2(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder2(root, visitor);
	}
	private void postorder2(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;

		Deque<Node<E>> stack = new LinkedList<>();
		Node<E> prev = null;
		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			if (node.right == null || node.right == prev) {
				visitor.isStop = visitor.visit(node.element);
				prev = node;
				node = null;
			} else {
				stack.push(node);
				node = node.right;
			}
		}
	}
	
	/*
	 * 层序遍历（有回调）- 迭代
	 */
	public void levelOrder(Visitor<E> visitor) {
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (visitor.visit(node.element)) return;
			
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}
	
	/*
	 * 是否是完全二叉树
	 */
	public boolean isComplete() {
		
		if (root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if (leaf && !node.isLeaf()) return false;
			
			if (node.left != null) {
				queue.offer(node.left);
			} else if (node.right != null) { // node.left == null && node.right != null
				return false;
			}
			
			if (node.right != null) {
				queue.offer(node.right);	
			} else { // node.right == null
				leaf = true;
			}
		}
		return true;
	}
	
	/*
	 * 二叉树的高度
	 */
	public int height() {
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		int levelSize = 1; // 标记某一层节点的个数，默认一个（第一层的root节点）
		int height = 0;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--; 
			
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
			
			// 等于0时，表示这一层的节点全部遍历完成了
			if (levelSize == 0) {
				levelSize = queue.size(); // 开始记录下一层的节点个数
				height++; // 开始下一层遍历时，高度加一
			}
		}
		return height;
	}
	
	public int height2() {
		return height(root);
	}

	private int height(Node<E> node) {
		if (node == null) return 0; // 设置递归停止条件
		return 1 + Math.max(height(node.left), height(node.right)); // 取左右子树的高度中较大的一个加一
	}
	
	public Node<E> predecessor(Node<E> node) {
		if (node == null) return node;
		
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
	
	public Node<E> successor(Node<E> node) {
		if (node == null) return node;
		
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		// node.parent == null
		// node == node.parent.right
		return node.parent;
	}
	
	/*
	 * 回调方法（相当于 OC 的 block）
	 */
	public static abstract class Visitor<E> {
		boolean isStop;
		//返回值：true（停止遍历）/false（继续遍历）
		abstract boolean visit(E element);
	}
	
	/*
	 * 节点
	 */
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}
		
		public boolean isHasTwoChildren() {
			return this.left != null && this.right != null;
		}
	}
	
	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
	
	public boolean isValidBST() {
		boolean isValid = false;
		preorder1(root, isValid);
		System.out.println("----");
    	return isValid;
    }
	
	public void preorder1(Node<E> root, boolean isValid) {
		if (root == null) return;
		
		if (root.left != null) {
			isValid = ((Comparable<E>)root.left.element).compareTo(root.element) < 0;
			System.err.println("left " + isValid);
		}
		if (root.right != null) {
			isValid = ((Comparable<E>)root.right.element).compareTo(root.element) > 0;
			System.err.println("right " + isValid);
		}
		preorder1(root.left, isValid);
		preorder1(root.right, isValid);
	}
}
