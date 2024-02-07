import sys

N = int(sys.stdin.readline().rstrip())
seq = [*map(int, sys.stdin.readline().rstrip().split())]
seq.sort()

length = 2

if len(seq) < 3:
    print(len(seq))
else:
    lp, rp = 0, 2
    while rp < len(seq):
        if seq[lp + 1] - seq[lp] < seq[rp] < seq[lp + 1] + seq[lp]:
            length = max(length, rp - lp + 1)
        else:
            lp += 1
        rp += 1
    print(length)
