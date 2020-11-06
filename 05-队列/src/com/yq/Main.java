package com.yq;

public class Main {
	
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
	
	public static void main(String[] args) {
		
		test1();
//		java.util.Queue<Integer> queue2;
//		java.util.LinkedList<E> linkedList;
	}
}
