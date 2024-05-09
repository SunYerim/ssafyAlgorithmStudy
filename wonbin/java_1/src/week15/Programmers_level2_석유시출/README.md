# [Programmers][level2] 석유시출/ (AC )

---

> **문제 설명**
> 

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/250136)]

[문제 내용 전체]

세로길이가 `n` 가로길이가 `m`인 격자 모양의 땅 속에서 석유가 발견되었습니다. 석유는 여러 덩어리로 나누어 묻혀있습니다. 당신이 시추관을 수직으로 **단 하나만** 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치를 찾으려고 합니다. 시추관은 열 하나를 관통하는 형태여야 하며, 열과 열 사이에 시추관을 뚫을 수 없습니다.

!https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/beb862a9-5382-4f61-adae-bd6e9503c014/%E1%84%89%E1%85%A5%E1%86%A8%E1%84%8B%E1%85%B2%E1%84%89%E1%85%B5%E1%84%8E%E1%85%AE-1.drawio.png

예를 들어 가로가 8, 세로가 5인 격자 모양의 땅 속에 위 그림처럼 석유가 발견되었다고 가정하겠습니다. 상, 하, 좌, 우로 연결된 석유는 하나의 덩어리이며, 석유 덩어리의 크기는 덩어리에 포함된 칸의 수입니다. 그림에서 석유 덩어리의 크기는 왼쪽부터 8, 7, 2입니다.

!https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/0b10a9f6-6d98-44d6-a342-f984ea47315c/%E1%84%89%E1%85%A5%E1%86%A8%E1%84%8B%E1%85%B2%E1%84%89%E1%85%B5%E1%84%8E%E1%85%AE-2.drawio.png

시추관은 위 그림처럼 설치한 위치 아래로 끝까지 뻗어나갑니다. 만약 시추관이 석유 덩어리의 일부를 지나면 해당 덩어리에 속한 모든 석유를 뽑을 수 있습니다. 시추관이 뽑을 수 있는 석유량은 시추관이 지나는 석유 덩어리들의 크기를 모두 합한 값입니다. 시추관을 설치한 위치에 따라 뽑을 수 있는 석유량은 다음과 같습니다.

| 시추관의 위치 | 획득한 덩어리 | 총 석유량 |
| --- | --- | --- |
| 1 | [8] | 8 |
| 2 | [8] | 8 |
| 3 | [8] | 8 |
| 4 | [7] | 7 |
| 5 | [7] | 7 |
| 6 | [7] | 7 |
| 7 | [7, 2] | 9 |
| 8 | [2] | 2 |

오른쪽 그림처럼 7번 열에 시추관을 설치하면 크기가 7, 2인 덩어리의 석유를 얻어 뽑을 수 있는 석유량이 9로 가장 많습니다.

석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열 `land`가 매개변수로 주어집니다. 이때 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량을 return 하도록 solution 함수를 완성해 주세요.

---

> **제한사항**
> 

입력/출력 제한사항

### 제한사항

- 1 ≤ `land`의 길이 = 땅의 세로길이 = `n` ≤ 500
    - 1 ≤ `land[i]`의 길이 = 땅의 가로길이 = `m` ≤ 500
    - `land[i][j]`는 `i+1`행 `j+1`열 땅의 정보를 나타냅니다.
    - `land[i][j]`는 0 또는 1입니다.
    - `land[i][j]`가 0이면 빈 땅을, 1이면 석유가 있는 땅을 의미합니다.

### 정확성 테스트 케이스 제한사항

- 1 ≤ `land`의 길이 = 땅의 세로길이 = `n` ≤ 100
    - 1 ≤ `land[i]`의 길이 = 땅의 가로길이 = `m` ≤ 100

### 효율성 테스트 케이스 제한사항

- 주어진 조건 외 추가 제한사항 없습니다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 아 그냥 치즈랑 똑같이 풀면 되겠구나 하고 풀었는데 시간초과가 걸렸다. 분명 500 x 500이라서 절대 시간 안터질 줄 알았는데 터져서 그거 해결하느라 머리를 좀 썼는데 도저히 안떠올라서 정답을 참고 하였다. 보니까 중복 체크를 set에 넣어서 해결하는 방식으로 풀었었다.

1. land 초기화 및 visited, oil 배열 생성
2. bfs를 돌면서 1 즉 석유를 만나면 석유가 모여있는 자리를 탐색해 석유의 양을 정한다.
3. set을 활용하여 각 석유 자리의 세로열 인덱스를 저장시킨다.
4. bfs를 다 돌고 나면 set에 있는 값들을 oil 배열의 인덱스로 사용하여 그 자리에 석유의 양을 더한다. 이 과정에서 석유를 계속 더해도 되는것이 visited 배열을 초기화를 안시켜서 가능하다.
5. 이후 oil 배열에서 가장 큰 값을 answer에 저장하고 리턴하면 된다.

---

> **코드**
> 

```java
import java.io.*;
import java.util.*;

class Solution {
    static int count = 0;
    static int[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int N;
    static int M;
    static int[][] land2;
    static int[] oil;
    
    static class point{
        int y;
        int x;
        
        public point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        land2 = new int[N][M];
        visited = new int[N][M];
        oil = new int[M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                land2[i][j] = land[i][j];
            }
        }
        
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(land2[j][i] == 1 && visited[j][i] == 0){
                    visited[j][i] = 1;
                    count = 1;
                    bfs(j, i);
                }
            }
        }
        
        for(int i : oil){
            answer = Math.max(answer, i);
        }
        return answer;
    }
    
    static void bfs(int y, int x){
        Queue<point> que = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        que.offer(new point(y, x));
        set.add(x);
        
        while(!que.isEmpty()){
            point p = que.poll();
            
            for(int i=0;i<4;i++){
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
            
                if(ny>=0 && ny<N && nx>=0 && nx<M && land2[ny][nx] == 1 && visited[ny][nx] == 0){
                    visited[ny][nx] = 1;
                    count++;
                    set.add(nx);
                    que.offer(new point(ny, nx));
                }
            }
        }
        
        for(int c : set){
            oil[c] += count;
        }
        
    }
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 시간 초과를 잡는다고 고생했다;;
- visited를 갱신하지 않고 중복체크를 하는 방식을 새로 배웠다.(사실 아이디어 싸움인듯)

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1일 / 135.64ms / 81.3MB / 81줄

알고리즘 분류

- 너비우선탐색
- 깊이우선탐색
- 자료구조