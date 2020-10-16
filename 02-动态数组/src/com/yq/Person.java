package com.yq;

public class Person {
	private int age;
	private String name;
	
	// “alt + command + s” 快速生成一些方法
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Person - finalize");
	}
}
