from collections import deque
import sys

dq = deque()
t = int(sys.stdin.readline())
for _ in range(t):
    inputStr = sys.stdin.readline().rstrip().split(" ")
    if inputStr[0] == 'push_back':
        dq.append(int(inputStr[1]))
    elif inputStr[0] == 'push_front':
        dq.appendleft(int(inputStr[1]))
    elif inputStr[0] == 'pop_front':
        print(-1) if not dq else print(dq.popleft())
    elif inputStr[0] == 'pop_back':
        print(-1) if not dq else print(dq.pop())
    elif inputStr[0] == 'size':
        print(len(dq))
    elif inputStr[0] == 'empty':
        print(1) if not dq else print(0)
    elif inputStr[0] == 'front':
        print(-1) if not dq else print(dq[0])
    elif inputStr[0] == 'back':
        print(-1) if not dq else print(dq[-1])