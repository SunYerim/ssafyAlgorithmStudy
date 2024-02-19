package week4.BOJ4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4485 {
	static class Node {// 다음 노드의 인덱스와, 그 노드로 가는데 필요한 비용을 담고 있다.
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> graph;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int test_case = 0;
		while(true) {
			int N = Integer.parseInt(in.readLine());
			if(N == 0) break;
			test_case++;
			sb.append("Problem " + test_case + ": ");
			int[][] array = new int[N][N];
			graph = new ArrayList<ArrayList<Node>>();
			for(int i = 0; i < N*N; i++) {
				graph.add(new ArrayList<Node>());
			}
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < 4; k++) {
						int nr = i + dx[k];
						int nc = j + dy[k];
						if(nr>=0&&nr<N&&nc>=0&&nc<N) {
							graph.get(i*N+j).add(new Node(nr, nc, array[nr][nc]));
						}
					}
				}
			}

			int[] dist = new int[N*N];
			for(int i = 0; i < dist.length; i++) {
				dist[i] = Integer.MAX_VALUE;
			}

			PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

			queue.offer(new Node(0, 0, array[0][0]));
			dist[0] = array[0][0];

			while(!queue.isEmpty()) {
				Node curNode = queue.poll();

				if(dist[curNode.x*N+curNode.y] < curNode.cost) continue;

				for(int i = 0; i < graph.get(curNode.x*N+curNode.y).size(); i++) {
					Node ntxNode = graph.get(curNode.x*N+curNode.y).get(i);
					if(dist[ntxNode.x*N+ntxNode.y] > curNode.cost + ntxNode.cost) {
						dist[ntxNode.x*N+ntxNode.y] = curNode.cost + ntxNode.cost;
						queue.offer(new Node(ntxNode.x, ntxNode.y, dist[ntxNode.x*N+ntxNode.y]));
					}
				}
			}

			sb.append(dist[dist.length-1]).append("\n");
		}
		System.out.println(sb);
	}
}
