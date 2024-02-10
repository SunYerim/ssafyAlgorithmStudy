package week3.BOJ10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10026 {
	private static char[][] colorArray;
	private static boolean[][] visited;
	private static int N;
	private static int count1; // no 적록색약
	private static int count2; // yes 적록색약
	private static char currentColor;
	private static int[] dr = {-1, 1, 0, 0}; // 4방탐색 좌표값
	private static int[] dc = {0, 0, 1, -1}; // 4방탐색 좌표값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		
		/* 입력값 받기 */
		N = Integer.parseInt(in.readLine());
		colorArray = new char[N][N];
		for(int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < N; j++) {
				colorArray[i][j] = str.charAt(j);
			}
		}
		/* 1. 적록색약이 아닌 사람의 구역 나누기 */
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				count1++;
				currentColor = colorArray[i][j];
				dfs(i, j);
			}
		}
		sb.append(count1 + " ");
		/* 2. 적록색약인 사람 -> R과 G가 같은 사람의 구역 나누기 */
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(colorArray[i][j] == 'R') {
					colorArray[i][j] = 'G';
				}
			}
		}
		// 초기화 해주기
		visited = new boolean[N][N];
		currentColor = 'A';
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) { // 방문했다면 구역에 포함된 애임, 아닌 애만 구역체크 해주면됨
				if(visited[i][j]) continue;
				count2++;
				currentColor = colorArray[i][j];
				dfs(i, j);
			}
		}
		sb.append(count2);
		System.out.println(sb);
	}

	private static void dfs(int row, int col) {
		visited[row][col] = true;
		for(int i = 0; i < 4; i++) {
			int nr = row + dr[i];
			int nc = col + dc[i];
			/* 같은 구역인 애들 방문해주기 */
			if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]&&colorArray[nr][nc] == currentColor) {
				dfs(nr, nc);
			}
		}
	}
}
