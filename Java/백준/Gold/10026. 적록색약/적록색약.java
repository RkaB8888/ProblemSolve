import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리:12,884KB, 시간:96ms
 */
public class Main {
	static int N;
	static char[][] map;
	static boolean[][] check;
	static boolean flag;// 적색 탐사 중 녹색 만났는지 확인용
	static int[][] dr_dc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int result1, result2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check[i][j] == true)
					continue;
				dfs1(i, j);
				result1++;
			}
		}
		sb.append(result1).append(' ');
		check = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (check[i][j] == true)
					continue;
				dfs2(i, j);
				result2++;
			}
		}
		sb.append(result2);
		System.out.println(sb);

	}

	public static void dfs1(int row, int col) {// 정상인
		check[row][col] = true;
		for (int i = 0; i < 4; i++) {
			int r = row + dr_dc[i][0];
			int c = col + dr_dc[i][1];
			if (r < 0 || c < 0 || r >= N || c >= N || check[r][c])
				continue;
			if (map[r][c] != map[row][col])
				continue;
			check[r][c] = true;
			dfs1(r, c);
		}
	}

	public static void dfs2(int row, int col) {// 색맹인
		check[row][col] = true;
		for (int i = 0; i < 4; i++) {
			int r = row + dr_dc[i][0];
			int c = col + dr_dc[i][1];
			if (r < 0 || c < 0 || r >= N || c >= N || check[r][c])
				continue;
			if ((map[r][c] == 'B' || map[row][col] == 'B') && (map[r][c] != 'B' || map[row][col] != 'B'))
				continue;
			check[r][c] = true;
			dfs2(r, c);
		}
	}

}