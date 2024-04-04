package week10.BOJ13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13549 {
    static int N, K;
    static boolean[] visited = new boolean[100001];
    static int[] dx = {-1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);
    }

    public static void bfs(int start, int destination) {
        Queue<int[] > q = new LinkedList<>();
        q.add(new int[]{start, 0});
        visited[start] = true;


        while (!q.isEmpty()) {
            int[] p = q.poll();
            int pos = p[0];
            int move = p[1];

            if (pos == destination) {
                System.out.println(move);
                return;
            }
            if (2*pos <= 100000 && !visited[2*pos]) {
                visited[2*pos] = true;
                q.add(new int[]{2*pos, move});
            }

            for (int i = 0; i < 2; i++) {
                int next = pos + dx[i];
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, move+1});
                }
            }

        }
    }
}