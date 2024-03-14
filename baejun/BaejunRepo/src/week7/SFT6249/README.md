<h3 align="center"> 
    📢  [레벨3] 소프티어(염기 서열) : https://softeer.ai/practice/6249
</h3>

<br>

## 🚀 문제

생명 공학을 연구하는 찬홍이는 요즘 DNA 염기서열을 연구하고 있다. DNA 염기서열이란 4종류의 핵염기 a(아데닌), c(사이토신), g(구아닌), t(티민)이 일자로 연결된 가닥이다. 문자열로는 a, c, g, t 네 문자의 나열로 나타낸다.
찬홍이는 인간에게 좋게 작용하는 좋은 염기서열 N개를 가지고 있고 이 모든 좋은 염기서열의 길이는 M이다. 주어진 좋은 염기서열은 몇 개의 와일드 카드(.)를 가지고 있어, 이 부분에는 a, c, g, t의 어떤 염기가 들어가도 좋은 염기서열로 작용한다고 하자.
주어진 좋은 염기서열의 조건을 만족할 수 있는 염기서열을 초염기서열이라고 한다. 그러나 주어진 모든 좋은 염기서열을 만족하는 것은 불가능할 수 있어서, 여러 초염기서열을 만들어서 여러 그룹의 좋은 염기서열을 커버할 수 있도록 하고자 한다.
주어진 모든 좋은 염기서열을 커버하기 위해 필요한 최소 갯수의 초염기서열은 몇 개일까?
---

## 🚦입출력 + 제한사항

* 1 ≤ N ≤ 15
* 1 ≤ M ≤ 20
* 주어지는 염기서열의 길이는 M이다.
* 염기서열은 'a','c', 'g', 't', '.'으로 이루어져 있다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- DFS로 모든 경우 탐색하는데, 적절한 가지치랑 배열 copy해서 백트래킹 적용

- [x] dfs를 이용하여 탐색

### 💻코드

```java
private static void DFS(int cnt) {
    if(minLength < list.size()) return; // 가지치기, 의미없는 경우의 수 cut (이 코드 한줄로 시간초과 통과,,)
	// 기저조건
	if(cnt == N) {
		if(minLength > list.size()) minLength = list.size();
		return;
	}
	// 유도조건
	for(int i = 0; i < list.size(); i++) {
		boolean flag = false; 
		for(int j = 0; j < list.get(i).length; j++) {// 해당 리스트의 char배열에 fit한지 검사
			if(array[cnt][j] == '.' || list.get(i)[j] == '.' || array[cnt][j] == list.get(i)[j]) continue;
			flag = true;
			break;
		}
		if(!flag) {
			char[] copy = new char[M];
			for(int k = 0; k < M; k++) {
				copy[k] = list.get(i)[k];
				if(array[cnt][k] == '.') continue;
				list.get(i)[k] = array[cnt][k];
			}
			DFS(cnt+1); // 바꾼채로 dfs 태우고 copy로 원상복구
			for(int k = 0; k < M; k++) {
				list.get(i)[k] = copy[k];
			}
		}
	}
	// 새로운 초염기를 만들어내는 경우까지 check
	char[] addChar = new char[M];
	for(int i = 0; i < M; i++) {
		addChar[i] = array[cnt][i];
	}
	list.add(addChar);
	DFS(cnt+1);
	list.remove(list.size()-1);
}
```

### 🙄 후기
소요시간 : 1시간 30분?  <br>
저 사이즈가 이미 커졌으면 컷 하는 가지를 생각 못해서 꽤나 애먹었다.. 백트래킹은 언제나 가지치기 하는걸 염두에 두곢!