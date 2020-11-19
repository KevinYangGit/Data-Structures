package com.yq;

import java.util.Comparator;

import com.mj.printer.BinaryTrees;

public class Main {
	
	static void test() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);;
	}
	
	static void test2() {
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3
		};
		
		BinarySearchTree<Person> bst = new BinarySearchTree<>(new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			bst.add(new Person(data[i]));
		}
		
		BinaryTrees.println(bst);;
	}
	
	public static void main(String[] args) {
		test();
	}
}
