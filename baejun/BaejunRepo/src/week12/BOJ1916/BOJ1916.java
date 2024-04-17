package week12.BOJ1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ1916 {
	static int N, M;
	
	static class Node {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static ArrayList<ArrayList<Node>> map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		
		map = new ArrayList<>();
		for(int i = 0; i < N+1; i++) {
			map.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());

			map.get(from).add(new Node(to, cost));
		}
		
		st = new StringTokenizer(in.readLine());
		int start = Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());
		
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		
		dist[start] = 0;
		
		queue.offer(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(dist[curNode.idx] < curNode.cost) continue;
			
			for(int i = 0; i < map.get(curNode.idx).size(); i++) {
				Node ntxNode = map.get(curNode.idx).get(i);
				
				if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
		
		System.out.println(dist[destination]);
	}
}