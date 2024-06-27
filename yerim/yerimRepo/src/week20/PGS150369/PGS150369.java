package week20.PGS150369;

/*logic
    1. cap <= 이면서, 먼 곳부터 다 배달될 수 있도록
    2. 돌아올때는, cap <= 이면서 수거할 수 있을만큼 뒷 집부터 수거.
        - 생각해보니까 어느 지점까지 수거를 했는지 marker 표시가 있어야 될 거 같기도 함.
    3. 처음에, length를 n으로 잡고 마지막 집부터 cap 용량만큼 배달할 수 있는 용량만큼 배달.
    5. 그리고 n을 갱신시켜줘야함. 최대로 갈 수 있는 거리
        - 종료조건을 어떻게 잡아야하지? 따로 변수 하나를 둬야하는건가 돌아오는 것 기준으로*/
import java.util.*;

class PGS150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;
        int pickup = 0;

        for (int i = n-1; i >= 0; i--) {
            // 배달해야되는 양 delivery 갱신
            delivery += deliveries[i];
            pickup += pickups[i];

            while (delivery > 0 || pickup > 0) {
                delivery -= cap;
                pickup -= cap;

                answer += 2 * (i+1);
            }
        }
        return answer;
    }
}