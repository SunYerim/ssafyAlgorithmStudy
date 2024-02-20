package week4.BOJ2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. 해당 문제는 벽을 언제 부술 것인지가 문제에서 중요하게 봐야할 점이다.
    2. visited 배열을 3차원으로 확장 & bfs로 접근

    */
public class BOJ2206 {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        bfs(new Node(0, 0, 0, 0));
    }

    static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            int wall = curr.wall;
            int dis = curr.distance;

            // 목적지에 도착하면
            if (x == n-1 && y == m-1) {
                System.out.println(dis + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 내에 있으면서
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 현재 좌표가 벽이 아니고 아직 방문하지 않은 경우
                    if (map[nx][ny] == 0 && !visited[nx][ny][wall]) {
                        visited[nx][ny][wall] = true;
                        queue.add(new Node(nx, ny, wall, dis+1));

                    }
                    // 현재 좌표가 벽이고, 아직 벽을 부수지 않았으며, 아직 방문하지 않은 경우
                    else if (wall == 0 && map[nx][ny] == 1 && !visited[nx][ny][wall]) {
                        visited[nx][ny][1] = true;
                        // 벽을 부쉈다.
                        queue.add(new Node(nx, ny, 1, dis+1));
                    }
                }
            }
        }
        // 모든 경우를 탐색한 후에도 목적지에 도달 못하는 경우에는 -1
        System.out.println(-1);

    }


    static class Node {
        // wall이 0이면 벽을 안 뚫은 것, 1이면 뚫은 것
        // distance: 현재까지 이동한 거리
        int x, y, wall, distance;

        public Node (int x, int y, int wall, int distance) {
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.distance = distance;
        }
    }
}
