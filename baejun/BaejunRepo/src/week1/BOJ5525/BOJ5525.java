package week1.BOJ5525;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ5525 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		String S = in.readLine();
		char[] sentenceArr = new char[S.length()];
		for(int i = 0; i < sentenceArr.length; i++) {
			sentenceArr[i] = S.charAt(i);
		}
		ArrayList<Character> P = new ArrayList<>();
		P.add('I');
		P.add('O');
		P.add('I');
		for(int i = 1; i < N; i++) {
			P.add('O');
			P.add('I');
		}

		ArrayList<Character> strList = new ArrayList<>();
		char peek;
		int answer = 0;
		int sentenceArrLength = sentenceArr.length - 1;
		while(sentenceArrLength >= 0) {
			if(strList.size() == 0) {
				if((peek = sentenceArr[sentenceArrLength--]) == 'I') {
					strList.add(peek);
				}
			} else {
				char strLastIndex = strList.get(strList.size() - 1);
				char stackPeekValue = sentenceArr[sentenceArrLength--];
				if(strLastIndex == stackPeekValue) {
					strList.clear();
					if(stackPeekValue == 'I') {
						strList.add(stackPeekValue);
					}
				} else {
					strList.add(stackPeekValue);
				}
			}
			if(P.size() == strList.size()) {
				answer++;
				strList.remove(strList.size() - 1);
				strList.remove(strList.size() - 1);
			}
		}
		System.out.println(answer);
	}
}
