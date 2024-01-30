package BOJ.BOJ_2800_괄호제거;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2800_괄호제거 {
	
	private static int N; // N개 중
	private static int R; // R개를 뽑아 줄세우는 경우의 수 구하기 (순열)
	private static boolean[] isSelected; // 현재 뽑은 수 flag 배열
	private static int[] numbers; // 현재까지 뽑은 수를 저장하는 배열
	private static int[] input; // 우리가 뽑을 원소들을 저장하는 배열
	private static int f_count = 0;
	private static int b_count = 0;

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		int count = 0;
		for(int i =0 ;i<s.length();i++) {
			if(s.charAt(i) == '(') {
				count++;
			}
		}
		N = count;
		
		for(int i = 1; i<=N;i++) {
			input[i - 1] = i;
		}

		
		for(int i=1;i<=count;i++) {
			R = i;
			Permutation(0, s);
		}
	}


	private static void Permutation(int cnt, String s) {
		if(cnt == R) {
			List<Character> list = new ArrayList<>();
			for(int i =0 ;i<s.length();i++) {
				if(s.charAt(i) == '(') {
					f_count++;
					for(int j = 0; j<N;j++) {
						if(f_count == numbers[i]) {
							
						}
					}
				}
			}
			return;
		}
		
		// 유도부분
		for(int i = 0; i<N ;i++) { // 가능한 모든 수 시도
			
			// 선택 여부 체크 (이미 뽑았는지 확인!)
			if(isSelected[i]) {
				continue;
			}
			
			numbers[cnt] = input[i]; // 뽑은 숫자 i를 결과 배열에 저장
			isSelected[i] = true; // 뽑은 숫자 체크
			Permutation(cnt + 1, s); // 다음 숫자 뽑으러 가기
			isSelected[i] = false; // 하나의 경우의 수를 구한 후 돌아 왔을 때, 뽑지 않은 상태로 되돌림

		
	}
}
