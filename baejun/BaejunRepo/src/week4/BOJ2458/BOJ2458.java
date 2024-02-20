package week4.BOJ2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

class Node {
	ArrayList<Integer> biggerThanV;
	ArrayList<Integer> smallerThanV;

	Node() {
		biggerThanV = new ArrayList<>();
		smallerThanV = new ArrayList<>();
	}
}

public class BOJ2458 {
	static Node[] graph;
	static int[] countBig;
	static int[] countSmall;
	static int answer;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new Node[N+1];
		countBig = new int[N+1];
		countSmall = new int[N+1];
		
		for(int i = 0; i < N+1; i++) {
			graph[i] = new Node();
		}
		/* 큰거랑 작은거 차례로 삽입 */
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			graph[idx1].biggerThanV.add(idx2);
			graph[idx2].smallerThanV.add(idx1);
		}
		/* 자기보다 큰거 순회하기(순회가 됐다면, 순서를 알 수있단 소리임) */
		for(int i = 1; i < N+1; i++) {
			visited = new boolean[N+1];
			biggerThanVDFS(i, i);
		}
		/* 자기보다 작은 거 순회하기 */
		for(int i = 1; i < N+1; i++) {
			visited = new boolean[N+1];
			smallerThanVBFS(i, i);
		}
		/* Big과 Small의 합이 N-1이라면(자기자신 제외) 모두의 순서를 정할 수 있단 소리임 */
		for(int i = 1; i < N+1; i++) {
			if(countBig[i] + countSmall[i] == N-1) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
	private static void smallerThanVBFS(int node, int originalNode) {
		visited[node] = true;
		for(int i = 0; i < graph[node].smallerThanV.size(); i++) {
			int idx = graph[node].smallerThanV.get(i);
			if(!visited[idx]) {
				countSmall[originalNode]++;
				smallerThanVBFS(idx, originalNode);
			}
		}
	}
	private static void biggerThanVDFS(int node, int originalNode) {
		visited[node] = true;
		for(int i = 0; i < graph[node].biggerThanV.size(); i++) {
			int idx = graph[node].biggerThanV.get(i);
			if(!visited[idx]) {
				countBig[originalNode]++;
				biggerThanVDFS(idx, originalNode);
			}
		}
	}
}