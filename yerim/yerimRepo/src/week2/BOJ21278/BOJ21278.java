package week2.BOJ21278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic
    1. 플로이드-워셜 알고리즘으로 각 경로간 최단거리를 구한뒤
    2. 2중 for문으로 치킨 가게 2개 고를 조합을 고른 후
    3. compareTo 메소드를 재정의하여 정렬한다.*/
public class BOJ21278 {
    static int N, M;
    static int[][] map;
    static int minNum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1]; // 도시 번호가 1번부터 시작하는 것을 고려해서



        int chickenDis = 0;
        int b1, b2 = 0;

        // 플로이드 적용 전 모든 거리 무한대로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        // 자기 자신은 0
        for (int i = 1; i <= N; i++) {
            map[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }

        // 플로이드 워셜 알고리즘
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    // 도달 가능할 때만.
                    if (map[a][k] != Integer.MAX_VALUE && map[k][b] != Integer.MAX_VALUE) {
                        map[a][b] = Math.min(map[a][b], map[a][k] + map[k][b]);
                    }
                }
            }
        }

        // 나중에 정렬하기 위한 배열 생성
        List<Node> chicken = new ArrayList<>();

        // 플로이드 워셜 수행 한 map에서 치킨집 2개 조합 선정해서 distance, 치킨집2개 선정
        for (int i = 1; i < N; i++) {
            for (int j = i+1; j <= N; j++) {
                int sum = 0;
                for (int k = 1; k <= N; k++) {
                    sum += Math.min(map[k][i], map[k][j]);
                }
                sum *= 2;  // 왕복 거리이므로 *2
                chicken.add(new Node(i, j, sum));
            }
        }

        Collections.sort(chicken);
        // 답
        System.out.print(chicken.get(0).city1+" "+chicken.get(0).city2+" "+chicken.get(0).distance);

    }

    static class Node implements Comparable<Node> {
        int city1;
        int city2;
        int distance;

        Node(int city1, int city2, int distance) {
            this.city1 = city1;
            this.city2 = city2;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

}


