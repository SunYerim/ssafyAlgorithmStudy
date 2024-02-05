package week2.BOJ2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2493 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		/* input 받기 */
		int N = Integer.parseInt(in.readLine());
		int[] koiTop = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			koiTop[i] = Integer.parseInt(st.nextToken());
		}

		int[] answer = new int[N];
		Deque<Node> deque = new ArrayDeque<Node>();
		/* deque에 index와 value로 삽입 (Node 클래스), 뒤에서부터 탐색 */
		for(int i = koiTop.length - 1; i >= 0; i--) {
			if(deque.isEmpty()) { // 비어있으면 스택에 삽입
				deque.addLast(new Node(i+1, koiTop[i]));
			} else { // 현재 탑의 value가 더 크면 걔가 수신탑이 됨
				while(!deque.isEmpty() && deque.peekLast().number <= koiTop[i]) {
					answer[deque.pollLast().index-1] = i+1;
				}
				deque.addLast(new Node(i+1, koiTop[i]));
			}
		}
		for(int e : answer) {
			sb.append(e + " ");
		}
		System.out.println(sb);
	}
}

class Node {
	int index;
	int number;
	public Node(int index, int number) {
		this.index = index;
		this.number = number;
	}
}
