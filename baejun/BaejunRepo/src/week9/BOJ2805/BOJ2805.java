package week9.BOJ2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805 {
	static long N, M;
	static long[] trees;
	static long maxValue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		trees = new long[(int) N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(trees);
		long left = 0;
		long right = 1000000000;
		
		binarySearch(left, right);
		
		System.out.println(maxValue);
	}
	private static void binarySearch(long left, long right) {
		long mid = (left + right) / 2;
		boolean flag = false;
		long value = 0;
		if(left <= right) {
			for(int i = 0; i < N; i++) {
				if(trees[i]-mid > 0) {
					value += (trees[i]-mid);
				}
				if(value >= M) {
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
		return;
	}
}
