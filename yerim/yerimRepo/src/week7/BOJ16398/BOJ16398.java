package week7.BOJ16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398 {
    static final int INF = (int)1e9;
    static int N;
    static int[][] map;
    static int[] d = new int[1001];
    static ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
    public static void main(String[] args) throws IOException {
        // 플로이드-워셜 접근
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Arrays.fill(d, INF);

        // 초기화
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<Node>());
        }

        // 그래프 보충
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    list.get(i).add(new Node(j, num));
                }
            }

        }

        dijkstra(0);

        System.out.println(d[N-1]);

    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        d[start] = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int now = poll.end;
            int dist = poll.weight;
            if (d[now] < dist) continue;
            for (int i = 0; i < list.get(now).size(); i++) {
                int cost = d[now] + list.get(now).get(i).getWeight();
                if (cost < d[list.get(now).get(i).getEnd()]) {
                    d[list.get(now).get(i).getEnd()] = cost;
                    queue.offer(new Node(list.get(now).get(i).getEnd(), cost));
                }
            }
        }

    }

    static class Node implements Comparable<Node> {
        private int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return this.end;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
