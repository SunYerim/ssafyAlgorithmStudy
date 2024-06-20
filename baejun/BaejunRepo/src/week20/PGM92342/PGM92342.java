package week20.PGM92342;

public class PGM92342 {
	/* 동점이여도 지기 때문에, lion은 appeach+1발 쏴서 이기거나, 0발로 져야함 */
	static int[] lionScore;
	static int nCopy;
	static int[] infoCopy;
	static int[] score = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
	static int maxScoreGap = -1;
	static int[] maxScoreGapLion;
	
	class Solution {
		public int[] solution(int n, int[] info) {
			nCopy = n;
			infoCopy = info;
			lionScore = new int[11];
			maxScoreGapLion = new int[11];
			DFS(0, 0, 0, n);

			if(maxScoreGap == -1) {
				int[] returnArr = {-1};
				return returnArr;
			} else {
				return maxScoreGapLion;
			}
		}

		private void DFS(int cnt, int appeach, int lion, int spare) {
			// 기저조건
			if(cnt == 11) {
				if(spare != 0) lionScore[10] += spare; //여분이 남았다면 => 가장 작은 점수인 0에 여분 다 쏘기
				int appeachGet = 0;
				int lionGet = 0;
				for(int i = 0; i < 11; i++) { // 점수 계산
					if(infoCopy[i] < lionScore[i]) {
						lionGet += score[i];
					} else {
						if(infoCopy[i] != 0) {
							appeachGet += score[i];
						}
					}
				}
				int scoreGap = lionGet - appeachGet;
				if(scoreGap > 0) {
					if(maxScoreGap < scoreGap) { //갱신(max값과 그 때의 배열)
						maxScoreGap = scoreGap;
						for(int i = 0; i < 11; i++) {
							maxScoreGapLion[i] = lionScore[i];
						}
					} else if(maxScoreGap == scoreGap) {
						for(int i = 10; i >= 0; i--) { //점수차가 같을땐, 낮은 점수 max값
							if(maxScoreGapLion[i] == lionScore[i]) continue;

							if(maxScoreGapLion[i] < lionScore[i]) {
								for(int j = 0; j < 11; j++) {
									maxScoreGapLion[j] = lionScore[j];
								}
								break;
							} else { //갱신 필요없
								break;
							}
						}
					}
				}
				return;
			}
			// 유도조건
			for(int i = cnt; i < 11; i++) {
				// lion이 이기는거
				if(spare - (infoCopy[i]+1) >= 0) {
					lionScore[i] = infoCopy[i]+1;
					DFS(i+1, appeach, lion+score[i], spare - (infoCopy[i]+1));
					lionScore[i] = 0;
				} else { //lion이 지는거(0발)
					if(infoCopy[i] != 0) {
						lionScore[i] = 0;
						DFS(i+1, appeach+score[i], lion, spare);
					} else {
						lionScore[i] = 0;
						DFS(i+1, appeach, lion, spare);
					}
				}
			}
		}
	}
}
