<h3 align="center"> 
    📢  [실버3] 백준(회전하는 큐) : https://www.acmicpc.net/problem/1021
</h3>

<br>

## 🚀 문제

지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다. 지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.
지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.

1. 첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
2. 왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
3. 오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다. 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
- 첫째 줄에 문제의 정답을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 첫번째 원소를 삭제하는 연산도 있고
- 왼쪽, 오른쪽 한칸씩 옮기는 연산도 각각 끝자리의 요소를 삭제하고 삽입하는거라서
- linked list로 구현해볼 생각을 했다
- 실제로 소요시간도 괜찮게 나왔고, 성공적인 접근이였던거 같다.

- [x] Linked List로 인덱스 양 끝의 삽입, 삭제 진행

### 💻코드

```java
for(int i = 0; i < M; i++) {
	int pickUpNumber = Integer.parseInt(st.nextToken());
	int idx = linkedList.indexOf(pickUpNumber);
	int size = linkedList.size() / 2;
	int direction = 0; // 0은 left, 1은 right
	if(idx > size) {
		direction = 1;
	}
	while(true) {
		// 뽑아낼 원소가 첫번째 자리에 오면 뽑아내기
		if(linkedList.get(0) == pickUpNumber) {
			linkedList.remove(0);
			break;
		}
		// 방향에 따라 각 끝자리 요소 삭제와 삽입
		if(direction == 0) {
			cnt++;
			int removeEle = linkedList.remove(0);
			linkedList.add(removeEle);
		} else {
			cnt++;
			int removeEle = linkedList.remove(linkedList.size() - 1);
			linkedList.add(0, removeEle);
		}
	}
}
```

### 🙄 후기
소요시간 : 30분  <br>

linked list 한번 써보려고 푼 문제! <br>
각 자료구조가 활약할 수 있는 상황과 특성을 이해하고 적용하는 것을 연습해보자