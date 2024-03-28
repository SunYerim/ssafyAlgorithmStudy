package week9.PGS43238;

import java.util.*;
/* logic
    1. right : 가장 오래 걸리는 심사관이 모든 사람을 심사하는 경우의 시간으로 설정
    2. complete: mid시간동안 심사를 받을 수 있는 사람의 수를 계산하기 위함
        - complete += mid / times[i] : 각 심사관이 mid 시간동안 심사할 수 있는 사람의 수를 complete에 더한다.
        - if (complete >= n) break: 이미 심사받아야할 사람 수 n을 초과하거나 만족시키면, 더 이상의 계산은 불필요하므로 반복 종료
    3. if (complete < n) left = mid + 1: complete가 n보다 작다면, mid시간안에 모든 사람을 심사할 수 없다는 의미이므로, left = mid + 1로 조정하여 범위 조정
    4. else: complete가 n 이상이라면, right를 mid-1로 조정후 answer에 mid값을 저장 => 해당 경우가 현재까지 찾은 조건을 만족하는 최소시간이 된다.
    */
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = times[times.length-1] * (long) n;

        while (left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;
            for (int i = 0; i < times.length; i++) {
                complete += mid / times[i];

                if (complete >= n) break;
            }
            if (complete < n) left = mid + 1;
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}