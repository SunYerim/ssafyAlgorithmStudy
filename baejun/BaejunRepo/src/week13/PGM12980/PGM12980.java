package week13.PGM12980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class PGM12980 {
	
	public static void main(String[] args) throws IOException {
		
	}
	
	public class Solution {
	    public int solution(int N) {
	        int answer = 0;
	        
	        while(N > 1) {
	            if(N % 2 == 0) {
	                N /= 2;
	            } else {
	                N--;
	                answer++;
	            }
	        }
	        
	        return answer+1;
	    }
	}
}