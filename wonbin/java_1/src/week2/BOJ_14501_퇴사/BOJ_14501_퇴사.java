package week2.BOJ_14501_퇴사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Pair{
	int ti;
	int pi;
	
	Pair(int ti, int pi){
		this.ti = ti;
		this.pi = pi;
	}
}

public class BOJ_14501_퇴사 {
	
	
	static int tmp;
	static int day = 0;
	static int pay = 0;
	static int max = Integer.MIN_VALUE;
	static List<Pair> pairs;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 일하는 날짜 수 입력
		pairs = new ArrayList<>();
		
		for(int i=0; i<N;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			Pair pr = new Pair(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
			pairs.add(pr);
		} // 각 요일마다 일하는 기간 및 얻는 돈의 양 입력 pair에 저장
		
		run(0,0);
		
		bw.write(max+""+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void run(int cnt, int money) {
		if(cnt>=N) {
			max = Math.max(max, money);
			return;
		}
		
		if(cnt + pairs.get(cnt).ti<=N) run(cnt + pairs.get(cnt).ti, money + pairs.get(cnt).pi); // N을 넘지 않으면 계속 더함
		else run(cnt + pairs.get(cnt).ti, money); // 만약 넘으면 money 합치지 않음
		
		run(cnt+1, money); // 해당 index 미포함
	}

}
