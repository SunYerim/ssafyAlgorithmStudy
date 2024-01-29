package week2.BOJ2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {
    static int N;
    static int[] laizer, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        laizer = new int[N];
        ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            laizer[i] = Integer.parseInt(st.nextToken());
        }

        // for문 대신 stack 처리
        Stack<Node> stack = new Stack<>();

        // 뒤에서 부터 -> 시간초과
//        for (int i = N-1; i > 0; i--) {
//            for (int j = i-1; j > 0; j--) {
//                if (laizer[i] < laizer[j]) {
//                    ans[i] = j+1;
//                    break;
//                }
//            }
//        }

        // 스택을 사용하면 어떤식으로 로직처리가 들어가야할지 -> node클래스를 생성해서 해결
        // 자신의 높이와 비교하면서 pop처리 시키면 됨.
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && stack.peek().number < laizer[i]) {
                stack.pop();
            }
            // 비어 있으면
            if (stack.isEmpty()) {
                ans[i] = 0;
            } else {
                ans[i] = stack.peek().index + 1;
            }
            stack.push(new Node(i, laizer[i]));
        }
        //System.out.println(Arrays.toString(ans));
        for (int i = 0; i < N-1; i++) {
            System.out.print(ans[i]+ " ");
        }
        System.out.print(ans[N-1]);
    }

    static class Node {
        int index;
        int number;
        public Node(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }
}
