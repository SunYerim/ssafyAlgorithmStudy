package week4.BOJ4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* logic
    생각한 방향
    1. DFS로 min값 갱신하면서 처리 x
    2. 최소비용 알고리즘 사용해서 로직 처리 -> 다익스트라*/
public class BOJ4485 {
    static int[][] map;
    static int[][] dist;
    static boolean[][] visited;
    public static final int INF = (int) 1e9;
    static int n;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 1;
        // n의 입력값이 0으로 들어오면 실행 안됨.
        while (true) {
            n = Integer.parseInt(br.readLine());
            // 종료 조건
            if (n == 0) {
                return;
            }
            map = new int[n][n];
            dist = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }
            dijkstra(0, 0);
            System.out.println("Problem "+count+": "+dist[n-1][n-1]);
            count++;
        }
    }



    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        // 비용이 낮은 것을 우선 순위 갖도록 설정한다.
        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            }
            return 1;
        }

    }

    public static void dijkstra(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 노드로 가기 위한 최단 경로는 0으로 설정 -> 큐에 삽입
        pq.offer(new Node(x, y, map[x][y]));
        dist[x][y] = map[x][y];

        while (!pq.isEmpty()) {
            // 가장 최단 거리가 짧은 노드에 대한 정보를 꺼내기
            Node now= pq.poll();

            if (visited[now.x][now.y]) continue;
            visited[now.x][now.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (dist[nx][ny] > dist[now.x][now.y] + map[nx][ny]) {
                        dist[nx][ny] = dist[now.x][now.y] + map[nx][ny];
                        pq.add(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }

    }

}
