package week12.BOJ1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. visited 배열을 이용
    2. (0, 0) 좌표부터 dfs 탐색을 시작
    3. 만약 사방이 모두 다 방문한 문자일때는 break -> return count;*/
public class BOJ1987 {
    static int r, c;
    static int[][] map;
    static boolean[] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        visited = new boolean[26];

        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j) - 'A';
            }
        }

        int ans = dfs(0, 0, 1);
        System.out.println(ans);

    }

    private static int dfs(int x, int y, int count) {
        int result = count;
        // 우선 방문 처리를 하고
        visited[map[x][y]] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[map[nx][ny]]) {
                result = Math.max(result, dfs(nx, ny, count + 1));
            }
        }

        // 다시 방문처리 풀어주기.
        visited[map[x][y]] = false;
        return result;
    }
}