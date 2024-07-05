package week21.BOJ11000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11000 {
	static int N;

	static class Node implements Comparable<Node> {
		int start;
		int end;

		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o1) {
			if(start > o1.start) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());

		PriorityQueue<Node> queue = new PriorityQueue<>();
		PriorityQueue<Integer> room = new PriorityQueue<>();


		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			queue.offer(new Node(num1, num2));
		}

		int answer = 0;
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			if(room.isEmpty()) {
				room.offer(curNode.end);
				answer++;
				continue;
			}
			if(room.peek() <= curNode.start) { // 기존에 있던 방 사용
				room.poll();
			} else { // 새로운 방 생성
				answer++;
			}
			room.offer(curNode.end);
		}
		System.out.println(answer);
	}
}