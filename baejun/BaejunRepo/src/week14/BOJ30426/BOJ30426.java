package week14.BOJ30426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ30426 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[K][2];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int L = Integer.parseInt(in.readLine());
		int[] dontGoList = new int[L];
		for(int i = 0; i < L; i++) {
			dontGoList[i] = Integer.parseInt(in.readLine());
		}
		Arrays.sort(dontGoList); // 이분탐색 위함
		
		ArrayList<Integer> paramList = new ArrayList<>();
		paramList.add(0); // 마지막 -> 첫번째로 가는데, 마지막엔 0이어야 utopia라서 초기값 0
		for(int i = K-1; i >=0; i--) {
			ArrayList<Integer> newList = new ArrayList<>();
			boolean[] visited = new boolean[N*2]; // 중복방지

			for(int j = 0; j < paramList.size(); j++) {
				int num1 = (map[i][0] % N)*(-1);
				if(num1 < 0) num1 += N;
				int C1 = paramList.get(j) + num1;
				if(C1 >= N) C1 = C1%N; // C(현위치)는 N보다 클 수 없음(문제에 명시)
				if(!visited[C1] && Arrays.binarySearch(dontGoList, C1) < 0) {
					visited[C1] = true;
					newList.add(C1);
				}
				
				int num2 = (map[i][1] % N)*(-1);
				if(num2 < 0) num2 += N;
				int C2 = paramList.get(j) + num2;
				if(C2 >= N) C2 = C2%N;
				if(!visited[C2] && Arrays.binarySearch(dontGoList, C2) < 0) {
					visited[C2] = true;
					newList.add(C2);
				}
			}
			paramList = newList;
		}
		/* 시작할때 현재위치가, 가능한 경우의 수인지 체크해서 답 출력 */
		if(paramList.indexOf(M) != -1) {
			System.out.println("utopia");
		} else {
			System.out.println("dystopia");
		}
	}
}