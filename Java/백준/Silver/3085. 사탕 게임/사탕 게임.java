import java.io.*;
import java.util.Arrays;

/**
 * @description DFS
 * @performance 메모리: ? KB, 동작시간: ? ms
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
		Arrays.fill(rows, 1);
		cols = new int[N];
		Arrays.fill(cols, 1);
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			int cnt1 = 1, cnt2 = 1;
			for (int j = 1; j < N; j++) {
				if (map[i][j - 1] == map[i][j]) { // 행 검사
					cnt1++;
					rows[i] = Math.max(cnt1, rows[i]);
				} else {
					cnt1 = 1;
				}
				if (map[j - 1][i] == map[j][i]) { // 열 검사
					cnt2++;
					cols[i] = Math.max(cnt2, cols[i]);
				} else {
					cnt2 = 1;
				}
			}
		}
		out:for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				for(int k = 0 ; k < 2 ; k++) {
					if(isBorder(i+DIR[k][0],j+DIR[k][1])) continue;
					swap(i,j,i+DIR[k][0],j+DIR[k][1]);
					checkRowCol(i,j,i+DIR[k][0],j+DIR[k][1]);
					for(int l = 0 ; l < N ;l++) {
						result = Math.max(rows[l], result);
						result = Math.max(cols[l], result);
						if(result == N) break out;
					}
					swap(i,j,i+DIR[k][0],j+DIR[k][1]);
				}
			}
		}
		System.out.print(result);
	}

	private static boolean isBorder(int i, int j) {
		return i>=N||j>=N;
	}

	private static void checkRowCol(int row1, int col1, int row2, int col2) {
		if(row1==row2) { // 행 row1, 열 col1, col2 검사
			int cnt1 = 1, cnt2 = 1, cnt3 = 1;
			for(int i = 1 ; i < N ; i++) {
				if (map[row1][i-1] == map[row1][i]) {
					cnt1++;
					rows[row1] = Math.max(cnt1, rows[row1]);
				} else {
					cnt1 = 1;
				}
				if (map[i-1][col1] == map[i][col1]) {
					cnt2++;
					cols[col1] = Math.max(cnt2, cols[col1]);
				} else {
					cnt2 = 1;
				}
				if (map[i-1][col2] == map[i][col2]) {
					cnt3++;
					cols[col2] = Math.max(cnt3, cols[col2]);
				} else {
					cnt3 = 1;
				}
			}
		} else { // 행 row1, row2, 열 col1 검사
			int cnt1 = 1, cnt2 = 1, cnt3 = 1;
			for(int i = 1 ; i < N ; i++) {
				if (map[row1][i-1] == map[row1][i]) {
					cnt1++;
					rows[row1] = Math.max(cnt1, rows[row1]);
				} else {
					cnt1 = 1;
				}
				if (map[row2][i-1] == map[row2][i]) {
					cnt2++;
					rows[row2] = Math.max(cnt2, rows[row2]);
				} else {
					cnt2 = 1;
				}
				if (map[i-1][col1] == map[i][col1]) {
					cnt3++;
					cols[col1] = Math.max(cnt3, cols[col1]);
				} else {
					cnt3 = 1;
				}
			}
		}
	}

	private static void swap(int row1, int col1, int row2, int col2) {
		char temp = map[row1][col1];
		map[row1][col1] = map[row2][col2];
		map[row2][col2] = temp;
	}
	
}