<h3 align="center"> 
    📢  [레벨3] 프로그래머스(입국심사) : https://school.programmers.co.kr/learn/courses/30/lessons/43238#
</h3>

<br>

## 🚀 문제

n명이 입국심사를 위해 줄을 서서 기다리고 있습니다. 각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.
처음에 모든 심사대는 비어있습니다. 한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다. 가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다. 하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.
모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.
입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때, 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.

---

## 🚦입출력 + 제한사항

입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
심사관은 1명 이상 100,000명 이하입니다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 이분탐색 주간이니까.. 바로 알 수 있었음, 근데 입력이 워낙에 커서 아니여도 바로 알 수 있었을듯?
- 근데 문제는 이 시간에 가능한지를 어케 체크하노~ 그걸 모르겠어가지고 그냥 그건 검색해봤음(times배열 돌면서 시간/소요시간 해서 이 시간에 통과 가능한 인원 수 계산)

- [x] 이분탐색

### 💻코드

```java
public void binarySearch(long left,long right,int n,int times[]) {
    long mid = (left + right) / 2;
    
    if(left <= right) {
        long cnt = 0;
        boolean flag = false;
        for(int i = 0; i < times.length; i++) { // 이 시간에 모든인원 수속 가능한지 체크
            cnt += mid / (long)times[i];
            if(cnt >= (long)n) {
                flag = true;
                break;
            }
        }
        if(flag) { // 가능한 경우
            right = mid - 1;
            if(bestTime > mid) bestTime = mid;
            binarySearch(left, right, n, times);
        } else { // 안되는 경우
            left = mid + 1;
            binarySearch(left, right, n, times);
        }
    }
    return;
}
```

### 🙄 후기
소요시간 : 1시간 20분  <br>
머리가 안도나.. 저 수속 가능한지 체크하는 저걸 못 떠올리네 ㅠ