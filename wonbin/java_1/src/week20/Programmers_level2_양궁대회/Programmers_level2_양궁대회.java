package week20.Programmers_level2_양궁대회;

public class Programmers_level2_양궁대회 {

	static int[] path;
    static int[] answer = {-1};
    static int max = Integer.MIN_VALUE;
    
    public int[] solution(int n, int[] info) {
        path = new int[11];
        
        per(n,0,info);
        
        if(max == -1) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
    
    static void per(int n, int cnt, int[] info) {
        
        if(cnt == n) {
            int tmp = score(info);
            
            if(max<=tmp) {
                max = tmp;
                answer = path.clone();
            }
            
            return;
        }
        
        for(int i=0; i<info.length && path[i]<=info[i];i++){
            path[i]++;
            per(n, cnt+1, info);
            path[i]--;
        }
    }
    
    static int score(int[] info) {
        int lion = 0;
        int appech = 0;
        
        for(int i=0;i<path.length;i++) {
            if(info[i] ==0 && path[i] == 0)
                continue;
            if(info[i] >= path[i]) 
                appech += 10 - i;
            else
                lion += 10 - i;
        }
        
        int diff = lion - appech;
        
        if(diff <= 0 )
            diff = -1;
        
        return diff;
    }
}
