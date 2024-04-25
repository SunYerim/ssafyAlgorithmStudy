package week13.BOJ_G3_11085_군사이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_G3_11085_군사이동 {

	static int p;
	static int w;
	static int c;
	static int v;
	static ArrayList<node>[] graph;
	static int[] dist;
	static int[] visited;
	
	static class node implements Comparable<node>{
		int w;
		int cost;
		
		public node(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "node [w=" + w + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(node o) {
			return this.cost - o.cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		p = Integer.parseInt(s2[0]);
		w = Integer.parseInt(s2[1]);
		
		s = br.readLine();
		s2 = s.split(" ");
		
		c = Integer.parseInt(s2[0]);
		v = Integer.parseInt(s2[1]);
		graph = new ArrayList[p];
		visited = new int[p];
		dist = new int[p];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0;i<p;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<w;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new node(b, c));
			graph[b].add(new node(a, c));
		}
		
		dist[c] = 0;
		dijk(c, 0);
		
		System.out.println(dist[v]);
	}

	private static void dijk(int start, int value) {
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(start, value));
		
		while(!pq.isEmpty()) {
			node nd = pq.poll();
			
			if(visited[nd.w] == 1)
				continue;
			
			for(node next : graph[nd.w]) {
				if(dist[next.w] > dist[nd.w] + next.cost) {
					dist[next.w] = dist[nd.w] + next.cost;
					visited[next.w] = 1;
					pq.offer(new node(next.w, dist[next.w]));
				}
			}
		}
	}
}
