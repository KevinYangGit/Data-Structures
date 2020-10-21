package com.yq;

public class Main {

	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		list.add(12);
		list.add(0, 11);
		list.add(13);
		list.add(list.size(), 14);
		
		list.remove(1);
		
		System.out.println(list);
	}

}
