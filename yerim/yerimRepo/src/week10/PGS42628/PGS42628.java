package week10.PGS42628;

import java.util.*;
class PGS42628 {
    public int[] solution(String[] operations) {
        int[] answer = {};
        Queue<Integer> minQueue = new PriorityQueue<>(); // 최솟값
        Queue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder()); // 최댓값

        for (int i = 0; i < operations.length; i++) {
            String tmp[] = operations[i].split(" ");
            String instruction = tmp[0];
            Integer number = Integer.parseInt(tmp[1]);

            // 명령어가 I일때
            if (instruction.equals("I")) {
                minQueue.offer(number);
                maxQueue.offer(number);
            }


            // 큐가 비어있지 않을 때
            if  (instruction.equals("D")){
                // 명령어가 D일때
                if (!minQueue.isEmpty() || !maxQueue.isEmpty()) {
                    // 1일때 -> 최댓값 삭제
                    if (number == 1) {
                        int tmp2 = maxQueue.poll();
                        minQueue.remove(tmp2);
                    }
                    // -1일때 -> 최솟값 삭제
                    else if (number == -1) {
                        int tmp1 = minQueue.poll();
                        maxQueue.remove(tmp1);
                    }
                }
            }
        }
        // 다 돌았을 때 비어있으면
        if (maxQueue.isEmpty() || minQueue.isEmpty()) {
            answer = new int[]{0, 0};
        } else {
            answer = new int[2];
            answer[0] = maxQueue.poll();
            answer[1] = minQueue.poll();
        }
        return answer;
    }
}