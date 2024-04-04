package week10.BOJ11559;

import java.io.*;
import java.util.*;

public class BOJ11559 {
    static char[][] field = new char[12][6];
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static List<int[]> toPop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            field[i] = br.readLine().toCharArray();
        }

        int chain = 0;
        while (true) {
            boolean isPopped = false;
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.' && !visited[i][j]) {
                        toPop = new ArrayList<>();
                        bfs(i, j, field[i][j]);
                        if (toPop.size() >= 4) {
                            popPuyo();
                            isPopped = true;
                        }
                    }
                }
            }

            if (!isPopped) break;
            dropPuyo();
            chain++;
        }

        System.out.println(chain);
    }

    static void bfs(int x, int y, char color) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            toPop.add(current);

            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6) {
                    if (!visited[nx][ny] && field[nx][ny] == color) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    static void popPuyo() {
        for (int[] p : toPop) {
            field[p[0]][p[1]] = '.';
        }
    }

    static void dropPuyo() {
        for (int col = 0; col < 6; col++) {
            for (int row = 11; row >= 0; row--) {
                if (field[row][col] == '.') {
                    for (int above = row - 1; above >= 0; above--) {
                        if (field[above][col] != '.') {
                            field[row][col] = field[above][col];
                            field[above][col] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

}