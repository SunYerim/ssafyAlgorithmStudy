<h3 align="center"> 
    📢  [레벨2] 프로그래머스(2022 KAKAO BLIND RECRUITMENT / 양궁대회) : https://school.programmers.co.kr/learn/courses/30/lessons/92342
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 동점이어도 어피치가 이기기 때문에, lion입장에선 쏠 수 있는 화살 수를 적절히 배분해야된다.
- 이기거나 지거나인데 지는 경우에 화살을 소비 할 필요가 없으므로, appeach+1발 쏴서 이기거나 0발 쏴서 지거나
- True(appeach+1) OR False(0) DFS돌려서 모든 경우의 수 탐색
- 그리고 예외적으로, 다 돌고 여분 화살이 남았다면, 동점일때 가장 적은 점수에 화살 수가 많은 것이 우선이므로, 남은 화살은 다 0점에 투자

-

### 💻코드

```java
private void DFS(int cnt, int appeach, int lion, int spare) {
	// 기저조건
	if(cnt == 11) {
		if(spare != 0) lionScore[10] += spare; //여분이 남았다면 => 가장 작은 점수인 0에 여분 다 쏘기
		int appeachGet = 0;
		int lionGet = 0;
		for(int i = 0; i < 11; i++) { // 점수 계산
			if(infoCopy[i] < lionScore[i]) {
				lionGet += score[i];
			} else {
				if(infoCopy[i] != 0) {
					appeachGet += score[i];
				}
			}
		}
		int scoreGap = lionGet - appeachGet;
		if(scoreGap > 0) {
			if(maxScoreGap < scoreGap) { //갱신(max값과 그 때의 배열)
				maxScoreGap = scoreGap;
				for(int i = 0; i < 11; i++) {
					maxScoreGapLion[i] = lionScore[i];
				}
			} else if(maxScoreGap == scoreGap) {
				for(int i = 10; i >= 0; i--) { //점수차가 같을땐, 낮은 점수 max값
					if(maxScoreGapLion[i] == lionScore[i]) continue;

					if(maxScoreGapLion[i] < lionScore[i]) {
						for(int j = 0; j < 11; j++) {
							maxScoreGapLion[j] = lionScore[j];
						}
						break;
					} else { //갱신 필요없
						break;
					}
				}
			}
		}
		return;
	}
	// 유도조건
	for(int i = cnt; i < 11; i++) {
		// lion이 이기는거
		if(spare - (infoCopy[i]+1) >= 0) {
			lionScore[i] = infoCopy[i]+1;
			DFS(i+1, appeach, lion+score[i], spare - (infoCopy[i]+1));
			lionScore[i] = 0;
		} else { //lion이 지는거(0발)
			if(infoCopy[i] != 0) {
				lionScore[i] = 0;
				DFS(i+1, appeach+score[i], lion, spare);
			} else {
				lionScore[i] = 0;
				DFS(i+1, appeach, lion, spare);
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 1시간 20분  <br>
아이디어는 어렵지 않게(한 10분?)만에 정리가 되는데.. <br>
미취겠다, 코드로 옮기는게 너무 느리다. <br>
1학기때였음 30분컷 낼 문제였던거 같은데, 큰일났다. 지금이라도 열심히 꾸준히 풀자 제발 ㅋㅋ