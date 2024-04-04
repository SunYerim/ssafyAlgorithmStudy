package week10.BOJ_G4_1504_특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_G4_1504_특정한최단경로 {

	static int N;
	static int E;
	static ArrayList<point>[] list;
	static int[] dist;
	static int[] dist2;
	static int[] dist3;
	static int v1;
	static int v2;
	static int root1;
	static int root2;
	static int answer;
	static int[] visited;
	
	static class point implements Comparable<point>{ // 오름차순을 위한 comparable 상속
		int v;
		int cost;
		
		public point(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(point o) {
			return this.cost - o.cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		E = Integer.parseInt(s2[1]);
		list = new ArrayList[N+1];
		dist = new int[N+1];
		dist2 = new int[N+1];
		dist3 = new int[N+1];
		visited = new int[N+1];
		
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<>();
			dist[i] = 10000000;
			dist2[i] = 10000000;
			dist3[i] = 10000000;
		}
		
		for(int i=0;i<E;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);
			int cost = Integer.parseInt(s2[2]);
			
			// 양방향 그래프라서 양쪽 다 만들어줌
			list[start].add(new point(end, cost));
			list[end].add(new point(start, cost));
		}
		
		s = br.readLine();
		s2 = s.split(" ");
		
		v1 = Integer.parseInt(s2[0]);
		v2 = Integer.parseInt(s2[1]);
		
		// 최단거리 다익스트라
		dik(1, 0);
		
		if(v2 == N) { // v2가 N이면 v2는 고려 안하고 v1이후 바로 N
			dik2(v1, 0);
			answer = dist[v1] + dist2[N];
		}
		else { // 아닐경우 v1을 먼저 갈 경우와 v2를 먼저 갈 경우로 나누어서 최솟값을 도출
			dik2(v1, 0);
			dik3(v2, 0);
			root1 = dist[v1] + dist2[v2] + dist3[N];
			root2 = dist[v2] + dist3[v1] + dist2[N];
			answer = Math.min(root1, root2);
		}
		
		if(answer >= 10000000)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	private static void dik(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist[next.v] > p.cost + next.cost) {
					dist[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist[next.v]));
				}
			}
		}
		
	}
	
	private static void dik2(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist2[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist2[next.v] > p.cost + next.cost) {
					dist2[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist2[next.v]));
				}
			}
		}
		
	}
	
	private static void dik3(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist3[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist3[next.v] > p.cost + next.cost) {
					dist3[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist3[next.v]));
				}
			}
		}
		
	}
}