package com.yq;

import com.mj.printer.BinaryTrees;
import com.yq.tree.BST;

public class Main {
	
	static void test1() {
		Integer data[] = new Integer[] {
				1, 2, 3
		};
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		
		System.out.println(bst.isValidBST());
	}
	
	public static void main(String[] args) {
		test1();
	}
}
