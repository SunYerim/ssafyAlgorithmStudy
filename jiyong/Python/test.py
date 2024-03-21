import sys

input = sys.stdin.readline

N, M = map(int, input().rstrip().split())

items = [input().rstrip() for _ in range(N)]
items.sort()
used = [False] * (N)
answer = 0

lp, rp = 0, 1


def can_combine(idx, s1, s2):
    temp = list(s1)
    for i in range(len(s1)):
        if s2[i] == '.' or s1[i] == s2[i]:
            continue
        elif s1[i] == '.':
            temp[i] = s2[i]
        else:
            return False
    items[idx] = ''.join(temp)
    return True


while lp < N - 1:
    if used[lp]:
        pass
    else:
        answer += 1
        rp = lp + 1
        while rp < N:
            if not used[rp] and can_combine(lp, items[lp], items[rp]):
                used[rp] = True
            rp += 1
    lp += 1

print(answer + (0 if used[lp] else 1))