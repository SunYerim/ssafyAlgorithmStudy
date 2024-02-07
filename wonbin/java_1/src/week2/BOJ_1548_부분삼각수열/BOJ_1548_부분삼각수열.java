package week2.BOJ_1548_부분삼각수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class triangle{
	int a1;
	int b1;
	int c1;
	
	triangle(int a1, int b1, int c1){
		this.a1 = a1;
		this.b1 = b1;
		this.c1 = c1;
	}

	@Override
	public String toString() {
		return "triangle [a1=" + a1 + ", b1=" + b1 + ", c1=" + c1 + "]";
	}
	
}

public class BOJ_1548_부분삼각수열 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String[] s2 = s.split(" ");
		int answer = 0;
		long a;
		long b;
		long c;
		int tmp =0;
		List<triangle> list = new ArrayList<>();
		long[] arr = new long[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(s2[i]);
		}
		
		if(N == 1) answer =1;
		else if(N==2) answer =2;
		else {
			for(int i=0;i<N;i++) {
				c = arr[i];
				arr[i] = 0;
				for(int j=0;j<N - 1;j++) {
					a=arr[j];
					if(arr[j] == 0) continue;
					for(int k=j+1;k<N;k++) {
						b = arr[k];
						if(arr[k] == 0) continue;
						else {
							if(a+b>c) {
								if(list.isEmpty()) {
									list.add(new triangle(j,k,i));
									list.add(new triangle(k,j,i));
									list.add(new triangle(j,i,k));
									list.add(new triangle(k,i,j));
									list.add(new triangle(i,j,k));
									list.add(new triangle(i,k,j));
									answer++;
								}
								else {
									for(triangle tri : list) {
										if(new triangle(j,k,i).equals(tri)) {
											tmp =1;
											break;
										}
									}
									if(tmp == 0) {
										list.add(new triangle(j,k,i));
										list.add(new triangle(k,j,i));
										list.add(new triangle(j,i,k));
										list.add(new triangle(k,i,j));
										list.add(new triangle(i,j,k));
										list.add(new triangle(i,k,j));
										answer++;
									}
									else tmp=0;
								}
							}
						}
					}
				}
				arr[i] = c;
			}
		}
		
		sb.append(answer);
		System.out.println(sb);
	}
}