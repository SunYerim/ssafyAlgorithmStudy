<h3 align="center"> 
    📢  [A형대비] SWEA(특이한 자석) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH&categoryId=AWIeV9sKkcoDFAVH&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 시간제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초
2. 자석의 개수는 4 개이며, 각 자석은 8 개의 날을 가지고 있다.
3. 자석을 회전시키는 횟수 K 는 1 이상 20 이하의 정수이다. ( 1 ≤ K ≤ 20 )
4. 하나의 자석이 1 칸 회전될 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1 칸 회전된다.
5. 자석을 회전시키는 방향은 시계방향이 1 로, 반시계 방향이 -1 로 주어진다.
6. 날의 자성은 N 극이 0 으로, S 극이 1 로 주어진다.
7. 각 자석의 날 자성정보는 빨간색 화살표 위치의 날부터 시계방향 순서대로 주어진다.
  예를 들어, [Fig. 1] 의 1 번 자석의 자성정보는 0 0 1 0 0 1 0 0 과 같이 주어진다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 그냥 단순 구현문제
- 근데 자석톱니바퀴의 수가 4개, 날이 8개로 고정 돼 있어서, 그냥 4개의 객체를 만들고 각 상황에 대한 구분을 지어 동작시켜줬다

- [x]

### 💻코드

```java
static class Magnet {
	int leftContact; // 왼쪽 자석과 접촉하는 인덱스
	int rightContact; // 오른쪽 자석과 접촉하는 인덱스
	int arrowContact; // 화살표가 가리키는 인덱스
	int direction; // 회전 방향
	int[] magnetism; // 극(N or S) 정보

	Magnet(int leftContact, int rightContact, int arrowContact) {
		this.leftContact = leftContact;
		this.rightContact = rightContact;
		this.arrowContact = arrowContact;
		magnetism = new int[8];
	}
}
```

### 🙄 후기
소요시간 : 30분 <br>
오히려 코드는 길지만 <br>
경우의 수를 나눠놓고 객체로 분리하고 메소드 분리하고 하니까, 오히려~ 다른 문제들보다 훨씬 깔끔하고 빨리 풀렸다 <br>
코드가 더러워지고 복잡해질거같다면 메소드로 분리해서 관리하는게 나중에 엣지 찾아서 수정하기에도 용이하고 좋은 듯 하다.