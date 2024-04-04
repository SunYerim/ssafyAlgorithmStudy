package week7.Softeer_HSAT6회정기코딩인증평가기출_염기서열커버;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Softeer_HSAT6회정기코딩인증평가기출_염기서열커버 {

	static int N;
	static int M;
	static ArrayList<String> list = new ArrayList<>();
	static ArrayList<String> list2 = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		int tmp = 0;
		int tmp2 = 0;
		
		for(int i=0;i<N;i++) {
			s=br.readLine();
			tmp = 0;
			if(i==0)
				list.add(s);
			else {
				for(int j=0;j<list.size();j++) {
					String st = list.get(j);
					String st2 = "";
					for(int k=0;k<M;k++) {
						if(st.charAt(k) != s.charAt(k)) {
							if(st.charAt(k) == '.') {
								st2 += s.charAt(k);
								continue;
							}
							else if(s.charAt(k) == '.'){
								st2 += st.charAt(k);
								continue;
							}
							tmp++;
							break;
						}
						st2 += st.charAt(k);
					}
					if(st2.length() == M) {
						list2.add(st2);
						tmp2 = j;
						break;
					}
				}
				if(tmp == list.size())
					list.add(s);
				if(list2.size() != 0) {
					list.remove(tmp2);
					list.add(list2.get(0));
					list2.clear();
				}
			}
		}
		
		bw.append(list.size()+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}
