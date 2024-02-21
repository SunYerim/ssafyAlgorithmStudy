t = int(input())

def rotate(wheel, direction):
    global visited, arr, idx
    visited[wheel] = True
    if wheel > 0 and not visited[wheel-1] and arr[wheel][(idx[wheel]-2)%8] != arr[wheel-1][(idx[wheel-1]+2)%8]:
        rotate(wheel-1, -direction)
    if wheel < 3 and not visited[wheel+1] and arr[wheel][(idx[wheel]+2)%8] != arr[wheel+1][(idx[wheel+1]-2)%8]:
        rotate(wheel+1, -direction)
    idx[wheel] = (idx[wheel] - direction)%8

for test_case in range(t):
    result = 0
    idx = [0]*4
    k = int(input())
    arr = [list(map(int, input().split())) for _ in range(4)]

    for _ in range(k): # 쿼리 수만큼 반복
        visited = [False]*4
        num, d = map(int, input().split()) # 회전시키는 바퀴 번호, 회전 방향
        rotate(num-1, d)

    for i in range(4):
        result += arr[i][idx[i]]*(1<<i)

    print(f"#{test_case+1} {result}")