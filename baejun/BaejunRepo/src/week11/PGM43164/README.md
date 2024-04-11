<h3 align="center"> 
    📢  [레벨3] 프로그래머스(표현 가능한 이진트리) : https://school.programmers.co.kr/learn/courses/30/lessons/150367
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 아애 감도 못잡았고, 접근법 여러개 보고 이해했다
- 1. 포화이진트리이므로 가능한한 최소의 높이를 찾아서 포화이진트리의 높이를 결정한다.
- 2. 부족한만큼 앞에 0을 붙여준다
- 3. DFS를 돌려서 root가 0일때 자식에 1인경우가 있는지 찾는다, 있다면 트리불가

- [x] DFS

### 💻코드

```java
public void DFS(String str) {
    // 기저조건(1이거나(끝까지갔) 이미 답이 구해졌거나)
    if(str.length() <= 1 || answerFlag) {
        return;
    }
    // 유도조건(mid 구해서 문제 없나 체크하고, 자식들 다시 DFS 보내기)
    int mid = (str.length()-1) / 2;
    char root = str.charAt(mid);
    if(root == '0') {
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '1') {
                answerFlag = true;
                break;
            }
        }
        String left = "";
        String right = "";
        for(int i = 0; i < mid; i++) {
            left += str.charAt(i);
        }
        for(int i = mid+1; i < str.length(); i++) {
            right += str.charAt(i);
        }
        DFS(left);
        DFS(right);
    } else {
        String left = "";
        String right = "";
        for(int i = 0; i < mid; i++) {
            left += str.charAt(i);
        }
        for(int i = mid+1; i < str.length(); i++) {
            right += str.charAt(i);
        }
        DFS(left);
        DFS(right);
    }
}
```

### 🙄 후기
소요시간 : 2시간 <br>
이해는 어려웠지만 구현은 쉬웠따..