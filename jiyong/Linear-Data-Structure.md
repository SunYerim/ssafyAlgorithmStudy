# **Linear Data Structure**

> **Index**
>
> 0. **[시간 복잡도(Time Complexity)](#time-complexity)**
> 1. **[선형 자료구조(Linear Data Structure)](#linear-data-structure)**
> 2. **[배열(Array)](#array)**
> 3. **[순차 리스트(Sequential List)](#sequential-list)**
> 4. **[연결 리스트(Linked List)](#linked-list)**
> 5. **[환형 리스트(Circular List)](#circular-list)**
> 6. **[스택(Stack)](#stack)**
> 7. **[큐(Queue)](#queue)**
> 8. **[양방향 큐(Deque)](#deque)**

## **시간 복잡도(Time Complexity)** <a id="time-complexity" />

> **시간복잡도란?**

- 컴퓨터과학에서 시간복잡도는 문제를 해결하는데 걸리는 시간과 입력의 함수 관계를 가리킨다.
- 알고리즘의 시간복잡도는 주로 Big-O 표기법을 사용하여 나타내며, 계수와 낮은 차수의 항을 제외시키는 방법이다(점근적 묘사라고도 표현).

<table><tr><td>
<img src=https://www.researchgate.net/publication/371457259/figure/fig3/AS:11431281166794429@1686405800300/Big-O-time-complexity-chart-based-feature-selection-a-Big-O-complexity-chart-b-Time.ppm height="250px"/>
</td><td>
<img src=https://miro.medium.com/max/4080/1*Uzrw9faXdYgg20I6NjUTBw.png height="250px"/>
</td></tr>
<tr>
<td align="center"><span>[그림 1]</span></td>
<td align="center"><span>[그림 2]</span></td>
</tr>
</table>

- [그림 1]은 시간복잡도를 시각화한 그래프이고, [그림2]는 여러 자료구조에서의 연산에 대한 시간복잡도를 정리한 표이다.
- **알고리즘을 공부한다면 늘 시간복잡도를 계산하는 습관을 들여야 한다.**

###

---

## **선형 자료구조(Linear Data Structure)** <a id="linear-data-structure" />

> **자료구조란?**

- 디지털 컴퓨터에서 모든 정보는 0과 1이 근간이 되는데, 이 자체로는 인간이 해석하기 어렵다.
- 자료구조는 데이터를 해석함에 있어서 틀과 규칙, 나아가 효율적인 데이터 처리를 위한 함수나 명령을 의미한다. 즉, 흔히 아는 숫자, 문자부터 배열, 리스트, 객체 등이 모두 자료구조이다.
<div align="center">
<img src="https://haileyjohj.github.io/assets/images/data-structure.png" height="300px"/>
</div>

- 자료구조를 잘 활용하는 것이 시간복잡도를 줄이는 데에 많은 영향을 준다.

###

> **선형 자료구조**

- 선형 자료구조란 다수 개의 데이터가 선형을 이루는 구조로, 이웃한 데이터끼리 1:1의 연관관계로 묶인 데이터들의 집합이다.
- 구조체로는 순차 리스트와 연결 리스트가 있고, 추상 구조로는 스택, 큐, 양방향 큐가 있다.
- [그림 2]와 같이 각각의 구조는 저마다의 장단점이 있고, 상황에 맞게 잘 사용하는 것이 중요하다.

###

---

## **배열(Array)** <a id="array"/>

> **배열의 구조**

<div align="center">
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20210322122448/1DDynamicArray.png" height="200px">
</div>

- 배열은 <u>동일한 타입</u>의 데이터를 <u>연속된 메모리 공간</u>에 적재하는 자료구조이다.
- 연속된 메모리 공간에 할당해야 하므로, 배열의 길이는 할당과 함께 결정되고, 불변한다.
- 배열을 담고 있는 변수는 배열의 head를 참조하는 포인터 값을 가진다.

- 동일한 타입의 데이터를 담고 있기 때문에, head 주소로부터 주소값을 계산하여 참조할 수 있다(Random Access).
- 공간 지역성에 의해 Cache Hit Rate가 높다.

###

> **배열의 단점**

- 배열은 불변의 길이로 이루어져 있기 때문에, 데이터를 직접 삭제할 수 없다(해당 데이터를 다른 값으로 대체하기만 가능).
- 이미 배열의 모든 공간에 데이터를 넣었다면, 더 이상 데이터를 담을 수 없다.
- 배열만으로 데이터 집합을 관리하려면 그에 알맞는 함수를 프로그램에서 구현해주어야 한다.
- **이처럼 배열에서 CRUD 연산을 할 때, 여러 상황을 고려하여야 한다.**

###

---

## **순차 리스트(Sequential List)** <a id="sequential-list"/>

> **순차 리스트란?**

- 배열의 장점을 취하고 불편한 점을 개선하기 위해, 내부적으로 배열을 활용한 구조체이다.
- 여러 연산을 함수적으로 미리 구현해두었다.
- Java의 ArrayList, Python의 List 클래스가 이에 해당.
  - 주의할 점 : 두 언어 모두 객체지향 언어로, 실제 순차 리스트의 데이터는 연속된 메모리에 적재되긴 하지만 원소로 모두 객체만을 담기 때문에 실제로는 참조값이 담겨져 있고 <u>참조되는 객체는 불연속적인 주소</u>를 가짐.

> 순차 리스트의 한계

- 빈 원소(Element)를 허용하지 않기 때문에, `삭제` 연산이 이후 빈 공간을 메우기 위해 `Shift`연산이 발생(오버헤드).
- 또한, `삽입` 연산에도 연속된 메모리 공간에서 순서성을 보장하기 위해 `Shift`연산 발생(오버헤드).
- 순차 리스트는 가변길이처럼 보이게 작동하나 실제로는 배열을 활용하기 때문에, `데이터 > 길이`인 경우, 더 긴 배열을 할당하는 오버헤드 발생 / `데이터 < 길이`인 경우, 메모리 낭비 문제가 있음.
- 즉, `삽입` / `삭제`가 빈번한 경우에는 부적합.

---

## **연결 리스트(Linked List)** <a id="linked-list"/>

> **연결 리스트란?**

<div align="center">
<img src="https://velog.velcdn.com/images%2F717lumos%2Fpost%2Fb38d2c1e-7878-4b17-93b1-07cc0b99096a%2F%EB%A6%AC%EC%8A%A4%ED%8A%B82.png" height="200px"/>
</div>

- 데이터와 다음 원소의 참조값을 가지는 **노드**라는 구조를 활용하여 리스트를 구현한 구조체.
- 단방향으로만 연결된 단일(Singly) 연결 리스트와 양방향으로 연결된 이중(Doubly) 연결 리스트가 있다.
- 참조값 포인터를 통하여 연결한 구조라서, 메모리에 연속적으로 적재되지 않는다.
- `삽입` / `삭제` 연산에서 연결된 참조값만 바꾸는 방식으로 작동하기에 오버헤드가 발생하지 않는다.
- 메모리에 불연속적으로 적재되므로 Random Access가 불가능하고, Sequential Access만 가능하다(조회가 느림).

> 단방향 vs 양방향

|                |           단일 연결 리스트           |         이중 연결 리스트         |
| :------------: | :----------------------------------: | :------------------------------: |
| 메모리 사용량  |           **비교적 적게**            |           비교적 많이            |
|  역방향 탐색   |          처음부터 다시 순회          |     **prev 참조값으로 순회**     |
|      구현      |              **간단함**              |              복잡함              |
| 중간 원소 삭제 | 이전 노드를 알기 위해 추가 탐색 필요 | **이전 노드 정보를 가지고 있음** |
| |각각의 Trade-off를 고려하여 용도에 맞게 사용! ||



###


---

## **환형 리스트(Circular List)** <a id="circular-list"/>

> **환형 리스트란?**

<div align="center">
<img src="https://hsue66.github.io/assets/img/postimg/124circular.png" height="200px"/>
</div>

- 원형 리스트라고도 불리며, 기본적으로 연결 리스트로 구현.
- 연결 리스트의 마지막 노드가 `null`이 아닌 처음 노드를 참조.
- 마지막 노드와 처음 노드가 물리적으로 연결된 구조라서, 한 방향으로 순회해도 모든 노드를 탐색할 수 있음.
- 순환 구조 외에는 연결 리스트와 별다른 차이점이 없어서 특정 상황에서만 유리하게 작용.

---

## **스택(Stack)** <a id="stack"/>
> **스택이란?**

<div align="center">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fm3XOD%2FbtrAgozacDI%2FfRqJAKfWAaK9iwfEKIkdyk%2Fimg.png" height="200px"/>
</div>

- 리스트의 한 쪽 끝에서만 `삽입` / `삭제` 연산이 허용되는 자료구조
- 마지막에 `삽입`된 원소가 먼저 `삭제`되므로 LIFO(Last-In-First-Out) 혹은 후입선출 구조라고 표현한다.
- 대부분의 상황에서는 리스트 마지막 원소의 `삽입` / `삭제` 연산이 `O(1)`인 순차 리스트가 구현체로 적합하다.
- `재귀(Recursive)`, `깊이 우선 탐색(DFS)`과 같은 알고리즘이 스택을 활용한다.

---

## **큐(Queue)** <a id="queue"/>
> **큐란?**

<div align="center">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5NOv1%2FbtqSTINnoq8%2F4f8bjzzf6W4POewlq8At31%2Fimg.png" height="200px"/>
</div>

- 리스트의 한 쪽 끝에서는 `삽입` 연산, 다른 쪽 끝에서는 `삭제` 연산만 허용되는 자료구조
- 먼저 `삽입`된 원소부터 순서대로 `삭제`되므로 FIFO(First-In-First-Out) 혹은 선입선출 구조라고 표현한다.
- 리스트 첫 원소의 `삽입` / `삭제` 연산이 `O(1)`인 연결 리스트가 구현체로 적합하다.
- `스케쥴링`, `너비 우선 탐색(BFS)`과 같은 알고리즘이 큐를 활용한다.
- 환형 리스트를 구현체로 사용할 수 있다. 이는 환형 큐라고 한다.
- 또, 힙(heap) 트리를 구현체로 사용할 경우에는 우선순위 큐를 구현할 수 있다.


---

## **양방향 큐(Deque)** <a id="deque"/>
> **양방향 큐란?**

<div align="center">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fen6hR1%2Fbtq9Tfup4CL%2FEVq1da8ijs5ki85MzkG631%2Fimg.png" height="200px"/>
</div>

- Double-Ended Queue의 줄인말이다.
- 리스트의 양쪽 끝 모두에서 `삽입` / `삭제` 연산이 허용되는 자료구조
- 이 또한, 큐와 같이 연결리스트로 구현하는 것이 적합한데, 특히나 이중 연결 리스트로 구현하는 것이 가장 어울린다.
- 양방향에서 입출력을 허용하기에 다양한 문제에 범용적으로 사용할 수 있으나, 이중 연결 리스트의 단점을 그대로 가지고 있어 유의하며 사용해야 한다.
