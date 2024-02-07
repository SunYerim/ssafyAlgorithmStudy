import sys
t = int(sys.stdin.readline())
arr = []
flag = True
for _ in range(t):
    x, r = map(int, sys.stdin.readline().split())
    if not arr or arr[-1] <= x-r:
        arr.append(x-r)
        arr.append(x+r)
    elif arr[0] >= x+r:
        arr.insert(0, x+r)
        arr.insert(0, x-r)
    else:
        for idx in range(len(arr)):
            if x-r<=arr[idx+1] and x+r>=arr[idx]:
                if x-r >= arr[idx] and x+r<=arr[idx+1]:
                    arr[idx+1:idx+1] = [x-r, x+r]
                else:
                    flag = False
                break
    if not flag:
        break

print("NO") if not flag else print("YES")
