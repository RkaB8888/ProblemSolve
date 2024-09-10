import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][], result;
	static int drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
//	static int drdc[][] = {{1,0},{0,-1},{0,1}};
//	static int drdc[][] = {{1,0},{0,1}};
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		check = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check[i][j] = true;
				dfs(1, map[i][j], i, j);
				check[i][j] = false;
			}
		}
		System.out.println(result);
	}

	public static void dfs(int cnt, int sum, int r, int c) {
		if (cnt == 4) {
			if (result < sum)
				result = sum;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int row = r + drdc[i][0];
			int col = c + drdc[i][1];
			if (row < 0 || col < 0 || row >= N || col >= M || check[row][col])
				continue;
			check[row][col] = true;
			if (cnt == 2) {
				for (int j = 0; j < 4; j++) {
					int row2 = r + drdc[j][0];
					int col2 = c + drdc[j][1];
					if (row2 < 0 || col2 < 0 || row2 >= N || col2 >= M || check[row2][col2])
						continue;
					check[row2][col2] = true;
					dfs(cnt + 2, sum + map[row][col] + map[row2][col2], row2, col2);
					check[row2][col2] = false;
				}
			}
			dfs(cnt + 1, sum + map[row][col], row, col);
			check[row][col] = false;
		}
	}
}