package week21.PGM159993;

import java.util.*;
import java.io.*;

public class PGM159993 {

    static char[][] arrays;
    static int xLen, yLen;
    static int xS, yS; // S(시작좌표)
    static int xL, yL; // L(레버좌표)
    static int xE, yE; // E(출구좌표)
    static boolean[][] visited;
    static int[][] secondCnt;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    class Solution {


        public int solution(String[] maps) {
            int answer = 0;
            xLen = maps.length;
            yLen = maps[0].length();
            arrays = new char[xLen][yLen];
            /* 입력값 -> char배열 + S, L, E 좌표값 추출 */
            for (int i = 0; i < xLen; i++) {
                for (int j = 0; j < yLen; j++) {
                    arrays[i][j] = maps[i].charAt(j);
                    if (arrays[i][j] == 'S') {
                        xS = i;
                        yS = j;
                    } else if (arrays[i][j] == 'L') {
                        xL = i;
                        yL = j;
                    } else if (arrays[i][j] == 'E') {
                        xE = i;
                        yE = j;
                    }
                }
            }
            visited = new boolean[xLen][yLen];
            secondCnt = new int[xLen][yLen];
            bfs(xS, yS, 'L'); // S -> L
            if (secondCnt[xL][yL] == 0) {
                answer = -1;
            } else {
                int lever = secondCnt[xL][yL];
                visited = new boolean[xLen][yLen];
                secondCnt = new int[xLen][yLen];
                bfs(xL, yL, 'E'); // S -> L
                if (secondCnt[xE][yE] == 0) {
                    answer = -1;
                } else {
                    answer = lever + secondCnt[xE][yE];
                }
            }

            return answer;
        }

        private static void bfs(int startX, int startY, char target) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startX);
            queue.offer(startY);
            visited[startX][startY] = true;

            while (!queue.isEmpty()) {
                int x = queue.poll();
                int y = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = x + dx[i];
                    int nc = y + dy[i];
                    if (nr >= 0 && nr < xLen && nc >= 0 && nc < yLen && !visited[nr][nc]
                            && arrays[nr][nc] != 'X') {
                        queue.offer(nr);
                        queue.offer(nc);
                        visited[nr][nc] = true;
                        secondCnt[nr][nc] = secondCnt[x][y] + 1;
                    }
                }
            }
        }
    }
}
