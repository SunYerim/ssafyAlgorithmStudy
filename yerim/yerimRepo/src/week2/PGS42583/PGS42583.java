package week2.PGS42583;

import java.util.*;

// 들어오고 나가는 구조 -> 큐를 사용한다.
public class PGS42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        int time = 0;

        for (int i = 0; i < truck_weights.length; i++) {
            int truck = truck_weights[i];

            while (true) {
                // 큐에 아무것도 없는 경우 -> 어떠한 트럭도 다리 위에 없음
                if (queue.isEmpty()) {
                    queue.add(truck);
                    sum += truck;
                    time++; // 다리에 오를때만 시간 추가
                    break;
                    // 큐에 다리 길이만큼 다리가 꽉 찬 경우
                } else if (queue.size() == bridge_length) {
                    sum -= queue.poll();
                } else {
                    if (sum + truck <= weight) {
                        queue.add(truck);
                        sum += truck;
                        time++;
                        break;
                    } else {
                        queue.add(0);
                        time++;
                    }
                }
            }
        }
        return time + bridge_length;
    }
}