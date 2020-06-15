package codings;

import java.util.Arrays;

public class NextMaximumNumber {

	static void swap(char[] a,int i, int j) {
		char temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	static void NextMax(char ar[], int n) {
		int i;
		for(i=n-1;i>0;i--) {
			if(ar[i]>ar[i-1])
				break;
			
		}
		if(i==0)
			System.out.println("Not Possible");
		else {
			int x=ar[i-1], min=i;
			for(int j=i+1;j<n;j++) {
				if(ar[j]>x && ar[j]<ar[min])
					min=j;
			}
			swap(ar,i-1,min);
			Arrays.sort(ar,i,n);
			
			for(int k=0;k<n;k++)
				System.out.print(ar[k]);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] arr= {'5','4','2','8','1'};
		int l = arr.length;
		NextMax(arr,l);
	}

}
