package week1.BOJ2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		int startNumber = 1; // 시작 수
		for(int i = 0; i < (N-1); i++) {
			startNumber *= 10;
		}
		int divideNum = startNumber; // 나누기 자릿수 셋팅
		int endNumber = startNumber * 10;
		startNumber *= 2; // 0,1 은 앞자리가 안되므로 2부터 시작
		endNumber -= startNumber; // 범위 수 8, 9는 첫 자리가 안되므로 앞자리 7까지만 진행
		for (int i = startNumber; i < endNumber; i++) {
			int divider = divideNum;
			if(i / divider == 4 || i / divider == 6) { // 첫 자리 4랑 6이면 건너뛰기
				i += divider;
			}
			while(divider > 0) {
				if (!isPrime(i / divider)) {
					i += (divider - 1);
					break;
				}
				/* 모든 자리수 소수면 정ㅂ*/
				if (divider == 1) {
					sb.append(i).append("\n");
					break;
				}
				divider /= 10;	
			}
		}
		System.out.println(sb);
	}

	private static boolean isPrime(int number) {
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i == 0) {
				return false;
			}
		}
		if(number < 2) {
			return false;
		}
		return true;
	}
}
