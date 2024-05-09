package week15.PGM250136;

import java.util.*;
import java.io.*;


public class PGM250136 {

	static int[] colScore;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int row;
	static int col;

	public int solution(int[][] land) {
		row = land.length;
		col = land[0].length;
		System.out.println(col);
		colScore = new int[col];
		visited = new boolean[row][col];

		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(land[i][j] == 1&&!visited[i][j]) {
					bfs(i, j, land);
				}
			}
		}

		int max = 0;
		for(int i = 0; i < col; i++) {
			if(max < colScore[i]) max = colScore[i];
		}
		return max;
	}

	public void bfs(int startX, int startY, int[][] land) {
		int cnt = 0;
		boolean[] visitForCol = new boolean[col];

		Queue<Integer> queue = new LinkedList<>();
		queue.add(startX);
		queue.add(startY);
		visited[startX][startY] = true;
		visitForCol[startY] = true;

		while(!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			cnt++;

			for(int i = 0; i < 4; i++) {
				int nr = x + dx[i];
				int nc = y + dy[i];
				if(nr>=0&&nr<row&&nc>=0&&nc<col&&!visited[nr][nc]&&land[nr][nc] == 1) {
					visited[nr][nc] = true;
					visitForCol[nc] = true;
					queue.add(nr);
					queue.add(nc);
				}
			}
		}
		for(int idx = 0; idx < col; idx++) {
			if(visitForCol[idx]) {
				colScore[idx] += cnt;
			}
		}
	}
}