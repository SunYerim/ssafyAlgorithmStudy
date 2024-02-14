# [Programmers][lv2] 더맵게/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42626)]

[문제 내용 전체]

매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다. 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

`섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)`

Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.

Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

---

> **제한사항**
> 

입력/출력 제한사항

### 제한 사항

- scoville의 길이는 2 이상 1,000,000 이하입니다.
- K는 0 이상 1,000,000,000 이하입니다.
- scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
- 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.

### 입출력 예

| scoville | K | return |
| --- | --- | --- |
| [1, 2, 3, 9, 10, 12] | 7 | 2 |

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 우선순위 큐를 사용을 안하고 풀어보았는데 틀린것도 많고 시간복잡도에서 전부 시간초과가 떴다. 그래서 우선순위 큐를 사용해야겠다고 생각하고 해보았는데 이상하게 계속 여러가지가 해결이 안되었다.

답이 -1이 나오는 경우가 답이 안나오는거 같았는데 처음엔 flag를 새우고 배열을 정렬해서 제일 큰 수를 flag에 넣고 일일이 비교를 해보면서  flag를 지났는데도 답이 안나오면 -1이 나오도록 했는데 안되는 논리였는지 계속 실패를 했다. 이구간에서 시간을 좀 잡아먹었는데 코드를 계속 보니 큐에서 지우는건 2개를 하고 넣는건 1개를 해서 답이 만약 안나오는 경우는 큐가 1개 이하일 경우인걸 캐치 하였다. 그래서 그 후 쉽게 풀었다.

---

> **코드**
> 

```java
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        int sum = 0;
		int answer = 0;
		int flag = 0;
        int tmp = 0;
        
        Arrays.sort(scoville);
        
        for(int i=0;i<scoville.length;i++) {
			p.offer(scoville[i]);
            if(p.peek()> K) break;
			if(i == scoville.length -1) flag = scoville[i];
		}
		
		while(p.peek()<K) {
            if(p.size() <= 1) {
				answer = -1;
				break;
			}
			sum += p.poll();
			sum += p.poll()*2;
			answer++;
			p.add(sum);
			sum = 0;
		}
        
        return answer;
    }
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 우선순위 큐를 활용할 줄 알면 매우 쉽게 풀리는 문제였다.
- 이런 문제에서도 시간을 많이 잡아먹다니 많이 자괴감이 들었다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간? / 1002.21ms / 125MB / 34줄

알고리즘 분류

- 힙(heap)