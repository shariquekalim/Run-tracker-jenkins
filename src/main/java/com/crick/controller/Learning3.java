package com.crick.controller;

import java.util.LinkedList;

public class Learning3 {
	public static void main(String[] args) {
		LinkedList< Integer> list= new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		
		for (Integer integer : list) {
			System.out.println(integer.reverse(10));
		}
		
	}

}
