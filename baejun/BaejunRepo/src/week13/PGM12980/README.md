<h3 align="center"> 
    📢  [레벨2] 프로그래머스(점프와 순간 이동) : https://school.programmers.co.kr/learn/courses/30/lessons/12980
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 숫자 N: 1 이상 10억 이하의 자연수
- 숫자 K: 1 이상의 자연수

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 전에 이거 거의 똑같은거 0-1BFS or Dijkstra 유형으로 풀었어서 그거겠거니 ~ 했는데, 아니였고~
- 홀수면 어쩌구 짝수면 어쩌구로 품

-

### 💻코드

```java
public int solution(int N) {
    int answer = 0;
    
    while(N > 1) {
        if(N % 2 == 0) {
            N /= 2;
        } else {
            N--;
            answer++;
        }
    }
    
    return answer+1;
}
```

### 🙄 후기
소요시간 : 1시간  <br>
뭘 체크하고자 하는 문젠지 모르겠다, 이런 문제 맘에 안듬..