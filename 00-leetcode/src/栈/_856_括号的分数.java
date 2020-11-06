package 栈;
/*
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */
import java.util.Stack;

public class _856_括号的分数 {
	/*
	 * 简化代码
	 */
	static int scoreOfParentheses6(String S) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0);
			} else {
				int top = stack.pop();
				int left = stack.pop();
				stack.push(left + Math.max(2 * top, 1));
			}
		}
		return stack.pop();
	}
	
	/*
	 * 虚拟头结 - 简化代码
	 */
	static int scoreOfParentheses5(String S) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0); // 使用0标记“(”
			} else {
				// c == ')'

				int top = stack.pop();
				if (top == 0) {
					top = 1;
				} else {
					top *= 2;
				}
				
				int left = stack.peek();
				stack.pop();
				top += left;
				
				stack.push(top);
			}
		}
		return stack.pop();
	}
	/*
	 * 使用虚拟头结点，统一处理逻辑
	 */
	static int scoreOfParentheses4(String S) {
		Stack<Integer> stack = new Stack<>();
		stack.push(0); // 虚拟头
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0); // 使用0标记“(”
			} else {
				// c == ')'

				int top = stack.pop();
				if (top == 0) {
					top = 1;
				} else {
					stack.pop();
					top *= 2;
				}
				
				int left = stack.peek();
				if (left != 0) {
					stack.pop();
					top += left;	
				}
				
				stack.push(top);
			}
		}
		return stack.pop();
	}
	
	/*
	 * 使用累加代替 while 循环
	 */
	static int scoreOfParentheses3(String S) {
		Stack<Integer> stack = new Stack<>();
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0); // 使用0标记“(”
			} else {
				// c == ')'

				/*
				 * ()、(()(、()( 对于只有一层括号的情况，top == 0：
				 * 1. 取出 top = pop();
				 * 2. 修改 top = 1，并 push(top);
				 * 3. 判断前一项是否可加
				 *  
				 * 示例：
				 * ( : [0] ~> () : [1] 
				 * (()( : [0, 1, 0] ~> (()() : [0, 2]
				 * ()(：[1, 0] ~> ()()：[2]
				 */
				int top = stack.pop();
				if (top == 0) {
					top = 1;
					if (stack.isEmpty()) {
						stack.push(top);
					} else {
						int left = stack.peek();
						if (left != 0) {
							stack.pop();
							top += left;
							stack.push(top);
						} else {
							stack.push(top);
						}	
					}
				} else {
					/* 
					 * (())、(()()) 对于外层有括号的情况，top != 0：
					 * 1. 取出 top = pop();
					 * 2. 修改 top *=2，并 push(top);
					 * 3. 判断前一项是否可加；
					 * 
					 * 示例：
					 * (() : [0, 1] ~> (()) : [2]
					 * (()() : [0, 1, 1] ~> (()()) : [4]
					 * (()(( : [0, 1, 0, 0] ~> (()(() : [0, 1, 0, 1]
					 */
					stack.pop();
					top *= 2;
					if (stack.isEmpty()) {
						stack.push(top);
					} else {
						int left = stack.peek();
						if (left != 0) {
							stack.pop();
							top += left;
							stack.push(top);
						} else {
							stack.push(top);
						}	
					}
				}
			}
		}
		return stack.pop();
	}
	
	static int scoreOfParentheses2(String S) {
		Stack<Integer> stack = new Stack<>();
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0); // 使用0标记“(”
			} else {
				// c == ')'

				/*
				 * ()、(()(、()( 对于只有一层括号的情况，直接将“()”置为1： 
				 * ( : [0] ~> () : [1] 
				 * (()( : [0, 1, 0] ~> (()() : [0, 1, 1]
				 * ()(：[1, 0] ~> ()()：[1, 1]
				 * 
				 * 
				 * (())、(()()) 对于外层有括号的情况，将内部所有的数值相加后乘2，并弹出c对应位置的左括号 
				 * (() : [0, 1] ~> (()) : [2]
				 * (()() : [0, 1, 1] ~> (()()) : [4]
				 */
				int score = 0;
				int top = stack.pop();
				while (top != 0) {
					System.out.println(top);
					score += top;
					top = stack.pop();
				}
				score = Math.max(2 * score, 1); // 包含 top == 0 的情况
				stack.push(score);
			}
		}
		int score = 0;
		while (!stack.empty()) {
			score += stack.pop();
		}
		return score;
	}

	static int scoreOfParentheses(String S) {
		Stack<Integer> stack = new Stack<>();
		for (char c : S.toCharArray()) {
			if (c == '(') {
				stack.push(0); // 使用0标记“(”
			} else {
				// c == ')'

				/*
				 * ()、(()(、()( 对于只有一层括号的情况，直接将“()”置为1： ( : [0] ~> () : [1] (()( : [0, 1, 0] ~>
				 * (()() : [0, 1, 1] ()(：[1, 0] ~> ()()：[1, 1]
				 */
				if (stack.peek() == 0) {
					stack.pop();
					stack.push(1);
				} else {
					/*
					 * (())、(()()) 对于外层有括号的情况，将内部所有的数值相加后乘2，并弹出c对应位置的左括号 (() : [0, 1] ~> (()) : [2]
					 * (()() : [0, 1, 1] ~> (()()) : [4]
					 */
					int score = 0;
					int top = stack.pop();
					while (top != 0) {
						score += top;
						top = stack.pop();
					}
					score *= 2;
					stack.push(score);
				}
			}
		}
		int score = 0;
		while (!stack.empty()) {
			score += stack.pop();
		}
		return score;
	}
}
