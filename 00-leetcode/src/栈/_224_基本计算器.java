package 栈;

import java.util.Stack;

/*
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class _224_基本计算器 {
	/*
	 * "(1+(4+5+2)-3)+(6+8)"
	 * "(4+5+2)"
	 * "1 - 11"
	 * " 2-1 + 2 "
	 * "2147483647"
	 * "1 + 1"
	 * "2-(5-6)"
	 * "(1)"
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
	
	static int calculate2(String s) {
		Stack<String> stack = new Stack<>();
		stack.push("0");
		stack.push("+");
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == ' ') {
				continue;
			}
			
			System.out.println(c);
			if (c == '(') {
				stack.push(c + "");
			} else if (c == ')') {
				String n1 = stack.pop();
				String operator = stack.pop(); // +、-、（
				if (operator.equals("(")) {
					stack.push(n1);
				} else {
					String n2 = stack.pop();
					stack.pop(); // (
					System.out.println(n2 + operator + n1);
					if (operator.equals("+")) {
						stack.push(String.valueOf(Integer.valueOf(n2) + Integer.valueOf(n1)));
					} else if (operator.equals("-")) {
						stack.push(String.valueOf(Integer.valueOf(n2) - Integer.valueOf(n1)));
					}
				}
			} else if (c == '+' || c == '-') { // ( n +
				String n1 = stack.pop();
				String operator = stack.pop();
				if (operator.equals("(")) {
					stack.push(operator);
					stack.push(n1);
				} else {
					String n2 = stack.pop();
					System.out.println(n2 + operator + n1);
					if (operator.equals("+")) {
						stack.push(String.valueOf(Integer.valueOf(n2) + Integer.valueOf(n1)));
					} else if (operator.equals("-")) {
						stack.push(String.valueOf(Integer.valueOf(n2) - Integer.valueOf(n1)));
					}
				}
				stack.push(c + "");
			} else {
				String left = stack.peek();
				if (left.equals("+") || left.equals("-") || left.equals("(")) { // + c、- c、( c
					stack.push(c + "");
				} else { // 位数 > 1
					stack.pop();
					stack.push(left + c + "");
				}
			}
		}
		System.out.println(stack.size());
		if (stack.size() > 1) {
			calculate3(stack);
		}
		return Integer.valueOf(stack.pop());

	}

	static void calculate3(Stack<String> stack) {
		String n1 = stack.pop();
		String operator = stack.pop();
		String n2 = stack.pop();
		System.out.println(n2 + operator + n1);
		if (operator.equals("+")) {
			stack.push(String.valueOf(Integer.valueOf(n2) + Integer.valueOf(n1)));
		} else if (operator.equals("-")) {
			stack.push(String.valueOf(Integer.valueOf(n2) - Integer.valueOf(n1)));
		}
	}
}
