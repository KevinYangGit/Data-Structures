package com.yq.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import com.mj.printer.BinaryTreeInfo;


@SuppressWarnings("unchecked")
//public class BinarySearchTree<E extends Comparable> {
public class BST<E> extends BinaryTree<E>{
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
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
	
	public boolean contains(E element) {
		return node(element) != null;
	}

	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		
		//删除度为2的节点
		if (node.isHasTwoChildren()) {
			//找到后继节点
			Node<E> s = successor(node);
			//用后继节点的值覆盖度为2的节点的值
			node.element = s.element;
			//删除后继节点
			node = s;
		}
		
		//删除度为1或0的节点
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if (replacement != null) { // node是度为1的节点
			// 更改parent
			replacement.parent = node.parent;
			// 更改parent的left、right的指向
			if (node.parent == null) { //node是度为1的节点并且是根节点
				root = replacement;
			} else if (node.parent.left == node) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} else if (node.parent == null) { // node是叶子节点并且是根节点
			root = null;
		} else { // node是叶子节点，但不是根节点
			if (node.parent.left == node) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) return node;
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return node;
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
	
	//测试- 二叉树的宽度
	public int widthOfBinaryTree() {
    	int maxWidth = 0;
    	if (root == null) return maxWidth;
    	
    	Queue<Node<E>> queue = new LinkedList<>();
    	queue.offer(root);
    	int levelSize = 1;
    	while (!queue.isEmpty()) {
    		Node<E> node = queue.poll();
    		levelSize--;
    		
    		if (node.left != null) {
				queue.offer(node.left);
			}
    		
    		if (node.right != null) {
    			if (node.left == null) {
    				Node<E> newNode = new Node<>(node.element, null);
    				queue.offer(newNode);
    			}
    			
    			queue.offer(node.right);
			}
    		
    		if (levelSize == 0) {
				levelSize = queue.size();
				maxWidth = maxWidth > levelSize ? maxWidth : levelSize;
			}
		}
    	
        return maxWidth;
    }
}
