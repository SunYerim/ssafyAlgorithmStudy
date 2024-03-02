package week6.SWEA1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1952 {
	static int days, month, threeMonth, year, minCost;
	static int[] usingDays, monthCost, usingThreeMonth;
	static boolean[] visited, visitedForCalcurate;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#" + testCase + " ");
			st = new StringTokenizer(in.readLine());
			days = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			usingDays = new int[13];
			st = new StringTokenizer(in.readLine());
			for(int i = 1; i < 13; i++) {
				usingDays[i] = Integer.parseInt(st.nextToken());
			}
			monthCost = new int[13];
			for(int i = 1; i < 13; i++) {
				if((usingDays[i] * days) > month) {
					monthCost[i] = month;
				} else {
					monthCost[i] = usingDays[i] * days;
				}
			}
			minCost = year; // 1년권 사용한 경우
			// 3달권 0개 사용한 경우
			int sum = 0;
			for(int e : monthCost) {
				sum += e;
			}
			if(sum < minCost) minCost = sum;
			// 3달권 4개 사용한경우
			if(minCost > (threeMonth * 4)) minCost = (threeMonth * 4);
			// 3달권 1개~3개 사용한 경우
			for(int i = 1; i < 4; i++) {
				visited = new boolean[13];
				usingThreeMonth = new int[i];
				combination(1, 0, i);
			}
			sb.append(minCost).append("\n");
		}
		System.out.println(sb);
	}

	private static void combination(int start, int cnt, int r) {
		// 기저조건
		if(cnt == r) {
			visitedForCalcurate = new boolean[13];
			calcuration();
			return;
		}
		
		// 유도조건
		for(int i = start; i < 13; i++) {
			if(!visited[i]) {
				visited[i] = true;
				usingThreeMonth[cnt] = i;
				combination(i+1, cnt+1, r);
				visited[i] = false;
			}
		}
	}

	private static void calcuration() {
		int sum = 0;
		sum += (threeMonth * usingThreeMonth.length);
		for(int e : usingThreeMonth) {
			for(int i = 0; i < 3; i++) {
				if(e+i < 13) visitedForCalcurate[e+i] = true;
			}
		}
		for(int i = 1; i < 13; i++) {
			if(!visitedForCalcurate[i]) sum += monthCost[i];
		}
		minCost = Math.min(sum, minCost);
	}
}
