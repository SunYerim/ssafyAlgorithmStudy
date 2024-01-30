package week1.BOJ10866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ10866 {
	private static LinkedList<Integer> deque = new LinkedList<>(); // 잦은 삽입,삭제라 링크드 리스트로 구현

	private static void push_front(int element) {
		deque.add(0, element);
	}
	private static void push_back(int element) {
		deque.add(element);
	}
	private static void pop_front() {
		int outNumber = (deque.isEmpty()) ? -1 : deque.pollFirst();
		System.out.println(outNumber);
	}
	private static void pop_back() {
		int outNumber = (deque.isEmpty()) ? -1 : deque.pollLast();
		System.out.println(outNumber);
	}
	private static void size() {
		System.out.println(deque.size());
	}
	private static void empty() {
		int outNumber = (deque.isEmpty()) ? 1 : 0;
		System.out.println(outNumber);
	}
	private static void front() {
		int outNumber = (deque.isEmpty()) ? -1 : deque.peekFirst();
		System.out.println(outNumber);
	}
	private static void back() {
		int outNumber = (deque.isEmpty()) ? -1 : deque.peekLast();
		System.out.println(outNumber);
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		/* tc만큼 수행 : 키워드에 해당하는 메서드 호출 */
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(in.readLine());
			switch(st.nextToken()) {
			case "push_front" :
				push_front(Integer.parseInt(st.nextToken()));
				break;
			case "push_back" :
				push_back(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front" :
				pop_front();
				break;
			case "pop_back" :
				pop_back();
				break;
			case "size" :
				size();
				break;
			case "empty" :
				empty();
				break;
			case "front" :
				front();
				break;
			case "back" :
				back();
				break;
			default :
			}
		}
	}
}
