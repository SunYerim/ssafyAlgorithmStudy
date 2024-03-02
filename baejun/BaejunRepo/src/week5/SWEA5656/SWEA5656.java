package week5.SWEA5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA5656 {

	static int[][] arrays;
	static int[][] arraysCopy;
	static boolean[][] visited;
	static int N;
	static int W;
	static int H;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Integer> numberOfCases; // N단위
	static boolean[] visitForComb;
	static int minValue;
	static int[] comb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#" + testCase + " ");
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			minValue = Integer.MAX_VALUE;
			arrays = new int[H][W];
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < W; j++) {
					arrays[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			numberOfCases = new ArrayList<>();
			comb = new int[N];
			combination(0);
			/* N번만큼 총알쏘기 */
			for(int cases = 0; cases < numberOfCases.size(); cases+=N) {
				arraysCopy = new int[H][W];
				for(int i = 0; i < H; i++) {
					for(int j = 0; j < W; j++) {
						arraysCopy[i][j] = arrays[i][j];
					}
				}
				for(int i = cases; i < cases+N; i++) {
					boolean flag = false;
					for(int j = 0; j < H; j++) {
						if(arraysCopy[j][numberOfCases.get(i)] != 0) {
							flag = true;
							break;
						}
					}
					if(!flag) continue;
					visited = new boolean[H][W];
					bfs(numberOfCases.get(i));
					arrangeArrays();
				}
				int cnt = 0;
				for(int[] arr : arraysCopy) {
					for(int e : arr) {
						if(e != 0) cnt++;
					}
				}
				if(minValue > cnt) minValue = cnt;
			}
			sb.append(minValue).append("\n");
		}
		System.out.println(sb);
	}


	private static void combination(int cnt) {
		//기저조건
		if(cnt == N) {
			for (int is : comb) {
				numberOfCases.add(is);
			}
			return;
		}

		//유도조건
		for(int i = 0; i < W; i++) {
			comb[cnt] = i;
			combination(cnt+1);
		}
	}


	private static void arrangeArrays() {
		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < W; i++) {
			for(int j = 0; j < H; j++) {
				if(arraysCopy[j][i] != 0) {
					stack.add(arraysCopy[j][i]);
				}
			}
			for(int j = H-1; j >= 0; j--) {
				if(stack.isEmpty()) arraysCopy[j][i] = 0;
				else arraysCopy[j][i] = stack.pop();
			}
		}
	}


	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < H; i++) {
			visited[i][start] = true;
			if (arraysCopy[i][start] == 0) continue;
			if (arraysCopy[i][start] == 1) {
				arraysCopy[i][start] = 0;
				return;
			}
			queue.offer(i);
			queue.offer(start);
			break;
		}
		while(!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			if (arraysCopy[x][y] == 1) {
				arraysCopy[x][y] = 0;
				continue;
			}
			for(int i = 0; i < 4; i++) {
				int nr = x + dx[i];
				int nc = y + dy[i];
				if(nr>=0&&nr<H&&nc>=0&&nc<W&&!visited[nr][nc]) {
					visited[nr][nc] = true;
					if(arraysCopy[nr][nc] > 1) {
						queue.offer(nr);
						queue.offer(nc);							
					} else if (arraysCopy[nr][nc] == 1) arraysCopy[nr][nc] = 0;
				}
				for(int j = 1; j < arraysCopy[x][y]-1; j++) {
					if(i == 0) {
						nr += j;
					} else if(i == 1) {
						nr -= j;
					} else if(i == 2) {
						nc += j;
					} else if(i == 3) {
						nc -= j;
					}
					if(nr>=0&&nr<H&&nc>=0&&nc<W&&!visited[nr][nc]) {
						visited[nr][nc] = true;
						if(arraysCopy[nr][nc] > 1) {
							queue.offer(nr);
							queue.offer(nc);							
						} else if (arraysCopy[nr][nc] == 1) arraysCopy[nr][nc] = 0;
					}
					if(i == 0) {
						nr -= j;
					} else if(i == 1) {
						nr += j;
					} else if(i == 2) {
						nc -= j;
					} else if(i == 3) {
						nc += j;
					}
				}
			}
			arraysCopy[x][y] = 0;
		}
	}
}