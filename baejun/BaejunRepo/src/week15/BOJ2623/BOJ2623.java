package week15.BOJ2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2623 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		int[] dist = new int[N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			ArrayList<Integer> list = new ArrayList<Integer>();
			int number = Integer.parseInt(st.nextToken());
			for(int j = 0; j < number; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			for(int j = 0; j < list.size() - 1; j++) {
				graph.get(list.get(j)).add(list.get(j+1));
				dist[list.get(j+1)]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i < dist.length; i++) {
			if(dist[i] == 0) queue.offer(i);
		}
		int cnt = 0;
		while(!queue.isEmpty()) {
			int node = queue.poll();
			cnt++;
			sb.append(node).append("\n");
			for(int i = 0; i < graph.get(node).size(); i++) {
				int idx = graph.get(node).get(i);
				dist[idx]--;
				if(dist[idx] == 0) {
					queue.offer(idx);
				}
			}
		}
		
		if(cnt < N) {
			System.out.println(0);
			return;
		}

		System.out.println(sb);
	}
}