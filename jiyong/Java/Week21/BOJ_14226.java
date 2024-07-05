import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_14226 {
    public static int S;
    public static boolean[][] visited;

    public static class Node {
        int pos, clipBoard, time;

        public Node(int pos, int clipBoard, int time) {
            this.pos = pos;
            this.clipBoard = clipBoard;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(in.readLine());
        visited = new boolean[2001][2001];
        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(1, 0, 0));
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            // 붙여넣기
            if (now.clipBoard != 0 && now.pos + now.clipBoard < 2001 && !visited[now.pos + now.clipBoard][now.clipBoard]) {
                if (now.pos + now.clipBoard == S)
                    return now.time + 1;
                queue.offer(new Node(now.pos + now.clipBoard, now.clipBoard, now.time + 1));
                visited[now.pos + now.clipBoard][now.clipBoard] = true;
            }
            // 복사
            if (now.pos != now.clipBoard && !visited[now.pos][now.pos]) {
                queue.offer(new Node(now.pos, now.pos, now.time + 1));
                visited[now.pos][now.pos] = true;
            }
            // 지우기
            if (now.pos > 0 && !visited[now.pos - 1][now.clipBoard]) {
                if (now.pos - 1 == S)
                    return now.time + 1;
                queue.offer(new Node(now.pos - 1, now.clipBoard, now.time + 1));
                visited[now.pos - 1][now.clipBoard] = true;
            }
        }
        return S;
    }
}
