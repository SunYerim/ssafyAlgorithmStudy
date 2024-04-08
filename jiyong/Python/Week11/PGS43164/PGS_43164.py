from collections import defaultdict, Counter

net = defaultdict(list)
answer = []


def DFS(cur, routes, cnt, target):
    global net, answer
    if len(routes) == target:
        answer = routes + [cur]
    else:
        for nxt in net[cur]:
            if cnt[(cur, nxt)] and not answer:
                DFS(nxt, routes + [cur], cnt - Counter([(cur, nxt)]), target)


def solution(tickets):
    global net, answer
    cnt = Counter()
    for s, e in sorted(tickets):
        net[s].append(e)
        cnt[(s, e)] += 1
    DFS('ICN', [], cnt, len(tickets))
    return answer


# test driver
if __name__ == '__main__':
    print(solution([["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL", "SFO"]]))
    # output : ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
