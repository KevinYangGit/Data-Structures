package com.yq;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;


@SuppressWarnings("unchecked")
//public class BinarySearchTree<E extends Comparable> {
public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public int size() {
		return 0;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public void clear() {
		
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		if (root == null) {
			root = new Node<>(element, null);
			return;
		}
		
		Node<E> node = root;
		Node<E> parent = root;
		int cmp = 0;
		while (node != null) {
			parent = node;
			cmp = compare(element, node.element);
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				node.element = element;
				return;
			}
		}
		
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		
		size++;
	} 
	
	public void remove(E element) {
		
	}
	
	public void contains(E element) {
		
	}
	/*
	 * 前序遍历
	 */
	public void preorderTraversal(Visitor<E> visitor) {
		if (visitor == null) return;
		preorderTraversal(root, visitor);
	}
	
	private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;

		visitor.isStop = visitor.visit(node.element);
		preorderTraversal(node.left, visitor);
		preorderTraversal(node.right, visitor);
	}
	/*
	 * 中序遍历
	 */
	public void inorderTraversal(Visitor<E> visitor) {
		if (visitor == null) return;
		inorderTraversal(root, visitor);
	}
	
	private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;
		
		inorderTraversal(node.left, visitor);
		if (visitor.isStop) return;
		visitor.isStop = visitor.visit(node.element);
		inorderTraversal(node.right, visitor);
	}
	
	/*
	 * 后序遍历
	 */
	public void postorderTraversal(Visitor<E> visitor) {
		if (visitor == null) return;
		postorderTraversal(root, visitor);
	}
	
	private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.isStop) return;
		
		postorderTraversal(node.left, visitor);
		postorderTraversal(node.right, visitor);
		if (visitor.isStop) return;
		visitor.isStop = visitor.visit(node.element);
	}
	
	/*
	 * 层序遍历（有回调）
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
	 * 二叉搜索树比较逻辑
	 *   
	 * 等于0：e1 == e2  
	 * 大于0：e1 > e2  
	 * 小于0：e1 < e2  
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	/*
	 * 回调方法（相当于 OC 的 block）
	 * 返回值：true（停止遍历）/false（继续遍历）
	 */
//	public static interface Visitor<E> {
	public static abstract class Visitor<E> {
		public boolean isStop;
		public abstract boolean visit(E element);
	}
	
	/*
	 * 节点
	 */
	private static class Node<E> {
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

	@Override
//	public Object string(Object node) {
//		return ((Node<E>)node).element;
//	}
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
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
	
//	public int height() {
//		return height(root);
//	}
//	
//	private int height(Node<E> node) {
//		if (node == null) return 0; // 设置递归停止条件
//		return 1 + Math.max(height(node.left), height(node.right)); // 取左右子树的高度中较大的一个加一
//	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if (node == null) return;
		
		sb.append(prefix).append(node.element).append("\n");
		toString(node.left, sb, prefix + "L_");
		toString(node.right, sb, prefix + "R_");
	}
}
