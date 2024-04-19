package week13.BOJ11085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ11085 {
	
	static class Node {
		int idx;
		int cost;
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static int p, w, start, end;
	static ArrayList<ArrayList<Node>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		p = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		
		for(int i = 0; i < p; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < w; i++) {
			st = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new Node(to, cost));
			list.get(to).add(new Node(from, cost));
		}
		
		int[] dist = new int[p];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		
		dist[start] = 0;
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			System.out.println(1);
			
			if(dist[curNode.idx] < curNode.cost) continue;
			
			for(int i = 0; i < list.get(curNode.idx).size(); i++) {
				Node ntxNode = list.get(curNode.idx).get(i);
				
				if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
		
		System.out.println(Arrays.toString(dist));
		
	}
}