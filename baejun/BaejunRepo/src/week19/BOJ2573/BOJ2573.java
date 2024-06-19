package week19.BOJ2573;

import java.io.*;
import java.util.*;

public class BOJ2573 {
	static class Position {
		int x;
		int y;
		
		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int r, c;
	static int[][] map;
	static ArrayList<Position> positions;
	static ArrayList<Position> removePosition;
	static boolean[][] isIceBerg;
	static boolean[][] visited;
	static int iceBergCnt = 0;
	static boolean isOneGroup = false;
	
	static int removeIceBerg;
	static int time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		isIceBerg = new boolean[r][c]; //빙산 존재 여부
		positions = new ArrayList<>();
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					positions.add(new Position(i, j));
					isIceBerg[i][j] = true;
					iceBergCnt++;
				}
			}
		}
		
		time = 0;
		while(!isOneGroup) { // 분리 되거나, 한번에 싹 다 녹을 때 까지 진행
			removeIceBerg = 0;
			visited = new boolean[r][c];
			removePosition = new ArrayList<>();
			time++;
			bfs();
		}
		
		System.out.println(time-1);
	}

	private static void bfs() {
		int curIceBurg = 0; // 이 bfs에서 순회한 빙산 개수
		
		int startX = 0;
		int startY = 0;
		for (Position ele : positions) {
			if(ele != null && isIceBerg[ele.x][ele.y]) {
				startX = ele.x;
				startY = ele.y;
				break;
			}
		}
		
		Queue<Position> queue = new LinkedList<>();
		
		queue.offer(new Position(startX, startY));
		visited[startX][startY] = true;
		curIceBurg++;
		
		while(!queue.isEmpty()) {
			Position curPosition = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = curPosition.x + dx[i];
				int nc = curPosition.y + dy[i];
				if(nr>=0&&nr<r&&nc>=0&&nc<c&&map[nr][nc] == 0) { // 여기가 바다면, 현재 빙산을 1 녹인다.
					map[curPosition.x][curPosition.y]--;
					if(map[curPosition.x][curPosition.y] == 0) {
						map[curPosition.x][curPosition.y] = 99999;
						isIceBerg[curPosition.x][curPosition.y] = false; //만약 빙산이 다 녹았으면 바다로 바꿔주기
						removePosition.add(new Position(curPosition.x, curPosition.y)); // 삭제 빙산 리스트에 추가
						removeIceBerg++; // 삭제한 빙산의 개수
					}
				} 
				if(nr>=0&&nr<r&&nc>=0&&nc<c&&!visited[nr][nc]&&isIceBerg[nr][nc]) { // 여기가 빙산이면, queue에 추가
					queue.offer(new Position(nr, nc));
					visited[nr][nc] = true;
					curIceBurg++;
				}
			}
		}
		
		for (Position ele : removePosition) { // 삭제해야될 빙산들 삭제
			map[ele.x][ele.y]= 0; 
		}
		
		if(removePosition.size() == iceBergCnt) { // 한번에 다 녹았다 == 분리되지 못했다 == 0(문제 조건)
			time = 1;
			isOneGroup = true;
		}
		else if(iceBergCnt != curIceBurg) { // 모든 빙산을 탐색하지 않았다 == 분리 됐다
			isOneGroup = true;
		} else {
			iceBergCnt -= removeIceBerg;
		}
	}
}
