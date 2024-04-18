package week12.BOJ_G4_13701_중복제거;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class BOJ_G4_13701_중복제거 {

	static LinkedHashSet<Integer> set;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		set = new LinkedHashSet<>();
		
		for(int i=0;i<s2.length;i++) {
			set.add(Integer.parseInt(s2[i]));
		}
		
		list = new ArrayList<>(set);
		
		for(int i=0;i<list.size();i++) {
			bw.append(list.get(i)+""+" ");
		}
		
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}
