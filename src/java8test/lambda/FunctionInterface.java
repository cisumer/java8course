package java8test.lambda;

public class FunctionInterface{
	public static void main(String[] args) {
		//Java1.5
		Runnable a=new Runnable(){
			public void run() {
				System.out.println("running");
			}
		};
		//Java1.8
		Runnable b=()->System.out.print("running");
		MyRunnable c=()->System.out.print("running");
	}
}
