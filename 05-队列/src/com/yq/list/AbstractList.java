package com.yq.list;

//implements 实现接口
//abstract 抽象类。1、可以选择实现接口；2、抽象类无法创建（new AbstractList<>()）；

//不对外公开，只负责实现公共代码
public abstract class AbstractList<E> implements List<E>{

	protected int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}

	@Override
	public void add(E element) {
		add(size, element);
	}
	
	//protected 子类可以访问
	protected void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("Index:" + index + ", Size" + size);
	}
	
	protected void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	protected void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
}
