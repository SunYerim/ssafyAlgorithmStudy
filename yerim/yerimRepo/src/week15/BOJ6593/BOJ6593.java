package week15.BOJ6593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6593 {
    static int l, r, c;
    static int startX, startY, startZ, endX, endY, endZ;
    static boolean[][][] visited;
    static char[][][] map;
    static int[] dx = {0, 0, 1, -1, 0, 0}; // 동서남북상하
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean flag = true;

        while (flag) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (l == 0 && r == 0 && c == 0) {
                flag = false;
                break;
            }

            map = new char[l][r][c];
            visited = new boolean[l][r][c];

            // 입력받기
            for (int z = 0; z < l; z++) {
                for (int x = 0; x < r; x++){
                    String tmp = br.readLine();
                    for (int y = 0; y < c; y++) {
                        map[z][x][y] = tmp.charAt(y);
                        if (map[z][x][y] == 'S') {
                            startZ = z;
                            startX = x;
                            startY = y;
                        }
                        else if (map[z][x][y] == 'E') {
                            endZ = z;
                            endX = x;
                            endY = y;
                        }
                    }
                }
                br.readLine();
            }

//            System.out.println("startZ: " + startZ + ", startX: " + startX + ", startY: " + startY);
//            System.out.println("l: " + l + ", r: " + r + ", c: " + c);


            // map에 대해서 bfs 로직 실행
            if (!bfs(new Node(startZ, startX, startY, 0))) {
                sb.append("Trapped!").append("\n");
            };
        }

        if (!flag) {
            System.out.println(sb.toString());
        }
    }

    private static boolean bfs(Node start) {
        Queue<Node> queue = new ArrayDeque<>();
        // 출발지를 넣는다
        queue.offer(new Node(start.z, start.x, start.y, start.time));
        visited[start.z][start.x][start.y] = true;
        // 6방향 탐색
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            // 기저
            if (map[curr.z][curr.x][curr.y] == 'E') {
                sb.append("Escaped in ").append(curr.time).append(" minute(s).").append("\n");
                return true;
            }
            // 유도
            for (int i = 0; i < 6; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nz = curr.z + dz[i];
                int time = curr.time;

                if (isContain(nz, nx, ny) && !visited[nz][nx][ny]  && map[nz][nx][ny] != '#') {
                    visited[nz][nx][ny] = true;
                    queue.add(new Node(nz, nx, ny, time+1));
                }

            }
        }
        return false;
    }

    private static boolean isContain(int z, int x, int y) {
        if (z >= 0 && y >= 0 && x >= 0 && z < l && x < r && y < c)
            return true;
        return false;
    }

    static class Node {
        private int z, x, y, time;
        public Node(int z, int x, int y, int time) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}