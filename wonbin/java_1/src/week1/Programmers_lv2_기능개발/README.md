# Programmers_lv2_기능개발

# [Programmers][Lv2] 기능개발/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42586)]

프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

---

> **제한사항**
> 

입력/출력 제한사항

- 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
- 작업 진도는 100 미만의 자연수입니다.
- 작업 속도는 100 이하의 자연수입니다.
- 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

---

> **문제 풀이**
> 

배열sol을 하나 만들어 그곳에 progresses의 값을 넣고 무한 반복으로 speeds 값을 넣었다.

배열의 앞의 값보다 뒤쪽이 먼저 100이 된다면 반복에서 카운트 되지 않게 하고 앞 순서부터 100이 될때마다 count의 값을 더했다. 처음 배열의 값을 all에 넣고 count가 할당될때마다 all값에서 count 값을 빼고 all이 0이 되면 반복이 종료 되도록 하였다. 그리고 나온 answer2값들을 answer에 넣어서 답을 구했다.

---

> **코드**
> 

```java
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] sol = new int[100];
        int count = 0;
        int all = 0;
        int tmp = 0;
        
        for(int a: progresses){
            sol[all] = a;
            all++;
        }
        int[] answer2 = new int[all];
        int a2 = all;
        
        while(all != 0){
            count = 0;
            for(int i =0 ; i<a2;i++){
                if(sol[i]>=100){
                    if(i == 0 || sol[i - 1] == 0){
                        count++;
                        sol[i] = 0;
                    }
                }
                else if(sol[i] != 0){
                    sol[i] += speeds[i];
                }
            }
            if(count != 0){
                answer2[tmp++] = count;
                all -= count;
            }
        }
        int[] answer = new int[tmp];
        for(int i = 0;i<tmp;i++){
            answer[i] = answer2[i];
        }
        return answer;
    }
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 구현 자체는 어렵지 않았다.
- 분류는 스택/큐 라고 되어있는데 스택이나 큐는 인덱스 참조가 불가능하여 배열로 구현하였다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 문제풀이 시간: 30분
- 실행시간: 평균 0.12ms
- 메모리 : 평균 75mb
- 코드길이: 프로그래머스 기준 39줄

알고리즘 분류

- 스택, 큐