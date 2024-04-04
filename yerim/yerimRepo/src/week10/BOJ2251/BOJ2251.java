package week10.BOJ2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 물을 옮길 수 있는 경우는 6가지이다.
public class BOJ2251 {
    static int maxA, maxB, maxC;
    static ArrayList<Integer> bottles;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());
        bottles = new ArrayList<>();

        bfs();
        // 오름차순
        Collections.sort(bottles);

        for (int i = 0; i < bottles.size(); i++) {
            System.out.print(bottles.get(i)+" ");
        }
    }

    // 여기서는 물을 옮길 수 있는 6가지 경우가 있으므로 해당 경우를 다 따져준다.
    private static void bfs() {
        Queue<Water> queue = new PriorityQueue<>();
        // visited배열
        boolean[][][] visited = new boolean[maxA+1][maxB+1][maxC+1];

        queue.add(new Water(0, 0, maxC)); // 처음
        while (!queue.isEmpty()) {
            Water curr = queue.poll();
            if (visited[curr.x][curr.y][curr.z]) continue;

            // 첫번째 물통이 비어있을 때,
            if (curr.x == 0) {
                bottles.add(curr.z);
            }

            visited[curr.x][curr.y][curr.z] = true;

            // 모든 경우의 수 다 따져준다.
            // 1. x -> y
            if (curr.x + curr.y <= maxB) {
                queue.add(new Water(0, curr.x + curr.y, curr.z));
            } else {
                queue.add(new Water(curr.x + curr.y - maxB, maxB, curr.z));
            }
            // 2. x -> z
            if (curr.x + curr.z <= maxC) {
                queue.add(new Water(0, curr.y, curr.x + curr.z));
            } else {
                queue.add(new Water(curr.x + curr.z - maxC, curr.y, maxC));
            }
            // 3. y -> x
            if (curr.x + curr.y <= maxA) {
                queue.add(new Water(curr.x + curr.y, 0, curr.z));
            } else {
                queue.add(new Water(maxA, curr.x + curr.y - maxA, curr.z));
            }
            // 4. y -> z
            if (curr.y + curr.z <= maxC) {
                queue.add(new Water(curr.x, 0, curr.y + curr.z));
            } else {
                queue.add(new Water(curr.x, curr.y + curr.z - maxC, maxC));
            }
            // 5. z -> x
            if (curr.x + curr.z <= maxA) {
                queue.add(new Water(curr.x + curr.z, curr.y, 0));
            } else {
                queue.add(new Water(maxA, curr.y, curr.x+curr.z - maxA));
            }
            // 6. z -> y
            if (curr.y + curr.z <= maxB) {
                queue.add(new Water(curr.x, curr.y + curr.z, 0));
            } else {
                queue.add(new Water(curr.x, maxB, curr.y + curr.z - maxB));
            }
        }
    }

    static class Water implements Comparable<Water>{
        private int x, y, z;
        public Water(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(Water o) {
            return 0;
        }
    }
}