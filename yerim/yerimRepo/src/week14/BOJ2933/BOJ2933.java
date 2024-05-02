package week14.BOJ2933;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 막대기를 순서대로 날리고 -> 4방탐색 -> 미네랄을 아래로 누른다.
public class BOJ2933 {
    static char[][] map;
    static int r, c, n;
    static int[] heights;
    static int[][] clusters;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<Location> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        n = Integer.parseInt(br.readLine());
        heights = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = r-Integer.parseInt(st.nextToken());

            // 부수고
            destructMineral(heights[i], i % 2 == 0 ? 1 : 2);
            // 클러스터 내리기.
            bfs();
        }

        // print
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    private static void bfs() {
        visited = new boolean[r][c];
        ArrayList<Location> list = new ArrayList<>();

        // 땅에 있는 건 일단 방문처리
        for (int j = 0; j < c; j++) {
            if (map[r-1][j] == '.' || visited[r-1][j])
                continue;
            visited[r-1][j] = true;
            q.add(new Location(r-1,j));

            while (!q.isEmpty()) {
                Location curr = q.poll();

                for (int i = 0; i < 4; i++ ){
                    int ni = curr.i + dx[i];
                    int nj = curr.j + dy[i];

                    if (!isRange(ni, nj) || visited[ni][nj] || map[ni][nj] == '.') continue;

                    visited[ni][nj] = true;
                    q.add(new Location(ni, nj));
                }
            }
        }

        // 공중에 있는 거 찾아 -> 방문처리 안 되있는거
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && map[i][j] == 'x') {
                    map[i][j] = '.';
                    list.add(new Location(i, j));
                }
            }
        }

        if (list.isEmpty()) {
            return;
        }

        // 내리기
        boolean down = true;
        while (down) {
            for (Location location : list) {
                int ni = location.i + 1;
                int nj = location.j;

                if (!isRange(ni, nj) || map[ni][nj] == 'x') {
                    down = false;
                    break;
                }
            }
            if (down) {
                for (Location loc : list) {
                    loc.i++;
                }
            }
        }

        for (Location loc : list) {
            map[loc.i][loc.j] = 'x';
        }
    }


    private static void destructMineral(int height, int dir) {
        // 1이면 -> 왼쪽
        if (dir == 1) {
            for (int i = 0; i < c; i++) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        }
        // 2이면
        else {
            for (int i = c-1; i >= 0; i--) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        }
    }

    private static boolean isRange(int i, int j) {
        if (i < 0 || i >= r || j < 0 || j >= c) return false;
        return true;
    }


    static class Location {
        private int i, j;
        public Location(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}