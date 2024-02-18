package week4.BOJ5972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5972 {
	static int V, E, start;
	static ArrayList<ArrayList<Node>> graph;

	static class Node {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = 1;
		graph = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < V + 1; i++) {
			graph.add(new ArrayList<Node>());
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 양방향 그래프
			graph.get(s).add(new Node(e, c));
			graph.get(e).add(new Node(s, c));
		}
		boolean[] visited = new boolean[V + 1];
		int[] dist = new int[V + 1];
		for(int i = 0; i < V + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		queue.offer(new Node(start, 0));
		dist[start] = 0;
		
		// 다익스트라 알고리즘 구현부
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			if(curNode.idx == V) {
				System.out.println(dist[curNode.idx]);
				return;
			}
			
			if(dist[curNode.idx] < curNode.cost) {
				continue;
			}
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
				Node ntxNode = graph.get(curNode.idx).get(i);
				
				if(dist[ntxNode.idx] > curNode.cost + ntxNode.cost) {
					dist[ntxNode.idx] = curNode.cost + ntxNode.cost;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
	}
}
