package week7.PGM72413;

class Solution {
	public int solution(int n, int s, int a, int b, int[][] fares) {//지점 개수, 시작지점, a도착지, b도착지, 간선정보
        int answer = Integer.MAX_VALUE;
        
        //플로이드와샬
        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i < n+1; i++) {
        	for(int j = 1; j < n+1; j++) {
        		if(i == j) dist[i][i] = 0;
        		else dist[i][j] = 20000001;
        	}
        }
        
        for(int[] fare : fares) {
        	dist[fare[0]][fare[1]] = fare[2];
        	dist[fare[1]][fare[0]] = fare[2];
        }
        
        for(int i = 1; i < n+1; i++) {
        	for(int j = 1; j < n+1; j++) {
        		for(int k = 1; k < n+1; k++) {
                	dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }
        
        for(int i = 1; i < n+1; i++) {
        	for(int j = 1; j < n+1; j++) {
        		if(answer > dist[s][i] + dist[i][a] + dist[i][b]) answer = dist[s][i] + dist[i][a] + dist[i][b];
        	}
        }
        
        for(int i = 1; i < n+1; i++) {
        	for(int j = 1; j < n+1; j++) {
        		System.out.print(dist[i][j] + " ");
        	}
        	System.out.println();
        }
        
        return answer;
    }
}

public class PGM72413 {
	public static void main(String[] args) {
		// testcase
		Solution sl = new Solution();
		int[][] fare = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		System.out.println(sl.solution(6, 4, 6, 2, fare));
	}	
}
