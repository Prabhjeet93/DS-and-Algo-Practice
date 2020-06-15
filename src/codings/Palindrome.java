package codings;

public class Palindrome {

	
	public static void numPalindrome(int n) {
		
		int rev=0,rem;
		int tmp=n;
		while(n!=0) {
			
			rem=n%10;
			rev=rev*10+rem;
			n=n/10;
		}
				if(rev==tmp) 
			System.out.println(rev+" is Palndrom");
		else 
			System.out.println(rev+" is not Palndrom");
	}
	
	public static void stringPalindrome(String str) {
		String tmp=str,rev = "";
		int len=str.length();
		for(int i=len-1;i>=0;i--) {
			rev=rev+str.charAt(i);
			
		}
		
		if(rev.equalsIgnoreCase(str))
			System.out.println("String is palindrome");
		else 
			System.out.println("String isn't palindrome");
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		numPalindrome(121);
		numPalindrome(123);
		stringPalindrome("malayalam");
		stringPalindrome("malayalama");
	}

}
