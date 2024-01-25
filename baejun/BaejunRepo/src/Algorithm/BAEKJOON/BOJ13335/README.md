<h3 align="center"> 
    📢  [실버1] 백준(트럭) : https://www.acmicpc.net/problem/13335
</h3>

<br>

## 🚀 문제

강을 가로지르는 하나의 차선으로 된 다리가 하나 있다. 이 다리를 n 개의 트럭이 건너가려고 한다. 트럭의 순서는 바꿀 수 없으며, 트럭의 무게는 서로 같지 않을 수 있다. 다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다. 다리의 길이는 w 단위길이(unit distance)이며, 각 트럭들은 하나의 단위시간(unit time)에 하나의 단위길이만큼만 이동할 수 있다고 가정한다. 동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대하중인 L보다 작거나 같아야 한다. 참고로, 다리 위에 완전히 올라가지 못한 트럭의 무게는 다리 위의 트럭들의 무게의 합을 계산할 때 포함하지 않는다고 가정한다.
예를 들어, 다리의 길이 w는 2, 다리의 최대하중 L은 10, 다리를 건너려는 트럭이 트럭의 무게가 [7, 4, 5, 6]인 순서대로 다리를 오른쪽에서 왼쪽으로 건넌다고 하자. 이 경우 모든 트럭이 다리를 건너는 최단시간은 아래의 그림에서 보는 것과 같이 8 이다.

다리의 길이와 다리의 최대하중, 그리고 다리를 건너려는 트럭들의 무게가 순서대로 주어졌을 때, 모든 트럭이 다리를 건너는 최단시간을 구하는 프로그램을 작성하라.

---

## 🚦입출력 + 제한사항

- 입력 데이터는 표준입력을 사용한다. 입력은 두 줄로 이루어진다. 입력의 첫 번째 줄에는 세 개의 정수 n (1 ≤ n ≤ 1,000) , w (1 ≤ w ≤ 100) and L (10 ≤ L ≤ 1,000)이 주어지는데, n은 다리를 건너는 트럭의 수, w는 다리의 길이, 그리고 L은 다리의 최대하중을 나타낸다. 입력의 두 번째 줄에는 n개의 정수 a1, a2, ⋯ , an (1 ≤ ai ≤ 10)가 주어지는데, ai는 i번째 트럭의 무게를 나타낸다.
- 출력은 표준출력을 사용한다. 모든 트럭들이 다리를 건너는 최단시간을 출력하라.

---

### 📜 문제 풀이(기능 목록, 접근법)
- [x] queue를 이용하여 트럭 하나씩 다리에 태워 보내기
- [x] 1. 다리를 배열로 구현하여(다리 길이만큼 배열길이 생성) 현재 다리의 트럭들을 한칸씩 앞으로 이동
- [x] 1. 그리고 조건을 만족한다면(무게제한, 트럭 수 제한) 큐에서 제일 앞 트럭을 다리에 태우기를 반복
- [x] 2. 다리도 queue로 구현하여 다리 길이(w)만큼 0을 집어넣는다
- [x] 2. 마찬가지로 queue에서 하나씩 꺼내는 메소드
- [x] 2. 조건을 만족한다면 큐에서 제일 앞 트럭을 다리 queue에 태우기

### 💻코드

```java
// 1초마다 앞으로 한칸씩 땡기고 조건 맞으면 다리에 트럭 올리고 반복
while(!truckQueue.isEmpty()) {
	unitTime++;
	movingForwardTruck();
	ridingOnBridge();
}

/* 다리를 queue로 구현한 케이스 */
private static void movingForwardTruck() {
	if(!bridgeQueue.isEmpty()) {
		int nextTruck = 0;
		if(bridgeQueue.peek() != null) nextTruck = bridgeQueue.peek();
		currentBridgeWeight -= bridgeQueue.poll();
		if(nextTruck != 0) currentBridgeTruckNumber--;
	}
}

private static void ridingOnBridge() {
	int nextTruck = 0;
	if(truckQueue.peek() != null) nextTruck = truckQueue.peek();			
	if(nextTruck + currentBridgeWeight <= L && currentBridgeTruckNumber <= w) {
		bridgeQueue.offer(truckQueue.poll());
		currentBridgeWeight += nextTruck;
		currentBridgeTruckNumber++;
	} else {
		bridgeQueue.offer(0);
	}
}

/* 다리를 배열로 구현한 풀이 */
private static void movingForwardTruck() {
	for(int i = 0; i < bridgeState.length; i++) {
		if(i == 0 && bridgeState[i] != 0) {
			currentBridgeWeight -= bridgeState[i];
			bridgeState[i] = 0;
		}
		if(bridgeState[i] != 0) {
			bridgeState[i-1] = bridgeState[i];
			bridgeState[i] = 0;
		}
	}
}

private static void ridingOnBridge() {
	int nextTruck = truckQueue.peek();
	if(nextTruck + currentBridgeWeight <= L && bridgeState[w - 1] == 0) {
		bridgeState[w - 1] = truckQueue.poll();
		currentBridgeWeight += nextTruck;
	}
}
```

### 🙄 후기

queue만 떠올리면 문제자체는 어렵지않은데.. <br>
오히려 다리는 배열로 다루는게 더 빠르더라(미세하지만) <br>
queue 사용에 익숙해지는것에 포커스를 두자, 그리고 배열 길이처럼 queue도 길이를 선언할까 싶었는데 길이만큼 0으로 채워넣는 아이디어는 생각못했다