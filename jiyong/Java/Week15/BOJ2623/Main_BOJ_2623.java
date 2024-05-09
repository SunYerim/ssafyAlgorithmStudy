package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2623 {
	static int N, M, idx, path[], inDegree[];
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		idx = 0;
		path = new int[N];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			inDegree[e]++;
			while (st.hasMoreTokens()) {
				s = e;
				e = Integer.parseInt(st.nextToken());
				graph[s].add(e);
				inDegree[e]++;
			}
		}

		topologicalSort();
		if(idx == N) {
			for(int i = 0; i < N; i++ ) {
				sb.append(path[i]).append("\n");
			}
		} else {
			sb.append(0);
		}
		System.out.println(sb);
	}

	private static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int now = queue.poll();
			path[idx++] = now;
			for (int next : graph[now]) {
				if (--inDegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}
