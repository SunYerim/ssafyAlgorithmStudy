package week5.SWEA2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2105 {
	
	static class Dessert {
		int x;
		int y;
		int variation;
		
		Dessert(int x, int y, int variation) {
			this.x = x;
			this.y = y;
			this.variation = variation;
		}
	}
	
	static int N;
	static Dessert[][] map;
	static boolean[][] visitedForMap;
	static boolean[] visitedForDessert;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int maxValue;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#" + testCase + " ");
			N = Integer.parseInt(in.readLine());
			map = new Dessert[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = new Dessert(i, j, Integer.parseInt(st.nextToken()));
				}
			}
			visitedForMap = new boolean[N][N];
			maxValue = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visitedForDessert = new boolean[101];
					DFS(0, 0, i, j, i, j); // cnt, 이전 인덱스 방향, x좌표, y좌표, 목표좌표x, 목표좌표y
				}
			}
			sb.append(maxValue).append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int cnt, int prevDirec, int x, int y, int destX, int destY) {
		//기저조건
		if(cnt != 0 && x == destX && y == destY) { // 출발지로 돌아왔으면 디저트 몇개 먹었는지 체크
			if(maxValue < cnt) maxValue = cnt;
			return;
		}
		//유도조건
		if(cnt == 0) { // 출발지에선 같은 방향으로 한칸 나아감(제자리 돌기 방지)
			visitedForDessert[map[x][y].variation] = true;
			int nr = x+dx[prevDirec];
			int nc = y+dy[prevDirec];
			if(nr>=0&&nr<N&&nc>=0&&nc<N) {
				if(!visitedForDessert[map[nr][nc].variation]) {
					visitedForDessert[map[nr][nc].variation] = true;
					DFS(cnt+1, prevDirec, nr, nc, destX, destY);
					visitedForDessert[map[nr][nc].variation] = false;
				}
			}
		} else { // 같은 방향으로 가는거 한번, 방향 45도 틀어서 한번(시계방향으로) -> 이때, 방향은 같이갈땐 유지, 틀땐 변경해줘야함
			int nr = x+dx[prevDirec];
			int nc = y+dy[prevDirec];
			if(nr == destX && nc == destY) {
				DFS(cnt+1, prevDirec, nr, nc, destX, destY);
				return;
			}
			else if(nr>=0&&nr<N&&nc>=0&&nc<N) {
				if(!visitedForDessert[map[nr][nc].variation]) {
					visitedForDessert[map[nr][nc].variation] = true;
					DFS(cnt+1, prevDirec, nr, nc, destX, destY);
					visitedForDessert[map[nr][nc].variation] = false;
				}
			}
			if(prevDirec+1 == 4) return; // 방향이 3일땐 3으로 가는 경우만 구하면 됨.(트는 순간 사각형 어그러짐)
			nr = x+dx[prevDirec+1];
			nc = y+dy[prevDirec+1];
			if(nr == destX && nc == destY) { // 목적지에 도착했으면 바로 호출
				DFS(cnt+1, prevDirec+1, nr, nc, destX, destY);
			}
			else if(nr>=0&&nr<N&&nc>=0&&nc<N) {
				if(!visitedForDessert[map[nr][nc].variation]) {
					visitedForDessert[map[nr][nc].variation] = true;
					DFS(cnt+1, prevDirec+1, nr, nc, destX, destY);
					visitedForDessert[map[nr][nc].variation] = false;
				}
			}
		}
	}
}
