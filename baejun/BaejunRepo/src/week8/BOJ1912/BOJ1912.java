package week8.BOJ1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1912 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		int[] acc = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		acc[0] = arr[0];
		int maxValue = acc[0];
		for(int i = 1; i < N; i++) {
			if(acc[i-1] + arr[i] > arr[i]) {
				acc[i] = acc[i-1] + arr[i];
			} else {
				acc[i] = arr[i];
			}
			maxValue = Math.max(maxValue, acc[i]);
		}
		System.out.println(maxValue);
	}
}
