package com.yq;

import java.util.Stack;

public class Main {

	/*
	 * 1 + 1
	 */
	static int calculate(String s) {
		Stack<Integer> stack = new Stack<>();
		int res = 0;
		int operand = 0; //位数
		int sign = 1; //+：1，-：-1
		
		for (char c : s.toCharArray()) {
			if (c == ' ')
				continue;
			
			if (c >= '0' && c <= '9') {
				operand = operand*10 + (c - '0');
			} else if (c == '+' || c == '-') {
				res += operand*sign;
				operand = 0; // 重置位数
				sign = c == '+' ? 1 : -1; // 重置符号
				
			} else if (c == '(') {
				stack.push(res);
				stack.push(sign);
				res = 0;
				sign = 1;
			} else { // c == ')'
				res += operand*sign;
				operand = 0;
				
				int a = stack.pop(); //上一次的 sign
				int b = stack.pop(); //上一次的 sum
				
				res += b*a;
			}
		}
		return res + sign * operand;
	}
		
	public static void main(String[] args) {
//		System.out.println(isValid1("({})"));
//		String[] tokens = {"2","1","+","3","*"};
//		System.out.println(calculate(tokens));
//		System.out.println(String.valueOf("-10"));
//		System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
//		System.out.println(calculate("(4+5+2)"));
		System.out.println(calculate("1 - 11"));
//		System.out.println(calculate2(" 2-1 + 2 "));
//		System.out.println(calculate2("2147483647"));
//		System.out.println(calculate2("1 + 1"));
//		System.out.println(calculate2("2-(5-6)"));
//		System.out.println(calculate2("(1)"));
//		System.out.println('3' - '0');

	}
}
