
[백준][G1] 1060 좋은 수 / (AC)
---
> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1060)]

정수 집합 S가 주어졌을때, 다음 조건을 만족하는 구간 [A, B]를 좋은 구간이라고 한다.

- A와 B는 양의 정수이고, A < B를 만족한다.
- A ≤ x ≤ B를 만족하는 모든 정수 x가 집합 S에 속하지 않는다.

정수 x를 포함하는 좋은 구간의 개수가 정수 y를 포함하는 좋은 구간의 개수보다 작으면 x는 y보다 더 좋다고 한다. x와 y를 포함하는 좋은 구간의 개수가 같거나, 구간의 개수가 둘 다 무한대와 같은 경우, 작은 수를 더 좋다고 한다.

집합 S가 주어지고, 이를 이용해 전체 정수를 더 좋은 수가 앞으로 오게 정렬했다고 가정하자. 앞에 오는 수 n개를 구해보자.


---

> **제한사항**
> 

**입력**

첫째 줄에 집합 S의 크기 L이 주어진다. 둘째 줄에는 집합에 포함된 정수가 주어진다. 셋째 줄에는 n이 주어진다.

**출력**

상위 N개의 수를 공백으로 구분해 출력한다.

---

> **문제 풀이**

문제를 해석하는 것 자체가 어려운데, 


---

> **코드**
> 

```java
package BOJ1060;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int L = Integer.parseInt(in.readLine());
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		String[] split = in.readLine().split(" ");
		for (int i = 0; i < L; i++) {
			numbers.add(Integer.parseInt(split[i]));
		}
		int N = Integer.parseInt(in.readLine());

		Collections.sort(numbers);

		PriorityQueue<Num> queue = new PriorityQueue<>((o1, o2) -> {
			if (o1.length != o2.length) {
				return o1.length > o2.length ? 1 : o1.length < o2.length ? -1 : 0;
			}
			return o1.num - o2.num;
		});
		for (int i = 0; i < L; i++) {
			queue.offer(new Num(numbers.get(i + 1), 0));
		}

		for (int i = 0; i < L; i++) {
			for (Num num : Num.makeNum(numbers.get(i), numbers.get(i + 1))) {
				queue.offer(num);
			}
		}
		while (!queue.isEmpty() && N > 0) {
			N--;
			out.append(queue.poll().num).append(" ");
		}
		if (N > 0) {
			for (int i = 1; i <= N; i++) {
				out.append(numbers.get(L) + i).append(" ");
			}
		}
		System.out.println(out);
	}
}

class Num {
	public int num;
	public long length;

	public Num(int num, long length) {
		this.num = num;
		this.length = length;
	}

	public static List<Num> makeNum(int s, int e) {
		List<Num> result = new ArrayList<>();
		if ((e - s) > 100) {
			for (int i = 1; i <= 50; i++) {
				result.add(new Num(s + i, (long) i * (e - s - i) - 1));
				result.add(new Num(e - i, (long) i * (e - s - i) - 1));
			}
		} else {
			for (int i = 1; i < e - s; i++) {
				result.add(new Num(s + i, i * (e - s - i) - 1));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "[n:" + num + ", l:" + length + "]";
	}
}
```

---

> **후기**

`recursive` 하게 구현해야하지만 `재귀함수`로 구현한다면 최대 `100,000`번의 호출이 일어나기 때문에 `스택 오버플로우`가 발생한다는 점을 주의. 또한, `서브트리`를 나눌 때 직접 나누어 생성하면 공간복잡도가 $N^2$가 되기 때문에, 참조할 `트리 순회 순서`는 그대로 유지하고 추상적으로 `인덱스`를 이용하여 트리를 나누는 방식으로 구현하여야 MLE가 발생하지 않는다.  

문제 풀이 시간 : 2시간 / 실행시간 : `204ms` / 메모리 : `136396KB` / 코드길이 : `868B`

알고리즘 분류 : 트리, 분할정복, 재귀