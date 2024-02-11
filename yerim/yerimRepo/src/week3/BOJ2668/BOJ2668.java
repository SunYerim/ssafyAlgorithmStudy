package week3.BOJ2668;
/*logic
   1. 시작점의 숫자 -> 다시 시작점의 숫자로 되돌아오면 사이클이 있는 것으로 판단
   2. 이 아이디어를 DFS로 수행한다
        - 시작 노드에서 시작하여 각 노드가 가리키는 노드로 이동하며 탐색 진행
        - 이미 방문한 노드를 다시 방문하면? => 그 때의 노드부터 시작 노드까지는 사이클을 이루는 것으로 판단.
        - 이 결과를 result 리스트에 추가한다.
   3. DFS를 모든 숫자에 대해 수행*/

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
            // 시작점의 숫자와 current 수가 같아진다면 result에 추가
            if (start == current) {
                result.add(start);
            }
        } else {
            visited[current] = true;
            DFS(start, numArr[current]);
        }
    }
}
