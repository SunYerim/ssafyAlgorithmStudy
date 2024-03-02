package week6.BOJ17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17281 {
	static int innings, curHitterNumber, maxScore;
	static int [][] expectedGoals;
	static int[] orders;
	static boolean[] visited, baseState;
	static int[] hitterOrder;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		innings = Integer.parseInt(in.readLine()); // 이닝
		expectedGoals = new int[innings][10]; // 1번부터사용
		for(int i = 0; i < innings; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j < 10; j++) {
				expectedGoals[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		maxScore = 0;
		orders = new int[10]; // 2~9번
		visited = new boolean[10];
		permutation(2);
		System.out.println(maxScore);
	}

	private static void permutation(int cnt) {
		// 기저조건
		if(cnt == 10) {
			// 각 순열 경우의 수마다 게임 진행 
			curHitterNumber = 1;
			processGame();
			return;
		}
		// 유도조건
		for(int i = 2; i < 10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				orders[cnt] = i;
				permutation(cnt+1);
				visited[i] = false;
			}
		}
	}

	private static void processGame() {
		int score = 0;
		
		for(int inning = 0; inning < innings; inning++) {
			baseState = new boolean[3]; // 이닝마다 베이스는 초기화돼야함
			int outCount = 0; // 아웃카운트도 마찬가지로 이닝마다 초기화
			while(outCount < 3) { // 3아웃될때까지 이닝진행
				if(++curHitterNumber == 11) curHitterNumber = 2; // 타자 일순
				int number = 0;
				if(curHitterNumber == 5) { // 4번타자는 1번선수 고정
					number = 1;
				}
				else if(curHitterNumber > 5) { // 4번타자에 1번 넣는다고 하나씩 밀림
					number = orders[curHitterNumber-1];
				}
				else {
					number = orders[curHitterNumber];
				}
				/* 아웃, 안타, 2루타, 3루타, 홈런 별로 베이스 상황 및 점수 갱신 */
				if(expectedGoals[inning][number] == 0) {
					outCount++;
				} else if (expectedGoals[inning][number] == 1) {
					if(baseState[2]) {
						score++;
						baseState[2] = false;
					}
					if(baseState[1]) {
						baseState[2] = true;
						baseState[1] = false;
					}
					if(baseState[0]) {
						baseState[1] = true;
						baseState[0] = false;
					}
					baseState[0] = true;
				} else if (expectedGoals[inning][number] == 2) {
					if(baseState[2]) {
						score++;
						baseState[2] = false;
					}
					if(baseState[1]) {
						score++;
						baseState[1] = false;
					}
					if(baseState[0]) {
						baseState[2] = true;
						baseState[0] = false;
					}
					baseState[1] = true;
				} else if (expectedGoals[inning][number] == 3) {
					if(baseState[2]) {
						score++;
						baseState[2] = false;
					}
					if(baseState[1]) {
						score++;
						baseState[1] = false;
					}
					if(baseState[0]) {
						score++;
						baseState[0] = false;
					}
					baseState[2] = true;
				} else if (expectedGoals[inning][number] == 4) {
					if(baseState[2]) {
						score++;
						baseState[2] = false;
					}
					if(baseState[1]) {
						score++;
						baseState[1] = false;
					}
					if(baseState[0]) {
						score++;
						baseState[0] = false;
					}
					score++;
				}
			}
		}
		maxScore = Math.max(maxScore, score); // 최대값 저장
	}
}