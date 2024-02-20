package week4.BOJ2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2252 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		int[] dist = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			graph.get(idx1).add(idx2);
			dist[idx2]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i < dist.length; i++) {
			if(dist[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node + " ");
			for(int i = 0; i < graph.get(node).size(); i++) {
				int idx = graph.get(node).get(i);
				dist[idx]--;
				if(dist[idx] == 0) {
					queue.offer(idx);
				}
			}
		}
		System.out.println(sb);
	}
}
