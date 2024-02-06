package Algorithm.BOJ1326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class BOJ1326 {
    static int N, A, B;
    static int[] arr, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1); // -1로 초기화

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        BFS();
        System.out.println(Arrays.toString(dist));
        System.out.println(dist[B]);
    }

    public static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        dist[A] = 0;
        queue.add(A);

        while (!queue.isEmpty()) {
            int current = queue.remove();

            // 2방향
            for (int i = -1; i <= 1; i += 2) {
                int next = current;

                while (true) {
                    next += i * arr[current];
                    if (next < 1 || next > N) break;

                    if (dist[next] != -1) continue; // 볼 필요가 없음.

                    dist[next] = dist[current] + 1; // 이동거리 값 갱신
                    queue.add(next);
                }
            }
        }
    }
}
