package week7.BOJ1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1389 {
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] dist = new int[N+1][N+1];
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				if(i == j) { // 자기자신 0으로 초기화
					dist[i][j] = 0;
					continue;
				}
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i < M; i++) { // 양방향으로 값 주기
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			dist[num1][num2] = 1;
			dist[num2][num1] = 1;
		}
		
		// 플로이드 워셜
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				for(int k = 1; k < N+1; k++) {
					dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
				}	
			}
		}
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i = 1; i < N+1; i++) {
			int num = 0;
			for(int j = 1; j < N+1; j++) {
				num+=dist[i][j];
			}
			if(i == 1) {
				min = num;
				minIndex = 1;
				continue;
			}
			if(min > num) {
				min = num;
				minIndex = i;
			}
		}
		System.out.println(minIndex);
	}
}
