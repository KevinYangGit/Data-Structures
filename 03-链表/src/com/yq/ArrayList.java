package com.yq;

/* java 语法
 * 1.static 表示静态方法，通过类进行调用。这里的方法都是需要 ArrayList 对象调用的，所以不需要加 static。
 * 2.构造函数之间调用使用 this。
 * 3.final 是常量的意思，相当于其它编程语言的 const。static 定义的变量内存只有一份。
 * 4.垃圾回收。
 * 5.在 Java 中，成员变量会自动初始化，如：int 类型自动初始化为 0；对象类型自动初始化为 null。
 */

@SuppressWarnings("unchecked")
public class ArrayList<E> extends AbstractList<E> {
	/*
	 * 所有的元素
	 */
	private E[] elements;
	/*
	 * 默认容量
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 清除所有元素
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}

	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	public E set(int index, E element) {
		rangeCheck(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}

	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		ensureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}

	/**
	 * 删除index位置的元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		rangeCheck(index);
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		} 
		elements[--size] = null;
		return old;
	}

	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
//				if (elements[i] == element) return i; // == 比较内存地址
				if (elements[i].equals(element)) return i; // 重写 equals()。Integer 调用 equals()，内部比较的是数值
			}	
		}
		return ELEMENT_NOT_FOUND;
	}
	
	// 重写 toString 方法
	// 在 toString 方法中将元素拼接成字符串
	@Override
	public String toString() {
		// 字符串拼接建议使用 StringBuilder
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(elements[i]);
//			if (i != size - 1) {
//				string.append(", ");
//			}
		}
		string.append("]");
		return string.toString();
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		// 新容量为就容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1); //iOS 1.6, java 1.5, >> 表示除以2
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
}
