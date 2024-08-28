import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 27,644KB 시간 216ms
 */
public class Main {
	static int R;
	static int C;
	static char[][] map;
	static int[][] dr_dc = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };// 하 상 우 좌
	static int blankX;
	static int blankY;
	static int bitmask; //하상우좌
	static char[] block = {'0','0','0','-','0','3','2','0','0','4','1','0','|','0','0','+'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		searchBlank();
		sb.append(blankX+1).append(' ').append(blankY+1).append(' ').append(block[bitmask]);
		System.out.println(sb);
	}

	public static void searchBlank() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != '.')
					continue;
				if (isBlank(i, j)) {
					blankX = i;
					blankY = j;
					return;
				}
			}
		}
	}

	public static boolean isBlank(int row, int col) {
		int r, c;
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			r = row + dr_dc[i][0];
			c = col + dr_dc[i][1];
			if (r < 0 || c < 0 || r >= R || c >= C || map[r][c] == '.')
				continue;
			if (i == 0 && (map[r][c] == '|' || map[r][c] == '+' || map[r][c] == '2' || map[r][c] == '3')) {
				bitmask|=(1<<3);
				flag = true;
			}
			if (i == 1 && (map[r][c] == '|' || map[r][c] == '+' || map[r][c] == '1' || map[r][c] == '4')) {
				bitmask|=(1<<2);
				flag = true;
			}
			if (i == 2 && (map[r][c] == '-' || map[r][c] == '+' || map[r][c] == '3' || map[r][c] == '4')) {
				bitmask|=(1<<1);
				flag = true;
			}
			if (i == 3 && (map[r][c] == '-' || map[r][c] == '+' || map[r][c] == '1' || map[r][c] == '2')) {
				bitmask|=1;
				flag = true;
			}

		}
		return flag;
	}

}