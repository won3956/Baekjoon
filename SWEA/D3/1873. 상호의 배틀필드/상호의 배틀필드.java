import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static int H, W, N, idx;
	static char[][] map;
	static int row, col;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; // U D L R
	static char[] tank = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			//맵의 크기 정보
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='^') {
						idx = 0;
						row = i; col = j;
					}else if(map[i][j]=='>') {
						idx = 3;
						row = i; col = j;
					}else if(map[i][j]=='<') {
						idx = 2;
						row = i; col = j;
					}else if(map[i][j]=='v') {
						idx = 1;
						row = i; col = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String commands = br.readLine();
			for (int i = 0; i < N; i++) {
				char cmd = commands.charAt(i);
				move(cmd);
			}
			System.out.print("#"+tc+" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}
	public static void move(char cmd) {
		switch (cmd) {
		case 'U':
			idx = 0;
			break;
		case 'D':
			idx = 1;
			break;
		case 'L':
			idx = 2;
			break;
		case 'R':
			idx = 3;
			break;
		case 'S':
			shoot();
			return;
		}
		int nr = row + dr[idx];
		int nc = col + dc[idx];
		map[row][col] = tank[idx];
		if(!inrange(nr, nc)) return;
		if(map[nr][nc]=='.') {
			map[row][col] = '.';
			map[nr][nc] = tank[idx];
			row = nr; col = nc;
		}
	}
	public static void shoot() {
		for(int i = 1; i < Math.max(H, W); i++) {
			int nr = row + dr[idx] * i;
			int nc = col + dc[idx] * i;
			if(!inrange(nr, nc)) break;
			if(map[nr][nc]=='*') {
				map[nr][nc] = '.';
				break;
			}else if(map[nr][nc]=='#') {
				break;
			}
		}
	}

	public static boolean inrange(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W; 
	}
}