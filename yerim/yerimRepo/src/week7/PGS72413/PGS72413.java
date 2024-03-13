package week7.PGS72413;

import java.util.*;
// 갱신 -> Math.min(합승x, 합승 o)
class Solution{
    static int[][] map;
    static int[] d; // 거리테이블 사이즈 ....
    public int solution(int n, int s, int a, int b, int[][] fares) {
        map = new int[n+1][n+1];

        // map의 그래프 값 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 매 지점에서 낼 수 있는 최대 택시 요금으로
                map[i][j] = 200*100000+1;

            }
            map[i][i] = 0;

        }
        for (int i = 0; i < fares.length; i++) {
            int a1 = fares[i][0];
            int b1 = fares[i][1];
            int c1 = fares[i][2];

            map[a1][b1] = c1;
            map[b1][a1] = c1;

        }

        // 플로이드-워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }

        // 최저 택시요금
        int minRate = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minRate = Math.min(minRate, map[s][i] + map[i][a] + map[i][b]);
        }
        return minRate;
    }
}