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
		boolean zeroExist = false;
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			map[i] = Long.parseLong(st.nextToken());
			if(map[i] == 0) zeroExist  = true;
		}
		
		Arrays.sort(map);
		
		answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				boolean flag = binarySearch(j+1, N-1, map[i], map[j], i);
				if(flag) {
					answer++;
					break;
				}
			}
		}
//		System.out.println(set.size());
		System.out.println(answer);
	}

	private static boolean binarySearch(int left, int right, long target, long sumValue, int targetIdx) {
		
		while(left <= right) {
			int mid = (left+right) / 2;
			
			if(mid != targetIdx) {
				if(map[mid]+sumValue == target) {
					return true;
				}
				
				if(map[mid] + sumValue < target) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			} else {
				if(map[mid] + sumValue == target) {
					if((mid>0 && map[mid-1]==map[mid]) || (mid<N-1 && map[mid+1] == map[mid])) {
						return true;
					} else {
						break;
					}
				} else {
					if(map[mid] + sumValue < target) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}
			}
			
		}
		
		return false;
	}



}