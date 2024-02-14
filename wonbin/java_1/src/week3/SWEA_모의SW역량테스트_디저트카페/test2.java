package week3.SWEA_모의SW역량테스트_디저트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		String s2 = "";
		
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
