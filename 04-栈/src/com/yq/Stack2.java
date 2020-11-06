package com.yq;

import com.yq.list.ArrayList;

public class Stack2<E> {
	ArrayList<E> list = new ArrayList<>();
	/*
	 * 清空
	 */
	public void clear() {
		list.clear();
	}
	/*
	 * 大小
	 */
	public int size() {
		return list.size();
	}
	/*
	 * 判空
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/*
	 * 入栈
	 */
	public void push(E element) {
		list.add(element);
	}
	/*
	 * 出栈
	 */
	public E pop() {
		return list.remove(list.size()-1);
	}
	/*
	 * 获取顶部元素
	 */
	public E top() {
		return list.get(list.size()-1);
	}
}
