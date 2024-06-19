import re
def solution(user_id, banned_id):
    answer = 0
    graph = [[x for x in user_id if re.fullmatch(y.replace('*', '.'), x)] for y in banned_id]
    cases = []
    def DFS(level, visited):
        if level == len(graph):
            if (s := sorted(visited)) not in cases:
                cases.append(s)
        else:
            for g in graph[level]:
                if g not in visited:
                    DFS(level+1, visited+[g])
    DFS(0, [])
    return len(cases)