
[프로그래머스][Lv.3] 42579 베스트 앨범 / (AC)
---
> **문제 설명**


[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42579)]


스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

---

> **제한사항**

- genres[i]는 고유번호가 i인 노래의 장르입니다.
- plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
- genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
- 장르 종류는 100개 미만입니다.
- 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
- 모든 장르는 재생된 횟수가 다릅니다.

**입출력 예**

| genres | plays | return|
| --- | --- | --- |
| ["classic", "pop", "classic", "classic", "pop"] | [500, 600, 150, 800, 2500] | [4, 1, 3, 0] |

**입출력 예 설명**

classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

고유 번호 3: 800회 재생
고유 번호 0: 500회 재생
고유 번호 2: 150회 재생
pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

고유 번호 4: 2,500회 재생
고유 번호 1: 600회 재생
따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.

장르 별로 가장 많이 재생된 노래를 최대 두 개까지 모아 베스트 앨범을 출시하므로 2번 노래는 수록되지 않습니다.



---

> **문제 풀이**

문제의 요구사항에 따라 로직을 작성하되, `해시 맵`을 통해 자료를 저장함으로써 `키워드`를 이용한 탐색이 `Random Access`가 가능하도록 구현해야 탐색 시간 복잡도가 `O(n)`에서 `O(1)`로 줄어든다. 또한, 자료들을 정렬할 때 직접 기준을 세워 정렬을 할 수 있는지 묻는 문제.

1. `key`: `장르`, `value`: `총 재생횟수`인 `해시 맵` 생성
2. `key`: `장르`, `value`: (`노래`의 `재생횟수`와 `고유번호`를 담은 `객체`)의 `리스트`인 `해시 맵` 생성
3. `장르`의 종류를 담은 `리스트`를 생성하고, 1번에서 생성한 `해시 맵`의 `value`를 기준으로 정렬
4. 3번에서 정렬된 `리스트`를 순회하며, 2번의 각 `장르`별 `리스트`를 `재생횟수` 기준으로 정렬
5. 정렬된 노래 중 최대 2개를 추출하여 정답 `리스트`에 추가




---

> **코드**
> 

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int[] solution(String[] genres, int[] plays) {
		int[] answer;
		int index = 0;
		Map<String, Integer> genrePlays = new HashMap<>();
		Map<String, ArrayList<Music>> genreMusics = new HashMap<>();
		List<String> genreSetList;
		List<Integer> playLists = new ArrayList<>();

		for (int i = 0; i < genres.length; i++) {
			if (genrePlays.get(genres[i]) == null) {
				genrePlays.put(genres[i], plays[i]);
			} else {
				genrePlays.put(genres[i], genrePlays.get(genres[i]) + plays[i]);
			}
			if (genreMusics.get(genres[i]) == null) {
				genreMusics.put(genres[i], new ArrayList<Music>());
			}
			genreMusics.get(genres[i]).add(new Music(i, plays[i]));
		}

		genreSetList = new ArrayList<String>(genrePlays.keySet());

		Collections.sort(genreSetList, (o1, o2) -> genrePlays.get(o2) - genrePlays.get(o1));

		for (String genre : genreSetList) {
			Collections.sort(genreMusics.get(genre), (o1, o2) -> o2.plays - o1.plays);
			for (int i = 0; i < Math.min(genreMusics.get(genre).size(), 2); i++) {
				playLists.add(genreMusics.get(genre).get(i).idx);
			}
		}
		answer = new int[playLists.size()];
		for (int num : playLists) {
			answer[index++] = num;
		}

		return answer;
	}

// 테스트 코드
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		Solution sol = new Solution();
		System.out.println(Arrays.toString(sol.solution(genres, plays)));
	}

}

class Music {
	int idx;
	int plays;

	public Music(int idx, int plays) {
		this.idx = idx;
		this.plays = plays;
	}
}
```
```python
from collections import defaultdict as ddict
def solution(genres, plays):
    answer = []
    genres_plays = ddict(int)
    genres_music = ddict(list)
    for i, (g, p) in enumerate(zip(genres, plays)):
        genres_plays[g] += p
        genres_music[g].append((i, p))
    for key, _ in sorted(genres_plays.items(), key=lambda x: x[1], reverse=True):
        for i, p in sorted(genres_music[key], key=lambda x: x[1], reverse=True)[:2]:
            answer.append(i)
    return answer
```



---

> **후기**


1년 전 쯤 파이썬으로 풀었던 문제인데, 다시금 되새기며 이번엔 자바로 구현해보았다... <del>역시 시퀀스는 파이썬이 짱</del>


문제 풀이 시간 : 30~60분 / 실행시간(총합) : `23.88ms` / 메모리(총합) : `1117.6MB`

알고리즘 분류 : 해시, 자료구조, 정렬