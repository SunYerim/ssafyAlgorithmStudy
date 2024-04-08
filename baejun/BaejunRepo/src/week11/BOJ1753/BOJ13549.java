package week11.BOJ1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549 {
	static int V, E, start;
	static class Node {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	static ArrayList<ArrayList<Node>> list;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i = 0; i < V+1; i++) {
			list.add(new ArrayList<>());
		}
		
		start = Integer.parseInt(in.readLine());
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list.get(from).add(new Node(to, cost));
		}
		
		dijkstra(start);
		for(int i = 1; i < dist.length; i++) {
			if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(dist[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start2) {
		dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		queue.offer(new Node(start, 0));
		
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