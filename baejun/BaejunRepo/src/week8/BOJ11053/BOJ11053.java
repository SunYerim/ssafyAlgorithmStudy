package week8.BOJ11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11053 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int[] arr = new int[N];
		int[] acc = new int[N];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int maxValue = 0;
		for(int i = N-1; i >= 0; i--) {
			int maxCnt = 1;
			for(int j = i+1; j < N; j++) {
				if(arr[j] > arr[i]) {
					if(maxCnt < acc[j]+1) maxCnt = acc[j]+1;
				}
			}
			acc[i] = maxCnt;
			maxValue = Math.max(maxValue, acc[i]);
		}
		System.out.println(maxValue);
		
	}
}
