package BOJ1060;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int L = Integer.parseInt(in.readLine());
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		String[] split = in.readLine().split(" ");
		for (int i = 0; i < L; i++) {
			numbers.add(Integer.parseInt(split[i]));
		}
		int N = Integer.parseInt(in.readLine());

		Collections.sort(numbers);

		PriorityQueue<Num> queue = new PriorityQueue<>((o1, o2) -> {
			if (o1.length != o2.length) {
				return o1.length > o2.length ? 1 : o1.length < o2.length ? -1 : 0;
			}
			return o1.num - o2.num;
		});
		for (int i = 0; i < L; i++) {
			queue.offer(new Num(numbers.get(i + 1), 0));
		}

		for (int i = 0; i < L; i++) {
			for (Num num : Num.makeNum(numbers.get(i), numbers.get(i + 1))) {
				queue.offer(num);
			}
		}
		while (!queue.isEmpty() && N > 0) {
			N--;
			out.append(queue.poll().num).append(" ");
		}
		if (N > 0) {
			for (int i = 1; i <= N; i++) {
				out.append(numbers.get(L) + i).append(" ");
			}
		}
		System.out.println(out);
	}
}

class Num {
	public int num;
	public long length;

	public Num(int num, long length) {
		this.num = num;
		this.length = length;
	}

	public static List<Num> makeNum(int s, int e) {
		List<Num> result = new ArrayList<>();
		if ((e - s) > 100) {
			for (int i = 1; i <= 50; i++) {
				result.add(new Num(s + i, (long) i * (e - s - i) - 1));
				result.add(new Num(e - i, (long) i * (e - s - i) - 1));
			}
		} else {
			for (int i = 1; i < e - s; i++) {
				result.add(new Num(s + i, i * (e - s - i) - 1));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "[n:" + num + ", l:" + length + "]";
	}
}