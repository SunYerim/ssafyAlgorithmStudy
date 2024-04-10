<h3 align="center"> 
    📢  [레벨3] 프로그래머스(여행경로) : https://school.programmers.co.kr/learn/courses/30/lessons/43164
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- dfs

- [x] dfs

### 💻코드

```java
public String[] solution(String[][] tickets) {
    String[] answer = {};
    int cnt = 0;
    visited = new boolean[tickets.length];
    allRoute = new ArrayList<>();
    
    dfs("ICN", "ICN", tickets, cnt);
    
    Collections.sort(allRoute);
    answer = allRoute.get(0).split(" ");
    
    return answer;
}
```

### 🙄 후기
소요시간 : 2시간 <br>
