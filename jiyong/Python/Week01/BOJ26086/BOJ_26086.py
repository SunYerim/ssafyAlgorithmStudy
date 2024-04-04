import sys

N, Q, k = map(int, sys.stdin.readline().rstrip().split())

tasks = []
queries = []

for _ in range(Q):
    query = sys.stdin.readline().rstrip()
    if query[0] == '0':
        tasks.append(int(query[2:]))
    elif query[0] == '1':
        queries.clear()
        queries.append([1, len(tasks)])
    else:
        queries.append([2, len(tasks)])

for query in queries:
    q, l = query
    if q == 1:
        tasks = sorted(tasks[:l], reverse=True) + tasks[l:]
    else:
        tasks = tasks[:l][::-1] + tasks[l:]

print(tasks[-k])