<h3 align="center"> 
    📢  [A형대비] SWEA(숫자 만들기) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH
</h3>

<br>

## 🚀 문제

선표는 게임을 통해 사칙 연산을 공부하고 있다.
N개의 숫자가 적혀 있는 게임 판이 있고, +, -, x, / 의 연산자 카드를 숫자 사이에 끼워 넣어 다양한 결과 값을 구해보기로 했다.
수식을 계산할 때 연산자의 우선 순위는 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산한다.
예를 들어 1, 2, 3 이 적힌 게임 판에 +와 x를 넣어 1 + 2 * 3을 만들면 1 + 2를 먼저 계산하고 그 뒤에 * 를 계산한다.
즉 1+2*3의 결과는 9이다.

주어진 연산자 카드를 사용하여 수식을 계산했을 때 그 결과가 최대가 되는 수식과 최소가 되는 수식을 찾고, 두 값의 차이를 출력하시오.

---

## 🚦입출력 + 제한사항

1. 시간 제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초
2. 게임 판에 적힌 숫자의 개수 N 은 3 이상 12 이하의 정수이다. ( 3 ≤ N ≤ 12 )
3. 연산자 카드 개수의 총 합은 항상 N - 1 이다.
4. 게임 판에 적힌 숫자는 1 이상 9 이하의 정수이다.
5. 수식을 완성할 때 각 연산자 카드를 모두 사용해야 한다..
6. 숫자와 숫자 사이에는 연산자가 1 개만 들어가야 한다.
7. 완성된 수식을 계산할 때 연산자의 우선 순위는 고려하지 않고, 왼쪽에서 오른쪽으로 차례대로 계산한다.
8. 나눗셈을 계산 할 때 소수점 이하는 버린다.
9. 입력으로 주어지는 숫자의 순서는 변경할 수 없다.
10. 연산 중의 값은 -100,000,000 이상 100,000,000 이하임이 보장된다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 처음엔 순열을 돌려야겠다 했는데, 연산자 개수에 맞춰서 연산자를 저장한 캐릭터배열을 만들고 그걸 돌림
- 시간초과가 뜸. 결과값이 중복인것에 대한 처리를 못해줬기 때문
- 지용이가 알려줘서 그냥 연산자 4개 중복순열을 돌리는데, 내가 보유중인 연산자 개수에 벗어나는 애들은 가지치기 해줘서 풀림

- [x] 중복순열 활용해 연산자 경우의 수 추출

### 💻코드

```java
private static void permutation(int start) {
	// 기저조건
	if(start == N-1 && operCount[0] >= 0 && operCount[1] >= 0 && operCount[2] >= 0 && operCount[3] >= 0) {
		int numbersIdx = 0;
		int sum = numbers[0];
		for(char op : ops) {
			switch(op) {
			case '+':
				sum += numbers[++numbersIdx];
				break;
			case '-':
				sum -= numbers[++numbersIdx];
				break;
			case '*':
				sum *= numbers[++numbersIdx];
				break;
			case '/':
				sum /= numbers[++numbersIdx];
				break;
			}
		}
		if(sum > maxValue) maxValue = sum;
		if(sum < minValue) minValue = sum;
		return;
	}
	//유도조건
	for(int i = 0; i < 4; i++) {
		if(operCount[i] == 0) continue;
		ops[start] = oper[i];
		operCount[i]--;
		permutation(start+1);
		operCount[i]++;
	}
}
```

### 🙄 후기
소요시간 : 2시간  <br>
화..화이팅<br>
똑같은 문제 나오면 풀수있겠다