package 栈;
/*
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/submissions/
 */
import java.util.Stack;

public class _232_用栈实现队列 {
	Stack<Integer> inStack;
	Stack<Integer> outStack;
	
    public _232_用栈实现队列() {
    	inStack = new Stack<>();
    	outStack = new Stack<>();
    }
    
    /** 入栈 */
    public void push(int x) {
    	inStack.push(x);
    }
    
    /** 出栈 */
    public int pop() {
    	checkOutStack();
    	return outStack.pop();
    }
    
    /** 首元素 */
    public int peek() {
    	checkOutStack();
    	return outStack.peek();
    }
    
    /** 判空 */
    public boolean empty() {
    	return inStack.isEmpty() && outStack.isEmpty();
    }
    
    private void checkOutStack() {
    	if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}
	}
}
