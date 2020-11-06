package com.yq;

import com.yq.list.ArrayList;

public class Stack<E> extends ArrayList<E>{
	/*
	 * 入栈
	 */
	public void push(E element) {
		add(element);
	}
	/*
	 * 出栈
	 */
	public E pop() {
		return remove(size-1);
	}
	/*
	 * 获取顶部元素
	 */
	public E top() {
		return get(size-1);
	}
}
