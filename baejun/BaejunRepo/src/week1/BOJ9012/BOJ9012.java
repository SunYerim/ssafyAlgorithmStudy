package week1.BOJ9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		Stack<Character> stack = new Stack<>();
		for (int test_case = 1; test_case <= T; test_case++) {
			String checkVPSString = in.readLine();
			for(int i = 0; i < checkVPSString.length(); i++) {
				char parenthesis = checkVPSString.charAt(i);
				if(parenthesis == '(') {
					stack.push(parenthesis);
				} else {
					if(!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
					} else {
						stack.push(parenthesis);
					}
				}
			}
			if(stack.isEmpty()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			stack.clear();
		}
	}
}
