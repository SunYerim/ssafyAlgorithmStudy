package week3.BOJ1967;
/* logic
    1. node 클래스 생성해서 트리 관리
    2. 지름의 조건을 충족하려면 루트 기준으로 양쪽으로 뻗어 내리는 길이가 동일해야할 거 같다.
    3. dfs 사용 -> 완전탐색으로 해결하면 될 거 같음.
    4. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1967 {
    static int n;
    static ArrayList<ArrayList<Node>> trees;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        trees = new ArrayList<>();
        dist = new int[n+1];
        visited = new boolean[n+1];

        for (int i = 0; i <= n; i++) {
            trees.add(new ArrayList<>());
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            trees.get(parent).add(new Node(number, weight));
            trees.get(number).add(new Node(parent, weight)); // 양방향이기때문.

        }

        dfs(1, 0, visited);

        int start = dfs(1, 0, visited).node;

        dist = new int[n+1];
        visited = new boolean[n+1];
        dfs(start, 0, visited);

        int diameter = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > diameter) {
                diameter = i;
            }
        }

        System.out.println(diameter);


    }

    static Node dfs(int node, int distance, boolean[] visited) {
        visited[node] = true;
        dist[node] = distance;
        Node max = new Node(node, distance);
        //visited[node] = true;
        for (Node next : trees.get(node)) {
            if (!visited[next.node]) {
                Node temp = dfs(next.node, distance + next.dist, visited);
                if (temp.dist > max.dist) {
                    max = temp;
                }
            }
        }
        return max;
    }

    static class Node {
        int node;
        int dist;

        public Node (int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
