import sys
import os

reader = os.fdopen(sys.stdin.fileno(), 'rb', 1000)


def read_ints():
    global reader
    while True:
        num = 0
        while True:
            char = reader.read(1)
            if b'0' <= char <= b'9':
                num = 10 * num + int(char)
            elif char == b' ':
                yield num
                break
            else:
                yield num
                exit()
                break


bit = [0] * 1048576
for n in read_ints():
    if not bit[n // 32] & (1 << (n % 32)):
        bit[n // 32] |= (1 << (n % 32))
        print(n, end=' ')
