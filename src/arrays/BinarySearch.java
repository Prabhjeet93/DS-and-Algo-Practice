package arrays;

import org.omg.Messaging.SyncScopeHelper;

public class BinarySearch {
	
	public int binarySearching(int[] arr, int low, int high, int x) {
		
		if(high>=low) {
		
		int mid=(low+high)/2;
		
		if(arr[mid]==x)
			return mid;
		
		if(x>arr[mid])
			return binarySearching(arr,mid+1,high,x);
		
		return binarySearching(arr,low,mid-1,x);
		}
		return -1;
		
		
	}

	public static void main(String[] args) {
		BinarySearch bs=new BinarySearch();
		int[] a= {1,3,7,10,13,20,51,89,93,99,100};
		int n=a.length;
		int x=89;
		int p=bs.binarySearching(a,0,n,x);
		if(p==-1)
			System.out.println("not found");
		else
		 System.out.println(p);
	}

}
