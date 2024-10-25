package com.crick.controller;

public class Learning1 {
	public static void main(String[] args) {
		int[] array = { 1, 3, 5, 3, 6, 1, 4 ,6};
		for (int i = 0; i < array.length; i++) {
			for(int j=i+1;j<array.length;j++) {
				if(array[i]==array[j]) {
					System.out.println("Duplicate Elements : "+ array[i]);
				}
			}
		}

	}
}
