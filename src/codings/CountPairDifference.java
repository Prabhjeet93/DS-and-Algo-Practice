package codings;

import java.util.Arrays;

public class CountPairDifference {
	
	static int binarySearch(int arr[], int low,  
            int high, int x) 
{ 
if (high >= low)  
{ 
int mid = low + (high - low) / 2; 
if (x == arr[mid]) 
return mid; 
if (x > arr[mid]) 
return binarySearch(arr, (mid + 1), 
                       high, x); 
else
return binarySearch(arr, low,  
                 (mid - 1), x); 
} 
return -1; 
}
	 static int countPairsWithDiffK(int arr[], int n, int k) 
	    { 
	        int count = 0, i; 
	          
	        // Sort array elements 
	        Arrays.sort(arr); 
	  
	        // code to remove duplicates from arr[]  
	  
	        // Pick a first element point 
	        for (i = 0; i < n - 1; i++) 
	            if (binarySearch(arr, i + 1, n - 1, 
	                             arr[i] + k) != -1) 
	                count++; 
	  
	        return count; 
	    } 
	
	
	public static void main(String[] args) {
		
		int[] arr = {8,12,16,4,0,20};
		//int[] arr = {1,5,3,4,2};
		
		// int arr[] = { 1, 5, 3, 4, 2 }; 
	        int n = arr.length; 
	        int k = 4; 
	        System.out.println("Count of pairs with given diff is "
	                            + countPairsWithDiffK(arr, n, k)); 
	}

}
