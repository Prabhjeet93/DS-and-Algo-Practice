
public class methodName {
	
	public static void Method1() {
		System.out.println("Inside Method1");
		Method2();
	}
	
	public static void Method2() {
		System.out.println("Inside Method2");
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		System.out.println(stackTrace[2].getMethodName());
	}

	
	public static void main(String[] args) {
		
		 Method1();
		
	}
}
