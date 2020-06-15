package codings;

public class StringsManupulation {
	//1. find that string has all unique characters or not - without using another data str

	//2. if an element in an matrix M*N matrix is 0its entire row and column is set 0

	//3. remove duplicate from unsorted linked list

	//4. Given two sorted arrays A & B  and  A can hold element of B, merge B in to A in sorted order
public static void main(String[] args) {
	byte[] b_arr = {70, 101, 101, 107, 115};
	String s_byte =new String(b_arr);
	
	//System.out.println(s_byte);
	
	String str="Learn Share ";
	//System.out.println(str.indexOf("ear", 5));
	String str1="prabh";
	String str2="prabhjeet";
	//uniqueString(str1);
	//uniqueString(str2);
	if(uniqueString(str1))
		System.out.println("String doesn't have any duplicate");
	
	else 
		System.out.println("String have  duplicate");
}

public static boolean uniqueString(String str) {
	
	
	
	int len=str.length();
	char[] chr=str.toCharArray();
	System.out.println(len);
	
	for(int i=0;i<len;i++) {
		for(int j=i+1;j<len;j++) {
			if(chr[i]==chr[j]) {
				return false;
			}
			
		}
	}
return true;
	
}

 


}

