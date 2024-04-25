# [Gold III] 벽 부수고 이동하기 - 2206

[문제 링크](https://www.acmicpc.net/problem/2206)

### 성능 요약

메모리: 160612 KB, 시간: 828 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색

### 문제 설명

<p>N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.</p>

<p>만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.</p>

<p>한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.</p>

<p>맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.</p>

### 입력

 <p>첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.</p>

### 출력

 <p>첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.</p>

---
> **후기**

예전에 `말이 되고 싶은 원숭이`라는 문제를 푼 적이 있는데 이때 visited 배열을 3차원으로 확장하여 푼 경험이 있다.
이번 문제도 벽을 한 번 부수는데 이를 기록하기위해서 visited 배열을 3차원 배열로 확장하여 접근해보았다.
다른 방법이 있을수도 있겠지만 어쨋든 여기에 bfs를 사용하여 문제를 해결하였다!

> **간단한 설명**

Node클래스에 wall이라는 변수를 두었는데, 0이면 벽을 안 뚫은 것이고 1이면 뚫은 것으로 표시하였다.
다른 로직은 다들 보면 이해할 수 있을 것 같고,
bfs 로직내에 이동하려는 좌표가 범위 내에 있을때 두 가지 조건으로 나눠지게 된다.

첫 번째는 현재 좌표가 벽이 아니고, 아직 방문하지 않은 경우이다.
이 경우는 벽을 부수지 않고 이동할 수 있으므로, 그대로 이동하고 현재 위치를 방문 표시하며, 이동한 거리를 1 증가시킨다.

두 번째는 현재 좌표가 벽이고, 벽을 아직 부수지 않았으며, 아직 방문하지 않은 경우이다.
이 경우는 벽을 부순 후 이동하므로, 벽을 부수는 행동을 표시하고 이동한 거리를 1증가시킨다.