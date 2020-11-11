package 队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */

//public class _225_用队列实现栈 {
//
//	Deque<Integer> queue;
//	
//    public _225_用队列实现栈() {
//    	queue = new LinkedList<>();
//    }
//    
//    /** 元素 x 入栈 */
//    public void push(int x) {
//    	queue.add(x);
//    }
//    
//    /** 移除栈顶元素 */
//    public int pop() {
//    	return queue.pollLast();
//    }
//    
//    /** 获取栈顶元素 */
//    public int top() {
//    	return queue.peekLast();
//    }
//    
//    /** 返回栈是否为空 */
//    public boolean empty() {
//    	return queue.isEmpty();
//    }
//}


public class _225_用队列实现栈 {

	Queue<Integer> queue1;
	Queue<Integer> queue2;
	
    public _225_用队列实现栈() {
    	queue1 = new LinkedList<>();
    	queue2 = new LinkedList<>();
    }
    
    /** 元素 x 入栈 */
    public void push(int x) {
    	queue2.add(x);
    	while (!queue1.isEmpty()) {
			queue2.offer(queue1.poll());
		}
    	Queue<Integer> temp = queue1;
    	queue1 = queue2;
    	queue2 = temp;
    }
    
    /** 移除栈顶元素 */
    public int pop() {
    	return queue1.poll();
    }
    
    /** 获取栈顶元素 */
    public int top() {
    	return queue1.peek();
    }
    
    /** 返回栈是否为空 */
    public boolean empty() {
    	return queue1.isEmpty();
    }
}
