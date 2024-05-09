<h3 align="center"> 
    📢  [골드4] 백준(거짓말) : https://www.acmicpc.net/problem/1043
</h3>

<br>

## 🚀 문제


---

## 🚦입출력 + 제한사항


---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- union-find로 진실 아는 쪽 기준으로 parents 형성
- 이후 다시 파티를 돌며 진실을 아는 사람과 엮여있는 사람이 있다면 그 파티에선 진실만 말해야됨

- [x] union-find

### 💻코드

```java
private static void union(int a, int b) {
	int rootA = find(a);
	int rootB = find(b);

	int rootAContainsTruth = Arrays.binarySearch(alreadyKnowTruth, rootA);
	int rootBContainsTruth = Arrays.binarySearch(alreadyKnowTruth, rootB);

	if(rootAContainsTruth >= 0 && rootBContainsTruth < 0) {
		parents[rootB] = rootA;
	} else if(rootAContainsTruth < 0 && rootBContainsTruth >= 0) {
		parents[rootA] = rootB;
	} else {
		parents[rootA] = rootB;
	}
}

private static int find(int a) {
	if(parents[a] == a) return a;

	return parents[a] = find(parents[a]);
}

private static void makeset() {
	for(int i = 1; i <= N; i++) {
		parents[i] = i;
	}
}
```

### 🙄 후기
소요시간 : 1시간  <br>
진실아는 사람 리스트에 있나없나를 binarySearch로 봤는데, 이거 배열을 sort를 안해놓고 써서 잠깐 헤맸다 <br>
하나하나 꼼꼼히 기본에 충실하자 ! +코테대비느낌으로, ide를 써도 최대한 깡 코딩으로 연습하자