
package com.yq;

/*
 * 动态数组的缩容
 * 动态数组的优化
 */

/* java 语法
 * 1.static 表示静态方法，通过类进行调用。这里的方法都是需要 ArrayList 对象调用的，所以不需要加 static。
 * 2.构造函数之间调用使用 this。
 * 3.final 是常量的意思，相当于其它编程语言的 const。static 定义的变量内存只有一份。
 * 4.垃圾回收。
 * 5.在 Java 中，成员变量会自动初始化，如：int 类型自动初始化为 0；对象类型自动初始化为 null。
 */

@SuppressWarnings("unchecked")
public class ArrayList3<E> extends AbstractList<E> {
	/*
	 * 首元素索引
	 */
	int first;
	/*
	 * 所有的元素
	 */
	private E[] elements;
	/*
	 * 默认容量
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	public ArrayList3(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
		first = 0;
	}
	
	public ArrayList3() {
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
		
		// 缩容
		if (elements != null && elements.length > DEFAULT_CAPACITY) {
			elements = (E[]) new Object[DEFAULT_CAPACITY];
		}
	}

	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) { //O(1)
		rangeCheck(index);
		index = (first + index) % elements.length;
		return elements[index];
	}

	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	public E set(int index, E element) { //O(1)
		rangeCheck(index);
		index = (first + index) % elements.length;
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
		/*
		 * 最好：O(1)
		 * 最坏：O(n)
		 * 平均：O(n)
		 */
		rangeCheckForAdd(index);
		
		ensureCapacity(size + 1);
		
		if (index * 2 >= size) {
			for (int i = size; i > index; i--) {
				int newIndex = (first + i) % elements.length;
				int nextIndex = (first + i - 1) % elements.length;
				elements[newIndex] = elements[nextIndex];
			}
			index = (first + index) % elements.length;
			elements[index] = element;
		} else {
			first = (first - 1) >= 0 ? first - 1 : elements.length - 1;
			for (int i = 0; i < index; i++) {
				int newIndex = (first + i) % elements.length;
				int nextIndex = (first + i + 1) % elements.length;
				elements[newIndex] = elements[nextIndex];
			}
			index = (first + index) % elements.length;
			elements[index] = element;
		}
		System.out.println(first);
		size++;
	}

	/**
	 * 删除index位置的元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		/*
		 * 最好：O(1)
		 * 最坏：O(n)
		 * 平均：O(n)
		 */
		rangeCheck(index);
		
		int oldIndex = (first + index) % elements.length;
		E old = elements[oldIndex];
		
		if (index * 2 >= size) {
			for (int i = index + 1; i < size; i++) {
				int newIndex = (first + i) % elements.length;
				int nextIndex = (first + i - 1) % elements.length;
				elements[nextIndex] = elements[newIndex];
			}
			int last = (first + size - 1) % elements.length;
			elements[last] = null;
		} else {
			for (int i = index; i > 0; i--) {
				int newIndex = (first + i) % elements.length;
				int nextIndex = (first + i - 1) % elements.length;
				elements[newIndex] = elements[nextIndex];
			}
			elements[first] = null;
			first = (first + 1) < elements.length ? first + 1 : 0;
		}
		size--;
		trim();
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
				int newIndex = (first + i) % elements.length;
				if (elements[newIndex] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
//				if (elements[i] == element) return i; // == 比较内存地址
				int newIndex = (first + i) % elements.length;
				if (elements[newIndex].equals(element)) return i; // 重写 equals()。Integer 调用 equals()，内部比较的是数值
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
			int newIndex = (first + i) % elements.length;
			if (newIndex != first) {
				string.append(", ");
			}
			string.append(elements[newIndex]);
//			if (i != size - 1) {
//				string.append(", ");
//			}
		}
		string.append("]");
		return string.toString();
	}
	
	private int ensureIndex(int index) {
		return (first + index) % elements.length;
	}
	
	private void rightMoveFitst() {
		first = (first + 1) < elements.length ? first + 1 : 0;
	}
	
	private void leftMoveFitst() {
		first = (first - 1) >= 0 ? first - 1 : elements.length - 1;
	}
	
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		// 新容量为就容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1); //iOS 1.6, java 1.5, >> 表示除以2
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			int index = (first + i) % elements.length;
			newElements[i] = elements[index];
		}
		elements = newElements;
		first = 0;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	private void trim() {
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity >> 1;
		// 剩余空间小于一半 || 空间大小 <= 默认空间
		if (size >= newCapacity || oldCapacity <= DEFAULT_CAPACITY) return;
		
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			int newIndex = (first + i) % elements.length;
			newElements[i] = elements[newIndex];
		}
		elements = newElements;
		first = 0;
		
		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}
}
