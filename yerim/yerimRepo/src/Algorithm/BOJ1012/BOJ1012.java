package Algorithm.BOJ1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int T, M, N, K;
    static int cabbage[][];
    static boolean visited[][];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int numberOfArea;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        // 테스트케이스만큼 돌린다.
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cabbage = new int[M][N];
            visited = new boolean[M][N];
            numberOfArea = 0;

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                cabbage[cx][cy] = 1;

            }

            // 순회
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && cabbage[i][j] != 0) {
                        numberOfArea++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(numberOfArea);

        }

    }

    // dfs 순회 로직 추가
    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내에 있을 때
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (!visited[nx][ny] && cabbage[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}
