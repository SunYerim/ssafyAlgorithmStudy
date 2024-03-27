package week9.PGM43238;

import java.io.*;
import java.util.*;

public class PGM43238 {
    static long bestTime;
    
    public static void main(String[] args) {
		// solution(); // case돌리기
	}
    
    public long solution(int n, int[] times) {        
        Arrays.sort(times); // 이분 탐색을 위한 정렬        
        long left = 1;
        long right = times[times.length-1] * (long)n; // worst : 모두가 가장 오래걸리는 심사대로
        
        bestTime = right;
        binarySearch(left, right, n, times);
        
        return bestTime;
    }
    
    public void binarySearch(long left,long right,int n,int times[]) {
        long mid = (left + right) / 2;
        
        if(left <= right) {
            long cnt = 0;
            boolean flag = false;
            for(int i = 0; i < times.length; i++) { // 이 시간에 모든인원 수속 가능한지 체크
                cnt += mid / (long)times[i];
                if(cnt >= (long)n) {
                    flag = true;
                    break;
                }
            }
            if(flag) { // 가능한 경우
                right = mid - 1;
                if(bestTime > mid) bestTime = mid;
                binarySearch(left, right, n, times);
            } else { // 안되는 경우
                left = mid + 1;
                binarySearch(left, right, n, times);
            }
        }
        return;
    }
}