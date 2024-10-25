package com.crick.controller;

class Learning2 { 
	/* Function to print product array 
	for a given array arr[] of size n */
	void productArray(int arr[], int n) 
	{ 

		// Base case 
		if (n == 1) { 
			System.out.print(0); 
			return; 
		} 
		// Initialize memory to all arrays 
		int left[] = new int[n]; 
		int right[] = new int[n]; 
		int prod[] = new int[n]; 

		int i, j; 

		/* Left most element of left array 
is always 1 */
		left[0] = 1; 

		/* Right most element of right 
array is always 1 */
		right[n - 1] = 1; 

		/* Construct the left array */
		for (i = 1; i < n; i++)  // 1800
			left[i] = arr[i - 1] * left[i - 1]; 
     // {10,30,150,900,1800}
		/* Construct the right array */
		for (j = n - 2; j >= 0; j--)  // j=3 right[3]=6
          //n=5 n-2=3 
          // j=2 6*2 right[2]=12
          //j=1 
       //        {1800,180,60,12,2}
			right[j] = arr[j + 1] * right[j + 1]; 

		/* Construct the product array using 
		left[] and right[] */
		for (i = 0; i < n; i++) 
			prod[i] = left[i] * right[i]; 
         //  {18000,5400,900,10800,3600}
		/* print the constructed prod array */
		for (i = 0; i < n; i++) 
			System.out.print(prod[i] + " "); 

		return; 
	} 

	/* Driver program to test the above function */
	public static void main(String[] args) 
	{ 
		Learning2 pa = new Learning2(); 
		int arr[] = { 10, 3, 5, 6, 2 }; 
		int n = arr.length; 
		System.out.println("The product array is : "); 
		pa.productArray(arr, n); 
	} 
} 

// This code has been contributed by Mayank Jaiswal
