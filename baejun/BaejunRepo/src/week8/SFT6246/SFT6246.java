package week8.SFT6246;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SFT6246 {
	static int n, m, answer, checkingVisit;
	static int[][] array, checked;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		answer = 0; // 정답
		checkingVisit = 0; // 순서에 맞게 방문하는지 체크하기 위한 변수
		
		array = new int[n][n];
		visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < n; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		checked = new int[m][2];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			checked[i][0] = Integer.parseInt(st.nextToken()) - 1; // row
			checked[i][1] = Integer.parseInt(st.nextToken()) - 1; // col
		}
		
		visited[checked[checkingVisit][0]][checked[checkingVisit][1]] = true;
		checkingVisit++;
		dfs(checked[0][0], checked[0][1]);
		
		System.out.println(answer);
	}

	/* m개의 첫번째 위치에서 출발해서 4방 탐색인데 벽 조건 추가, 기저조건에서 필수 위치(순서에 맞게) 다 갔는지 확인하고 +1*/
	private static void dfs(int row, int col) {
		// 기저조건
		if(row == checked[m-1][0] && col == checked[m-1][1]) {
			if(checkingVisit == m) answer++; // 순서에 맞게 다 돌았으면 정답
			return;
		}
		// 유도조건
		for(int i = 0; i < 4; i++) {
			int nr = row + dx[i];
			int nc = col + dy[i];
			if(nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && array[nr][nc] == 0) {
				visited[nr][nc] = true;
				/* 백트래킹 : 순번에 맞게 방문해야하므로 필수방문의 현재 순번과 같다면 ++해줌*/
				if(nr == checked[checkingVisit][0] && nc == checked[checkingVisit][1]) checkingVisit++;
				dfs(nr, nc);
				if(nr == checked[checkingVisit-1][0] && nc == checked[checkingVisit-1][1]) checkingVisit--;
				visited[nr][nc] = false;
			}
		}
	}
}
