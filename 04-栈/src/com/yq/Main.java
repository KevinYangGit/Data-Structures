package com.yq;


public class Main {
	
	static void testStack() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	static void testStack2() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
	
	public static void main(String[] args) {
		testStack2();
		java.util.Stack<Integer> stack;
	}
}
