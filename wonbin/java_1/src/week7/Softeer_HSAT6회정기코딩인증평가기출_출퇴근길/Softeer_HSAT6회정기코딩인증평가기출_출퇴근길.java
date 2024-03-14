package week7.Softeer_HSAT6회정기코딩인증평가기출_출퇴근길;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Softeer_HSAT6회정기코딩인증평가기출_출퇴근길 {

	static int n; // 정점 개수
	static int m; // 간선 개수
	static int S; // 출발지
	static int T; // 도착지
	static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	static ArrayList<Integer> tmp = new ArrayList<>();
	static int[] start_count;
	static int[] return_count;
	static int[] visited;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		n = Integer.parseInt(s2[0]);
		m = Integer.parseInt(s2[1]);
		start_count = new int[n + 1];
		return_count = new int[n + 1];
		visited = new int[n + 1];
		answer = 0;

		for (int i = 0; i < m + 1; i++) {
			map.add(new ArrayList<>()); // 간선 수만큼 배열 초기화
		}

		for (int i = 0; i < m; i++) {
			s = br.readLine();
			s2 = s.split(" ");

			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);

			map.get(start).add(end);
		}

		s = br.readLine();
		s2 = s.split(" ");
		S = Integer.parseInt(s2[0]);
		T = Integer.parseInt(s2[1]);

		bfs_s(S);
		Arrays.fill(visited, 0);
		bfs_t(T);

		for (int i = 1; i <= n; i++) {
			if (start_count[i] != 0 && return_count[i] != 0) {
				if(i == S || i == T)
					continue;
				else
					answer++;
			}
		}

		bw.append(answer + "");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

	static void bfs_s(int start) {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(start);
		
		while(!que.isEmpty()) {
			int tmp = que.poll();
			start_count[tmp]++;
			
			for(int i=0;i<map.get(tmp).size();i++) {
				if(map.get(tmp).get(i) == S)
					que.offer(map.get(tmp).get(i));
				else if(visited[map.get(tmp).get(i)] == 0 && map.get(tmp).get(i) != T) {
					que.offer(map.get(tmp).get(i));
					visited[map.get(tmp).get(i)]++;
				}
			}
		}
	}
	
	static void bfs_t(int start) {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(start);
		
		while(!que.isEmpty()) {
			int tmp = que.poll();
			return_count[tmp]++;
			
			for(int i=0;i<map.get(tmp).size();i++) {
				if(map.get(tmp).get(i) == T)
					que.offer(map.get(tmp).get(i));
				else if(visited[map.get(tmp).get(i)] == 0 && map.get(tmp).get(i) != S) {
					que.offer(map.get(tmp).get(i));
					visited[map.get(tmp).get(i)]++;
				}
			}
		}
	}

}
