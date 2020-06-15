package arrays;

public class RorateArray {
	
	
	public static void rotateArr1(int[] a, int d) {
		
		int l=a.length;
		
		int[] temp = new int[d];
		for(int i=0;i<d;i++) {
			temp[i]=a[i];
		}
		
		
		for(int i=0;i<l-d;i++) {
			a[i]=a[i+d];
		}
		
		for(int i=l-d,j=0;i<l&&j<d;i++,j++) {
			a[i]=temp[j];
		}
	}

	public static void rotateArr2(int[] ar,int q) {
		
		int l=ar.length;
		for(int i=0;i<q;i++) {
			arrRotateSwap(ar,l);
		}
		
	}
	
	public static void arrRotateSwap(int[] arr,int len) {
		
		int tmp=arr[0];
		
		for(int i=0;i<len-1;i++)
			arr[i]=arr[i+1];
		
		arr[len-1]=tmp;
		
	}
	
public static void rotateArr3(int[] ar,int q) {
		
		int l=ar.length;
		int temp=ar[0];
		for(int i=0;i<l-1;i++) 
			ar[i]=ar[i+1];
		ar[l-1]=temp;
		
	}
	
	
	public static void main(String[] args) {
	
		int[] arr= {1,2,3,4,5,6,7};
		int r=3;
		
		rotateArr1(arr,r);
		
		for(int p:arr)
			System.out.print(p+" ");
		
		System.out.println(" ");
		
    rotateArr2(arr,r);
		
		for(int p:arr)
			System.out.print(p+" ");
	}

}

