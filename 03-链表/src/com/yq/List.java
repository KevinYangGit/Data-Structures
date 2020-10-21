package com.yq;

//只声明公共接口，不实现
//List<E> list1 = new ArrayList<>();
//List<E> list2 = new LinkedList<>();
public interface List<E> {
	// AbstractList对外界不可见，所以放在List里
	public static final int ELEMENT_NOT_FOUND = -1;
	/**
	 * 清除所有元素
	 */
	public void clear();
	/**
	 * 元素的数量
	 * @return
	 */
	public int size();
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty();
	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element);
	/**
	 * 添加元素到尾部
	 * @param element
	 */
	public void add(E element); 
	/**
	 * 获取index位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index);
	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	public E set(int index, E element);
	/**
	 * 在index位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element);
	/**
	 * 删除index位置的元素
	 * @param index
	 * @return
	 */
	public E remove(int index);
	/**
	 * 查看元素的索引
	 * @param element
	 * @return
	 */
	public int indexOf(E element);
}
