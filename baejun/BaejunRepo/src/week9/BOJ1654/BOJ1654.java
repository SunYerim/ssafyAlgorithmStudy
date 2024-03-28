package week9.BOJ1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654 {
	static long K, N;
	static long[] randsome;
	static long maxValue;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		K = Long.parseLong(st.nextToken());
		N = Long.parseLong(st.nextToken());
		randsome = new long[(int) K];
		for(int i = 0; i < (int) K; i++) {
			randsome[i] = Long.parseLong(in.readLine());
		}
		Arrays.sort(randsome);
		binarySearch(0, 2147483647);
		System.out.println(maxValue);
		
	}
	private static void binarySearch(long left, long right) {
		long mid = (left + right) / 2;
		long cnt = 0;
		boolean flag = false;
		
		if(left <= right) {
			for(int i = 0; i < (int) K; i++) {
				cnt += randsome[i] / mid;
				if(cnt >= N) {
					flag = true;
					break;
				}
			}
			if(flag) {
				left = mid + 1;
				maxValue = mid;
				binarySearch(left, right);
			} else {
				right = mid - 1;
				binarySearch(left, right);
			}
		}
	}
}
