
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {

	static int D, W, K, min;
	static int[] inject;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d9_2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			inject = new int[D];
			for (int d = 0; d < D; d++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int w = 0; w < W; w++) {
					map[d][w] = Integer.parseInt(st.nextToken());
				}
			}
			// for(int[] a:map) System.out.println(Arrays.toString(a));System.out.println();
			min = K;
			subs(0, 0);// 행,주입수
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void subs(int row, int cnt) {
		if (cnt >= min)
			return;
		if (row == D) {
			if (check())
				min = Math.min(min, cnt);
			return;
		}
		inject[row] = -1;// 그대로
		subs(row + 1, cnt);
		inject[row] = 0;// A투입
		subs(row + 1, cnt + 1);
		inject[row] = 1;// B투입
		subs(row + 1, cnt + 1);
	}

	static boolean check() {
		for (int w = 0; w < W; w++) {
			int cnt = 1;
			for (int d = 0; d < D - 1; d++) {
				int curr = inject[d] == -1 ? map[d][w] : inject[d];
				int next = inject[d + 1] == -1 ? map[d + 1][w] : inject[d + 1];
				if (curr == next) {
					cnt++;
					if (cnt >= K)
						break;
				} else {
					cnt = 1;
				}
			}
			if (cnt < K)
				return false;
		}
		return true;

	}

}
