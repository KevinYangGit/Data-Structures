package 栈;

import java.util.Stack;

import com.sun.net.httpserver.Authenticator.Result;

/*
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class _150_逆波兰表达式求值 {
	
	static int evalRPN2(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String s : tokens) {
			switch (s) {
			case "+":
				stack.push(stack.pop() + stack.pop());
				break;
			case "-":
				stack.push(-(stack.pop() - stack.pop()));
				break;
			case "*":
				stack.push(stack.pop() * stack.pop());
				break;
			case "/":
				Integer s1 = stack.pop();
				stack.push(stack.pop() / s1);
				break;
			default:
				stack.push(Integer.valueOf(s));
			}
		}
		return stack.pop();
	}
	
	static int evalRPN(String[] tokens) {
    	Stack<Integer> stack = new Stack<>(); 
    	for (String s : tokens) {
    		if (s == "+") {
    			stack.push(stack.pop() + stack.pop());
			} else if (s == "-") {
    			stack.push(-(stack.pop() - stack.pop()));
			} else if (s == "*") {
    			stack.push(stack.pop() * stack.pop());
			} else if (s == "/") {
				Integer s1 = stack.pop();
    			stack.push(stack.pop() / s1);
			} else {
				stack.push(Integer.valueOf(s));
			}
    	}
    	return stack.pop();
    }
}
