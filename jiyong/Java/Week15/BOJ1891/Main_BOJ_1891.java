package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main_BOJ_1891 {
	static int d, num[];
	static long x, y;
	static boolean possible = true;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		d = Integer.parseInt(st.nextToken());
		num = Stream.of(st.nextToken().trim().split("")).mapToInt(Integer::parseInt).toArray();

		st = new StringTokenizer(br.readLine());

		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());

		horizontalMove(d - 1, x);
		if (possible)
			verticalMove(d - 1, y);

	}

	private static void horizontalMove(int idx, long col) {
		if (col == 0)
			return;
		if (idx < 0) {
			if (col > 0) {
				possible = false;
				System.out.println(-1);
			}
			return;
		}
		int d = 0;
		switch (num[idx]) {
		case 1:
			if (col % 2 != 0)
				num[idx] = 2;
			if (col > 0)
				d = 1;
			break;
		case 2:
			if (col % 2 != 0)
				num[idx] = 1;
			if (col < 0)
				d = -1;
			break;
		case 3:
			if (col % 2 != 0)
				num[idx] = 4;
			if (col < 0)
				d = -1;
			break;
		case 4:
			if (col % 2 != 0)
				num[idx] = 3;
			if (col > 0)
				d = 1;
			break;
		}
		horizontalMove(idx - 1, (col + d) / 2);

	}

	private static void verticalMove(int idx, long row) {
		if (row == 0) {
			printNumArray();
			return;
		}
		if (idx < 0) {
			if (row == 0) {
				printNumArray();
			} else {
				System.out.println(-1);
			}
			return;
		}
		int d = 0;
		switch (num[idx]) {
		case 1:
			if (row % 2 != 0)
				num[idx] = 4;
			if (row > 0)
				d = 1;
			break;
		case 2:
			if (row % 2 != 0)
				num[idx] = 3;
			if (row > 0)
				d = 1;
			break;
		case 3:
			if (row % 2 != 0)
				num[idx] = 2;
			if (row < 0)
				d = -1;
			break;
		case 4:
			if (row % 2 != 0)
				num[idx] = 1;
			if (row < 0)
				d = -1;
			break;
		}
		verticalMove(idx - 1, (row + d) / 2);

	}

	private static void printNumArray() {
		System.out.println(Arrays.stream(num).mapToObj(String::valueOf).reduce((x, y) -> x + y).get());
	}
}
