package com.yq.circle;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	/*
	 * 首元素
	 */
	private int front;
	/*
	 * 数据大小
	 */
	private int size;
	/*
	 * 容器
	 */
	private E[] elements;
	/*
	 * 默认容量
	 */
	private static final int DEFAULT_CAPICATY = 10;
	
	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPICATY];
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for (int i = 0; i < elements.length; i++) {
			elements[Index(i)] = null;
		}
		size = 0;
		front = 0;
	}

	public void enQueue(E element) {
		ensureCapicity(size + 1);
		elements[Index(size)] = element;
		size++;
	}

	public E deQueue() {
		E element = elements[front];
		elements[front] = null;
		front = Index(1);
		size--;
		return element;
	}

	public E front() {
		return elements[front];
	}
	
	/*
	 * 已知 n>=0，m>=0，n < 2m，则 n%m 等价于
	 * n - (m > n ? 0 : m)
	 */
	private int Index(int index) {
		index += front;
		return index - (elements.length > index ? 0 : elements.length);
	}
	
	private void ensureCapicity(int capicaty) {
		int oldCapicaty = elements.length;
		if (oldCapicaty >= capicaty) return;
		int newCapicaty = oldCapicaty + (oldCapicaty >> 1);
		E[] newElements = (E[]) new Object[newCapicaty];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[Index(i)];
		}
		elements = newElements;
		front = 0;
	}
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capicaty=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
}
