package week4.BOJ_17471_게리맨더링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_17471_게리맨더링 {
	
	static int N;
	static int[] path = new int[11];
	static int zero_count = 0;
	static int answer;
	static int[][] graph = new int[11][11];
	static int min = Integer.MAX_VALUE;
	static ArrayList<gaeri> list = new ArrayList<>();
	static int sum = 0;
	static int sum2 = 0;
	static int[] num_count = new int[11];
	
	static class gaeri{
		int idx;
		int value;
		public gaeri(int idx, int value) {
			super();
			this.idx = idx;
			this.value = value;
		}
		@Override
		public String toString() {
			return "gaeri [idx=" + idx + ", value=" + value + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=1;i<=N;i++) { // 선거지역 번호 및 인원수 객체 저장
			list.add(new gaeri(i, Integer.parseInt(s2[i-1])));
		}
		
		for(int i=1;i<=N;i++) { // 선거지도 그래프 그리기
			s = br.readLine();
			s2 = s.split(" ");
			if(Integer.parseInt(s2[0]) == 0)
				zero_count++;
			else {
				for(int j = 0;j<s2.length - 1;j++) {
					graph[i][j] = Integer.parseInt(s2[j+1]);
				}
			}
		}
		
		
		if(N == 2) {
			answer = Math.abs(list.get(0).value - list.get(1).value);
		}
		else if(N - zero_count < 2) // 선거구를 2가지로 나눌 수 없는 경우
			answer = -1;
		else {
			for(int i=1;i<N;i++) {
				comb(1,0,i);
			}
			answer = min;
		}
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		
		bw.append(answer + "" + "\n");
		bw.flush();
		bw.close();
		
	}
	
	static void comb(int start, int cnt, int R) {
		
		if(cnt == R) {
			ArrayList<Integer> arr = new ArrayList<>();
			int tmp = 0;
			int tmp2 = 0;
			sum = 0;
			sum2 = 0;
			for(int i=1;i<=N;i++) { // 1~6 저장
				arr.add(i);
			}
			
			for(int i=1;i<11;i++)
				if(path[i] != 0)
					arr.remove(Integer.valueOf(path[i])); // 조합된 수와 분리
			
			Arrays.fill(num_count, 0);
			for(int i=0;i<arr.size();i++) {
				num_count[arr.get(i)]++;
			}
			for(int i=1;i<10;i++) { // 조합된 수 무엇과 연결되었는지 검사
				if(path[i] != 0) {
					num_count[path[i]]++;
					dfs(path[i]); // 하나만 검사하면 되니 하나 검사하고 break;
					break;
				}
			}
			
			for(int i=1;i<11;i++) { // 조합된 수랑 체크된거랑 수량이 같은지 검사 = 연결이 잘 되어 있는가
				if(num_count[i] != 0) {
					for(int j=1;j<11;j++) {
						if(path[j] != 0 && path[j] == i)
							tmp++;
					}
				}
			}
			
			if(tmp == cnt) // 연결이 잘 되어 있다면 합 연산
			{
				for(int i=1;i<11;i++) {
					if(path[i] != 0) {
						sum += list.get(path[i] - 1).value;
					}
				}
			}
			else
				return;
			
			Arrays.fill(num_count, 0);
			for(int i=1;i<11;i++) {
				if(path[i] != 0)
					num_count[path[i]]++;
			}
			dfs(arr.get(0));
			
			for(int i=1;i<11;i++) {
				if(num_count[i] != 0) {
					for(int j=0;j<arr.size();j++) {
						if(i == arr.get(j))
							tmp2++;
					}
				}
			}
			
			if(tmp2 == arr.size())
			{
				for(int i=0;i<arr.size();i++) {
					sum2 += list.get(arr.get(i) - 1).value;
				}
			}
			else return;
			
			if(min>Math.abs(sum - sum2))
				min = Math.abs(sum - sum2);
			
			return;
		}
		
		for(int i=start;i<=N;i++) {
			path[i] = i;
			comb(i+1, cnt+1, R);
			path[i] = 0;
		}
		
	}
	
	static void dfs(int cnt) {
		
		for(int i=0;i<11;i++) {
			if(graph[cnt][i] != 0 && num_count[graph[cnt][i]] == 0) {
				num_count[graph[cnt][i]]++;
				dfs(graph[cnt][i]);
			}
			
		}
		
	}
}
