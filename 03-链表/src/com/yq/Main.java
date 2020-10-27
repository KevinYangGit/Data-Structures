package com.yq;

import com.yq.circle.SingleCircleLinkedList;

//import com.yq.single.SingleLinkedList;

public class Main {
	
	static void testList(List<Integer> list) {
		list.add(12);
		list.add(0, 11);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(list.size(), 16);
		
		list.remove(1);
		list.remove(2);
		
		System.out.println(list);
	}

	public static void main(String[] args) {
		// 双向链表
//		testList(new LinkedList<>());

		// 循环单向链表
		testList(new SingleCircleLinkedList<>());
	}

}
