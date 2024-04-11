package week11.BOJ1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549 {
	static int N, E;
	static int[] dist;

	static class Node {
		int idx;
		int cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for(int i = 0; i < N+1; i++) {
			list.add(new ArrayList<>());
		}

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list.get(from).add(new Node(to, cost));
			list.get(to).add(new Node(from, cost));
		}

		st = new StringTokenizer(in.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		int answer = -1;

		dist = new int[N+1];
		djikstra(1); // 시작점
		int oneToV1 = (dist[v1] != Integer.MAX_VALUE) ? dist[v1] : Integer.MAX_VALUE;
		int oneToV2 = (dist[v2] != Integer.MAX_VALUE) ? dist[v2] : Integer.MAX_VALUE;
		if(v1 != 1) {
			dist = new int[N+1];
			djikstra(v1); // v1
		}
		int v1ToV2 = (dist[v2] != Integer.MAX_VALUE) ? dist[v2] : Integer.MAX_VALUE;
		int v1ToN = (dist[N] != Integer.MAX_VALUE) ? dist[N] : Integer.MAX_VALUE;
		dist = new int[N+1];
		djikstra(v2); // v2

		int v2ToV1 = (dist[v1] != Integer.MAX_VALUE) ? dist[v1] : Integer.MAX_VALUE;
		int v2ToN = (dist[N] != Integer.MAX_VALUE) ? dist[N] : Integer.MAX_VALUE;

		if((oneToV1 + v1ToV2 + v2ToN) < (oneToV2 + v2ToV1 + v1ToN)) {
			if(oneToV1 != Integer.MAX_VALUE && v1ToV2 != Integer.MAX_VALUE && v2ToN != Integer.MAX_VALUE) {
				answer = oneToV1 + v1ToV2 + v2ToN;
			} else if(oneToV2 != Integer.MAX_VALUE && v2ToV1 != Integer.MAX_VALUE && v1ToN != Integer.MAX_VALUE) {
				answer = oneToV2 + v2ToV1 + v1ToN;
			}
		} else {
			if(oneToV2 != Integer.MAX_VALUE && v2ToV1 != Integer.MAX_VALUE && v1ToN != Integer.MAX_VALUE) {
				answer = oneToV2 + v2ToV1 + v1ToN;
			} else if(oneToV1 != Integer.MAX_VALUE && v1ToV2 != Integer.MAX_VALUE && v2ToN != Integer.MAX_VALUE) {
				answer = oneToV1 + v1ToV2 + v2ToN;
			} 
		}

		System.out.println(answer);
	}

	private static void djikstra(int start) {
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[start] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		queue.offer(new Node(start, dist[start]));

		while(!queue.isEmpty()) {
			Node curNode = queue.poll();

			if(curNode.cost > dist[curNode.idx]) continue;

			for(int i = 0; i < list.get(curNode.idx).size(); i++) {
				Node ntxNode = list.get(curNode.idx).get(i);

				if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
	}
}