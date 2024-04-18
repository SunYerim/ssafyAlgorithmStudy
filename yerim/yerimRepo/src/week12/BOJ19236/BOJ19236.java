package week12.BOJ19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic
    1. 물고기의 이동은 일단 문제에 나와있는대로 이동시킨다.
    2. 상어는 갈 수 있는 경로에서 이동가능한 칸들을 모두 방문 -> backtracking 실행
    3. 기저 조건 -> 상어가 이동할 수 있는 칸이 없으면 집으로 간다 ...? (0, 0)
    4. 물고기의 이동
        -

    5. 상어의 이동
    */
public class BOJ19236 {
    static int ans = 0;
    static int[][] map;
    // 방향 정의 list
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[4][4];
        List<Fish> fishes = new ArrayList<>();
        // 정보 입력받기
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                Fish fish = new Fish(i, j, direction, num, true);
                fishes.add(fish);
                map[i][j] = num;
            }
        }
        // 정렬 기준에 맞춰 정렬
        Collections.sort(fishes);

        Fish first = fishes.get(map[0][0]-1);
        // 상어 생성
        Shark shark = new Shark(0, 0, first.dir, first.fishes);
        first.isAlive = false; // 상어가 지나갔다.
        // 상어 init 위치에
        map[0][0] = -1;

        // 물고기와 상어를 이동하는 메서드
        moveShark(map, shark, fishes);

//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(ans);

    }

    // 빈칸일때, 다른 물고기가 있는 칸일때 이동이 가능한 칸이다.
    private static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        // 매 턴마다 모든 물고기가 다 이동을 한다.
        if (!fish.isAlive) return; // 잡아 먹혔으면 볼 필요 없음.

        for (int j = 0; j < 8; j++) {
            int nextDir = (fish.dir + j) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
            // 정상적
            if(arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;
                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny]-1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.fishes;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.fishes;
                fish.dir = nextDir;
                return;
            }

        }
    }

    private static void moveShark(int[][] arr, Shark shark, List<Fish> fishes) {
        // 백트레킹 구현
        // 상어가 갈 수 있는 방향으로 갈 수 있는 칸들 방문했을때, 안했을때 경우 따짐 -> backtracking
        if (ans < shark.eatSum) {
            ans = shark.eatSum;
        }

        // 물고기
        fishes.forEach(e -> moveFish(e, arr, fishes));

        // 상어 이동
        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            // 범위 내
            if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && arr[nx][ny] > 0) {
                // 물고기를 일단 한 번 다 잡아먹고
                int[][] copyArr = copyMap(arr);
                List<Fish> copyFishes = copyFish(fishes);

                copyArr[shark.x][shark.y] = 0;
                Fish fish = copyFishes.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(fish.x, fish.y, fish.dir, shark.eatSum + fish.fishes);
                fish.isAlive = false;
                copyArr[fish.x][fish.y] = -1;

                moveShark(copyArr, newShark, copyFishes);
            }
        }
    }

    // 배열 복사
    private static int[][] copyMap(int[][] map) {
        int[][] tmp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    private static List<Fish> copyFish(List<Fish> arr) {
        List<Fish> tmp = new ArrayList<>();
        for (Fish fish : arr) {
            tmp.add(new Fish(fish.x, fish.y, fish.dir, fish.fishes, fish.isAlive));
        }
        return tmp;
    }


    static class Fish implements Comparable<Fish>{
        private int x, y, dir, fishes;
        private boolean isAlive = true;
        public Fish(int x, int y, int dir, int fishes, boolean isAlive){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.fishes = fishes;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return this.fishes - o.fishes;
        }
    }

    static class Shark {
        private int x, y, dir, eatSum;

        public Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }
}