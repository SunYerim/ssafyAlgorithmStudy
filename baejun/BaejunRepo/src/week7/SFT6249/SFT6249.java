package week7.SFT6249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SFT6249 {
	static int N, M, minLength;
	static char[][] array;
	static ArrayList<char[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		//입력받기
		array = new char[N][M];
		for(int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j);
			}
		}
		minLength = Integer.MAX_VALUE;
		list = new ArrayList<>();
		DFS(0);
		System.out.println(minLength);
	}

	private static void DFS(int cnt) {
        if(minLength < list.size()) return; // 가지치기, 의미없는 경우의 수 cut (이 코드 한줄로 시간초과 통과,,)
		// 기저조건
		if(cnt == N) {
			if(minLength > list.size()) minLength = list.size();
			return;
		}
		// 유도조건
		for(int i = 0; i < list.size(); i++) {
			boolean flag = false; 
			for(int j = 0; j < list.get(i).length; j++) {// 해당 리스트의 char배열에 fit한지 검사
				if(array[cnt][j] == '.' || list.get(i)[j] == '.' || array[cnt][j] == list.get(i)[j]) continue;
				flag = true;
				break;
			}
			if(!flag) {
				char[] copy = new char[M];
				for(int k = 0; k < M; k++) {
					copy[k] = list.get(i)[k];
					if(array[cnt][k] == '.') continue;
					list.get(i)[k] = array[cnt][k];
				}
				DFS(cnt+1); // 바꾼채로 dfs 태우고 copy로 원상복구
				for(int k = 0; k < M; k++) {
					list.get(i)[k] = copy[k];
				}
			}
		}
		// 새로운 초염기를 만들어내는 경우까지 check
		char[] addChar = new char[M];
		for(int i = 0; i < M; i++) {
			addChar[i] = array[cnt][i];
		}
		list.add(addChar);
		DFS(cnt+1);
		list.remove(list.size()-1);
	}
}