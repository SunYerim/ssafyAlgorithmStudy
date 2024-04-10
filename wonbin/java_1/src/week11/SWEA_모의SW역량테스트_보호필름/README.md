# [SWEA][모의SW역량테스트] 보호필름 (AC)

---

> **문제 설명**
> 

[[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu#none)]

[문제 내용 전체]

성능이 우수한 보호 필름을 제작하려고 한다.

보호 필름은 [Fig. 1]와 같은 엷은 투명한 막을 D장 쌓아서 제작된다.

막은 [Fig. 1]과 같이 동일한 크기를 가진 바(bar) 모양의 셀들이 가로 방향으로 W개 붙여서 만들어진다.

이렇게 제작된 필름은 두께 D, 가로 크기 W의 보호 필름이라고 한다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV5V27Z6AcQDFAWu

**[Fig. 1]**

각 셀들은 특성 A 또는 특성 B를 가지고 있다. 보호 필름의 성능은 셀들의 특성이 어떻게 배치됨에 따라 결정된다.

[Fig. 1]은 셀 6개를 이어서 만든 막의 단면이다.

[Fig. 2]는 셀 8개로 이루어진 엷은 막 6장을 쌓아서 만든 두께 6, 가로크기 8인 보호 필름의 단면이다.  A, B는 각 셀들이 가진 특성 A, 특성 B를 의미한다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV5V3FoaAcYDFAWu

**[Fig. 2]**

보호 필름의 성능을 검사하기 위해 합격기준 K라는 값을 사용한다.

충격은 보호 필름 단면의 세로 방향으로 가해지므로, 세로 방향 셀들의 특성이 중요하다. (충격방향은 [Fig. 3]의 빨간색 화살표 참조)

**단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능검사를 통과하게 된다.**

[Fig. 3]과 같이 보호 필름의 단면이 주어지고 합격기준 K값이 3으로 주어지는 경우를 생각해 보자.(예제 입력 1번과 동일)

세로방향 ①, ②, ③, ⑤, ⑥, ⑦, ⑧번 위치에는 동일한 특성을 지닌 셀이 3개 이상 연속적으로 있다. ([Fig. 3]의 빨간색 사각형 참조)

하지만 ④번 위치는 동일한 특성을 지닌 셀이 3개 이상 연속적으로 있지 않으므로 성능검사를 통과할 수 없다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV5V2ME6AbYDFAWu

**[Fig. 3] 보호 필름 단면의 초기상태**

성능검사에 통과하기 위해서 약품을 사용하여야 한다.

약품은 막 별로 투입할 수 있으며 이 경우 투입하는 막의 모든 셀들은 하나의 특성으로 변경된다.

특정 막에 약품 A를 투입하면 막 내의 모든 셀들이 특성 A로 변경되며, 약품 B를 넣게 되면 특성이 모두 특성 B로 변경된다.

[Fig. 4]는 세 번째 막에 약품 A를 투입하여 특성 A로 변경하고, 여섯 번째 막에 약품 B를 투입하여 특성 B로 변경시킨 경우이다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV5V2UD6AbcDFAWu

**[Fig. 4] 3번째 막을 특성A로, 6번째 막을 특성B로 변경**

약품 투입횟수 두 번으로 ①~⑧번까지의 모든 세로방향에 대해서 동일한 특성의 셀들이 연속적으로 3개 이상 있기 때문에 성능검사를 통과하였다. (합격기준 K=3)

[Fig. 3]의 경우 약품을 투입하여 성능검사를 통과시키는 방법은 여러 방법이 있을 수 있지만 투입횟수의 최소값은 2이다.

따라서 성능검사를 통과하기 위한 최소 약품투입 횟수는 2가 된다.

두께 D, 가로크기 W인 보호 필름 단면의 정보와 합격기준 K가 주어졌을 때, 약품 투입 횟수를 최소로 하여 성능검사를 통과할 수 있는 방법을 찾고,

이때의 약품 투입 횟수를 출력하라. ([Fig. 3] 예제의 경우 정답은 2가 된다.)

약품을 투입하지 않고도 성능검사를 통과하는 경우에는 0을 출력한다.

---

> **제한사항**
> 

입력/출력 제한사항

**[제약사항]**

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 5초

2. 보호 필름의 두께 D는 3이상 13이하의 정수이다. (3≤D≤13)

3. 보호 필름의 가로크기 W는 1이상 20이하의 정수이다. (1≤W≤20)

4. 합격기준 K는 1이상 D이하의 정수이다. (1≤K≤D)

5. 셀이 가질 수 있는 특성은 A, B 두 개만 존재한다.

**[입력]**

첫 줄에 총 테스트 케이스의 개수 T가 주어진다.

두 번째 줄부터 T개의 테스트 케이스가 차례대로 주어진다.

각 테스트 케이스의 첫 줄에는 보호 필름의 두께 D, 가로크기 W, 합격기준 K가 차례로 주어진다.

그 다음 D줄에 보호 필름 단면의 정보가 주어진다. 각 줄에는 셀들의 특성 W개가 주어진다. (특성A는 0, 특성B는 1로 표시된다.)

**[출력]**

테스트 케이스의 개수만큼 T줄에 T개의 테스트 케이스 각각에 대한 답을 출력한다.

각 줄은 “#x”로 시작하고 공백을 하나 둔 다음 정답을 출력한다. (x는 1부터 시작하는 테스트 케이스의 번호이다)

출력해야 할 정답은 성능검사를 통과할 수 있는 약품의 최소 투입 횟수이다. 약품을 투입하지 않고도 성능검사를 통과하는 경우에는 0을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

문제를 보고 부분집합을 사용해야하는구나 라는 것은 빠르게 깨달았다.

하지만 구현을 하는 것이 생각보다 까다로웠다. 여러번 시도해 보다가 다른 풀이를 참조해서 해결 하였다.

1. 약품을 주입할 1차원 리스트를 생성하였다.
2. 부분집합을 구현해서 약품을 안넣은 경우를 -1, a 약품을 넣은 경우를 0, b약품을 넣은 경우를 1로 두고 부분집합을 구성하였다.
3. 세로로 탐색해서 위와 아래를 비교하는데 두 위치에 약품을 넣었나 안넣었나를 확인 후 둘의 성분이 같으면 개수를 카운트해서 K보다 작으면 false 크면 true를 반환해서 최솟값을 구하였다.

---

> **코드**
> 

```java
package week11.SWEA_모의SW역량테스트_보호필름;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_보호필름 {
	
	static int D;
	static int W;
	static int K;
	static int[][] map;
	static int[] inject;
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			D = Integer.parseInt(s2[0]); // 세로
			W = Integer.parseInt(s2[1]); // 가로
			K = Integer.parseInt(s2[2]);
			map = new int[D][W];
			inject = new int[D];
			answer = K;
			
			for(int i=0;i<D;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			subs(0,0);
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}

	static boolean check() {
		for (int w = 0; w < W; w++) {
			int cnt = 1;
			for (int d = 0; d < D - 1; d++) {
				int curr = inject[d] == -1 ? map[d][w] : inject[d]; // -1 이면 그대로 아니면 주입된 약물과 비교
				int next = inject[d + 1] == -1 ? map[d + 1][w] : inject[d + 1]; // -1 이면 그대로 아니면 주입된 약물과 비교
				if (curr == next) { // 만약 위와 아래가 같다면
					cnt++;
					if (cnt >= K)
						break;
				} else {
					cnt = 1;
				}
			}
			if (cnt < K)
				return false;
		}
		return true;

	}
	
	static void subs(int row, int cnt) {
		if (cnt >= answer)
			return;
		if (row == D) {
			if (check())
				answer = Math.min(answer, cnt);
			return;
		}
		inject[row] = -1;// 그대로
		subs(row + 1, cnt);
		inject[row] = 0;// A투입
		subs(row + 1, cnt + 1);
		inject[row] = 1;// B투입
		subs(row + 1, cnt + 1);
	}
	
	
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 부분집합을 연습할 수 있는 좋은 문제이다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 반 / 287 ms / 32,168 kb / 2,017

알고리즘 분류

- 부분집합
- 백트레킹