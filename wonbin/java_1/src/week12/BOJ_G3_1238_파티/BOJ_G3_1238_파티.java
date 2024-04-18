package week12.BOJ_G3_1238_파티;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_G3_1238_파티 {

	static int N;
	static int M;
	static int X;
	static ArrayList<node>[] graph;
	static ArrayList<node>[] graph2;
	static int[] dist;
	static int[] dist2;
	static int[] visited;
	static int max;
	
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		X = Integer.parseInt(s2[2]);
		graph = new ArrayList[N+1];
		graph2 = new ArrayList[N+1];
		visited = new int[N+1];
		dist = new int[N+1];
		dist2 = new int[N+1];
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, 3000000);
		Arrays.fill(dist2, 3000000);
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new node(b,c));
			graph2[b].add(new node(a,c));
		}
		
		bfs(X);
		visited = new int[N+1];
		bfs2(X);
		
		for(int i=1;i<=N; i++) {
			if(dist[i] != 3000000 && dist2[i] != 3000000)
				max = Math.max(max, dist[i]+dist2[i]);
		}
		
		bw.append(max+"");
		bw.append("\n");
		bw.close();
		
	}

	private static void bfs(int num) {
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(num, 0));
		dist[num] = 0;
		
		while(!pq.isEmpty()) {
			node nd = pq.poll();
			
			if(visited[nd.w] == 0)
				visited[nd.w]++;
			else
				continue;
			
			for(node e : graph[nd.w]) {
				if(dist[e.w] > dist[nd.w]+e.cost) {
					dist[e.w] = dist[nd.w]+e.cost;
					pq.offer(new node(e.w, dist[e.w]));
				}
			}
		}
		
	}
	
	private static void bfs2(int num) {
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(num, 0));
		dist2[num] = 0;
		
		while(!pq.isEmpty()) {
			node nd = pq.poll();
			
			if(visited[nd.w] == 0)
				visited[nd.w]++;
			else
				continue;
			
			for(node e : graph2[nd.w]) {
				if(dist2[e.w] > dist2[nd.w]+e.cost) {
					dist2[e.w] = dist2[nd.w]+e.cost;
					pq.offer(new node(e.w, dist2[e.w]));
				}
			}
		}
		
	}
}
