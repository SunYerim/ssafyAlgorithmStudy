package week7.BOJ11265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11265 {
    static final int INF = (int)1e9;
    static int N, M;
    static int[][] map;
    static int[] d = new int[501];
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // 플로이드-워셜 접근
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 초기화
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<Node>());
        }


        // 0번부터 출발하는 걸로
        // 그래프 보충
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    list.get(i).add(new Node(j, num));
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            dijkstra(a);



            if (d[b] <= c)
                System.out.println("Enjoy other party");
            else
                System.out.println("Stay here");
        }


    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        Arrays.fill(d, INF);
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
