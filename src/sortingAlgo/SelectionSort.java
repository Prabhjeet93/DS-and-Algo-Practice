package sortingAlgo;

public class SelectionSort {
	
	public void SelectionSortingAlgo(int[] arr) {
		
		int len=arr.length;
		
	for(int i=0;i<len-1;i++) {
		int min=i;
		for(int j=i+1;j<len;j++)
			if(arr[min]>arr[j])
				min=j;
		int temp=arr[min];
		arr[min]=arr[i];
		arr[i]=temp;
	}
	}
	
	public static void main(String[] args) {
		SelectionSort ss=new SelectionSort();
		int[] a={1,9,4,2,2,1};
		
		ss.SelectionSortingAlgo(a);
		
		for(int q:a)
			System.out.println(q);
	}

}
