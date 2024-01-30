from collections import deque
R, C = map(int, input().split())
J, F = (), []
maze = []
for r in range(R):
  cur = input()
  if not J and 'J' in cur: J = (r, cur.find('J'), 0, 'J')
  if 'F' in cur: F.extend([(r, c, 0, 'F') for c in range(C) if cur[c]== 'F'])
  maze.append([*cur])

dist = ((1,0), (-1,0), (0,1), (0,-1))

dQ = deque()
dQ.extend(F)

def isIn(r, c, R, C):
  return 0<=r<R and 0<=c<C

while dQ:
  cur_r, cur_c, time, type_ = dQ.popleft()
  
  maze[cur_r][cur_c] = time

  for dr, dc in dist:
    nr, nc = cur_r + dr, cur_c + dc
    if isIn(nr, nc, R, C):
      if maze[nr][nc] in ('.','J'):
        dQ.append((nr, nc, time+1, type_))

def isGoal(r, c, R, C):
  return r==0 or c ==0 or r==R-1 or C==C-1

dQ.append(J)
found = 0

while dQ and not found:
  cur_r, cur_c, time, type_ = dQ.popleft()
  
  maze[cur_r][cur_c] = '#'

  for dr, dc in dist:
    nr, nc = cur_r + dr, cur_c + dc
    if isIn(nr, nc, R, C):
      if maze[nr][nc] == '.' or (type(maze[nr][nc]) == int and maze[nr][nc]> time):
        if isGoal(nr, nc, R, C): 
          found = time + 2
          break
        dQ.append((nr, nc, time+1, type_))
print(found if found else 'IMPOSSIBLE')
