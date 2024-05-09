package week15.BOJ_G3_2623_음악프로그램;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BOJ_G3_2623_음악프로그램 {

	static int N;
	static int M;
	static int[] count;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> answer;
	static int flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		count = new int[N+1];
		graph = new ArrayList[N+1];
		answer = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			for(int j=1;j<=Integer.parseInt(s2[0]);j++) {
				if(j != Integer.parseInt(s2[0])) {
					graph[Integer.parseInt(s2[j])].add(Integer.parseInt(s2[j+1]));
				}
			}
		}
		
		for(int i=1;i<=N;i++) { // 위상정렬 테이블 채우기
			for(int j=0;j<graph[i].size();j++) {
				count[graph[i].get(j)]++;
			}
		}
		
		TopologicalSort();
		
		for(int i=1;i<=N;i++) {
			if(count[i] != 0) {
				flag++;
				break;
			}
		}
		
		if(flag != 0)
			System.out.println(0);
		else {
			for(int i=0;i<answer.size();i++) {
				bw.append(answer.get(i)+"");
				bw.append("\n");
			}
			
			bw.close();
		}

	}

	private static void TopologicalSort() {
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++) {
			if(count[i] == 0)
				que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int tmp = que.poll();
			answer.add(tmp);
			
			for(int i=0;i<graph[tmp].size();i++) {
				count[graph[tmp].get(i)]--;
				if(count[graph[tmp].get(i)] == 0)
					que.offer(graph[tmp].get(i));
			}
		}
		
	}

}
