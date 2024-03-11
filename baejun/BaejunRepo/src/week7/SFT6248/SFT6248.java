package week7.SFT6248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SFT6248 {
	static int N, M, startV, endV;
	static boolean[] visited1, visited2, visited3, visited4;
	static ArrayList<ArrayList<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list.get(num1 - 1).add(num2 - 1);
		}
		
		st = new StringTokenizer(in.readLine());
		
		startV = Integer.parseInt(st.nextToken()) - 1;
		endV = Integer.parseInt(st.nextToken()) - 1;
		
		visited3 = new boolean[N];
		visited4 = new boolean[N];
		
		visited1 = new boolean[N];
		BFS1(startV, endV);
		visited2 = new boolean[N];
		BFS2(endV, startV);
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			if(i == startV || i == endV) continue;
			if(visited3[i] && visited4[i]) cnt++;
		}
		System.out.println(Arrays.toString(visited1));
		System.out.println(Arrays.toString(visited2));

		System.out.println(Arrays.toString(visited3));
		System.out.println(Arrays.toString(visited4));
		System.out.println(cnt);
	}
	
	private static void BFS1(int start, int end) {
		visited1[start] = true;
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			visited3[node] = true;
			if(node == end) break;
			for(int i = 0; i < list.get(node).size(); i++) {
				if(!visited1[list.get(node).get(i)]) {
					visited1[list.get(node).get(i)] = true;
					queue.offer(list.get(node).get(i));
				}
			}
		}
	}
	
	private static void BFS2(int start, int end) {
		visited2[start] = true;
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			visited4[node] = true;
			if(node == end) break;
			for(int i = 0; i < list.get(node).size(); i++) {
				if(!visited2[list.get(node).get(i)]) {
					visited2[list.get(node).get(i)] = true;
					queue.offer(list.get(node).get(i));
				}
			}
		}
	}

	private static void DFS1(int node, int end) {
		//기저조건
		if(node == end) {
			for(int i = 0; i < visited1.length; i++) {
				if(visited1[i]) visited3[i] = true;
			}
			return;
		}
		//유도조건
		for(int i = 0; i < list.get(node).size(); i++) {
//			if(!visited1[list.get(node).get(i)]) {
				visited1[list.get(node).get(i)] = true;
				DFS1(list.get(node).get(i), end);
				visited1[list.get(node).get(i)] = false;
//			}
		}
	}
	
	private static void DFS2(int node, int end) {
		//기저조건
		if(node == end) {
			for(int i = 0; i < visited2.length; i++) {
				if(visited2[i]) visited4[i] = true;
			}
			return;
		}
		//유도조건
		for(int i = 0; i < list.get(node).size(); i++) {
//			if(!visited2[list.get(node).get(i)]) {
				visited2[list.get(node).get(i)] = true;
				DFS2(list.get(node).get(i), end);
				visited2[list.get(node).get(i)] = false;
//			}
		}
	}
}
