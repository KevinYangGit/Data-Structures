package com.yq;

import com.yq.circle.CircleLinkedList;
//import com.yq.circle.SingleCircleLinkedList;


//import com.yq.single.SingleLinkedList;

public class Main {
	
	static void testList(List<Integer> list) {
		list.add(0, 11);
		list.add(0, 12);
		list.add(0, 13);
		list.add(0, 14); // [14, 13, 12, 11]
		list.add(0, 15);
		list.add(3, 99); // [15, 14, 13, 99, 12, 11]
		
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44); // [15, 14, 13, 99, 12, 11, 11, 22, 33, 44]

		list.add(0, 55); // [55, 15, 14, 13, 99, 12, 11, 11, 22, 33, 44]
		list.add(2, 66); // [55, 15, 66, 14, 13, 99, 12, 11, 11, 22, 33, 44]
		list.add(list.size(), 77); // [55, 15, 66, 14, 13, 99, 12, 11, 11, 22, 33, 44, 77]

		list.remove(0); 
		list.remove(2); 
		list.remove(list.size() - 1); //[15, 66, 13, 99, 12, 11, 11, 22, 33, 44]
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		
//		Asserts.test(list.indexOf(44) == 9);
//		Asserts.test(list.indexOf(77) == List.ELEMENT_NOT_FOUND);
//		Asserts.test(list.contains(33));
//		Asserts.test(list.get(0) == 15);
//		Asserts.test(list.get(1) == 66);
//		Asserts.test(list.get(list.size() - 1) == 44);
		
		System.out.println(list);
	}
	
	static void josephus() {
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}
		
		list.reset();
		
		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.remove());
		}
	}

	public static void main(String[] args) {
		// 双向链表
//		testList(new LinkedList<>());

		// 单向循环链表
//		testList(new SingleCircleLinkedList<>());
		
		// 双向循环链表
//		testList(new CircleLinkedList<>());
		
		// 约瑟夫问题
//		josephus();
		
		// 动态数组优化
		testList(new ArrayList3<>());
	}

}
