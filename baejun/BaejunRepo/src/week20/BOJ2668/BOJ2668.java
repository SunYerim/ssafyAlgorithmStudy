package week20.BOJ2668;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2668 {
	static int N;
	static int[] array;
	static ArrayList<Integer> answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		array = new int[N+1]; // 1번부터 사용
		for(int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(in.readLine());
		}
		
		answer = new ArrayList<>();
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			visited[i] = true;
			DFS(i, i);
			visited[i] = false;
		}
		
		Collections.sort(answer);
		
		System.out.println(answer.size());
		for(int i = 0; i < answer.size(); i++) {
			System.out.println(answer.get(i));
		}
	}

	private static void DFS(int idx, int target) {
		if(!visited[array[idx]]) {
			visited[array[idx]] = true;
			DFS(array[idx], target);
			visited[array[idx]] = false;
		}
		if(array[idx] == target) {
			answer.add(target);
		}
	}
}
