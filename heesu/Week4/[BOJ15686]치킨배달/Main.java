import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int[][] arr;
	static int[][] chicken = new int[13][2];
	static int index;
	static int[] selected;
	static int min_count = 10000;
	static int[][] dxdy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int home = 0;
	static Queue<Node> chickenQ;
	

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		index = 0;
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j<n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				arr[i][j] = temp;
				if(temp == 2) {
					chicken[index][0] = i;
					chicken[index++][1] = j; //치킨집 개수는 index
				}else if(temp == 1) {
					home++;
				}
			}
		}
		selected = new int[index];
		comb(0, 0);
		sb.append(min_count);
		
		System.out.println(sb);
	}
	
	public static int bfs() {
		int count = 0;
		int count_home = home;
		int[][] visited = new int[n][n];
		while(count_home>0 ) {
			Node chicken = chickenQ.poll();
			for(int d = 0; d<4; d++	) {
				int curx = chicken.p.x + dxdy[d][0];
				int cury = chicken.p.y + dxdy[d][1];
				if(curx >=0 && curx<n&&cury>=0&&cury<n&&visited[curx][cury] == 0) {
					visited[curx][cury] = 1;
					if(arr[curx][cury]==1) {	
						count_home--;
						count += (chicken.sum);
					}
					chickenQ.add(new Node(new Pair(curx, cury), chicken.sum+1));
				}
			}
		}
		return count;
	}
	
	
	public static void comb(int cnt, int st) {
		if(cnt >= m) {
			chickenQ = new ArrayDeque<>();
			 for (int i = 0; i < index; i++) {
		            if (selected[i] == 1) {
		                chickenQ.add(new Node(new Pair(chicken[i][0], chicken[i][1]), 1));
		            }
		        }
			int c = bfs();
			min_count = Math.min(min_count, c);
			
		}else {
			for(int i = st; i<index; i++) {
				if(selected[i] == 1) continue;
				selected[i] = 1;
				comb(cnt+1, i+1);
				selected[i] = 0;
			}
		}
	}
}

class Node {
	Pair p;
	int sum;
	
	public Node(Pair p, int sum) {
		super();
		this.p = p;
		this.sum = sum;
	}
	
}

class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}