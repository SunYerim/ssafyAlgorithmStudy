package week1.PGS42587;

import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

// 우선순위 큐
public class PGS42587 {
    public int solution(int[] priorities, int location) {

        // Node 형 우선순위 큐 선언
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            Node node = new Node(i, priorities[i]);
            priorityQueue.add(node);
            queue.add(node);
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.priority == priorityQueue.peek().priority) {
                priorityQueue.poll();
                answer++;
                if (current.position == location) {
                    break;
                }
            } else {
                queue.add(current);
            }
        }
        return answer;
    }



    public class Node implements Comparable<Node> {
        int position;
        int priority;

        public Node(int position, int priority) {
            this.position = position;
            this.priority = priority;
        }

        @Override
        public int compareTo(Node o) {
            return o.priority - this.priority;
        }
    }

}