package week6.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Practice {
	static int[] parents;
	static int N;
	
	static public void makeSet() {
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static public int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int weight;
		
		Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; //같은 조직
		parents[aRoot] = bRoot;
		return true;
	}
	
	static Node[] graph;
	static int V, E;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new Node[E];
		parents = new int[V+1];
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[i] = (new Node(from, to, weight));
		}
		Arrays.sort(graph);
		makeSet();
		
		long weight = 0;
		int cnt = 0;
		for(Node node : graph) {
			if(!union(node.from, node.to)) continue; // 통합이 안일어났으면 pass
			
			weight += node.weight;
			if(++cnt == V-1) break;
		}
		System.out.println(weight);
	}
}
