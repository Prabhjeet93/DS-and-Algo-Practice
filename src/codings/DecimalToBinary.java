package codings;

public class DecimalToBinary {
	
	
	public static int BintoDeci(int n) {
		
		int temp=n;
		int base =1;
		int rem, value = 0;
		while(temp>0) {
			
			rem=temp%10;
			temp=temp/10;
			value=value+rem*base;
			base=base*2;
			
		}
		return value;
	}
	
	public static void DeciBin(int n) {
		
		int counter=0;
		int[] bin=new int[1000];
		int i=0;
		while(n>0) {
			
			bin[i]=n%2;
			n=n/2;
			if(bin[i]==1)
				counter++;
			i++;
			
		}
		
		for(int j=i-1;j>=0;j--) {
			System.out.println(bin[j]);
		}
		System.out.println(counter);
		
		
	}
	public static void main(String[] args) {
		System.out.println(BintoDeci(1010));
		
		System.out.println(Integer.parseInt("1011", 2));
		
		DeciBin(13);
		
	}

}
