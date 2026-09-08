import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] gyu, iny;
	static boolean[] isUsed;
	static int winGyu, winIn;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			winGyu = 0; winIn = 0;
			gyu = new int[9];
			iny = new int[9];
			isUsed = new boolean[19];
			
			for(int i = 0; i < 9; i++) {
				int input = Integer.parseInt(st.nextToken());
				gyu[i] = input;
				isUsed[input] = true;
			}
			
			perm(0);
			System.out.println("#"+tc+" "+winGyu+" "+winIn);
		}
	}
	public static void perm(int cnt) {
		if(cnt == 9) {
			cal();
			return;
		}
		
		for (int i = 1; i <= 18; i++) {
			if(isUsed[i]) continue;
			
			iny[cnt] = i;
			isUsed[i] = true;
			
			perm(cnt + 1);
			isUsed[i] = false;
		}
	}
	public static void cal() {
		int sumIn = 0; int sumGyu = 0;
		for (int i = 0; i < 9; i++) {
			if(iny[i] > gyu[i]) sumIn += (iny[i] + gyu[i]);
			if(iny[i] < gyu[i]) sumGyu += (iny[i] + gyu[i]);
		}
		if(sumIn > sumGyu) ++winIn;
		else if(sumIn < sumGyu) ++winGyu;
	}
}