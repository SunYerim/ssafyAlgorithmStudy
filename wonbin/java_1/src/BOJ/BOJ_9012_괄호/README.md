# [백준][S4] 괄호/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/9012)]

[문제 내용 전체]

괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.

여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.

## 출력

출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

이번 스터디에서 스택에 대해 배웠는데 그에 대한 기본 문제 예시로 나온 문제였다.

스택을 사용해서 문자열을 받았고 만약 ‘(’ 와 ‘)’ 가 만나면 pop을 이용하여 제거하는 방식으로 문제를 해결하였다.

만약 스택이 비어있으면 YES 아니면 NO를 출력하게 작성했다.

---

> **코드**
> 

```java
package BOJ.BOJ_9012_괄호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BOJ_9012_괄호 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> stk = new Stack<>();
		
		int a = Integer.parseInt(br.readLine());
		
		for(int i=0;i<a;i++) {
			String s = br.readLine();
			stk.clear();
			
			for(int j=0;j<s.length();j++) {
				
				if(s.charAt(j) == ')') {
					if(stk.empty()) {
						stk.push(s.charAt(j));
						break;
					}
					else if(stk.peek() == '(') {
						stk.pop();
					}
				}
				else {
					stk.push(s.charAt(j));
				}
			}
			
			if(stk.empty()) {
				bw.write("YES" + '\n');
			}
			else {
				bw.write("NO" +'\n');
			}
		}
		
		bw.close();
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 스택에 대한 기본을 익힐 수 있었다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 20분 / 128ms / 14264KB / 960B

알고리즘 분류

- [자료 구조](https://www.acmicpc.net/problem/tag/175)
- [문자열](https://www.acmicpc.net/problem/tag/158)
- [스택](https://www.acmicpc.net/problem/tag/71)