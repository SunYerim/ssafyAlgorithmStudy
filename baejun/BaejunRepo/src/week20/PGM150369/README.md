<h3 align="center"> 
    📢  [레벨2] 프로그래머스(2023 KAKAO BLIND RECRUITMENT / 택배 배달과 수거하기) : https://school.programmers.co.kr/learn/courses/30/lessons/150369#
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- Greedy하게 가장 먼 곳부터 해결해줘서 먼 곳까지 갈 일을 최소화 시키는 방법
- 까진 쉬웠으나, 시간초과를 어떻게..?
- stack을 이용해서 유효하지 않은 집은 애초에 안가게끔 설계(택배를 주거나 빈 박스 받을게 있는 집만 탐색)

-

### 💻코드

```java
public void operation() {
        int delieverCnt = 0;
        int pickupCnt = 0;
        
        int delieverDistance = 0;
        int pickupDistance = 0;
        
        // 배달 stack 이용해서 다녀오기
        while(!delieverValid.isEmpty() && delieverCnt < capCopy) {
            int idx = delieverValid.pop();
            
            if(deliveriesCopy[idx] <= (capCopy - delieverCnt)) {
                delieverCnt += deliveriesCopy[idx];
            } else {
                delieverCnt = capCopy;
                deliveriesCopy[idx] -= (capCopy - delieverCnt);
                delieverValid.push(idx);
            }
            if(delieverDistance == 0) delieverDistance = idx+1;
        }
        // 수거 stack 이용해서 다녀오기
        while(!pickupsValid.isEmpty() && pickupCnt < capCopy) {
            int idx = pickupsValid.pop();
            int maxPick = capCopy - pickupCnt;
            
            if(pickupsCopy[idx] <= maxPick) {
                pickupCnt += pickupsCopy[idx];
            } else {
                pickupCnt = capCopy;
                pickupsCopy[idx] -= maxPick;
                pickupsValid.push(idx);
            }
            if(pickupDistance == 0) pickupDistance = idx+1;
        }
        
        totalDistance += (delieverDistance > pickupDistance) ? delieverDistance*2 : pickupDistance*2;
}
```

### 🙄 후기
소요시간 : 1시간 10분  <br>
흠.. 스택 아이디어를 생각못했다, 쉬워보였는데 <br>
그냥 재활훈련 마냥 실버문제를 좀 많이 풀어야겠다 싶다