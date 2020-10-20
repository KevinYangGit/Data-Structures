package com.yq;

@SuppressWarnings("unchecked")
public class LinkedList<E> {

	private int size;
	private Node first;

	public static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
 	}
}
