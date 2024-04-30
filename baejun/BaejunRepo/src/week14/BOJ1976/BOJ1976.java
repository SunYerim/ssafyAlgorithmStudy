package week14.BOJ1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BOJ1976 {
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		parents = new int[N+1];
		makeSet();
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j < N+1; j++) {
				if(Integer.parseInt(st.nextToken()) == 1 && i < j) {
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(in.readLine());
		int[] route = new int[M];
		for(int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		boolean flag = true;
		for(int i = 0; i < M-1; i++) {
			int rootA = find(route[i]);
			int rootB = find(route[i+1]);
			if(rootA != rootB) {
				flag = false;
				break;
			}
		}
		
		if(flag) System.out.println("YES");
		else System.out.println("NO");
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return;
		
		parents[rootA] = rootB;
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