package week12.BOJ19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ19236 {

	static class Fish {
		int x;
		int y;
		int direction;

		Fish(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	static class Shark {
		int x;
		int y;
		int direction;

		Shark(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	static int[][] map;
	static Fish[] fishes;
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static Shark shark;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		fishes = new Fish[17]; // 1 ~ 16
		fishes[0] = null;
		map = new int[4][4];

		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 4; j++) {
				int fishNum = Integer.parseInt(st.nextToken());
				int fishDir = Integer.parseInt(st.nextToken());
				map[i][j] = fishNum;
				fishes[fishNum] = new Fish(i, j, fishDir);
			}
		}

		shark = new Shark(0, 0, fishes[map[0][0]].direction);
		fishes[map[0][0]] = null;
		int eatInit = map[0][0];
		map[0][0] = -1;
		arrangeFish();
		DFS(eatInit);
		System.out.println(max);
		
	}

	private static void DFS(int eat) {
//		System.out.println("현재 상어 위치 : " + shark.x + ", " + shark.y + "방향: " + shark.direction + "(먹은물고기: " + eat);
		if(max < eat) max = eat;
		boolean flag = false;
		
		for(int k = 1; k <= 3; k++) {
			int nr = shark.x + dx[shark.direction] * k;
			int nc = shark.y + dy[shark.direction] * k;
			
			if(nr>=0&&nr<4&&nc>=0&&nc<4&&map[nr][nc] != -1) {
				flag = true;
				Shark copyShark = new Shark(shark.x, shark.y, shark.direction);
				Fish[] copyFish = new Fish[17];
				int[][] copyMap = new int[4][4];
				copyFish(copyFish);
				copyMap(copyMap);
				
				
				int eating = map[nr][nc];
				shark.x = nr;
				shark.y = nc;
				shark.direction = fishes[map[nr][nc]].direction;
				fishes[map[nr][nc]] = null;
				map[nr][nc] = -1;
				arrangeFish();
				
				DFS(eat + eating);
				
				shark.x = copyShark.x;
				shark.y = copyShark.y;
				shark.direction = copyShark.direction;
				recoveryFish(copyFish);
				recoveryMap(copyMap);
			}
		}
		if(!flag) return;
	}

	private static void recoveryMap(int[][] copyMap) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = copyMap[i][j];
			}
		}
	}

	private static void copyMap(int[][] copyMap) {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void recoveryFish(Fish[] copyFish) {
		for(int i = 0; i < 17; i++) {
			if(copyFish[i] == null) fishes[i] = null;
			else fishes[i] = new Fish(copyFish[i].x, copyFish[i].y, copyFish[i].direction);
		}
	}
	
	private static void copyFish(Fish[] copyFish) {
		for(int i = 0; i < 17; i++) {
			if(fishes[i] == null) copyFish[i] = null;
			else copyFish[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].direction);
		}
	}

	private static void arrangeFish() {
		if(shark.x == 3 && shark.y == 1) {
//			System.out.println("이거보세요오오 " + shark.direction);
		}
		for(int i = 1; i < 17; i++) {
			boolean flag = false;
			if(fishes[i] == null) continue;
			for(int j = 0; j < 8; j++) {
				int nr = fishes[i].x + dx[fishes[i].direction];
				int nc = fishes[i].y + dy[fishes[i].direction];
				if(nr>=0&&nr<4&&nc>=0&&nc<4&&!(nr==shark.x && nc==shark.y)) {
					int temp = map[nr][nc]; // -1
					map[nr][nc] = map[fishes[i].x][fishes[i].y];
					map[fishes[i].x][fishes[i].y] = temp;
					flag = true;
					int tempX = fishes[i].x;
					int tempY = fishes[i].y;
					fishes[i].x = nr;
					fishes[i].y = nc;
					if(temp != -1) {
						fishes[temp].x = tempX;
						fishes[temp].y = tempY;	
					}
					
					break;
				} else {
					fishes[i].direction = (fishes[i].direction == 8) ? 1 : fishes[i].direction+1;
				}
			}
		}
	}
}