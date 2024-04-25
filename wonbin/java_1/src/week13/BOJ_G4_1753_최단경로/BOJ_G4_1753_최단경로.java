package week13.BOJ_G4_1753_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_G4_1753_최단경로 {

	static int V;
	static int E;
	static int start;
	static ArrayList<line>[] graph;
	static int[] visited;
	static int[] dist;
	
	
	static class line implements Comparable<line>{
		int v;
		int w;
		
		public line(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(line o) {
			return this.w - o.w;
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		V = Integer.parseInt(s2[0]);
		E = Integer.parseInt(s2[1]);
		start = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		dist = new int[V+1];
		visited = new int[V+1];
		
		Arrays.fill(dist, 30000000);
		
		for(int i=0;i<=V;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new line(b,c));
			
		}
		
		dist[start] = 0;
		bfs(start);
		
		for(int i=1;i<=V;i++) {
			if(dist[i] == 30000000) {
				bw.append("INF");
				bw.append("\n");
			}
			else {
				bw.append(dist[i]+"");
				bw.append("\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(int start) {
		PriorityQueue<line> que = new PriorityQueue();
		que.offer(new line(start, 0));
		
		while(!que.isEmpty()) {
			line li = que.poll();
			
			if(visited[li.v] == 1)
				continue;
			visited[li.v] = 1;
			
			for(line l : graph[li.v]) {
				if(dist[l.v] > dist[li.v] + l.w) {
					dist[l.v] = dist[li.v] + l.w;
					que.offer(new line(l.v, dist[l.v]));
				}
			}
		}
		
	}
}
