package week19.PGS150368;

/*logic
    1. 생각해봤을때, 그럼 for문을 1~40까지 돌면서, 그때 사용자 수와 판매액을 갱신해 나가면 될 거 같다는 생각이 듬.
    2. answer = [가입자, 판매금액] 꼴.
    3. */
import java.util.*;

class PGS150368 {
    static class Emoticon{
        double price;
        double percent;
        Emoticon(double price, double percent){
            this.price = price;
            this.percent = percent;
        }
    }
    // 할인율
    static double[] sales = {0.1, 0.2, 0.3, 0.4};
    static List<Emoticon> imsi = new ArrayList<>();
    static int maxJoin=Integer.MIN_VALUE, maxPrice = Integer.MIN_VALUE;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];

        //중복 순열을 위한 재귀함수
        dfs(0, users, emoticons);

        answer[0] = maxJoin;
        answer[1] = maxPrice;

        return answer;
    }

    public static void dfs(int depth, int[][] users, int[] emoticons){
        if(depth == emoticons.length){
            int total = 0;
            int join = 0;
            for(int i=0;i<users.length;i++){
                int userPercent = users[i][0];
                int userPrice = users[i][1];

                //개인이 이모티콘을 구매한 금액의 합
                int sum = 0;
                for(int j=0;j<imsi.size();j++){
                    Emoticon cur = imsi.get(j);
                    double curPrice = cur.price;
                    double curPercent = cur.percent;
                    //이모티콘의 할인율이 유저가 정한 할인율 이상이면 전체 구입
                    if(curPercent >= userPercent)
                        sum += curPrice;
                }
                if(sum >= userPrice)
                    join++;
                else{
                    total += sum;
                }

                if(maxJoin < join){
                    maxPrice = total;
                    maxJoin = join;
                }else if(maxJoin == join && maxPrice < total){
                    maxPrice = total;
                }
            }
            return;
        }

        for(int i=0;i<sales.length;i++){
            //Emoticon클래스를 자료형으로 가진 리스트에 할인된 가격 및 할인율을 담아준다.
            //중복 순열이기 때문에 visited와 같은 방문체크를 할 필요가 X
            imsi.add(new Emoticon(((1-sales[i])*(emoticons[depth])), (100*sales[i])));
            dfs( depth+1, users, emoticons);
            imsi.remove(imsi.size()-1);

        }
    }
}