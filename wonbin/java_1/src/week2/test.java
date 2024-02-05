package week2;

public class test {

	public static void main(String[] args) {
		System.out.println("test");
		String s ="abcdef";
		s=s.replace(String.valueOf(s.charAt(2)), "");
		
		System.out.println(s);
	}
}
