package com.yq;

import com.yq.circle.CircleDeque;
import com.yq.circle.CircleQueue;

public class Main {
	
	/*
	 * 队列
	 */
	static void test() {
		Queue<Integer> queue = new Queue<>();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
	/*
	 * 双端队列
	 */
	static void test1() {
		Deque<Integer> queue = new Deque<>();
		queue.enQueueFront(11);
		queue.enQueueFront(22);
		queue.enQueueRear(33);
		queue.enQueueRear(44);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
	/*
	 * 循环队列
	 */
	static void test2() {
		CircleQueue<Integer> queue = new CircleQueue<>();
		// capicaty=10 size=10 front=0, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		// capicaty=10 size=5 front=5, [null, null, null, null, null, 5, 6, 7, 8, 9]
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		// capicaty=10 size=10 front=5, [10, 11, 12, 13, 14, 5, 6, 7, 8, 9]
		for (int i = 0; i < 5; i++) {
			queue.enQueue(i+10);
		}
		// capicaty=22 size=20 front=0, [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, null, null]
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}
	
	/*
	 * 循环双端队列
	 */
	static void test3() {
		CircleDeque<Integer> queue = new CircleDeque<>();
		// capicity=22 size=20 front=20, [8, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, null, null, 10, 9]
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueRear(i + 100);
		}
		// capicity=22 size=14 front=1, [null, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, null, null, null, null, null, null, null]
		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}
		
		// capicity=22 size=16 front=0, [11, 7, 6, 5, 4, 3, 2, 1, 100, 101, 102, 103, 104, 105, 106, 12, null, null, null, null, null, null]
		queue.enQueueFront(11);
		queue.enQueueRear(12);
		
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
	public static void main(String[] args) {
//		test3();
//		test2();
//		test1();
//		java.util.Queue<Integer> queue2;
//		java.util.LinkedList<E> linkedList;
	}
}
