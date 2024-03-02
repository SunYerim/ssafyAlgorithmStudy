package week5.SWEA2112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2112 {
	static int D, W, K, answer;
	static int[][] cells, copyCells;
	static boolean[] visited, visitedForInserting;
	static int[] picked;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#" + testCase + " ");
			st = new StringTokenizer(in.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			cells = new int[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < W; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			copyCells = new int[D][W];
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					copyCells[i][j] = cells[i][j];
				}
			}
			/* 약품투입없이 성능테스트 통과하면 바로 쫑 */
			if(capacityTesting(cells)) {
				sb.append(0).append("\n");
				continue;
			}
			for(int i = 1; i <= D; i++) {
				if(i == D) {
					sb.append(i).append("\n");
					break;
				}
				visited = new boolean[D];
				picked = new int[i];
				combination(0, 0, i);
				if(answer != Integer.MAX_VALUE) {
					sb.append(i).append("\n");
					break;
				}
			}
		}
		System.out.println(sb);
	}

	private static void combination(int start, int cnt, int r) {
		if(answer != Integer.MAX_VALUE) return;
		// 기저조건
		if(cnt == r) {
			for(int i = 0; i <= cnt; i++) {
				visitedForInserting = new boolean[cnt];
				combForInserting(0, 0, i, picked);
			}
			for(int i = 0; i < picked.length; i++) {
				for(int j = 0; j < W; j++) {
					cells[picked[i]][j] = copyCells[picked[i]][j];
				}
			}
			return;
		}
		// 유도조건
		for(int i = start; i < D; i++) {
			if(!visited[i]) {
				visited[i] = true;
				picked[cnt] = i;
				combination(i+1, cnt+1, r);
				visited[i] = false;
			}
		}
	}

	private static void combForInserting(int start, int cnt, int r, int[] picked) {
		if(answer != Integer.MAX_VALUE) return;
		// 기저조건
		if(cnt == r) {
			for(int i = 0; i < picked.length; i++) {
				if(visitedForInserting[i]) {
					for(int j = 0; j < W; j++) {
						cells[picked[i]][j] = 1;
					}
				} else {
					for(int j = 0; j < W; j++) {
						cells[picked[i]][j] = 0;
					}
				}
			}
			if(capacityTesting(cells)) {
				answer = cnt;
			}
			return;
		}
		// 유도조건
		for(int i = start; i < picked.length; i++) {
			if(!visitedForInserting[i]) {
				visitedForInserting[i] = true;
				combForInserting(i+1, cnt+1, r, picked);
				visitedForInserting[i] = false;
			}
		}
	}

	private static boolean capacityTesting(int[][] cells) {
		int[][] acc = new int[D][W];
		for(int i = 0; i < W; i++) {
			boolean flag = false;
			for(int j = 0; j < D; j++) {
				if(j == 0) {
					acc[j][i] = 1;
					continue;
				}
				if(cells[j][i] == cells[j-1][i]) {
					acc[j][i] = acc[j-1][i]+1;
				} else {
					acc[j][i] = 1;
				}
				if(acc[j][i] == K) {
					flag = true;
					break;
				}
			}
			if(!flag) return false;
		}
		return true;
	}
}