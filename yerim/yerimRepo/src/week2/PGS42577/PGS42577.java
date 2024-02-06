package week2.PGS42577;

import java.util.*;
public class PGS42577 {
    public boolean solution(String[] phone_book) {

        Map<String, Integer> phoneMap = new HashMap<>();

        for (int i = 0; i < phone_book.length; i++) {
            phoneMap.put(phone_book[i], i);
        }

        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                if (phoneMap.containsKey(phone_book[i].substring(0, j))){
                    return false;
                }
            }
        }
        return true;


    }
}