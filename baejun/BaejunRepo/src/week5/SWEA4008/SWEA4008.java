package week5.SWEA4008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4008 {
	static boolean[] visited;
	static char[] operators;
	static int[] numbers;
	static int N;
	static char[] ops;
	static int minValue;
	static int maxValue;
	static int[] operCount;
	static char[] oper = {'+', '-', '*', '/'};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			minValue = Integer.MAX_VALUE;
			maxValue = Integer.MIN_VALUE;
			sb.append("#" + test_case + " ");
			// 입력값 처리과정
			N = Integer.parseInt(in.readLine());
			operators = new char[N-1];
			ops = new char[N-1];
			operCount = new int[4];
			int operatorsIdx = -1;
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < 4; i++) {
				int cnt = Integer.parseInt(st.nextToken());
				operCount[i] = cnt;
				char op = '+';
				switch(i) {
				case 0:
					op = '+';
					break;
				case 1:
					op = '-';
					break;
				case 2:
					op = '*';
					break;
				case 3:
					op = '/';
					break;
				}
				for(int j = 0; j < cnt; j++) {
					operators[++operatorsIdx] = op;
				}
			}
			numbers = new int[N];
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			// 로직구현
			visited = new boolean[N-1];
			permutation(0);
			if(minValue < 0) {
				minValue = Math.abs(minValue);
				if(maxValue < 0) {
					sb.append(Math.abs(minValue + maxValue));
				} else {
					sb.append(maxValue + minValue);				
				}
			} else {
				sb.append(maxValue - minValue);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int start) {
		// 기저조건
		if(start == N-1 && operCount[0] >= 0 && operCount[1] >= 0 && operCount[2] >= 0 && operCount[3] >= 0) {
			int numbersIdx = 0;
			int sum = numbers[0];
			for(char op : ops) {
				switch(op) {
				case '+':
					sum += numbers[++numbersIdx];
					break;
				case '-':
					sum -= numbers[++numbersIdx];
					break;
				case '*':
					sum *= numbers[++numbersIdx];
					break;
				case '/':
					sum /= numbers[++numbersIdx];
					break;
				}
			}
			if(sum > maxValue) maxValue = sum;
			if(sum < minValue) minValue = sum;
			return;
		}
		//유도조건
		for(int i = 0; i < 4; i++) {
			if(operCount[i] == 0) continue;
			ops[start] = oper[i];
			operCount[i]--;
			permutation(start+1);
			operCount[i]++;
		}
	}
}