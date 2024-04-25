package week13.BOJ4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ4179 {
    static int r, c;
    static int startX, startY;
    static char[][] map;
    static boolean[][] visited;
    static ArrayDeque<Node> fires;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
//        fireVisited = new boolean[r][c];
        fires = new ArrayDeque<>();

        // 입력받기
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'J') {
                    startX = i;
                    startY = j;
                } else if (map[i][j] == 'F') {
                    fires.offer(new Node(i, j, 0));
                }
            }
        }


        int ans = bfs(startX, startY);

        System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);

    }

    private static int bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y, 0));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            // 불내고
            spreadFire();
            int sizes = queue.size();
            for (int s = 0; s < sizes; s++) {
                Node curr = queue.poll();
                int a = curr.x;
                int b = curr.y;
                int total = curr.time;

                if (a == 0 || a == r-1 || b == 0 || b == c-1) {
                    return curr.time + 1;
                }

                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + a;
                    int ny = dy[j] + b;

                    if (nx >= 0 && ny >= 0 && nx < r && ny < c && !visited[nx][ny] && map[nx][ny] != 'F' && map[nx][ny] != '#') {
                        queue.offer(new Node(nx, ny, curr.time+1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    private static void spreadFire() {
        // fires 리스트에서 원소 꺼내면서 불을 낸다.
        // 갈 수 없는 곳은 -> 벽, 또다른 불이 난 공간
        // 만약, 지훈이가 있는 곳에 덮친다면 break;
        int size = fires.size();
        for (int i = 0; i < size; i++) {
            Node currFire = fires.poll();

            for (int j = 0; j < 4; j++) {
                int nx = currFire.x + dx[j];
                int ny = currFire.y + dy[j];
                // 범위 내면서 벽 아닐때
                if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] != '#' && map[nx][ny] != 'F') {
                    map[nx][ny] = 'F';
                    fires.add(new Node(nx, ny, 0));
                }
            }
        }


    }

    static class Node{
        private int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

    }

}