package week3.BOJ1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1068 {
    static int N, delNum;
    static int[] parent;
    static boolean[] deleted;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        deleted = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }

        delNum = Integer.parseInt(br.readLine());
        deleteNode(delNum);

        System.out.println(countLeaf());
    }

    static void deleteNode(int node) {
        deleted[node] = true;
        for (int i = 0; i < N; i++) {
            if (parent[i] == node) {
                deleteNode(i);
            }
        }
    }

    // 리프노드 세아리는 메서드
    static int countLeaf() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (deleted[i]) continue;
            boolean isLeaf = true;
            for (int j = 0; j < N; j++) {
                if (!deleted[j] && parent[j] == i) {
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf) count++;
        }
        return count;
    }

}
