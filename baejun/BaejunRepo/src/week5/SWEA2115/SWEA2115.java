package week5.SWEA2115;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA2115 {
	static int[][] arrays;
	static int N;
	static int M;
	static int C;
	static boolean[][] visited;
	static int[][] indexs;
	static int maxValue;
	static int maxForPowerSet;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			arrays = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					arrays[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N];
			indexs = new int[2][2];
			maxValue = 0;
			combination(0, 0);
			sb.append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	private static void combination(int start, int cnt) {
		//기저조건
		if(cnt == 2) {
			if(indexs[0][1] + M > N || indexs[1][1] + M > N) return; // 가지1 : 배열 범위 밖까지 가야할 경우 땡
			/* 가지2 : 선택한 두 인덱스가 겹치면 땡 */
			if(indexs[0][0] == indexs[1][0]) {
				boolean[] visit = new boolean[N];
				for(int i = indexs[0][1]; i < N; i++) {
					visit[i] = true;
				}
				for(int i = indexs[1][1]; i < N; i++) {
					if(visit[i]) return;
				}
			}
			int[] honeyA = new int[M];
			int[] honeyB = new int[M];
			int sumA = 0;
			int sumB = 0;
			int totalSum = 0;
			boolean flagA = false;
			boolean flagB = false;
			/* 범위의 꿀 배열로 저장, 꿀들의 합도 같이 기록 */
			for(int i = 0; i < M; i++) {
				honeyA[i] = (arrays[indexs[0][0]][indexs[0][1] + i]);
				honeyB[i] = (arrays[indexs[1][0]][indexs[1][1] + i]);
				sumA += honeyA[i];
				sumB += honeyB[i];
			}
			/* 가져갈 수 있는 양 C를 초과 : 부분집합을 돌려서 가장 가치있게 들고가는 경우의 수를 채택해야됨 */
			if(sumA > C) {
				flagA = true;
				maxForPowerSet = 0;
				visit = new boolean[M];
				powerSet(0, 0, honeyA);
				totalSum += maxForPowerSet;
			}
			if(sumB > C) {
				flagB = true;
				maxForPowerSet = 0;
				visit = new boolean[M];
				powerSet(0, 0, honeyB);
				sumB = maxForPowerSet;
				totalSum += maxForPowerSet;
			}
			/* C를 초과 안했다면 그냥 배열 전체를 제곱해주면서 가치 환산 */
			if(!flagA) {
				for(int e : honeyA) totalSum += e*e;
			}
			if(!flagB) {
				for(int e : honeyB) totalSum += e*e;
			}
			if(totalSum > maxValue) maxValue = totalSum;
			return;
		}
		//유도조건
		for(int i = start; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					indexs[cnt][0] = i;
					indexs[cnt][1] = j;
					combination(i+1, cnt+1);
					visited[i][j] = false;
				}
			}
		}
	}
	private static void powerSet(int cnt, int sum, int[] honey) {
		if(cnt == M) {
			if(sum > C) return;
			int a = 0;
			for(int i = 0; i < M; i++) { // 부분집합의 제곱의 합을 구해서 가장 가치있는 경우를 기록
				if(visit[i]) a += (honey[i]*honey[i]);
			}
			if(a > maxForPowerSet) maxForPowerSet = a;
			return;
		}
		visit[cnt] = true;
		powerSet(cnt+1, sum+honey[cnt], honey);
		visit[cnt] = false;
		powerSet(cnt+1, sum, honey);
	}
}
