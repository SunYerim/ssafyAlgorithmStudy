package week4.BOJ2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. 플로이드-워셜 알고리즘을 이용하여 해결함.
    2. 본인의 키 순서를 알 수 있으려면 -> count 변수가 node의 총 갯수보다 1만큼 작으면된다. => 자기 자신을 제외한 모든 노드와 비교를 했다는 말과 동일하기때문.
    */
public class BOJ2458 {
    static final int INF = (int) 1e9;
    static int n, m, answer;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];

        // 2차원 그래프 생성 후 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) {
                    graph[a][b] = 0;
                }
            }
        }

        // v -> e 가는 비용을 1로 초기화 (유방향)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int tall = Integer.parseInt(st.nextToken());
            graph[small][tall] = 1;
        }
        // 플로이드-와샬 알고리즘 수행. => 숫자 a, b에 대해 연결이 되어있는지 확인한다.
        for (int k = 1; k <= n; k++) {
            for (int a = 1 ; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (graph[a][k] == 1 && graph[k][b] == 1) {
                        graph[a][b] = 1; // 순서를 알 수 있음.
                    }
                }
            }
        }

        // 키 순서를 알 수 있는지에 대한 로직을 수행한다. => count 변수 생성. n-1이 되는지 안되는지
        answer = 0;
        for (int i = 1; i <=n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                count += graph[i][j] + graph[j][i];
            }

            if (count == n-1) {
                answer++;
            }
        }
        System.out.println(answer);

    }
}
