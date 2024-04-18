package week12.BOJ13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. bfs*/
public class BOJ13460 {
    static char[][] map;
    static int n, m;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        Marble redMarble = null;
        Marble blueMarble = null;

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'R') {
                    redMarble = new Marble(i, j, 0);
                }
                if (map[i][j] == 'B') {
                    blueMarble = new Marble(i, j, 0);
                }
            }
        }

        int ans = bfs(redMarble, blueMarble);

        System.out.println(ans);
    }

    private static int bfs(Marble red, Marble blue) {
        // 빨간, 파란 구슬 좌표 관리하는 각각의 큐
        Queue<Marble> reds = new ArrayDeque<>();
        Queue<Marble> blues = new ArrayDeque<>();

        reds.offer(red);
        blues.offer(blue);

        boolean redFlag = false;
        boolean blueFlag = false;


        while (!reds.isEmpty() && !blues.isEmpty()) {
            Marble redOne = reds.poll();
            Marble blueOne = blues.poll();

            // 기저
            if (redOne.move >= 10) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                Marble redMarble = new Marble(redOne.x, redOne.y, redOne.move + 1);
                Marble blueMarble = new Marble(blueOne.x, blueOne.y, blueOne.move + 1);

                map[redMarble.x][redMarble.y] = 'R';
                map[blueMarble.x][blueMarble.y] = 'B';

                redFlag = false;
                blueFlag = false;

                // 움직이지 못할때까지 계속 움직임.
                while (true) {
                    // 빨간 구슬이 구멍에 빠지는 경우
                    if (map[redMarble.x + dx[i]][redMarble.y + dy[i]] == 'O') {
                        redFlag = true;
                        map[redMarble.x][redMarble.y] = '.';
                        redMarble.x = redMarble.x + dx[i];
                        redMarble.y = redMarble.y + dy[i];
                    }

                    // 파란 구슬이 구멍에 빠지는 경우
                    if (map[blueMarble.x + dx[i]][blueMarble.y + dy[i]] == 'O') {
                        blueFlag = true;
                        break;
                    }

                    // 빨간 구슬, 파란구슬 모두 이제 더이상 움직일 수 없는 경우
                    // 파란구슬 벽or 장애물에 가로막혔을 때, 빨간구슬도 장애물이나 벽에 막히거나 빠졌을때
                    if ((map[redMarble.x + dx[i]][redMarble.y + dy[i]] != '.' || redFlag)
                            && map[blueMarble.x + dx[i]][blueMarble.y + dy[i]] != '.') {
                        break;
                    }

                    // 빨간
                    if (map[redMarble.x + dx[i]][redMarble.y + dy[i]] == '.' && !redFlag) {
                        map[redMarble.x][redMarble.y] = '.';
                        redMarble.x = redMarble.x + dx[i];
                        redMarble.y = redMarble.y + dy[i];
                        map[redMarble.x][redMarble.y] = 'R';
                    }

                    // 파란
                    if (map[blueMarble.x + dx[i]][blueMarble.y + dy[i]] == '.') {
                        map[blueMarble.x][blueMarble.y] = '.';
                        blueMarble.x = blueMarble.x + dx[i];
                        blueMarble.y = blueMarble.y + dy[i];
                        map[blueMarble.x][blueMarble.y] = 'B';
                    }
                }
                // 원하는 결과
                if (redFlag && !blueFlag) {
                    return redMarble.move;
                }

                // 좌표값 갱신
                if (!blueFlag) {
                    reds.offer(redMarble);
                    blues.offer(blueMarble);
                }

                // 움직이고나면 원래 자리 빈공간으로 setting
                if (map[redMarble.x][redMarble.y] == 'R') {
                    map[redMarble.x][redMarble.y] = '.';
                }
                if (map[blueMarble.x][blueMarble.y] == 'B') {
                    map[blueMarble.x][blueMarble.y] = '.';
                }
            }

        }
        // 아무 조건에도 안 걸리는 경우
        return -1;

    }

    // 객체로 관리
    static class Marble {
        private int x, y, move;
        public Marble(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}