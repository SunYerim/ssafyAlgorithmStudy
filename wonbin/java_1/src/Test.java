import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		
		String c1 = "abcdefghijklmnopqrstuvwxyz";
		String c2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		int[] n = new int[c1.length()];
		int[] np = new int[1000005];
		int count = 0;
		int tmp = 0;
		int max = 1;
		
		for(int i=0;i<s.length();i++) {
			for(int j=0;j<c1.length();j++) {
				if(s.charAt(i) == c1.charAt(j)) {
					n[j]++;
				}
				if(s.charAt(i) == c2.charAt(j)) {
					n[j]++;
				}
			}
		}
		
		for(int i = 0;i<c1.length();i++) {
			if(max<=n[i]) {
				max = n[i];
				np[i] = n[i];
			}
		}
		
		for(int i = 0;i<c1.length();i++) {
			if(max == np[i]) {
				count++;
				tmp = i;
			}
		}
		
		if(count > 1) {
			bw.write("?"+"\n");
		}
		else {
			bw.write(c2.charAt(tmp)+""+"\n");
		}
		bw.flush();
		bw.close();
	}
}
