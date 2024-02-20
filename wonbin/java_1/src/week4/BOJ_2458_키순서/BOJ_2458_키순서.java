package week4.BOJ_2458_키순서;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_2458_키순서 {

	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> key = new ArrayList<>(); // 그래프 구현 배열
	static int[] in_V;
	static int[] out_V;
	static int count = 0;
	static ArrayList<ArrayList<Integer>> key_in = new ArrayList<>(); // 노드의 진입 간선의 종류를 전부 넣는 배열
	static int[] num_count;
	static int tmp = 0;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		in_V = new int[N+1];
		out_V = new int[N+1];
		num_count = new int[N+1];
		
		for(int i=0;i<N+1;i++) {
			key.add(new ArrayList<>());
		}
		
		for(int i=0;i<N+1;i++) {
			key_in.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			key.get(Integer.parseInt(s2[1])).add(Integer.parseInt(s2[0])); // 각 노드가 어떤 노드를 받고 있는지
			out_V[Integer.parseInt(s2[0])]++; // 나가는 간선
		}
		
		for(int i=1;i<N+1;i++) {
			dfs(i, i);
		}
		
		for(int i=1;i<N+1;i++) {
			Arrays.fill(num_count, 0); // 카운트 배열 0으로 초기화
			
			for(int j=0;j<key_in.get(i).size();j++) {
				num_count[key_in.get(i).get(j)]++; // 중복 처리
			}
			for(int j=1;j<N+1;j++) {
				if(num_count[j] != 0)
					in_V[i]++; // 진입 간선
			}
		}
		
		for(int i=1;i<N+1;i++) {
			if(in_V[i] + out_V[i]== N - 1) // 진입 간선 + 나가는 간선이 N - 1이면 위치를 알 수 있음
				count++;
		}
		
		for(int i=1;i<N+1;i++) { // 진입 간선이 없는 경우를 카운트
			if(key.get(i).size() == 0)
				tmp++;
		}
		if(tmp == 1) // 진입 간선이 없는 노드가 하나일 경우 위치를 알 수 있음 (젤 큼)
			count++;
		
		bw.append(count+""+"\n");
		bw.flush();
		bw.close();
	}
	
	static void dfs(int node,int fix) {
		
		for(int i=0;i<key.get(node).size();i++) {
			key_in.get(fix).add(key.get(node).get(i));
			dfs(key.get(node).get(i), fix);
		}
	}
}
