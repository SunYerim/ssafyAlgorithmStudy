package week4.BOJ17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int population;
	List<Integer> list;
	
	Node(int population) {
		this.population = population;
		list = new ArrayList<Integer>();
	}
}

public class BOJ17471 {
	static boolean[] visited;
	static int N;
	static Node[] nodes;
	static int minValue = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		nodes = new Node[N+1];
		StringTokenizer st = new StringTokenizer(in.readLine());
		nodes[0] = new Node(0);
		for(int i = 1; i < N+1; i++) {
			nodes[i] = new Node(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(in.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				int idx = Integer.parseInt(st.nextToken());
				if(!nodes[i].list.contains(idx)) nodes[i].list.add(idx);
				if(!nodes[idx].list.contains(i)) nodes[idx].list.add(i);
			}
		}
		
		for(int i = 1; i < N; i++) {
			visited = new boolean[N+1];
			combination(1, i);
		}
		minValue = (minValue == Integer.MAX_VALUE) ? -1 : minValue;
		System.out.println(minValue);
	}

	private static void combination(int start, int r) {
		//기저조건
		if(r == 0) {
			ArrayList<Integer> pickNumber = new ArrayList<>();
			ArrayList<Integer> nonPickNumber = new ArrayList<>();
			for(int i = 1; i < N+1; i++) {
				if(visited[i]) {
					pickNumber.add(i);
				} else {
					nonPickNumber.add(i);
				}
			}

			if(bfs1(pickNumber) && bfs2(nonPickNumber)) {
				int pickCnt = 0;
				int nonPickCnt = 0;
				for(int e : pickNumber) {
					pickCnt += nodes[e].population;
				}
				for(int e : nonPickNumber) {
					nonPickCnt += nodes[e].population;
				}
				if(Math.abs(pickCnt - nonPickCnt) < minValue) minValue = Math.abs(pickCnt - nonPickCnt);
			}
			return;
		}
		//유도조건
		for(int i = start; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combination(i+1, r-1);
				visited[i] = false;
			}
		}
	}

	private static boolean bfs1(ArrayList<Integer> pickNumber) {
		if(pickNumber.size() == 1) return true;
		boolean[] visit = new boolean[N+1];
		int start = pickNumber.get(0);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visit[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			for(int i = 0; i < nodes[node].list.size(); i++) {
				if(!visit[nodes[node].list.get(i)] && pickNumber.contains(nodes[node].list.get(i))) {
					visit[nodes[node].list.get(i)] = true;
					queue.offer(nodes[node].list.get(i));
				}
			}
		}
		
		for(int e : pickNumber) {
			if(!visit[e]) return false;
		}
		return true;
	}
	
	private static boolean bfs2(ArrayList<Integer> nonPickNumber) {
		if(nonPickNumber.size() == 1) return true;
		boolean[] visit = new boolean[N+1];
		int start = nonPickNumber.get(0);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visit[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			for(int i = 0; i < nodes[node].list.size(); i++) {
				if(!visit[nodes[node].list.get(i)] && nonPickNumber.contains(nodes[node].list.get(i))) {
					visit[nodes[node].list.get(i)] = true;
					queue.offer(nodes[node].list.get(i));
				}
			}
		}
		
		for(int e : nonPickNumber) {
			if(!visit[e]) return false;
		}
		return true;
	}
}