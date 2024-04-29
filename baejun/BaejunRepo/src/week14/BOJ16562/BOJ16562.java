package week14.BOJ16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ16562 {
	static int N, M, K;
	static int[] cost;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		cost = new int[N+1];
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i < N+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		parents = new int[N+1];
		makeSet();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			union(from, to);
		}
		
		int ans = 0;
		for(int i = 1; i < N+1; i++) {
			if(parents[i] == i) {
				ans += cost[i];
			}
		}
		if(ans <= K) {
			System.out.println(ans);
		} else {
			System.out.println("Oh no");
		}
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) {
			if(cost[rootA] > cost[rootB]) {
				parents[rootA] = rootB; 
			}
			else {
				parents[rootB] = rootA;
			}
		}
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}

	private static void makeSet() {
		for(int i = 1; i < N+1; i++) {
			parents[i] = i;
		}
	}
}