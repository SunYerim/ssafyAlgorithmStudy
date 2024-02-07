def isValid(ps):
    stack = []
    for p in ps:
        if p == "(":
            stack.append(p)
        else:
            if not stack:
                return False
            else:
                stack.pop()
    return not stack


T = int(input())
for _ in range(T):
    ps = input()
    if isValid(ps):
        print("YES")
    else:
        print("NO")