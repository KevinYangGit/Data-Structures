package com.yq.circle;

@SuppressWarnings("unchecked")
public class CircleDeque<E> {
	private int front;
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPICITY = 10;
	
	public CircleDeque() {
		elements = (E[]) new Object[DEFAULT_CAPICITY];
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[Index(i)] = null;
		}
		size = 0;
		front = 0;
	}

	public void enQueueRear(E element) {
		ensureCapicity(size+1);
		elements[Index(size)] = element;
		size++;
	}

	public E deQueueFront() {
		E element = elements[front];
		elements[front] = null;
		front = Index(1);
		size--;
		return element;
	}

	public void enQueueFront(E element) {
		ensureCapicity(size+1);
		front = Index(-1);
		elements[front] = element;
		size++;
	}

	public E deQueueRear() {
		E element = elements[Index(size - 1)];
		elements[Index(size - 1)] = null;
		size--;
		return element;
	}

	public E front() {
		return elements[front];
	}

	public E rear() {
		return elements[Index(size - 1)];
	}
	
	private int Index(int index) {
		index += front;
		if (index < 0) {
			return index + elements.length;
		}
		return index - (elements.length > index ? 0 : elements.length);
	}
	
	private void ensureCapicity(int capicity) {
		int oldCapicity = elements.length;
		if (oldCapicity >= capicity) return;
		oldCapicity = oldCapicity + (oldCapicity >> 1);
		E[] newElements = (E[]) new Object[oldCapicity];
		for (int i = 0; i < elements.length; i++) {
			newElements[i] = elements[Index(i)];
		}
		elements = newElements;
		front = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capicity=").append(elements.length)
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
