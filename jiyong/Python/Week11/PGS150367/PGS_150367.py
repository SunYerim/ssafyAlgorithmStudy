from math import log2, ceil


def solution(numbers):
    answer = []
    for num in numbers:
        binary = to_binary(num)
        answer.append(1 if can_exp(binary) else 0)
    return answer


def to_binary(n: int):
    bi = list(bin(n)[2:])
    l = 2 ** ceil(log2(len(bi))) - 1
    if len(bi) < l:
        bi = ["0"] * (l - len(bi)) + bi
    elif len(bi) > l:
        bi = ["0"] * (((l + 1) * 2 - 1) - len(bi)) + bi
    return bi


def can_exp(b: str):
    if len(b) == 1:
        return True
    l, c, r = b[:len(b) // 2], b[len(b) // 2], b[len(b) // 2 + 1:]
    if c == '0':
        if l.count('1') or r.count('1'):
            return False
        else:
            return True
    else:
        return all((can_exp(l), can_exp(r)))


# test driver
if __name__ == '__main__':
    print(solution([7, 42, 5]))
    # output : [1, 1, 0]
