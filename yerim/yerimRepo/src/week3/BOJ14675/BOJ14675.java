package week3.BOJ14675;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14675 {
    static int n, q;
    static int[] degree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        degree = new int[n+1]; // 각 노드의 연결 간선의 수를 저장할 배열

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            degree[a]++;
            degree[b]++;
        }

         q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t==1) // 단절점 질의인 경우
            {
                if (degree[k] > 1) System.out.println("yes");
                else System.out.println("no");
            }
            else if (t == 2) { // 트리에서 모든 간선은 단절선이 될 수 있다.
                System.out.println("yes");
            }
        }
    }

}
