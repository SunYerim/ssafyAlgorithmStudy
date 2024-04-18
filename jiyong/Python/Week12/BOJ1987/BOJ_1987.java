import java.io.*;
import java.util.*;

class Main{
    static int R, C, maxDepth;
    static boolean visited[] = new boolean[26];
    static char[][] map;
    static int[] deltaR ={1, -1, 0, 0};
    static int[] deltaC ={0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        maxDepth = 0;
        st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int r=0; r<R; r++) {
            String line = in.readLine();
            for(int c=0; c<C; c++) {
                map[r][c] = line.charAt(c);
            }
        }
        visited[map[0][0] -'A'] = true;
        dfs(0,0, 1);
        System.out.println(maxDepth);
    }
    static void dfs(int r, int c, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        for(int d=0; d<4; d++) {
            int nr = r + deltaR[d];
            int nc = c + deltaC[d];
            if(isIn(nr, nc) && !visited[map[nr][nc]-'A']) {
                visited[map[nr][nc]-'A'] = true;
                dfs(nr, nc, depth+1);
                visited[map[nr][nc]-'A'] = false;
            }
        }
    }
    static boolean isIn(int r, int c) {
        return 0<=r && r<R && 0<=c && c<C;
    }
}