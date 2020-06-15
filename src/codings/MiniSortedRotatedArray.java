package codings;

public class MiniSortedRotatedArray {

	
	static int findMin(int[] arr, int low, int high) {
		//System.out.println("findMin("+low+" "+high);
		if(high<low)
			return arr[0];
		
		if(high==low)
			return arr[high];
		int mid=(low+high)/2;
		
		if(mid<high && arr[mid+1]<arr[mid])
			return arr[mid+1];
		
		if(mid>low && arr[mid]<arr[mid-1])
			return arr[mid];
		
		if(arr[high]>arr[mid])
			return findMin(arr, low, mid-1);
		
		
		return findMin(arr, mid+1, high);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//int[] a= {5,6,1,2,3,4};
		
		int[] a= {1,2,3,4,5,6,7};
		System.out.println(findMin(a,0,a.length-1));

	}

}
