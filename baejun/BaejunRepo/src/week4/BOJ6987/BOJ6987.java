package week4.BOJ6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6987 {
	static int[][] score;
	static int[][] match = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3 ,5}, {4, 5}}; // 모든 경기(15경기)
	static boolean flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		/* 조별로 경기진행(총 4조) */
		testcase : for(int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(in.readLine());
			score = new int[6][3];
			flag = false;

			// 승,무,패 입력
			for(int i = 0; i < score.length; i++) {
				int sum = 0;
				for(int j = 0; j < score[i].length; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
					sum+=score[i][j];
				}
				if(sum != 5) { // 만약 한 팀으 승무패 합이 5가 아니라면, 잘못된 경우이므로 바로 탈출
					System.out.println(0);
					continue testcase;
				}
			}
			
			matchDay(0);

			if(flag) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

	/* 총 15경기를 차례대로 진행하며 나올 수 있는 모든 경우의 수를 구하며, 주어진 케이스의 승무패와 다르다면 가지치기 */
	private static void matchDay(int start) {
		// 가지치기 : 이미 빠그러졌다면(0미만이라면) 컷
		if(start != 0 && (score[match[start-1][0]][0] < 0 || score[match[start-1][1]][2] < 0 || score[match[start-1][0]][1] < 0 || score[match[start-1][1]][1] < 0 || score[match[start-1][0]][2] < 0 || score[match[start-1][1]][0] < 0)) return;
		// 기저조건(끝까지 가지치기 안당하고 왔다는건 살아남았다는거)
		if(start == 15) {
			flag = true;
			return;
		}
		// 유도조건(각 팀의 승무패를 저장해놨기때문에 승.무.패 결과에 따라 카운트 깍아가면서 재귀호출)
		/*앞팀 승 - 뒷팀 패*/
		score[match[start][0]][0]--;
		score[match[start][1]][2]--;
		matchDay(start+1);
		score[match[start][0]][0]++;
		score[match[start][1]][2]++;
		/*무승부*/
		score[match[start][0]][1]--;
		score[match[start][1]][1]--;
		matchDay(start+1);
		score[match[start][0]][1]++;
		score[match[start][1]][1]++;
		/*앞팀 패 - 뒷팀 승*/
		score[match[start][0]][2]--;
		score[match[start][1]][0]--;
		matchDay(start+1);
		score[match[start][0]][2]++;
		score[match[start][1]][0]++;
	}
}
