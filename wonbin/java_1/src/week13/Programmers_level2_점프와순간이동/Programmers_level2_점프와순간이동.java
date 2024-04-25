package week13.Programmers_level2_점프와순간이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programmers_level2_점프와순간이동 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		int n = Integer.parseInt(br.readLine());
        
        while(true){
            if(n == 1){
                ans++;
                break;
            }
            
            if(n%2 == 1){
                ans++;
                n--;
            }
            else {
                n = n/2;
            }
        }
        
        System.out.println(ans);

    }
}
