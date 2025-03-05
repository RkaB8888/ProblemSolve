import java.io.*;

/**
 * @description 그리디
 * @performance 메모리: 13,360 KB, 동작시간: 96 ms
 * @author python98
 */

/*
 * 교환하지 않은 상태에서 열과 행의 가장 긴 길이를 입력해둔다. 교환했을 때 교환된 부분의 행과 열을 갱신한다. 길이가 N인 경우 그대로
 * 종료
 */
public class Main {
	static final int[][] DIR = { { 1, 0 }, { 0, 1 } };

	static int N, result;
	static int[] rows, cols;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rows = new int[N];
		cols = new int[N];
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			int cnt = 1;
			rows[i] = 1;
			for (int j = 1; j < N; j++) {
				if (map[i][j - 1] == map[i][j]) {
					cnt++;
				} else {
					cnt = 1;
				}
				rows[i] = Math.max(rows[i], cnt);
			}
		}
		for (int j = 0; j < N; j++) {
			int cnt = 1;
			cols[j] = 1;
			for (int i = 1; i < N; i++) {
				if (map[i - 1][j] == map[i][j]) {
					cnt++;
				} else {
					cnt = 1;
				}
				cols[j] = Math.max(cols[j], cnt);
			}
		}
		result = 0;
		out: for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 2; k++) {
					int ni = i + DIR[k][0];
					int nj = j + DIR[k][1];
					if (isBorder(ni, nj))
						continue;
					if (map[i][j] == map[ni][nj])
						continue;

					swap(i, j, ni, nj);

					int rowCnt1 = rows[i];
					int rowCnt2 = rows[ni];
					int colCnt1 = cols[j];
					int colCnt2 = cols[nj];

					updateRow(i);
					updateRow(ni);
					updateCol(j);
					updateCol(nj);

//					if (result == 0) {
						checkAll();
//					} else {
//						result = Math.max(result, rows[i]);
//						result = Math.max(result, rows[ni]);
//						result = Math.max(result, cols[j]);
//						result = Math.max(result, cols[nj]);
//					}

					swap(i, j, ni, nj);
					rows[i] = rowCnt1;
					rows[ni] = rowCnt2;
					cols[j] = colCnt1;
					cols[nj] = colCnt2;
					if (result == N)
						break out;
				}
			}
		}
		System.out.print(result);
	}

	private static void checkAll() {
		for (int i = 0; i < N; i++) {
			result = Math.max(result, rows[i]);
			result = Math.max(result, cols[i]);
		}
	}

	private static boolean isBorder(int i, int j) {
		return i >= N || j >= N;
	}

	private static void updateRow(int row) {
		int cnt = 1;
		rows[row] = 1;
		for (int j = 1; j < N; j++) {
			if (map[row][j - 1] == map[row][j]) {
				cnt++;
			} else {
				cnt = 1;
			}
			rows[row] = Math.max(rows[row], cnt);
		}
	}

	private static void updateCol(int col) {
		int cnt = 1;
		cols[col] = 1;
		for (int i = 1; i < N; i++) {
			if (map[i - 1][col] == map[i][col]) {
				cnt++;
			} else {
				cnt = 1;
			}
			cols[col] = Math.max(cols[col], cnt);
		}
	}

	private static void swap(int row1, int col1, int row2, int col2) {
		char temp = map[row1][col1];
		map[row1][col1] = map[row2][col2];
		map[row2][col2] = temp;
	}

}