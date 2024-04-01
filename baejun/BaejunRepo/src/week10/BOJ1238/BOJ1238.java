package week10.BOJ1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ1238 {
	static class Node {
		int idx; // 연결된 vertex idx
		int cost; // idx로 가는 cost
		
		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	
	static int N, M, dest;
	static ArrayList<ArrayList<Node>> list;
//	static PriorityQueue<Node> queue;
	static int[] dist;
	static int[] distForGoHome;
	static int[] distForStartParty;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i = 0; i < N+1; i++) { // 1번부터 사용
			list.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			list.get(from).add(new Node(to, cost));
		}
		distForGoHome = new int[N+1];
		dijkstra(dest);
		distForStartParty = new int[N+1];
		for(int i = 0; i <= N; i++) {
			dijkstra2(i);
			distForStartParty[i] = dist[dest];
		}
		
		int maxValue = -1;
		
		for(int i = 1; i <= N; i++) {
			int sum = distForGoHome[i] + distForStartParty[i];
			maxValue = (maxValue < sum) ? sum : maxValue;
		}
//		System.out.println(Arrays.toString(distForGoHome));
//		System.out.println(Arrays.toString(distForStartParty));
		System.out.println(maxValue);
	}

	private static void dijkstra2(int startIdx) {
		dist = new int[N+1];
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		queue.offer(new Node(startIdx, 0));
		dist[startIdx] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(dist[curNode.idx] < curNode.cost) {
				continue;
			}
			
			for(int i = 0; i < list.get(curNode.idx).size(); i++) {
				Node ntxNode = list.get(curNode.idx).get(i);
				
				if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
				}
			}
		}
		
	}

	private static void dijkstra(int startIdx) {
		distForGoHome = new int[N+1];
		PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		Arrays.fill(distForGoHome, Integer.MAX_VALUE);
		
		queue.offer(new Node(startIdx, 0));
		distForGoHome[startIdx] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			
			if(distForGoHome[curNode.idx] < curNode.cost) {
				continue;
			}
			
			for(int i = 0; i < list.get(curNode.idx).size(); i++) {
				Node ntxNode = list.get(curNode.idx).get(i);
				
				if(distForGoHome[ntxNode.idx] > ntxNode.cost + curNode.cost) {
					distForGoHome[ntxNode.idx] = ntxNode.cost + curNode.cost;
					queue.offer(new Node(ntxNode.idx, distForGoHome[ntxNode.idx]));
				}
			}
		}
	}
}
