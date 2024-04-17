package week12.BOJ14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14938 {
	static int N, M, R;
	
	static class Node {
		int idx;
		int weight;
		
		Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
	
	static ArrayList<ArrayList<Node>> map;
	static ArrayList<Integer> itemList;
	static int[] dist;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		itemList = new ArrayList<>();
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			itemList.add(Integer.parseInt(st.nextToken()));
		}
		
		map = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			map.add(new ArrayList<>());
		}
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken()) - 1;
			int num2 = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			map.get(num1).add(new Node(num2, weight));
			map.get(num2).add(new Node(num1, weight));
		}
		
		max = 0;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			dijkstra(i);
			for(int j = 0; j < dist.length; j++) {
				if(dist[j] <= M) {
					sum += itemList.get(j);
				}
			}
			if(max < sum) max = sum;
		}
		System.out.println(max);
	}

	private static void dijkstra(int start) {
		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		
		dist[start] = 0;
		queue.offer(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(dist[curNode.idx] < curNode.weight) continue;
			
			for(int i = 0; i < map.get(curNode.idx).size(); i++) {
				Node ntxNode = map.get(curNode.idx).get(i);
				if(dist[ntxNode.idx] > ntxNode.weight + curNode.weight) {
					dist[ntxNode.idx] = ntxNode.weight + curNode.weight;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
	}
}