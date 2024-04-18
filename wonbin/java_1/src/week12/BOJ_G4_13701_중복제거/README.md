# [플랫폼][난이도] 제목 / (AC / TLE / MLE / WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/13701)]

[문제 내용 전체]

## 문제

문제: N개의 정수 A1, A2, ..., AN 을 읽고, 이들 중에서 반복되는 수를 제외하고 남은 N'개의 수 B1, B2, ..., BN’ 을 입력된 순서대로 출력하시오. 이때,

1. 0 **≤** A < 2 = 33554432, i=1,2,…,N.
    
    i
    
    25
    
2. 입력의 개수 N은 1 이상 500만 이하이다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 A1, A2, ..., AN이 주어진다.

## 출력

B1, B2, ..., BN’을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

사실 자바라서 이렇게 해결이 된거 같은데 자바가 아닌 언어로 해결할려면 비트마스킹을 사용해야 하는거 같다. 자바로는 linkedhashset을 활용해서 순서가 보장되고 중복이 허용 안되는 자료구조를 활용해서 정말 쉽게 해결 하였다.

1. LinkedHashSet을 하나 사용한다
2. 입력값을 전부 set에 넣는다
3. set을 arraylist로 변환해서 출력한다.

---

> **코드**
> 

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Main {
	
	static LinkedHashSet<Integer> set;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		set = new LinkedHashSet<>();
		
		for(int i=0;i<s2.length;i++) {
			set.add(Integer.parseInt(s2[i]));
		}
		
		list = new ArrayList<>(set);
		
		for(int i=0;i<list.size();i++) {
			bw.append(list.get(i)+""+" ");
		}
		
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 비트마스킹을 이용한 해답을 찾아봐야겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 20분 / 2936ms / 649696KB / 859B

알고리즘 분류

- [비트마스킹](https://www.acmicpc.net/problem/tag/14)
- [비트 집합](https://www.acmicpc.net/problem/tag/152)