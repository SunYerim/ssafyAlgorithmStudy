<h3 align="center"> 
    📢  [골드5] 백준(직사각형의 개수) : https://www.acmicpc.net/problem/2171
</h3>

<br>

## 🚀 문제

2차원 평면 위에 N(1 ≤ N ≤ 5,000)개의 점들이 있다. 이런 점들 중 서로 다른 네 개의 점을 잡아서 연결하면 사각형이 만들어진다. 이러한 사각형들 중에서, x축과 y축에 평행한 직사각형의 개수를 구하는 프로그램을 작성하시오. 직사각형의 넓이는 양수이어야 한다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 각 점의 x, y좌표가 주어진다. 좌표의 범위는 -1,000,000,000 이상 1,000,000,000 이하이며, 두 점의 좌표가 같은 경우는 없다.
- 첫째 줄에 직사각형의 개수를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- x축을 기준으로 y축 리스트를 생성
- 같은 x축의 두개의 y점을 선정하고, 다른 x축에서 선정한 두개의 y점을 보유하고있다면, 직사각형이 성사되므로 그걸 계산함
- 근데 이 과정을 이분탐색 + 조합으로 풀었는데 시간초과가 났음, 생각해보면 조합을 직접 돌릴 필요가 없고 그냥 개수만 뽑으면되는거였음
- 그래서 이분탐색으로 같은 x축 기준 y축 리스트로 다른 x축의 같은 y축 개수를 세서 조합 개수를 구했음

- [x] 이분탐색 + 조합

### 💻코드

```java
private static void binarySearch2(int left, int right, int target, int idx) {
	int mid = (left+right) / 2;
	if(mid > nodes.get(idx).list.size()-1) return;
	if(nodes.get(idx).list.get(mid) == target) {
		flagForComb = true;
		return;
	}
	if(left <= right) {
		if(nodes.get(idx).list.get(mid) > target) {
			right = mid - 1;
			binarySearch2(left, right, target, idx);
		} else {
			left = mid + 1;
			binarySearch2(left, right, target, idx);
		}
	}
	return;
}
```

### 🙄 후기
소요시간 : 3시간 <br>
하하하.. 사실 조합을 직접 돌려서 시간초과가 난걸 깨달은 뒤부터 정답까지는 금방 도달했고 <br>
이분탐색 코드를 복붙하다가 엣지가 계속 떠서 시간을 욜라리 잡아먹었다. <br>
코드를 갖다 쓸땐 찬차안히 한바퀴 돌리자 꼭^_^