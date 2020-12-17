package com.yq;

import com.mj.printer.BinaryTrees;
import com.yq.tree.BST;

public class Main {
	
	static void test1() {
		Integer data[] = new Integer[] {
				7, 4, 2, 1, 3, 5, 9, 8, 11, 12
		};
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
	}
	
	public static void main(String[] args) {
		test1();
	}
}
