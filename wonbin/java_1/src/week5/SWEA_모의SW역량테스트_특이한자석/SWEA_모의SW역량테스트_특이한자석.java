package week5.SWEA_모의SW역량테스트_특이한자석;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_특이한자석 {
	
	static int N;
	static int[] magnet1;
	static int[] magnet2;
	static int[] magnet3;
	static int[] magnet4;

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			bw.append("#"+test_case+""+" ");
			
			int answer = 0;
			int head[] = new int [4];
			magnet1 = new int[8];
			magnet2 = new int[8];
			magnet3 = new int[8];
			magnet4 = new int[8];
			N = Integer.parseInt(br.readLine());
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
				
			for(int j = 0;j<8;j++) {
				magnet1[j] = Integer.parseInt(s2[j]);
			}
				
			s = br.readLine();
			s2 = s.split(" ");
				
			for(int j = 0;j<8;j++) {
				magnet2[j] = Integer.parseInt(s2[j]);
			}
			
			s = br.readLine();
			s2 = s.split(" ");
				
			for(int j = 0;j<8;j++) {
				magnet3[j] = Integer.parseInt(s2[j]);
			}
			
			s = br.readLine();
			s2 = s.split(" ");
				
			for(int j = 0;j<8;j++) {
				magnet4[j] = Integer.parseInt(s2[j]);
			}
			
			for(int i=0;i<N;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				
				if(Integer.parseInt(s2[0]) == 1) {
					if(Integer.parseInt(s2[1]) == 1) {
						turn1(1);
					}
					else {
						turn0(1);
					}
				}
				else if(Integer.parseInt(s2[0]) == 2) {
					if(Integer.parseInt(s2[1]) == 1) {
						turn1(2);
					}
					else {
						turn0(2);
					}
				}
				else if(Integer.parseInt(s2[0]) == 3) {
					if(Integer.parseInt(s2[1]) == 1) {
						turn1(3);
					}
					else {
						turn0(3);
					}
				}
				else if(Integer.parseInt(s2[0]) == 4) {
					if(Integer.parseInt(s2[1]) == 1) {
						turn1(4);
					}
					else {
						turn0(4);
					}
				}
			}
			
			if(magnet1[0] == 0)
				head[0] = 0;
			else
				head[0] = 1;
			
			if(magnet2[0] == 0)
				head[1] = 0;
			else
				head[1] = 2;
			
			if(magnet3[0] == 0)
				head[2] = 0;
			else
				head[2] = 4;
			
			if(magnet4[0] == 0)
				head[3] = 0;
			else
				head[3] = 8;
			
			answer = head[0] + head[1] + head[2] + head[3];
			bw.append(answer+"");
			bw.append("\n");
			bw.flush();
			
		}
		
		bw.close();
	}
	
	
	static void turn1(int num) {
		int tmp1 = 0;
		int tmp2 = 0;
		int tmp3 = 0;
		int tmp = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		
		if(num == 1) { // 1번 마그넷 -------------------------------------------------------------------------------------------
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet1[7];
			for(int i=6;i>=0;i--) {
				magnet1[i+1] = magnet1[i];
			}
			magnet1[0] = tmp;
			
			if(tmp1 != 0) { //역방향 2번 마그넷 움직임
				tmp = magnet2[0];
				for(int i=0;i<7;i++) {
					magnet2[i] = magnet2[i+1];
				}
				magnet2[7] = tmp;
				count2++;
			}
			else if(tmp2 != 0 && count2 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[7];
				for(int i=6;i>=0;i--) {
					magnet3[i+1] = magnet3[i];
				}
				magnet3[0] = tmp;
				count3++;
			}
			else if(tmp3 != 0 && count3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[0];
				for(int i=0;i<7;i++) {
					magnet4[i] = magnet4[i+1];
				}
				magnet4[7] = tmp;
				count2++;
			}
		} // 2번 마그넷 -------------------------------------------------------------------------------------------
		else if(num == 2) {
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet2[7];
			for(int i=6;i>=0;i--) {
				magnet2[i+1] = magnet2[i];
			}
			magnet2[0] = tmp;
			
			if(tmp1 != 0) { // 1번 마그넷 움직임
				tmp = magnet1[0];
				for(int i=0;i<7;i++) {
					magnet1[i] = magnet1[i+1];
				}
				magnet1[7] = tmp;
			}
			else if(tmp2 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[0];
				for(int i=0;i<7;i++) {
					magnet3[i] = magnet3[i+1];
				}
				magnet3[7] = tmp;
				count3++;
			}
			
			if(count3 != 0 && tmp3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[7];
				for(int i=6;i>=0;i--) {
					magnet4[i+1] = magnet4[i];
				}
				magnet4[0] = tmp;
			}
			
		}// 3번 마그넷 -------------------------------------------------------------------------------------------
		else if(num == 3) {
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet3[7];
			for(int i=6;i>=0;i--) {
				magnet3[i+1] = magnet3[i];
			}
			magnet3[0] = tmp;
			
			if(tmp2 != 0) { // 2번 마그넷 움직임
				tmp = magnet2[0];
				for(int i=0;i<7;i++) {
					magnet2[i] = magnet2[i+1];
				}
				magnet2[7] = tmp;
				count2++;
			}
			
			if(tmp3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[0];
				for(int i=0;i<7;i++) {
					magnet4[i] = magnet4[i+1];
				}
				magnet4[7] = tmp;
			}
			
			if(count2 != 0 && tmp1 != 0) { //1번 마그넷 움직임
				tmp = magnet1[7];
				for(int i=6;i>=0;i--) {
					magnet1[i+1] = magnet1[i];
				}
				magnet1[0] = tmp;
			}
			
		}// 4번 마그넷 -------------------------------------------------------------------------------------------
		else if(num == 4) {
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet4[7];
			for(int i=6;i>=0;i--) {
				magnet4[i+1] = magnet4[i];
			}
			magnet4[0] = tmp;
			
			if(tmp3 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[0];
				for(int i=0;i<7;i++) {
					magnet3[i] = magnet3[i+1];
				}
				magnet3[7] = tmp;
				count3++;
			}
			
			if(tmp2 != 0 && count3 != 0) { // 2번 마그넷 움직임
				tmp = magnet2[7];
				for(int i=6;i>=0;i--) {
					magnet2[i+1] = magnet2[i];
				}
				magnet2[0] = tmp;
				count2++;
			}
			
			if(tmp1 != 0 && count2 != 0) { // 1번 마그넷 움직임
				tmp = magnet1[0];
				for(int i=0;i<7;i++) {
					magnet1[i] = magnet1[i+1];
				}
				magnet1[7] = tmp;
			}
		}
	}
	
	// 역방향 움직임 ---------------------------------------------------------------------------------------------------------------------
	static void turn0(int num) {
		int tmp1 = 0;
		int tmp2 = 0;
		int tmp3 = 0;
		int tmp = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		
		if(num == 1) {
			
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet1[0];
			for(int i=0;i<7;i++) {
				magnet1[i] = magnet1[i+1];
			}
			magnet1[7] = tmp;
			
			if(tmp1 != 0) { //정방향 2번 마그넷 움직임
				tmp = magnet2[7];
				for(int i=6;i>=0;i--) {
					magnet2[i+1] = magnet2[i];
				}
				magnet2[0] = tmp;
				count2++;
			}
			else if(tmp2 != 0 && count2 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[0];
				for(int i=0;i<7;i++) {
					magnet3[i] = magnet3[i+1];
				}
				magnet3[7] = tmp;
				count3++;
			}
			else if(tmp3 != 0 && count3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[7];
				for(int i=6;i>=0;i--) {
					magnet4[i+1] = magnet4[i];
				}
				magnet4[0] = tmp;
			}
		}
		else if(num == 2) {
			
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet2[0];
			for(int i=0;i<7;i++) {
				magnet2[i] = magnet2[i+1];
			}
			magnet2[7] = tmp;
			
			if(tmp1 != 0) { // 1번 마그넷 움직임
				tmp = magnet1[7];
				for(int i=6;i>=0;i--) {
					magnet1[i+1] = magnet1[i];
				}
				magnet1[0] = tmp;
			}
			else if(tmp2 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[7];
				for(int i=6;i>=0;i--) {
					magnet3[i+1] = magnet3[i];
				}
				magnet3[0] = tmp;
				count3++;
			}
			
			if(count3 != 0 && tmp3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[0];
				for(int i=0;i<7;i++) {
					magnet4[i] = magnet4[i+1];
				}
				magnet4[7] = tmp;
			}
			
		}
		else if(num == 3) {
			
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet3[0];
			for(int i=0;i<7;i++) {
				magnet3[i] = magnet3[i+1];
			}
			magnet3[7] = tmp;
			
			if(tmp2 != 0) { // 2번 마그넷 움직임
				tmp = magnet2[7];
				for(int i=6;i>=0;i--) {
					magnet2[i+1] = magnet2[i];
				}
				magnet2[0] = tmp;
				count2++;
			}
			
			if(tmp3 != 0) { // 4번 마그넷 움직임
				tmp = magnet4[7];
				for(int i=6;i>=0;i--) {
					magnet4[i+1] = magnet4[i];
				}
				magnet4[0] = tmp;
			}
			
			if(count2 != 0 && tmp1 != 0) { //1번 마그넷 움직임
				tmp = magnet1[0];
				for(int i=0;i<7;i++) {
					magnet1[i] = magnet1[i+1];
				}
				magnet1[7] = tmp;
			}
			
		}
		else if(num == 4) {
			
			if(magnet1[2] != magnet2[6]) {
				tmp1++;
			}
			else if(magnet2[2] != magnet3[6]) {
				tmp2++;
			}
			else if(magnet3[2] != magnet4[6]) {
				tmp3++;
			}
			
			tmp = magnet4[0];
			for(int i=0;i<7;i++) {
				magnet4[i] = magnet4[i+1];
			}
			magnet4[7] = tmp;
			
			if(tmp3 != 0) { // 3번 마그넷 움직임
				tmp = magnet3[7];
				for(int i=6;i>=0;i--) {
					magnet3[i+1] = magnet3[i];
				}
				magnet3[0] = tmp;
				count3++;
			}
			
			if(tmp2 != 0 && count3 != 0) { // 2번 마그넷 움직임
				tmp = magnet2[0];
				for(int i=0;i<7;i++) {
					magnet2[i] = magnet2[i+1];
				}
				magnet2[7] = tmp;
				count2++;
			}
			
			if(tmp1 != 0 && count2 != 0) { // 1번 마그넷 움직임
				tmp = magnet1[7];
				for(int i=6;i>=0;i--) {
					magnet1[i+1] = magnet1[i];
				}
				magnet1[0] = tmp;
			}
			
		}
	}
	
}
