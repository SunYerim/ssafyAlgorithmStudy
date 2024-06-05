package week18.BOJ_G4_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ_G4_1647_도시분할계획 {

	static int N;
	static int M;
	static int load;
	static ArrayList<node>[] graph;
	static int[] visited;
	static int maxcost;
	static int answer;
	
	static class node implements Comparable<node> { // 우선순위 큐를 사용하기 위해 Comparable 사용
		int w;
		int cost;
		
		public node(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "node [w=" + w + ", cost=" + cost + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		visited = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2= s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new node(b, c));
			graph[b].add(new node(a, c));
			
		}
		
		Prim(1);
		answer -= maxcost;
		
		System.out.println(answer);
		
	}
	
	static void Prim(int start) { // MST 구현
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(start, 0));
		
		while(!pq.isEmpty()) {
			node e = pq.poll();
			
			int v = e.w;
			int cost = e.cost;
			
			if(visited[v] == 1)
				continue;
			
			visited[v] = 1;
			maxcost = Math.max(maxcost, cost);
			answer += cost;
			
			for(node n : graph[v]) {
				if(visited[n.w] == 0)
					pq.offer(n);
			}
			
			
		}
		
		
	}
}
