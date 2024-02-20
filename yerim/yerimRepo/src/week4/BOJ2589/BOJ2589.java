package week4.BOJ2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. 최단거리인데, 가장 긴 시간이 걸린다.
    2. 모든 L지점이 출발지가 될 수 있으며
    3. 각 지점에서 bfs를 돌려서 최장거리인 지점을 찾는다.*/
public class BOJ2589 {
    static int n, m;
    static Character[][] map;
    static boolean[][] isVisited;
    static int[] dx = {1, -1, 0, 0}; // 동-서-남-북
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new Character[n][m];
        isVisited = new boolean[n][m];
        // 입력 받아서 map에 넣기
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 육지이면
                if (map[i][j] == 'L') {
                    isVisited = new boolean[n][m];
                    // 모든 L 지점에 대해 bfs 탐색
                    int val = bfs(i, j);
                    result = Math.max(val, result);
                }
            }
        }
        System.out.println(result);
    }

    static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        int val = 0;
        isVisited[x][y] = true;
        queue.add(new Node(x, y, 0));
        // 큐가 안 비는 동안
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // 4방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                // 범위 내에 있으면서 방문하지 않고 이동하려는 칸이 육지일때
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !isVisited[nx][ny] && map[nx][ny] == 'L') {
                    isVisited[nx][ny] = true;
                    queue.add(new Node(nx, ny, node.count+1));
                    // 최장거리 갱신
                    val = Math.max(val, node.count+1);
                }
            }
        }
        return val;
    }

    static class Node{
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
