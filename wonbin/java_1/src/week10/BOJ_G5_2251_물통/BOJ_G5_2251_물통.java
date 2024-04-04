package week10.BOJ_G5_2251_물통;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

public class BOJ_G5_2251_물통 {
	
	static TreeSet<Integer> list = new TreeSet<>();
	static int capaA;
	static int capaB;
	static int capaC;
	static boolean[][][] visited = new boolean[201][201][201];
	
	static class water{
		int a;
		int b;
		int c;
		
		public water(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		capaA = Integer.parseInt(s2[0]);
		capaB = Integer.parseInt(s2[1]);
		capaC = Integer.parseInt(s2[2]);
		
		bfs();
		
		for(int a : list) {
			bw.append(a+""+" ");
		}
		bw.append("\n");
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		Queue<water> que = new ArrayDeque<>();
		que.add(new water(0,0,capaC));
		
		while(!que.isEmpty()) {
			water wt = que.poll();
			
			int a = wt.a;
			int b = wt.b;
			int c = wt.c;
			
			if(visited[a][b][c] == true)
				continue;
			
			visited[a][b][c] = true;
			
			if(a==0)
				list.add(c);
			
			// A -> B
			if(a+b >= capaB) // 넘치는 경우
				que.add(new water(a-(capaB-b), capaB, c));
			else
				que.add(new water(0, a+b, c));
			
			// A -> C
			if(a+c >= capaC) // 넘치는 경우
				que.add(new water(a-(capaC-c), b, capaC));
			else
				que.add(new water(0, b, a+c));
			
			// B -> C
			if(b+c >= capaC) // 넘치는 경우
				que.add(new water(a, b - (capaC - c), capaC));
			else
				que.add(new water(a, 0, b+c));
			
			// B -> A
			if(a+b >= capaA) // 넘치는 경우
				que.add(new water(capaA, b - (capaA - a), c));
			else
				que.add(new water(a+b, 0, c));
			
			// C -> B
			if(b+c >= capaB) // 넘치는 경우
				que.add(new water(a, capaB, c - (capaB - b)));
			else
				que.add(new water(a, b+c, 0));
			
			// C -> A
			if(a+c >= capaA) // 넘치는 경우
				que.add(new water(capaA, b, c - (capaA - a)));
			else
				que.add(new water(a+c, b, 0));
		}
		
	}
}
