package week15.BOJ2623;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상정렬
public class BOJ2623 {
    static int n, m;
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n+1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 입력받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int prev = -1;
            for (int j = 0; j < count; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (prev != -1) {
                    list.get(prev).add(num);
                    indegree[num]++;
                }
                prev = num;
            }
        }

        topologySort();
    }

    private static void topologySort() {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);

            for (int i = 0; i < list.get(now).size(); i++) {
                indegree[list.get(now).get(i)]-=1;
                if (indegree[list.get(now).get(i)] == 0) {
                    queue.offer(list.get(now).get(i));
                }
            }
        }

        // 위상정렬 결과 출력
        if (result.size() != n) {
            System.out.println(0);
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }
        }

    }
}