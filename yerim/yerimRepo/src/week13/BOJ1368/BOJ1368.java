package week13.BOJ1368;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1368 {
    static int n;
    static int[] pool;
    static int[] parents;
    static PriorityQueue<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        pool = new int[n+1];
        parents = new int[n+1];
        list = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            pool[i] = Integer.parseInt(br.readLine());
        }

        // 초기화
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (i == j) {
                    list.add(new Node(0, i, pool[i]));
                }
                else {
                    list.add(new Node(i, j, num));
                }
            }
        }

        int result = 0;

        while (!list.isEmpty()) {
            Node node = list.poll();
            if (find(node.s) == find(node.e)) continue;
            union(node.s, node.e);
            result += node.cost;
        }

        System.out.println(result);
    }

    static int find(int x) {
        if (x == parents[x])
            return x;
        return (parents[x] = find(parents[x]));
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    // 정렬
    static class Node implements Comparable<Node>{

        private int s, e, cost;
        public Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}