package 栈;
/*
 * https://leetcode-cn.com/problems/valid-parentheses/
 */

import java.util.HashMap;
import java.util.Stack;

public class _20_有效的括号 {
	
	public boolean isValid3(String s) {
    	Stack<Character> stack = new Stack<>();
    	
    	for (char c : s.toCharArray()) {
    		if (c == '(') {
				stack.push(')');
			} else if (c == '[') {
				stack.push(']');
			} else if (c == '{') {
				stack.push('}');
			} else {
				if (stack.isEmpty() || c != stack.pop()) return false;
			}
		}
    	
    	return stack.isEmpty();
    }
	
	private static HashMap<Character, Character> map = new HashMap<>();
	static {
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
	}
	
	public boolean isValid1(String s) {
		Stack<Character> stack = new Stack<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (map.containsKey(c)) {
    			stack.push(c);
    		} else {
    			if (stack.isEmpty()) return false;
    			if (map.get(stack.pop()) != c) return false;
    		}	
		}
    	
    	return stack.isEmpty();
	}
	
	public boolean isValid(String s) {
    	Stack<Character> stack = new Stack<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (c == '(' || c == '[' || c == '{') {
    			stack.push(c);
    		} else {
    			if (stack.isEmpty()) return false;
    			
    			char left = stack.pop();
    			if (left == '(' && c != ')') return false;
    			if (left == '[' && c != ']') return false;
    			if (left == '{' && c != '}') return false;
    		}	
		}
    	
    	return stack.isEmpty();
    }
}
