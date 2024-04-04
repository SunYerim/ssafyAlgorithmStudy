package week7.SFT6248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SFT6248 {
	static int N, M, startV, endV;
	static boolean[] visited1, visited2, visited3, visited4;
	static ArrayList<ArrayList<Integer>> list, list2;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		list2 = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
			list2.add(new ArrayList<>());
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list.get(num1 - 1).add(num2 - 1);
			list2.get(num2 - 1).add(num1 - 1);
		}

		st = new StringTokenizer(in.readLine());

		startV = Integer.parseInt(st.nextToken()) - 1;
		endV = Integer.parseInt(st.nextToken()) - 1;

		visited1 = new boolean[N];
		visited2 = new boolean[N];
		visited3 = new boolean[N];
		visited4 = new boolean[N];
		
		// 출,퇴근길은 한번씩만 방문, visited3, 4를 통해 해당 노드에서 도착점으로 갈수있는지 체크
		visited1[endV] = true;
		DFS1(startV, visited1);
		visited2[startV] = true;
		DFS1(endV, visited2);
		DFS2(startV, visited3);
		DFS2(endV, visited4);
		int cnt = 0;

		for(int i = 0; i < N; i++) {
			if(i == startV || i == endV) continue;
			if(visited1[i] && visited2[i] && visited3[i] && visited4[i]) cnt++;
		}
		System.out.println(cnt);
	}

	private static void DFS1(int node, boolean visited[]) {
		//기저조건
		if(visited[node]) {
			return;
		}
		visited[node] = true;
		//유도조건
		for(int i = 0; i < list.get(node).size(); i++) {
			DFS1(list.get(node).get(i), visited);
		}
	}

	private static void DFS2(int node, boolean visited[]) {
		//기저조건
		if(visited[node]) {
			return;
		}
		visited[node] = true;
		//유도조건
		for(int i = 0; i < list2.get(node).size(); i++) {
			DFS2(list2.get(node).get(i), visited);
		}
	}
}
