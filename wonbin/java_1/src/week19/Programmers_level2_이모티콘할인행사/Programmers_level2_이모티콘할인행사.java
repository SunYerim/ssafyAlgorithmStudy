package week19.Programmers_level2_이모티콘할인행사;

public class Programmers_level2_이모티콘할인행사 {

	static double[] discount;
    static int[] emoticons2;
    static double[] path;
    static int N;
    static int R;
    static int plus;
    static int sum;
    static int[] answer2;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        N = 4;
        R = emoticons.length;
        discount = new double[4];
        path = new double[R];
        emoticons2 = new int[R];
        double tmp = 0.4;
        answer2 = new int[2];
        
        for(int i=0;i<4;i++) {
            discount[i] = tmp;
            tmp -= 0.1;
        }
        
        //System.out.println(emoticons.length);
        
        permutation(N,0, users, emoticons);
        
        for(int i=0;i<2;i++){
            answer[i] = answer2[i];
        }
        
        return answer;
    }
    
    static void permutation(int n, int cnt, int[][] users, int[] emoticons) {
        
        if(cnt == R) {
            
            plus = 0;
            sum = 0;
            int[] count = new int[users.length];
            
            for(int i=0;i<cnt;i++) {
                emoticons2[i] = (int)(emoticons[i] - emoticons[i]*path[i]);
            }
            System.out.println();
            
            for(int i=0;i<users.length;i++) {
                for(int j=0;j<path.length;j++) {
                    if((int)(path[j]*100)>=users[i][0]) {
                        count[i] += emoticons2[j];
                    }
                }
                if(count[i] >= users[i][1]) {
                    plus++;
                }
                else {
                    sum += count[i];
                }
            }
            
            if(answer2[0] < plus) {
                answer2[0] = plus;
                answer2[1] = sum;
            }
            else if(answer2[0] == plus){
                answer2[1] = Math.max(answer2[1], sum);
            }
            
            return;
        }
        
        for(int i=0;i<n;i++) {
            path[cnt] = discount[i];
            permutation(n, cnt+1, users, emoticons);
        }
    }
}
