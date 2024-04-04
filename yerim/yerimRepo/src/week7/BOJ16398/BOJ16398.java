package week7.BOJ16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.util.Collections;


public class BOJ16398 {
    static int n;
    static long result;
    static ArrayList<Edge> graph;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        // 제국 내 모든 행성을 연결한다.
        // union-find -> 하나로 연결
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        parent = new int[n+1];
        // 부모 테이블 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    graph.add(new Edge(i, j, num));
                }
            }
        }

        Collections.sort(graph);

        // 간선하나씩 확인하면서 최소
        for (int i = 0; i < graph.size(); i++) {
            int cost = graph.get(i).getDistance();
            int a = graph.get(i).getStart();
            int b = graph.get(i).getEnd();
            // 사이클이 발생하지 않는 경우
            if (find(a) != find(b)) {
                union(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return (parent[x] = find(parent[x]));
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Edge implements Comparable<Edge> {

        private int start, end, distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        public int getEnd() {
            return end;
        }

        public int getStart() {
            return start;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}