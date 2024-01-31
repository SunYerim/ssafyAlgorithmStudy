<h3 align="center"> 
    📢  [실버4] 백준(괄호) : https://www.acmicpc.net/problem/9012
</h3>

<br>

## 🚀 문제

괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.
여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다. 

---

## 🚦입출력 + 제한사항

- 입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다. 
- 출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다. 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 딱 봐도 스택 문제
- 이외의 부가적인 조건도 없어서 스택으로 풀면 간단할듯

- [x] Stack을 이용하여 처리로직 진행

### 💻코드

```java
String checkVPSString = in.readLine();
for(int i = 0; i < checkVPSString.length(); i++) {
	char parenthesis = checkVPSString.charAt(i);
	if(parenthesis == '(') {
		stack.push(parenthesis);
	} else {
		if(!stack.isEmpty() && stack.peek() == '(') {
			stack.pop();
		} else {
			stack.push(parenthesis);
		}
	}
}
if(stack.isEmpty()) {
	System.out.println("YES");
} else {
	System.out.println("NO");
}
stack.clear();
```

### 🙄 후기
소요시간 : 20분  <br>

확실히 그냥 쌩으로 문제를 보는거랑, 어떤 자료구조를 쓰는 문제인지 알고 보는거랑은 천지차이다 <br>
지금은 스택정도의 수준을 보는거지만, 이후에 훨씬 복잡한 문제에도 아이디어를 생각해낼 수 있냐 싸움이기 때문에, 쉬운거부터 차근차근 습관을 들여놓자