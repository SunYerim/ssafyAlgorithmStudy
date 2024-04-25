package week13.BOJ11085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ11085 {
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int p, w, start, end; //p: 정점, w: 간선
	static int[] parents;
	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		p = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(in.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		parents = new int[p];
		edges = new Edge[w];
		
		for(int i = 0; i < w; i++) {
			st = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edges);
		makeSet();
		
		int cnt = 0;
		long weight = 0;
		for(Edge e : edges) {
			if(!union(e.from, e.to)) continue;
			
			weight += e.weight;
			if(++cnt == p-1) break;
		}
		System.out.println(weight);
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		parents[rootA] = rootB;
		return true;
	}

	private static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}

	private static void makeSet() {
		for(int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}
}