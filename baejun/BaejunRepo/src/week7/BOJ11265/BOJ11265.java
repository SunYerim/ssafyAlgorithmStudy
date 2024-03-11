package week7.BOJ11265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11265 {
	static int N, M;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N][N];
		// dist 초기화, 자기자신이라면 0
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) {
					dist[i][j] = 0;
					continue;
				}
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				if(i == j) continue;
				dist[i][j] = cost;
			}
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);				}
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(dist[from-1][to-1] < weight) sb.append("Enjoy other party").append("\n");
			else sb.append("Stay here").append("\n");
		}
		System.out.println(sb);
	}
}
