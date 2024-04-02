package week10.BOJ11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BOJ11559 {
	static char[][] field;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;
	static ArrayList<Integer> list;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		field = new char[12][6];
		for(int i = 0; i < field.length; i++) {
			String str = in.readLine();
			for(int j = 0; j < field[i].length; j++) {
				field[i][j] = str.charAt(j);
			}
		}
		
		answer = 0;
		
		while(true) { // 연쇄 단위로 반복
			visited = new boolean[12][6];
			list = new ArrayList<>(); // 터트릴 좌표 들고있을 list : 2단위로(x, y)
			for(int i = 0; i < field.length; i++) {
				for(int j = 0; j < field[i].length; j++) {
					if(field[i][j] != '.' && !visited[i][j]) {
						BFS(i, j); // 터트릴 애 있는지 확인
					}
				}
			}
			if(list.size() > 0) { // 터트릴 애가 있으면?(=> 연쇄가 유지 됐다면)
				answer++;
				for(int i = 0; i < list.size(); i+=2) {
					field[list.get(i)][list.get(i+1)] = '.'; // 터트리기
				}
			} else {
				break;
			}
			downSorting(); // 빈 공간 채우기
		}
		
		System.out.println(answer);
	}

	private static void downSorting() {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 12; j++) {
				if(field[j][i] != '.') {
					stack.push(field[j][i]);
				}
			}
			for(int j = 11; j >= 0; j--) {
				if(!stack.isEmpty()) {
					field[j][i] = stack.pop();
				} else {
					field[j][i] = '.';
				}
			}
		}
	}

	private static void BFS(int x, int y) {
		Stack<Integer> stack = new Stack<>(); // 좌표값을 보관하기 위한 stack
		int sameColorCnt = 0; // 같은 수 카운트
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);
		queue.add(y);
		char curColor = field[x][y];
		visited[x][y] = true;
		stack.add(y);
		stack.add(x);
		sameColorCnt++;
		
		while(!queue.isEmpty()) {
			int curX = queue.poll();
			int curY = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = curX + dx[i];
				int nc = curY + dy[i];
				if(nr>=0&&nr<12&&nc>=0&&nc<6&&!visited[nr][nc]&&curColor==field[nr][nc]) {
					sameColorCnt++;
					visited[nr][nc] = true;
					queue.add(nr);
					queue.add(nc);
					stack.add(nc); // nc, nr순으로 넣어야 꺼낼떄 nr, nc순이니
					stack.add(nr);
				}
			}
		}
		if(sameColorCnt >= 4) {
			for(int i = 0; i < sameColorCnt; i++) {
				if(!stack.isEmpty()) {
					list.add(stack.pop()); // x좌표
					list.add(stack.pop()); // y좌표
				}
			}
		}
	}
}