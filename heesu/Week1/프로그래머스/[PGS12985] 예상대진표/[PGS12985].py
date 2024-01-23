import math

def solution(n,a,b):
    answer = 0
    while n:
        answer+=1
        a = math.ceil(a/2)
        b = math.ceil(b/2)
        if a==b:
            return answer
        n = n//2
    return answer