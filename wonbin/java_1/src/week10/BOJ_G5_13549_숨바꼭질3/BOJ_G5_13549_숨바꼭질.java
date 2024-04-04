package week10.BOJ_G5_13549_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_G5_13549_숨바꼭질 {

	static int N;
	static int K;
	static int[] visited;
	static int count;
	
	static class person{
		int p;
		int cnt;
		
		public person(int p, int cnt) {
			this.p = p;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		K = Integer.parseInt(s2[1]);
		visited = new int[500000];
		count = Integer.MAX_VALUE;
		
		bfs(N);
		
		System.out.println(count);
	}

	private static void bfs(int n) {
		Deque<person> que = new ArrayDeque<>();
		que.offer(new person(n, 0));
		int tmp = 0;
		visited[n] = 1;
		
		while(!que.isEmpty()) {
			
			person point = que.poll();
			
			
			if(point.p == K) {
				count = point.cnt;
				return;
			}
			
			
			if(0 <= point.p * 2 && point.p * 2 <= 100000 && visited[point.p * 2] == 0) {
				que.addFirst(new person(point.p * 2, point.cnt));
				visited[point.p * 2] = 1;
			}
			
			if(0 <= point.p - 1 && point.p - 1 <= 100000 && visited[point.p - 1] == 0) {
				que.offer(new person(point.p - 1, point.cnt + 1));
				visited[point.p - 1] = 1;
			}
			
			if(0 <= point.p + 1 && point.p + 1 <= 100000 && visited[point.p + 1] == 0) {
				que.offer(new person(point.p + 1, point.cnt + 1));
				visited[point.p + 1] = 1;
			}
			
			
		}
	}

}