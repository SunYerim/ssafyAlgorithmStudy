package week14.BOJ1253;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ1253 {
	static int N;
	static long[] map;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		map = new long[N];

		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			map[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(map);

		answer = 0;
		for(int i = 0; i < N; i++) {
			long val1 = map[i];
			boolean flag = false;
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				int left = 0;
				int right = N-1;
				while(left <= right) {
					int mid = (left+right) / 2;
					long target = val1 - map[j];
					if(map[mid] == target && mid != i && mid != j) {
						flag = true;
						break;
					}

					if(map[mid] < target) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
			}
			if(flag) answer++;
		}
		System.out.println(answer);
	}

	private static boolean binarySearch(int left, int right, long target, long sumValue, int targetIdx) {

		while(left <= right) {
			int mid = (left+right) / 2;

			if(map[mid]+sumValue == target) {
				return true;
			}

			if(map[mid] + sumValue < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return false;
	}



}