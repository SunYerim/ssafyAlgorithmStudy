package week19.Programmers_level3_스티커모으기2;

public class Programmers_level3_스티커모으기2 {

	
	static int[] stic;
    static int s_size;
    static int count;
    static int ck;
    
    public int solution(int sticker[]) {
        int answer = 0;
        s_size = sticker.length;
        stic = new int[s_size];

        if(s_size % 2 == 0) {
            int tmp = 0;
            for(int i=0;i<s_size;i+=2) {
                tmp += sticker[i];
            }
            count = tmp;
            tmp = 0;
            for(int i=1;i<s_size;i+=2) {
                tmp += sticker[i];
            }
            count = Math.max(tmp, count);
            answer = count;
        }
        else {
            for(int i=0;i<s_size;i++) {
                int idx = i;
                count = 0;
                stic = new int[s_size];
                while(true) {
                    ck = 1;
                    if(stic[idx] == 0) {
                        count += sticker[idx];
                        stic[idx]++;
                        
                        if(idx-1 < 0){
                            stic[s_size-1]++;
                        }
                        else{
                            stic[idx-1]++;
                        }
                        
                        if(idx+1 == s_size) {
                            stic[0]++;
                        }
                        else{
                            stic[idx+1]++;
                        }
                    }
                    
                    for(int j=0;j<s_size;j++) {
                        if(stic[j] == 0) {
                            ck = 0;
                            break;
                        }
                    }
                    
                    if(ck == 1)
                        break;
                    idx++;
                    if(idx == s_size)
                        idx = 0;
                }
                answer = Math.max(count, answer);
            }
        }

        return answer;
    }
}
