package week21.BOJ14226;

import java.io.*;
import java.util.*;

public class BOJ14226 {
    static int N;
    static int min = Integer.MAX_VALUE;

    static class Node {
        int count;
        int cost;
        int clipBoard;

        Node(int count, int cost, int clipBoard) {
            this.count = count;
            this.cost = cost;
            this.clipBoard = clipBoard;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        int answer = bfs();

        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1, 0, 0));

        boolean[][] visited = new boolean[1001][1001];

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.count == N) {
                min = curNode.cost;
                break;
            }

            // 클립보드에 붙여넣고 나서
            queue.offer(new Node(curNode.count, curNode.cost + 1, curNode.count));
            // 그러고 -1이랑 붙여넣기 한번씩
            if (curNode.clipBoard != 0 && curNode.count + curNode.clipBoard <= N && !visited[curNode.count + curNode.clipBoard][curNode.clipBoard]) {
                queue.offer(new Node(curNode.count + curNode.clipBoard, curNode.cost + 1, curNode.clipBoard));
                visited[curNode.count + curNode.clipBoard][curNode.clipBoard] = true;
            }
            if (curNode.count >= 1 && !visited[curNode.count-1][curNode.clipBoard]) {
                queue.offer(new Node(curNode.count - 1, curNode.cost + 1, curNode.clipBoard));
                visited[curNode.count-1][curNode.clipBoard] = true;
            }
        }

        return min;
    }
}