package codings;

public class AmountCoins {
	static int counter=0;
	
	public static int count( int S[], int m, int n ) {
		      
		counter++;
		        if (n == 0) 
		            return 1; 
		         
		        if (n < 0) 
		            return 0; 
		       
		        if (m <=0 && n >= 1) 
		            return 0; 
		       if(m>=0 && n>=0) {
		    	   System.out.println(+counter+" Digits for sum "+n+ " is "+m);
		       }
		        return count( S, m - 1, n ) + 
		               count( S, m, n-S[m-1] ); 
		    } 
		      
		   	public static void main(String[] args) 
		    { 
		        int arr[] = {1,2,3}; 
		        int m = arr.length; 
		        System.out.println( count(arr, m, 4)); 
		          
		          
		    } 
		  
		}


