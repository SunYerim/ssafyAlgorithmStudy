[프로그래머스][Lv.3] 43164 여행 경로 / (AC)
---

###문제 설명


[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/43164)]

주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.

항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.

---

###제한사항

- 모든 공항은 알파벳 대문자 3글자로 이루어집니다.
- 주어진 공항 수는 3개 이상 10,000개 이하입니다.
- tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
- 주어진 항공권은 모두 사용해야 합니다.
- 만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
- 모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.

**입출력 예**

| tickets | return |
|:---|:---|
| [["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]] | ["ICN", "JFK", "HND", "IAD"] |
| [["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]] | ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] |


---

###문제 풀이

`'ICN'`부터 출발하여 `그래프 탐색`을 수행하는데, `모든 노드`가 경로에 포함되어야 하고, 그 중 사전 순으로 가장 빠른 경로가 정답이 되는 문제다. `위상정렬`과 비슷한 개념이라고 생각하면 된다.

`백 트래킹`류 문제와 유사하며, `티켓` 사용여부를 관리할 자료구조만 잘 정하면 되는데, 여기서는 `collections.Counter` 객체를 활용하였다. `Counter` 객체는 객체 간의 `+`, `-` 연산을 지원하기 때문에, 간편하게 `합집합`과 `차집합` 연산을 구현할 수 있다.

---

###코드


```python
from collections import defaultdict, Counter

net = defaultdict(list)
answer = []


def DFS(cur, routes, cnt, target):
    global net, answer
    if len(routes) == target:
        answer = routes + [cur]
    else:
        for nxt in net[cur]:
            if cnt[(cur, nxt)] and not answer:
                DFS(nxt, routes + [cur], cnt - Counter([(cur, nxt)]), target)


def solution(tickets):
    global net, answer
    cnt = Counter()
    for s, e in sorted(tickets):
        net[s].append(e)
        cnt[(s, e)] += 1
    DFS('ICN', [], cnt, len(tickets))
    return answer


# test driver
if __name__ == '__main__':
    print(solution([["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL", "SFO"]]))
    # output : ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
```

---

###후기

뭐라 더 설명할 게 없다. 이 문제는 원래 2레벨로 분류되었었는데, 대대적인 난이도 재편성 이후 3레벨로 승격했다. (N-Queen이 2레벨인데..?)

문제 풀이 시간 : 30분

알고리즘 분류 : `깊이 우선 탐색`, `백트래킹(?)`, `자료구조`
