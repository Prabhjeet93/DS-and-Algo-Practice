package codings;

public class SortZeroOneTwos {

	public static void sort(int[] arr) {
		
		int counter0=0,counter1=0,counter2=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==0) {
				counter0++;
			}
			else if(arr[i]==1) {
				counter1++;
			}
			else if(arr[i]==2) {
				counter2++;
			}
		}
		for(int j=0;j<counter0;j++) {
			arr[j]=0;
		}
		for(int k=counter0;k<counter0+counter1;k++) {
			arr[k]=1;
		}
		for(int k=counter0+counter1;k<counter0+counter1+counter2;k++) {
			arr[k]=2;
		}
		
		for(int a:arr) {
			System.out.print(a +" ");
		}
	}
	
	
	public static void main(String[] args) {
		
		int[] array= {1,2,0,1,0,2,0};
  sort(array);
	}

}
