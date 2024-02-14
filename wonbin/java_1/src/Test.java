import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String s2;
		
		for(int i=0;i<n;i++) {
			String s = br.readLine();
			s2 += s.charAt(0);
			s2 += s.charAt(s.length() -1);
			sb.append(s2).append("\n");
			s2 ="";
		}
		
		System.out.println(sb);
	}
}
