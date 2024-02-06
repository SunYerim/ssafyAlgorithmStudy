package week2.PGS84512;

public class PGS84512 {
    public int solution(String word) {
        int answer = 0;
        String temp = ""; // 나중에 word랑 동일한지 비교하기 위함.
        String[] wordList = {"A", "E", "I", "O", "U"};

        for (int i = 0; i < 5; i++) {
            temp = wordList[i];
            answer++;
            if (temp.equals(word)) return answer;
            for (int j = 0; j < 5; j++) {
                temp = wordList[i]+wordList[j];
                answer++;
                if (temp.equals(word)) return answer;
                for (int k = 0; k < 5; k++) {
                    temp = wordList[i]+wordList[j]+wordList[k];
                    answer++;
                    if (temp.equals(word)) return answer;
                    for (int l = 0; l < 5; l++) {
                        temp = wordList[i]+wordList[j]+wordList[k]+wordList[l];
                        answer++;
                        if (temp.equals(word)) return answer;

                        for (int m = 0; m < 5; m++) {
                            temp = wordList[i]+wordList[j]+wordList[k]+wordList[l]+wordList[m];
                            answer++;
                            if (temp.equals(word)) return answer;
                        }
                    }

                }
            }
        }
        return answer;
    }
}
