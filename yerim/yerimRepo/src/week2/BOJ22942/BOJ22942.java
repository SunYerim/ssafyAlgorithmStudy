package week2.BOJ22942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic
    1. Node 구조를 생성하여 각 원의 중심점과 반지름 길이를 저장
    2. 원들을 중심점 기준으로 정렬하되, 중심이 같다면, 반지름이 더 긴 원을 고려하도록 정렬
    3. 스택에 Node 하나씩 넣으면서 현재 원과 스택의 top에 있는 원이 겹치는지 확인
        - 겹치면 -> 현재 원을 스택에 넣지 않아야 함.
        - 안 겹치면 -> 현재 원을 스택에 넣으면 됨.
     4. 모든 원 순회 후에도 스택에 원이 남아있으면 ? -> "YES"
     5. 중간에 겹치는 원이 있어서 스택에 못 들어간다면 ? -> "NO"*/
public class BOJ22942 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // Node 클래스들을 저장할 배열
        List<Node> nodes = new ArrayList<Node>();
        // 나중에 사용할 스택
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            Node node = new Node(x, r);
            nodes.add(node);
        }

        // 중심점 기준으로 정렬하되, 중심이 같다면 반지름이 더 긴 원이 앞쪽으로
        Collections.sort(nodes);

        // 스택에 node push하면서 현재 원과 스택의 top에 있는 원이 겹치는가 확인
        //  겹치면 -> 스택에 넣지말고, 안 겹치면 -> 스택에 넣고
        stack.push(nodes.get(0));

        for (int i = 1; i < nodes.size(); i++) {
            Node top = stack.peek();
            Node current = nodes.get(i);

            // 두 원의 중심점 간의 거리
            int dis = Math.abs(top.middle - current.middle);

            // 두 원이 겹치는 경우
            if (!(dis > top.radius + current.radius) || !(dis > Math.abs(top.radius - current.radius)) || dis == 0) {
                System.out.println("NO");
                return;
            }

            // 안 겹침
            stack.push(current);
        }

        // 모든 원이 겹치지 않는다면
        System.out.println("YES");
//        if (!stack.isEmpty()) {
//            System.out.println("YES");
//        } else {
//            System.out.println("NO");
//        }



        // 모든 원 순회 후에도 스택에 원이 남아있다면 -> yes, 아니면 -> no
//        if (stack.size() == N) {
//            System.out.println("NO");
//        } else {
//            System.out.println("YES");
//        }


    }

    static class Node implements Comparable<Node> {
        int middle;
        int radius;
        public Node(int middle, int radius) {
            this.middle = middle;
            this.radius = radius;
        }

        @Override
        public int compareTo(Node node) {
//            if (this.middle < node.middle) {
//                return -1;
//            } else if (this.middle == node.middle) {
//                return this.radius > node.radius ? 1 : -1;
//            } else {
//                return 1;
//            }
            if (this.radius == node.radius) {
                return this.middle - node.middle;
            }
            else {
                return node.radius - this.radius;
            }
        }
    }
}
