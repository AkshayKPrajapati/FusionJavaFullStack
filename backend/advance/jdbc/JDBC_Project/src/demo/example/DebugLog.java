package demo.example;

public class DebugLog {
	public static void failLog(String msg) {
		System.out.println("\u001B[31m"+msg+"\u001B[0m");
	}
	public static void successLog(String msg){
		System.out.println("\u001B[32m"+msg+"\u001B[0m");
	}
}
