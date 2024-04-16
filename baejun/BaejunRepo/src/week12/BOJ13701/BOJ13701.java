package week12.BOJ13701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ13701 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new HashSet<>();
		
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			if(set.add(num)) {
				sb.append(num).append(" ");
			}
		}
		System.out.println(sb);
	}
}