package week3.BOJ7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class tomato {
	int x;
	int y;
	int day;

	public tomato(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
}

public class BOJ7576 {
	private static Queue<tomato> queue = new LinkedList<>();
	private static int[] dx = {1, -1, 0, 0};
	private static int[] dy = {0, 0, 1, -1};
	private static int[][] tomatos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		tomatos = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++) {
				tomatos[i][j] = Integer.parseInt(st.nextToken());
				if(tomatos[i][j] == 1) {
					queue.offer(new tomato(i, j, 0));
				}
			}
		}

		bfs();
	}

	private static void bfs() {
		tomato currentTomato = null;

		while(!queue.isEmpty()) {
			currentTomato = queue.poll();
			for(int i = 0; i < 4; i++) { // 4방탐색
				int nr = currentTomato.x + dx[i];
				int nc = currentTomato.y + dy[i];
				if(nr>=0&&nr<tomatos.length&&nc>=0&&nc<tomatos[0].length) {
					if(tomatos[nr][nc] == 0) {
						tomatos[nr][nc] = 1;
						queue.offer(new tomato(nr, nc, currentTomato.day+1)); //1일 증가
					}
				}
			}
		}
		if (checkTomato()) {
			System.out.println(currentTomato.day);
		} else {
			System.out.println(-1);
		}
	}

	private static boolean checkTomato() {
		for(int i = 0; i < tomatos.length; i++) {
			for(int j = 0; j < tomatos[i].length; j++) {
				if(tomatos[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
