package week13.BOJ2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ2110 {
	static int N, C;
	static int[] homes;
	

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		homes = new int[N];
		for(int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(homes);
		
		binarySearch(0, homes[N-1]);
		
	}


	private static void binarySearch(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2;
			
			int position = 0;
			int cnt = 1;
			for(int i = 1; i < homes.length; i++) {
				if(homes[i] - homes[position] >= mid) {
					position = i;
					cnt++;
				}
			}
			
			if(cnt < C) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(left-1);
	}
}