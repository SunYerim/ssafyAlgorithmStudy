package week20.PGM150369;

import java.util.*;
import java.io.*;

public class PGM150369 {
	static int capCopy;
	static long totalDistance = 0;
	static int[] deliveriesCopy, pickupsCopy;
	static Stack<Integer> delieverValid, pickupsValid;

	class Solution {
		public long solution(int cap, int n, int[] deliveries, int[] pickups) {
			/* static 사용 위해서 copy */
			capCopy = cap;
			deliveriesCopy = deliveries;
			pickupsCopy = pickups;

			delieverValid = new Stack<>();
			pickupsValid = new Stack<>();

			for(int i = 0; i < deliveries.length; i++) {
				if(deliveries[i] != 0) {
					delieverValid.push(i);
				}
			}
			for(int i = 0; i < pickups.length; i++) {
				if(pickups[i] != 0) {
					pickupsValid.push(i);
				}
			}

			while(!delieverValid.isEmpty() || !pickupsValid.isEmpty()) {
				operation();
			}

			return totalDistance;
		}

		public void operation() {
			int delieverCnt = 0;
			int pickupCnt = 0;

			int delieverDistance = 0;
			int pickupDistance = 0;

			// 배달 stack 이용해서 다녀오기
			while(!delieverValid.isEmpty() && delieverCnt < capCopy) {
				int idx = delieverValid.pop();

				if(deliveriesCopy[idx] <= (capCopy - delieverCnt)) {
					delieverCnt += deliveriesCopy[idx];
				} else {
					delieverCnt = capCopy;
					deliveriesCopy[idx] -= (capCopy - delieverCnt);
					delieverValid.push(idx);
				}
				if(delieverDistance == 0) delieverDistance = idx+1;
			}
			// 수거 stack 이용해서 다녀오기
			while(!pickupsValid.isEmpty() && pickupCnt < capCopy) {
				int idx = pickupsValid.pop();
				int maxPick = capCopy - pickupCnt;

				if(pickupsCopy[idx] <= maxPick) {
					pickupCnt += pickupsCopy[idx];
				} else {
					pickupCnt = capCopy;
					pickupsCopy[idx] -= maxPick;
					pickupsValid.push(idx);
				}
				if(pickupDistance == 0) pickupDistance = idx+1;
			}

			totalDistance += (delieverDistance > pickupDistance) ? delieverDistance*2 : pickupDistance*2;
		}
	}
}
