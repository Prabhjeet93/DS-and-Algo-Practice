package codings;

import java.util.Random;

public class MyMergeSort4 {

static double[] mergesort(double[] arr) {
	
	int N= arr.length;
	if(N<=1)
		return arr;
	double[] a= new double[N/2];
	double[] b=new double[N-N/2];
	System.out.println("start new iteration");
	System.out.print("a : ");
	for(int i=0;i<a.length;i++) {
		
		a[i]=arr[i];
		
		System.out.print("a : "+a[i]+" ");
	}
	System.out.println();
	System.out.print("b : ");
	for(int j=0;j<b.length;j++) {
		b[j]=arr[j+N/2];
		System.out.print("b : "+b[j]+" ");
	}
	System.out.println();
	return merge(mergesort(a), mergesort(b));
	
}
static double[] merge(double[] a,double[] b) {
	System.out.println("Now Merging elements");
	double[] c= new double[a.length+b.length];
	int i = 0, j = 0;
	for (int k = 0; k < c.length; k++) {
		if (i >= a.length) {
			c[k] = b[j++];
			System.out.println("c in i"+k+"-->"+c[k]);
		}
		else if (j >= b.length) {
			c[k] = a[i++];
			System.out.println("c in j"+k+"-->"+c[k]);
		}
		else if (a[i] <= b[j]) {
			c[k] = a[i++];
			System.out.println("c in ii"+k+"-->"+c[k]);
		}
		else {
			c[k] = b[j++];
			System.out.println("c in else"+k+"-->"+c[k]);
		}
	}
	return c;
	
}
	
	// generate N real numbers between 0 and 1, and mergesort them
	public static void main(String[] args) {
		int N = 8;
		double[] a = { 45, 23, 11, 89, 1, 98, 4, 28, 65, 43 };
		/*double[] a = new double[N];
		for (int i = 0; i < N; i++)
			a[i] = Math.random();*/
		a = mergesort(a);
		for (int i = 0; i < N; i++)
			System.out.println((a[i]));

		//System.out.println(isSorted(a));
	}
}
