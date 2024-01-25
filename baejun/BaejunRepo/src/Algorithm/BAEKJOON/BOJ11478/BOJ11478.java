package Algorithm.BAEKJOON.BOJ11478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ11478 {
	static Set<String> stringSet = new HashSet<String>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		String inputStr = in.readLine();
		
		/* 모든 부분문자열 추출 */ 
		for(int j = 1; j <= inputStr.length(); j++) {
			for(int i = 0; i <= inputStr.length() - j; i++) {
				String a = inputStr.substring(i, i + j);
				stringSet.add(a); //hashSet에 추가(중복제거)
			}	
		}
		
		sb.append(stringSet.size());
		System.out.println(sb);
	}
}
