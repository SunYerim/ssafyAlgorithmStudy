package week2.SWEA1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA1861 {
	private static int max;
	private static int maxIndex;
	private static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

		for (int test_case = 1; test_case <= T; test_case++) {
			max = 0;
			maxIndex = 0;
			sb.append("#" + test_case + " ");
			Map<Integer, Integer> map = new HashMap<>();
			int N = Integer.parseInt(in.readLine());
			int[][] arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int[] ele : direction) { // 4방향 현재값+1 인 값이 있는지 탐색
						int nr = i + ele[0];
						int nc = j + ele[1];
						if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr.length && arr[nr][nc] == (arr[i][j]+1)) {
							map.put(arr[i][j], arr[i][j]+1);
						}
					}
				}
			}
			map.forEach((key, value) -> { // 각 출발점마다 얼마나 가는지 계산, 값이 같다면 index가 더 적은걸로(문제조건)
				count = 0;
				DFS(map, key);
				if(count > max) {
					max = count;
					maxIndex = key;
				} else if(count == max) {
					if(key < maxIndex) {
						maxIndex = key;
					}
				}
			});
			sb.append(maxIndex + " " + max).append("\n");
		}

		System.out.println(sb);
	}

	private static void DFS(Map map, int key) {
		count++;
		if(map.get(key) != null) {
			DFS(map, (int) map.get(key));
		}
		return;
	}
}
