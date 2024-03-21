# [Softeer][level3] 자동차테스트 / (TLE)

---

> **문제 설명**
> 

[[문제 링크](page:blank;)]

[문제 내용 전체]

자동차 제조 과정에서는 다양한 테스트를 통해 해당 자동차가 잘 만들어졌는지를 평가합니다.

이러한 평가 지표 중 하나는 자동차의 연비입니다.

자동차의 연비가 높을수록 연료 소비가 적고, 더 많은 거리를 주행할 수 있으므로 이는 자동차가 잘 만들어졌는지의 지표로 사용될 수 있습니다.

만약 3대의 자동차를 테스트하고, 각각의 연비를 측정한다고 가정해봅시다.

첫 번째 자동차의 연비는 9km/L,

두 번째 자동차의 연비는 15km/L,

세 번째 자동차의 연비는 20km/L이라고 합시다.

이 경우, 중앙값은 15km/L이 됩니다.

따라서 이 데이터에서는 중앙값을 이용하여 자동차의 평균적인 연비를 파악할 수 있으며,

이는 자동차 제조 과정에서 테스트의 결과를 평가하는 데 활용될 수 있습니다.

n대의 자동차를 새로 만들었지만 여건상 3대의 자동차에 대해서만 테스트가 가능한 상황입니다.

n대의 자동차의 실제 연비 값이 주어졌을 때, q개의 질의에 대해 임의로 3대의 자동차를 골라 테스트하여 중앙값이 mi값이 나오는 서로 다른 경우의 수를 구하는 프로그램을 작성해보세요.

---

> **제한사항**
> 

입력/출력 제한사항

**제약조건
* 1 ≤ n ≤ 50,000
* 1 ≤ q ≤ 200,000
* 1 ≤ 자동차의 연비 ≤ 1,000,000,000
* 1 ≤ mi ≤ 1,000,000,000 (i는 1 이상 q 이하입니다. 즉, mi 는 각 질의에 대응하는 중앙값을 의미합니다.)**

**입력형식
첫 번째 줄에 n, q가 공백을 사이에 두고 주어집니다.

두 번째 줄에는 n개의 자동차의 연비에 해당하는 값이 공백을 사이에 두고 주어집니다.
주어지는 자동차의 연비는 서로 다름을 가정해도 좋습니다.

세 번째 줄부터는 q개의 줄에 걸쳐 테스트 결과로 기대하는 값인 mi가 한 줄에 하나씩 주어집니다.**

**출력형식
q개의 줄에 걸쳐 3대의 자동차를 골랐을 때 연비의 중앙값이 mi가 나오는 서로 다른 가짓수를 한 줄에 하나씩 출력합니다.**

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

분명 이분탐색을 활용해서 리스트에 포함되었는지 확인하고 단순 계산으로 한번만 반복했는데 시간 초과가 떴다. 아마 내가 짠 이분탐색에서 문제가 생긴거 같다.

---

> **코드**
> 

```java
package week8.Softeer_HSAT7회정기코딩인증평가기출_자동차테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Softeer_HSAT7회정기코딩인증평가기출_자동차테스트 {
	static int N;
	static int Q;
	static ArrayList<Integer> car;
	static int middle;
	static int[] answer;
	static int[] path;
	
	 public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			Q = Integer.parseInt(s2[1]);
			car = new ArrayList<>();
			answer = new int[Q];
			
			s = br.readLine();
			s2 = s.split(" ");
			
			for(int i=0;i<N;i++) {
				car.add(Integer.parseInt(s2[i]));
			}

			Collections.sort(car);
			
			for(int i=0;i<Q;i++) {
				int tmp = Integer.parseInt(br.readLine());
				
				int cnt = car.indexOf(tmp);
				
				if(binarySearch(0, car.size(), cnt) == 1) {
					int a1 = cnt;
					int a2 = car.size() - cnt - 1;
					if(cnt == 0)
						a1 = 0;
					if(cnt == car.size() - 1)
						a2 = 0;
					
					answer[i] = a1 * a2;
				}
				else
					answer[i] = 0;
				
			}
			
			for(int i=0;i<Q;i++) {
				bw.append(answer[i]+"");
				bw.append("\n");
			}
			bw.flush();
			bw.close();
	    }

	 static int binarySearch(int start, int end, int target) {
		 
		 while(start <= end) {
			 
			 int mid = (start + end) / 2;
			 
			 if(target == mid)
				 return 1;
			 else if(target > mid)
				 start = mid +1;
			 else if(target < mid)
				 end = mid -1;
		 }
		 
		 return 0;
		 
	 }

}

```

정답코드

```java
import java.io.*;
import java.util.*;

public class Main {

    static int n, q;
    static int [] info;
    static Map <Integer, Integer> isExist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        isExist = new HashMap<>();
        info = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) info[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(info);
        for (int i = 0; i < n; i++) isExist.put(info[i], i);
        for (int i = 0; i < q; i++) {
            int number = Integer.parseInt(br.readLine());
            if(isExist.get(number)!=null) sb.append(binarySearch(number)).append("\n"); 
            else sb.append(0).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int binarySearch(int number) {
        
        int l = 0;
        int h = n-1;
        while (l <= h) {
            int mid = (l+h)/2;
            if (info[mid] == number) {
                if(mid==0||mid==n-1)return 0;
                return mid * (n - 1 - mid);
            }
            else if(info[mid]<number) l = mid+1;
            else if(info[mid]>number) h = mid-1;
        }
        return 0;
    }
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 시간을 줄일 수 있는 방법만 생각하면 충분히 풀 수 있다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 나머지는 틀림

알고리즘 분류

- 이분탐색