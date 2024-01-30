<h3 align="center"> 
    📢  [실버1] 백준(IOIOI) : https://www.acmicpc.net/problem/5525
</h3>

<br>

## 🚀 문제

N+1개의 I와 N개의 O로 이루어져 있으면, I와 O이 교대로 나오는 문자열을 PN이라고 한다.
- P1 IOI
- P2 IOIOI
- P3 IOIOIOI
- PN IOIOI...OI (O가 N개)
I와 O로만 이루어진 문자열 S와 정수 N이 주어졌을 때, S안에 PN이 몇 군데 포함되어 있는지 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 N이 주어진다. 둘째 줄에는 S의 길이 M이 주어지며, 셋째 줄에 S가 주어진다.
- S에 PN이 몇 군데 포함되어 있는지 출력한다.
- 1 ≤ N ≤ 1,000,000
- 2N+1 ≤ M ≤ 1,000,000
- S는 I와 O로만 이루어져 있다.

- 서브태스크
번호	배점	제한
1	50	N ≤ 100, M ≤ 10 000.

2	50	
추가적인 제약 조건이 없다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 조건을 보면 알겠지만, 완탐으론 서브태스크 2번 통과가 택도 없음
- 그래서 M길이 만큼 순회하여 푸는 로직을 생각해내서 풀어봄
- 망할 시간초과가 계속 떠서 고민을 해보니
- M길이만큼 순회할꺼면 그 안엔 거의 O(1)짜리 연산밖에 없다는 말인데..
- 그래서 좀 자세히 보니, Pn이랑 S의 부분집합이 일치하는지 확인할때 O(N) 연산이 발생(N길이가 100만이라 시간초과 날수밖에없음)
- 그래서 또 고민해보니, S의 부분집합 자체가 패턴이 깨지면 초기화를 시키기 때문에, Pn과 부분집합의 길이가 같다는거 자체가 둘이 같다는걸 뜻한다는걸 알게됨
- 통과!

- [x] charArray를 통해 오버헤드 최소화(String 연산에 비해 빠름)

### 💻코드

```java
/* M길이 만큼 배열 탐색, Pn 규칙에 안맞는다면 즉시 초기화 */
while(sentenceArrLength >= 0) {
	if(strList.size() == 0) {
		if((peek = sentenceArr[sentenceArrLength--]) == 'I') {
			strList.add(peek);
		}
	} else {
		char strLastIndex = strList.get(strList.size() - 1);
		char stackPeekValue = sentenceArr[sentenceArrLength--];
		if(strLastIndex == stackPeekValue) {
			strList.clear();
			if(stackPeekValue == 'I') {
				strList.add(stackPeekValue);
			}
		} else {
			strList.add(stackPeekValue);
		}
	}
	if(P.size() == strList.size()) {
		answer++;
		strList.remove(strList.size() - 1);
		strList.remove(strList.size() - 1);
	}
}
```

### 🙄 후기
소요시간 : 2시간  <br>

후후.. 시간초과 해결하는거에 꽤나 애를 먹었다 <br>
맨날 풀고 맞추는데에만 급급하고 효율은 개나줘버리던 나에겐 꽤나 귀한 경험이였다. <br>
시간복잡도를 계산하고 어느 코드에서 시간복잡도의 초과를 발생시키는지 이해하고 짜는게 얼마나 중요한지 알 수 있었다!