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
				return o1.length - o2.length;
			}
			return o1.num - o2.num;
		});
		int end = Math.min(L, N);
		for (int i = 0; i < end; i++) {
			out.append(numbers.get(i + 1)).append(" ");
			N--;
		}

		int dist = 1;
		while (N > 0) {
			for (int i = 0; i < L; i++) {
				for (Num num : Num.makeNum(numbers.get(i), numbers.get(i + 1), dist)) {
					queue.offer(num);
				}
			}
			while (!queue.isEmpty() && N > 0) {
				N--;
				out.append(queue.poll().num).append(" ");
			}
			dist++;
		}
		System.out.println(out);
	}
}

class Num {
	public int num;
	public int length;

	public Num(int num, int length) {
		this.num = num;
		this.length = length;
	}

	public static List<Num> makeNum(int s, int e, int d) {
		List<Num> result = new ArrayList<>();
		if (s + d <= (s + e) / 2) {
			result.add(new Num(s + d, e - s - 1));
		}
		if (e - d > (s + e) / 2) {
			result.add(new Num(e - d, e - s - 1));
		}
		return result;
	}
}