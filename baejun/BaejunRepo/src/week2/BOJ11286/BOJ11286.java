package week2.BOJ11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ11286 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());
		/* 우선순위 큐 선언, 절댓값 비교(같다면 원래 값 비교) */
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int o1Abs = Math.abs(o1);
				int o2Abs = Math.abs(o2);
				
				if(o1Abs == o2Abs) {
					return Integer.compare(o1, o2);
				} else {
					return Integer.compare(o1Abs, o2Abs);
				}
			}
		});
		int input;
		/* input이 0이면 poll(없으면 0) 아니면 값 삽입 */
		for (int test_case = 1; test_case <= N; test_case++) {
			input = Integer.parseInt(in.readLine());
			if(input == 0) {
				if(queue.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(queue.poll()).append("\n");					
				}
			} else {
				queue.offer(input);
			}
		}
		System.out.println(sb);
	}
}
