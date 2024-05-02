package week14.BOJ16562;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16562 {
    static int n, m, k;
    static int[] friends;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friends = new int[n+1];
        visited = new boolean[n+1];
        parents = new int[n+1];

        // 초기화
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            friends[i] = num;
        }

        // m줄 입력받기
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int moneySum = 0;

        for (int i = 1; i <= n; i++) {
            int root = find(i);

            // 이미 방문했으면
            if (visited[root]) {
                visited[i] = true;
                continue;
            }

            moneySum += friends[root];

            visited[root] = true;
            visited[i] = true;

        }

        // 모든 학생을 친구로 만든다.
        if (moneySum > k)
            System.out.println("Oh no");
        else
            System.out.println(moneySum);


    }

    private static int find(int x) {
        if (x == parents[x])
            return x;
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (friends[a] < friends[b]) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }
}