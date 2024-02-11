package week3.BOJ14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940 {
    static int n, m, startX, startY;
    static int[][] map, map2;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        map2 = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                    //map2[startX][startY] = 0;
                }
            }
        }

        bfs(startX, startY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map2[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        // queue가 비기 전까지
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + tmp[0];
                int ny = dy[i] + tmp[1];

                // 범위 안에 있을때
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (map[nx][ny] == 1 && !visited[nx][ny]) {
                        queue.offer(new int[]{nx, ny});
                        map2[nx][ny] = map2[tmp[0]][tmp[1]] + 1;
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        map2[nx][ny] = 0;
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        // 다 돌았음에도 갈 수 있는데 못 간 땅이 있다면 -1로 처리해주면 됨.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1 ) {
                    map2[i][j] = -1;
                    visited[i][j] = true;
                }
            }
        }
    }
}
