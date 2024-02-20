package week4.BOJ2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2583 {
    static int m, n, k;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int numberOfArea;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        numberOfArea = 0;

        map = new int[m][n];
        visited = new boolean[m][n];
        List<Integer> numbers = new ArrayList<>();

        // 직사각형 색칠 표시
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken())-1; // 해당칸은 -1
            int endY = Integer.parseInt(st.nextToken())-1;

            // 바로 map에 색칠 처리를 해준다.
            for (int tmpX = startX; tmpX <= endX; tmpX++) {
                for (int tmpY = startY; tmpY <= endY; tmpY++) {
                    map[tmpY][tmpX] = 1;
                }
            }
        }

        // dfs로 로직을 처리
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    numberOfArea++;
                    int size = dfs(i, j);
                    numbers.add(size);
                }
            }
        }

        Collections.sort(numbers);
        System.out.println(numberOfArea);
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i)+" ");
        }


    }

    private static int dfs(int x, int y) {
        int size = 1;
        visited[x][y] = true;
        for (int i =0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny] && map[nx][ny] == 0) {
                size += dfs(nx, ny);
            }

        }
        return size;
    }
}
