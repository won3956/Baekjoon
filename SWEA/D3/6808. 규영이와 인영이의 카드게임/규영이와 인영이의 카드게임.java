import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static int[] gyu, iny, isUsed;
	static int idx;
	static int vicCount, defCount;
	static int win, lose; //규영이가 이긴횟수와 진횟수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			vicCount = 0; defCount = 0;
			gyu = new int[9];
			iny = new int[9];
			isUsed = new int[19];
			for(int i = 0; i < 9; i++) {
				int input = Integer.parseInt(st.nextToken());
				gyu[i] = input;
				isUsed[input] = i+1;
			}
			perm(0);
			System.out.println("#"+tc+" "+vicCount+" "+defCount);
		}
	}
	public static void perm(int count) {
		if(count == 9) {
			cal();
			return;
		}
		
		for (int i = 1; i <= 18; i++) {
			if(isUsed[i]>0) continue;
			iny[count] = i;
			isUsed[i] = count + 1;
			perm(count + 1);
			iny[count] = 0;
			isUsed[i] = 0;
		}
	}
	public static void cal() {
		int sumIn = 0; int sumGyu = 0;
		for (int i = 0; i < 9; i++) {
			int sum = iny[i] - gyu[i];
			if(sum > 0) sumIn += (iny[i] + gyu[i]);
			if(sum < 0) sumGyu += (iny[i] + gyu[i]);
		}
		if(sumIn > sumGyu) ++defCount;
		else if(sumIn < sumGyu) ++vicCount;
	}
}