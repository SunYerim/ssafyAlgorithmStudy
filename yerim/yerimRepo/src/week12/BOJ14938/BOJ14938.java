package week12.BOJ14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. 최단거리 table 갱신
    2. 예은이가 떨어지는 땅 n만큼 돌면서 max값 갱신
    3. 얻을 수 있는 아이템 최대 개수 return*/
public class BOJ14938 {
    static int n, m, r, ans = Integer.MIN_VALUE;
    static int[] items;
    static int[][] graph;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길 개수
        graph = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    graph[i][j] = 0;
            }
        }

        st = new StringTokenizer(br.readLine());
        items = new int[n+1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a][b] = l;
            graph[b][a] = l;
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (graph[a][b] > graph[a][k] + graph[k][b]) {
                        graph[a][b] = graph[a][k] + graph[k][b];
                    }
                }
            }
        }


        // 예은이가 얻을 수 있는 최대 아이템 개수 갱신
        for (int i = 1; i <= n; i++){
            // 수색 범위 내에 있으면 일단 count에 더하기
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != INF && graph[i][j] <= m)
                    count += items[j];
            }

            if (count > ans)
                ans = count;
        }

        System.out.println(ans);
    }
}