package week3.BOJ2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2668 {
    static int N;
    static ArrayList<Integer> result = new ArrayList<>();
    static boolean[] visited;
    static int[] numArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numArr = new int[N+1];


        for (int i = 1; i <= N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            DFS(i, i);
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    static void DFS(int start, int current) {
        // 이미 방문 했으면
        if (visited[current]) {
            if (start == current) {
                result.add(start);
            }
        } else {
            visited[current] = true;
            DFS(start, numArr[current]);
        }
    }
}
