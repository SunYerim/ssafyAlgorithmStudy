package week13.BOJ2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ2470 {
	static int N;
	static int[] list;
	static int mid;
	static int answer1, answer2, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		list = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MAX_VALUE;
		Arrays.sort(list); // 이분탐색을 위한 정렬
		
		for(int i = 0; i < N; i++) {
			binarySearch(0, N-1, (list[i]*-1)); // target은 해당 인덱스 값과 합이 0을 이루는 수
			
			if(i == mid) continue; // 같은 인덱스라면 패스
			
			/* mid => target값이 있다면 target Index, 없다면 target값이 삽입된다면 들어갈 index임. 따라서 그 인덱스 양옆의 값을 비교 */
			if(Math.abs(list[mid] + list[i]) < max) {
				max = Math.abs(list[mid] + list[i]);
				answer1 = list[mid];
				answer2 = list[i];
			}
			if(mid != 0 && Math.abs(list[mid-1] + list[i]) < max) {
				max = Math.abs(list[mid-1] + list[i]);
				answer1 = list[mid-1];
				answer2 = list[i];
			}
			if(mid != N-1 && Math.abs(list[mid+1] + list[i]) < max) {
				max = Math.abs(list[mid+1] + list[i]);
				answer1 = list[mid+1];
				answer2 = list[i];
			}
		}
		
		if(answer1 > answer2) System.out.println(answer2 + " " + answer1);
		else System.out.println(answer1 + " " + answer2);
	}

	private static void binarySearch(int left, int right, int target) {
		boolean flag = false;
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(list[mid] == target) {
				flag = true;
				break;
			} else if(list[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
}