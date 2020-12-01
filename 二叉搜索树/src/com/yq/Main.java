package com.yq;

import java.util.Comparator;

import com.mj.printer.BinaryTrees;
import com.yq.BinarySearchTree.Visitor;

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
		
		BinaryTrees.println(bst);
	}
	
	/*
	 * 前序遍历、中序遍历、后序遍历和层序遍历
	 */
	static void test3() {
		Integer data[] = new Integer[] {
				7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		BinaryTrees.println(bst);
		System.out.println();
		
//		bst.preorderTraversal();
//		bst.inorderTraversal();
//		bst.postorderTraversal();
//		bst.levelOrderTraversal();
		
		System.out.print("前序遍历：");
		bst.preorderTraversal(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 8;
			}
		});
		System.out.println();
		
		System.out.print("中序遍历：");
		bst.inorderTraversal(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 8;
			}
		});
		System.out.println();
		
		System.out.print("后序遍历：");
		bst.postorderTraversal(new Visitor<Integer>() {
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 8;
			}
		});
		System.out.println();
		
		System.out.print("层序遍历：");
		bst.levelOrder(new Visitor<Integer>() {			
			@Override
			public boolean visit(Integer element) {
				System.out.print(element + " ");
				return element == 8;
			}
		});
	}
	
	/*
	 * 树状打印二叉树
	 */
	static void test4() {
		Integer data[] = new Integer[] {
				7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		System.out.println(bst);
	}
	
	/*
	 * 练习-二叉树的高度
	 */
	static void test5() {
		Integer data[] = new Integer[] {
				7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		System.out.println("二叉树高度：" + bst.height());
	}
	
	static void test6() {
//		Integer data[] = new Integer[] {
//				7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
//		};
		Integer data[] = new Integer[] {
				7, 4, 2, 1, 3, 5, 9, 8, 11
		};
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
	}
	
	public static void main(String[] args) {
		test6();
	}
}
