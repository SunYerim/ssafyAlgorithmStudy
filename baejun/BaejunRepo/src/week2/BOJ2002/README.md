<h3 align="center"> 
    📢  [실버1] 백준(추월) : https://www.acmicpc.net/problem/2002
</h3>

<br>

## 🚀 문제

대한민국을 비롯한 대부분의 나라에서는 터널 내에서의 차선 변경을 법률로 금하고 있다. 조금만 관찰력이 있는 학생이라면 터널 내부에서는 차선이 파선이 아닌 실선으로 되어 있다는 것을 알고 있을 것이다. 이는 차선을 변경할 수 없음을 말하는 것이고, 따라서 터널 내부에서의 추월은 불가능하다.
소문난 명콤비 경찰 대근이와 영식이가 추월하는 차량을 잡기 위해 한 터널에 투입되었다. 대근이는 터널의 입구에, 영식이는 터널의 출구에 각각 잠복하고, 대근이는 차가 터널에 들어가는 순서대로, 영식이는 차가 터널에서 나오는 순서대로 각각 차량 번호를 적어 두었다.
N개의 차량이 지나간 후, 대근이와 영식이는 자신들이 적어 둔 차량 번호의 목록을 보고, 터널 내부에서 반드시 추월을 했을 것으로 여겨지는 차들이 몇 대 있다는 것을 알게 되었다. 대근이와 영식이를 도와 이를 구하는 프로그램을 작성해 보자.

---

## 🚦입출력 + 제한사항

- 입력은 총 2N+1개의 줄로 이루어져 있다. 첫 줄에는 차의 대수 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 대근이가 적은 차량 번호 목록이 주어지고, N+2째 줄부터 N개의 줄에는 영식이가 적은 차량 번호 목록이 주어진다. 각 차량 번호는 6글자 이상 8글자 이하의 문자열로, 영어 대문자('A'-'Z')와 숫자('0'-'9')로만 이루어져 있다.
같은 차량 번호가 두 번 이상 주어지는 경우는 없다.
- 첫째 줄에 터널 내부에서 반드시 추월을 했을 것으로 여겨지는 차가 몇 대인지 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- queue를 이용해서 들어온 순서대로 내보냄
- 터널에 들어오고, 들어온 순서대로 선입선출해서 나가는데? 다른놈이 먼저 나오면 걔는 추월한놈임, 큐에서 하나씩 빼면서 count

- [x] queue를 이용하여 데이터 관리

### 💻코드

```java
for(int i = 0; i < N; i++) {
	String currentCar = in.readLine();
	while(overtakingCarList.contains(queue.peek())) { // 추월차는 이미 카운팅했으니 queue에서 그냥 빼주기
		queue.poll();
	}
	if (!queue.peek().equals(currentCar)) { // 나와야될 차 말고 다른애가 나오면 추월했단 소리
		overtakingCarList.add(currentCar);
		overtakingCount++;
	} else {
		queue.poll();
	}
}
```

### 🙄 후기
소요시간 : 40분  <br>

자료구조 한주동안 바짝하고 나니, 아주 쉽게 아이디어가 떠오르고 풀렸다. <br>
실버1도 빌빌거렸었는데.. 발전하고 있다 !
