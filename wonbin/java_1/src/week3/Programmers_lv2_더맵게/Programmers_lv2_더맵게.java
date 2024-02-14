package week3.Programmers_lv2_더맵게;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Programmers_lv2_더맵게 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> p = new PriorityQueue<>();
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		int sum = 0;
		int answer = 0;
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i=0;i<s2.length;i++) {
			p.offer(Integer.parseInt(s2[i]));
		}
		
		
		while(p.peek()<K) {
			if(p.size()<=1) {
				answer = -1;
				break;
			}
			sum += p.poll();
			sum += p.poll()*2;
			answer++;
			p.add(sum);
			sum = 0;
		}
		
		System.out.println(answer);
	}
}
