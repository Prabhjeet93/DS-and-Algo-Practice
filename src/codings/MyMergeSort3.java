package codings;

import java.util.Random;

public class MyMergeSort3 {

	static int[] merge_sort(int A[], int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2; // defines the current array in 2 parts .
			merge_sort(A, start, mid); // sort the 1st part of array .
			merge_sort(A, mid + 1, end); // sort the 2nd part of array.

			// merge the both parts by comparing elements of both the parts.
			return merge(A, start, mid, end);
		}
		return null;
	}

static int[] merge(int A[ ] , int start, int mid, int end) {
	 //stores the starting position of both parts in temporary variables.
	int p = start ,q = mid+1;

	int[] Arr = new int[end-start+1];
	int k=0;

	for(int i = start ;i <= end ;i++) {
	    if(p > mid)      //checks if first part comes to an end or not .
	       Arr[k++] = A[ q++] ;

	   else if ( q > end)   //checks if second part comes to an end or not
	       Arr[ k++ ] = A[ p++ ];

	   else if( A[ p ] < A[ q ])     //checks which part has smaller element.
	      Arr[ k++ ] = A[ p++ ];

	   else
	      Arr[ k++ ] = A[ q++];
	 }
	  for (int p1=0 ; p1< k ;p1 ++) {
	   /* Now the real array has elements in sorted manner including both 
	        parts.*/
	     A[ start++ ] = Arr[ p1 ] ;                          
	  }
	  return Arr;
	}

	// generate N real numbers between 0 and 1, and mergesort them
	public static void main(String[] args) {
		int N = 10;
		int[] a = { 45, 23, 11, 89, 1, 98, 4, 28, 65, 43 };
		/*
		 * double[] a = new double[N]; for (int i = 0; i < N; i++) a[i] = Math.random();
		 */
		a = merge_sort(a, 0, N-1);
		for (int i = 0; i < N; i++)
			System.out.println((a[i]));

		// System.out.println(isSorted(a));
	}
}
