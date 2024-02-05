package week2.Programmers_lv2_기능개발;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] sol = new int[100];
        int count = 0;
        int all = 0;
        int tmp = 0;
        
        for(int a: progresses){
            sol[all] = a;
            all++;
        }
        int[] answer2 = new int[all];
        int a2 = all;
        
        while(all != 0){
            count = 0;
            for(int i =0 ; i<a2;i++){
                if(sol[i]>=100){
                    if(i == 0 || sol[i - 1] == 0){
                        count++;
                        sol[i] = 0;
                    }
                }
                else if(sol[i] != 0){
                    sol[i] += speeds[i];
                }
            }
            if(count != 0){
                answer2[tmp++] = count;
                all -= count;
            }
        }
        int[] answer = new int[tmp];
        for(int i = 0;i<tmp;i++){
            answer[i] = answer2[i];
        }
        return answer;
    }
}
