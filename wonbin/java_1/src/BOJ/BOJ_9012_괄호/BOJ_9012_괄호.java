package BOJ.BOJ_9012_괄호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_9012_괄호 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> stk = new Stack<>();
		
		int a = Integer.parseInt(br.readLine());
		
		for(int i=0;i<a;i++) {
			String s = br.readLine();
			stk.clear();
			
			for(int j=0;j<s.length();j++) {
				
				if(s.charAt(j) == ')') {
					if(stk.empty()) {
						stk.push(s.charAt(j));
						break;
					}
					else if(stk.peek() == '(') {
						stk.pop();
					}
				}
				else {
					stk.push(s.charAt(j));
				}
			}
			
			if(stk.empty()) {
				bw.write("YES" + '\n');
			}
			else {
				bw.write("NO" +'\n');
			}
		}
		
		bw.close();
	}
}
