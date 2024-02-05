package week2.Programmers_lv2_전화번호목록;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Programmers_lv2_전화번호목록 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		String[] phone_book = new String[s2.length];
		boolean answer = true;
		
		for(int i=0;i<s2.length;i++) {
			phone_book[i] = s2[i];
		}
		
		Arrays.sort(phone_book);
		
		
		for(int i=1;i<phone_book.length;i++) {
			if(phone_book[i].startsWith(phone_book[i-1])) answer = false;
			
			if(answer == false) break;
		}
		
		sb.append(answer);
		System.out.println(sb);
		
	}
}
