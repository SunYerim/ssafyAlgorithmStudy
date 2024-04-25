package week13.BOJ11085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11085 {
    static int p, w, c, v;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        parents = new int[p];
        //초기화
        for (int i = 0; i < p; i++) {
            parents[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 입력받기
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(start, end, width));
        }

        // union-find
        int result = 0;
        while (find(c) != find(v)) {
            Edge tmp = pq.poll();
            if (find(tmp.start) != find(tmp.end)) {
                union(tmp.start, tmp.end);
                result = tmp.width;
            }
        }
        System.out.println(result);

    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return (parents[x] = find(parents[x]));
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static class Edge implements Comparable<Edge> {

        private int start, end, width;

        public Edge(int start, int end, int width) {
            this.start = start;
            this.end = end;
            this.width = width;
        }
        // 큰것부터
        @Override
        public int compareTo(Edge o) {
            return o.width - this.width;
        }
    }
}