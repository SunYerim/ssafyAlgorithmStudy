package week2.BOJ24445;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ24445 {
	private static boolean[] visited;
	private static int[] order;
	private static int rank = 0;
	private static Map<Integer, List<Integer>> map = new HashMap<>();
	
	private static void bfs(int R) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(R);
		
		while(!queue.isEmpty()) {
			int key = queue.poll();
			if(!visited[key]) {
				visited[key] = true;
				order[key] = ++rank;
				if(map.containsKey(key)) {
					for (int e : map.get(key)) {
						if(!visited[e]) {
							queue.offer(e);
						}
					}
				}
			}
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		order = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			if (map.get(num1) == null) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(num2);
				map.put(num1, list);
			} else {
				map.get(num1).add(num2);
			}
			if (map.get(num2) == null) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(num1);
				map.put(num2, list);
			} else {
				map.get(num2).add(num1);
			}
		}
		
		map.forEach((key, value) -> {
			Collections.sort(value);
		});
		
		
		map.forEach((key, value) -> {
			Collections.reverse(value);
		});
		
		bfs(R);
		
		for(int i = 1; i < order.length; i++) {
			sb.append(order[i]).append("\n");
		}
		System.out.println(sb);
	}
}
