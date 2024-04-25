package week13.BOJ4179;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ4179 {
	static int R, C, startX, startY, minValue;
	static char[][] map;
	static ArrayList<Node> fireList;
	static int[][] fireMap;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static class Node {
		int x;
		int y;
		int cost;
		
		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		fireMap = new int[R][C];
		fireList = new ArrayList<>();
		
		for(int i = 0; i < R; i++) {
			String str = in.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'F') {
					fireList.add(new Node(i, j, 0));
				}
				if(map[i][j] == 'J') {
					startX = i;
					startY = j;
				}
			}
		}

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != 'F') {
					fireMap[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		visited = new boolean[R][C];
		bfsInFire(); // 불이 퍼지는 시간을 구하기 위한 BFS
		
		/* 지훈이의 초기위치가 가장자리라면 바로탈출가능, 아니라면 BFS */
		if(startX==0||startX==R-1||startY==0||startY==C-1) {
			minValue = 0;
		} else {
			minValue = Integer.MAX_VALUE;
			visited = new boolean[R][C];
			bfsInjihoon();
		}
		
		/* 탈출가능하면 시간최소값, 아니라면 IMPOSSIBLE 출력 */
		if(minValue == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(minValue+1);
		}
	}

	private static void bfsInjihoon() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = curNode.x + dx[i];
				int nc = curNode.y + dy[i];
				
				if(nr>=0&&nr<R&&nc>=0&&nc<C&&!visited[nr][nc]&&map[nr][nc]!='#'&&curNode.cost+1<fireMap[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Node(nr, nc, curNode.cost+1));
					if(nr==0||nr==R-1||nc==0||nc==C-1) {
						if(minValue > curNode.cost+1) minValue = curNode.cost+1;
					}
				}
			}
		}
	}

	private static void bfsInFire() {
		Queue<Node> queue = new LinkedList<>();
		for(int i = 0; i < fireList.size(); i++) {
			queue.offer(fireList.get(i));
			visited[fireList.get(i).x][fireList.get(i).y] = true;
		}
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = curNode.x + dx[i];
				int nc = curNode.y + dy[i];
				
				if(nr>=0&&nr<R&&nc>=0&&nc<C&&!visited[nr][nc]&&map[nr][nc]!='#') {
					visited[nr][nc] = true;
					fireMap[nr][nc] = curNode.cost+1;
					queue.offer(new Node(nr, nc, fireMap[nr][nc]));
				}
			}
		}
	}
}