<h3 align="center"> 
    📢  [골드5] 백준(숫자 고르기) : https://www.acmicpc.net/problem/2668
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- DFS
- DFS를 돌며 싸이클이 생기는지를 판단, 싸이클이 생긴다면 뽑을 수 있는 수이다.

### 💻코드

```java
private static void DFS(int idx, int target) {
	if(!visited[array[idx]]) {
		visited[array[idx]] = true;
		DFS(array[idx], target);
		visited[array[idx]] = false;
	}
	if(array[idx] == target) {
		answer.add(target);
	}
}
```

### 🙄 후기
소요시간 : 1시간  <br>
나 이제 그냥 실력 실버따리가 맞는듯.. ㅠㅠ<br>
시간복잡도를 100 * 100 = 10000인거를, 100*100*100*100*100*100* .... *100 ? 이건 말이안되는데? 이러고 있었음.. 뇌이징커브 이슈 미쳤다