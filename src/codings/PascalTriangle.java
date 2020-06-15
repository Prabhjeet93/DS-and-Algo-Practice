package codings;

public class PascalTriangle {

	
	public static int[][] pascalt(int n) {
		
		int[][] arr=new int[n][n];
		
		/*for(int i=0;i<n;i++) {
			arr[i][0]=1;
			arr[i][i]=1;
		}
		for(int j=2;j<n;j++) {
			for(int k=1;k<n-1;k++) {
		     
				arr[j][k]=arr[j-1][k-1]+arr[j-1][k];
		}
		}*/
		
		for(int j=0;j<n;j++) {
			for(int k=0;k<=j;k++) {
			if(j==k||k==0) 
			  arr[j][k]=1;
			else 
				arr[j][k]=arr[j-1][k-1]+arr[j-1][k];
			
		}
		}	
		
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) {
				if(arr[j][k]==0) 
					System.out.print(" ");
				
				else 
		       System.out.print(arr[j][k]+" ");
		}
			System.out.println("");
		}
		
		return arr;
	}
	public static void pascalAt(int n) {
		int[][] ar=pascalt(n);
		
		for(int l=0;l<n;l++) {
			 System.out.print(ar[n-1][l]+" ");
		}
			
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pascalt(8);
		//pascalAt(7);
	}

}
