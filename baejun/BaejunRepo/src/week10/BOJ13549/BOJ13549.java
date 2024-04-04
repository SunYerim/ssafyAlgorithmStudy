package week10.BOJ13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ13549 {
	
	static class Node {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static int start, K;
	static ArrayList<ArrayList<Node>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		start = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		
		
		for(int i = 0; i < 100001; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i <= 100000; i++) {
			if(i < 100000) {
				graph.get(i).add(new Node(i+1, 1));
			}
			if(i > 0) {
				graph.get(i).add(new Node(i-1, 1));
			}
			if(i <= 50000) {
				graph.get(i).add(new Node(i*2, 0));
			}
		}
		
		int[] dist = new int[100001];
		Arrays.fill(dist, 100000);
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		queue.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(dist[curNode.idx] < curNode.cost) { // 더 비싼경우 = 의미없음
				continue;
			}
			
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) { // 연결된 친구들의 최소값 갱신
				Node ntxNode = graph.get(curNode.idx).get(i);
				
				if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.add(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
		System.out.println(dist[K]);
	}
}
