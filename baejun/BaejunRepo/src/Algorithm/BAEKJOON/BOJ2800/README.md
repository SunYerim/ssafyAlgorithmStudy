<h3 align="center"> 
    📢  [골드5] 백준(괄호 제거) : https://www.acmicpc.net/problem/2800
</h3>

<br>

## 🚀 문제

어떤 수식이 주어졌을 때, 괄호를 제거해서 나올 수 있는 서로 다른 식의 개수를 계산하는 프로그램을 작성하시오. <br>
이 수식은 괄호가 올바르게 쳐져 있다. 예를 들면, 1+2, (3+4), (3+4*(5+6))와 같은 식은 괄호가 서로 쌍이 맞으므로 올바른 식이다. <br>
하지만, 1+(2*3, ((2+3)*4 와 같은 식은 쌍이 맞지 않는 괄호가 있으므로 올바른 식이 아니다. <br>
괄호를 제거할 때는, 항상 쌍이 되는 괄호끼리 제거해야 한다. <br>
예를들어 (2+(2*2)+2)에서 괄호를 제거하면, (2+2*2+2), 2+(2*2)+2, 2+2*2+2를 만들 수 있다. 하지만, (2+2*2)+2와 2+(2*2+2)는 만들 수 없다. 그 이유는 쌍이 되지 않는 괄호를 제거했기 때문이다. <br>
어떤 식을 여러 쌍의 괄호가 감쌀 수 있다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 음이 아닌 정수로 이루어진 수식이 주어진다. 이 수식은 괄호가 올바르게 쳐져있다. 숫자, '+', '*', '-', '/', '(', ')'로만 이루어져 있다. 수식의 길이는 최대 200이고, 괄호 쌍은 적어도 1개, 많아야 10개이다. 
- 올바른 괄호 쌍을 제거해서 나올 수 있는 서로 다른 식을 사전 순으로 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 괄호쌍을 찾는데, 이 문제에선 괄호쌍 단위로 제거를 하므로, 괄호쌍 인덱스 저장
- 괄호쌍 인덱스를 순회하며, 서로 다른 수식 만들어내기
- 사전순 정렬 후 출력

- [x] combination을 구현해 서로 다른 수식 생성
- [x] Collection.sort로 사전 순 정렬

### 💻코드

```java
/* 괄호쌍 pair를 인덱스번호로 저장 */
private static void findPairsOfBracket() {
	int[] leftBracket = new int[10];
	int leftBracketIndex = -1;
	for(int i = 0; i < expressionArr.length; i++) {
		if (expressionArr[i].equals("(")) {
			leftBracket[++leftBracketIndex] = i;
		} else if (expressionArr[i].equals(")")) {
			int[] arr = {leftBracket[leftBracketIndex], i};
			pairsOfBracket.add(arr);
			leftBracket[leftBracketIndex--] = 0;
		}
	}
}

/* nCr 형태로 combination 메서드 호출 */
private static void makeDifferentExpression() {
	for(int i = 1; i <= pairsOfBracket.size(); i++) {
		combination(pairsOfBracket, visited, 0, i);
	}
}

/* 재귀호출로 숫자 선정하여 조건에 맞는 수식 중복체크 후 생성 */
private static void combination(ArrayList<int[]> arrList, boolean[] visited, int start, int r) {
	if(r == 0) {
		ArrayList<Integer> checkedBracket = new ArrayList<>();
		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				checkedBracket.add(pairsOfBracket.get(i)[0]);
				checkedBracket.add(pairsOfBracket.get(i)[1]);
			}
		}
		String newExpression = new String();
		for(int i = 0; i < expressionArr.length; i++) {
			if(!checkedBracket.contains(i)) {
				newExpression += expressionArr[i];
			}
		}
		if(!differentExpression.contains(newExpression)) {
			differentExpression.add(newExpression);
		}
		
	} else {
		for(int i = start; i < pairsOfBracket.size(); i++) {
			visited[i] = true;
			combination(pairsOfBracket, visited, i + 1, r - 1);
			visited[i] = false;
		}
	}
}
```

### 🙄 후기
소요시간 : 2시간 30분 <br>

괄호쌍 페어를 만드는건 쉬웠고 오히려 서로 다른 수식을 생성하는 파트에서 한참 걸렸다 <br>
조합인걸 뒤늦게 알았고 조합 구현도 쩔쩔매서 결국 조합구현은 인터넷 참고를 했다 <br>
조합, 순열 이런 기초적인거부터 똑바로 할줄알아야 그 위에 문제를 해결할 수 있다...