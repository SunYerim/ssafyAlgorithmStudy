# [Programmers][level3] 스티커모으기2/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/12971)]

[문제 내용 전체]

N개의 스티커가 원형으로 연결되어 있습니다. 다음 그림은 N = 8인 경우의 예시입니다.

!https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d8d3a8b3-606c-4fb6-baf2-3a96cb53d70c/%E1%84%89%E1%85%B3%E1%84%90%E1%85%B5%E1%84%8F%E1%85%A5_hb1jty.jpg

원형으로 연결된 스티커에서 몇 장의 스티커를 뜯어내어 뜯어낸 스티커에 적힌 숫자의 합이 최대가 되도록 하고 싶습니다. 단 스티커 한 장을 뜯어내면 양쪽으로 인접해있는 스티커는 찢어져서 사용할 수 없게 됩니다.

예를 들어 위 그림에서 14가 적힌 스티커를 뜯으면 인접해있는 10, 6이 적힌 스티커는 사용할 수 없습니다. 스티커에 적힌 숫자가 배열 형태로 주어질 때, 스티커를 뜯어내어 얻을 수 있는 숫자의 합의 최댓값을 return 하는 solution 함수를 완성해 주세요. 원형의 스티커 모양을 위해 배열의 첫 번째 원소와 마지막 원소가 서로 연결되어 있다고 간주합니다.

---

> **제한사항**
> 

입력/출력 제한사항

### 제한 사항

- sticker는 원형으로 연결된 스티커의 각 칸에 적힌 숫자가 순서대로 들어있는 배열로, 길이(N)는 1 이상 100,000 이하입니다.
- sticker의 각 원소는 스티커의 각 칸에 적힌 숫자이며, 각 칸에 적힌 숫자는 1 이상 100 이하의 자연수입니다.
- 원형의 스티커 모양을 위해 sticker 배열의 첫 번째 원소와 마지막 원소가 서로 연결되어있다고 간주합니다.

### 제한 사항

- sticker는 원형으로 연결된 스티커의 각 칸에 적힌 숫자가 순서대로 들어있는 배열로, 길이(N)는 1 이상 100,000 이하입니다.
- sticker의 각 원소는 스티커의 각 칸에 적힌 숫자이며, 각 칸에 적힌 숫자는 1 이상 100 이하의 자연수입니다.
- 원형의 스티커 모양을 위해 sticker 배열의 첫 번째 원소와 마지막 원소가 서로 연결되어있다고 간주합니다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 그냥 완탐을 할까 했는데 그러면 시간 초과 걸릴거 같아서 그리디 형식으로 접근을 했다. 하지만 코드를 작성하고 보니 예외 케이스가 발생하는것을 알았다. 그래서 고민을 해보았지만 아무리 생각해도 답이 안나와 해답을 보았다. DP로 해결하는 문제였다.

1. n의 개수가 1, 2개일 경우를 예외 처리 해준다.
2. 나머지 경우에서 인덱스가 0과 1에서 시작할때를 구분해준다
3. dp테이블을 작성해 답을 구한다.

---

> **코드**
> 

내코드

```java
class Solution {
    
    static int[] stic;
    static int s_size;
    static int count;
    static int ck;
    
    public int solution(int sticker[]) {
        int answer = 0;
        s_size = sticker.length;
        stic = new int[s_size];

        if(s_size % 2 == 0) {
            int tmp = 0;
            for(int i=0;i<s_size;i+=2) {
                tmp += sticker[i];
            }
            count = tmp;
            tmp = 0;
            for(int i=1;i<s_size;i+=2) {
                tmp += sticker[i];
            }
            count = Math.max(tmp, count);
            answer = count;
        }
        else {
            for(int i=0;i<s_size;i++) {
                int idx = i;
                count = 0;
                stic = new int[s_size];
                while(true) {
                    ck = 1;
                    if(stic[idx] == 0) {
                        count += sticker[idx];
                        stic[idx]++;
                        
                        if(idx-1 < 0){
                            stic[s_size-1]++;
                        }
                        else{
                            stic[idx-1]++;
                        }
                        
                        if(idx+1 == s_size) {
                            stic[0]++;
                        }
                        else{
                            stic[idx+1]++;
                        }
                    }
                    
                    for(int j=0;j<s_size;j++) {
                        if(stic[j] == 0) {
                            ck = 0;
                            break;
                        }
                    }
                    
                    if(ck == 1)
                        break;
                    idx++;
                    if(idx == s_size)
                        idx = 0;
                }
                answer = Math.max(count, answer);
            }
        }

        return answer;
    }
}
```

정답 코드

```java
class Solution {
    public int solution(int sticker[]) {
        int size = sticker.length;
        if (size == 1) return sticker[0];
        else if (size == 2) return Math.max(sticker[0], sticker[1]);
 
        //0번째 인덱스를 고르면서 시작한 경우
        int[] dp1 = new int[size - 1];
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
 
        for (int i = 2; i < sticker.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }
 
        //1번째 인덱스를 고르면서 시작한 
        int[] dp2 = new int[size];
        dp2[0] = 0;
        dp2[1] = sticker[1];
 
        for (int i = 2; i < size; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }
 
        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- DP는 진짜 모르겠다;;
- 그래도 DP 테이블을 생각하는 습관을 기르면 해결이 될거 같기도 하다

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 실패

알고리즘 분류

- DP