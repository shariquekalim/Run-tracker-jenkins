package com.crick.controller;

import java.util.Scanner;

public class Learning {

	public static void main(String[] args) {
		
		System.out.println("Enter the number of element you want to store: ");
		Scanner sc=new Scanner(System.in);
		int num_element = sc.nextInt();
		
		int [] array=new int[num_element];
		System.out.println("Enter the element of array");
		for(int a=0; a<num_element; a++) {
			array[a]=sc.nextInt();
		}
		//int[] array = { 0, -1, 2, -3, 1 };
		System.out.println("Enter the check number ");
		//int num = -1;
		
		int num = sc.nextInt();

		for (int i = 0; i < array.length; i++) {
			for(int j=i+1; j<array.length; j++) {
				int add=array[i]+array[j];
				
				//System.out.println(array[i] +" + " + array[j] +"= "+ add);
				
				if(add==num) {
					System.out.println("The two number whose sum is extactly " + num+" is : "+ array[i] +" & " + array[j]);
				}
				else {
					System.out.println("Their is not any number whose sum is  "+ num);
				}
			}
			//System.out.println("----------------------------------");
		}
	}

}
