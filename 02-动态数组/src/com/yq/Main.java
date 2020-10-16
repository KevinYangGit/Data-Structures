package com.yq;

public class Main {

	public static void main(String[] args) {
		// int -> integer
		// 所有的类都继承自Object
		// new是想堆空间申请内存
		// 左边<Person>表示泛型的类型，右边的<>初始化用，可以不用写
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(new Person(20, "Tom"));
		persons.add(new Person(30, "Jerry"));
		persons.add(new Person(10, "Tom-Son"));
		System.out.println(persons);
		persons.clear();
		// 提示JVM进行垃圾回收
		System.gc();
		
//		ArrayList<Integer> list = new ArrayList<>();
//		ArrayList list = new ArrayList();
		// 添加
//		list.add(99);
//		list.add(88);
//		list.add(77);
//		list.add(66);
//		list.add(55);
//		list.add(44);
		
		// 插入
//		list.add(3, 100);
//		list.add(5, 200);
//		list.add(list.size(), 300);
//		
//		Asserts.test(list.get(3) == 100);
//		System.out.println(list);

		// 删除
//		list.remove(0);
//		Asserts.test(list.get(0) == 88);
//		System.out.println(list);
		
		// 扩容
//		for (int i = 0; i < 30; i++) {
//			list.add(i);
//		}
//		System.out.println(list);
	}

}
