package week2.BOJ1548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1548 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		/* input 처리 */
		int N = Integer.parseInt(in.readLine());
		int[] sequence = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		/* 처리로직 */
		Arrays.sort(sequence); // 배열 정렬
		int maxLength = 0;
		for(int i = 0; i < N; i++) {
			int currentLength = 0;
			int pointer = i + 2;
			while(pointer < N) {
				// 삼각 수열 만족한다면 범위 확장, 아니라면 쫑
				if(sequence[i] + sequence[i+1] > sequence[pointer]) {
					currentLength++;
					pointer++;
				} else {
					break;
				}
			}
			if(maxLength < currentLength) maxLength = currentLength;
		}
		maxLength = (N == 1) ? maxLength + 1 : maxLength + 2;
		System.out.println(maxLength);
	}
}