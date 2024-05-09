package week15.Programmers_level2_석유시출;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Programmers_level2_석유시출 {
	
	static int count = 0;
    static int[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int N;
    static int M;
    static int[][] land;
    static int[][] land2;
    static int[] oil;
    
    static class point{
        int y;
        int x;
        
        public point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    
    public static void main(String[] args) throws IOException{
    	
    	int answer = 0;
        N = land.length;
        M = land[0].length;
        land = new int[N][M];
        land2 = new int[N][M];
        visited = new int[N][M];
        oil = new int[M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                land2[i][j] = land[i][j];
            }
        }
        
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(land2[j][i] == 1 && visited[j][i] == 0){
                    visited[j][i] = 1;
                    count = 1;
                    bfs(j, i);
                }
            }
        }
        
        for(int i : oil){
            answer = Math.max(answer, i);
        }
        
        System.out.println(answer);
		
	}
    
    static void bfs(int y, int x){
        Queue<point> que = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        que.offer(new point(y, x));
        set.add(x);
        
        while(!que.isEmpty()){
            point p = que.poll();
            
            for(int i=0;i<4;i++){
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
            
                if(ny>=0 && ny<N && nx>=0 && nx<M && land2[ny][nx] == 1 && visited[ny][nx] == 0){
                    visited[ny][nx] = 1;
                    count++;
                    set.add(nx);
                    que.offer(new point(ny, nx));
                }
            }
        }
        
        for(int c : set){
            oil[c] += count;
        }
        
    }
    
    

}
