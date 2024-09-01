
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:23,296KB, 시간:153ms
 * 
 * @author SSAFY
 *
 */
public class Solution {
	static int N;
	static int map[][];
	static int maxCnt;
	static final int[][] dr_dc = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };// 시계방향

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxCnt = -1;
			for (int i = 0, iEnd = N - 2; i < iEnd; i++) {
				for (int j = 1, jEnd = N - 1; j < jEnd; j++) {
					process(i, j);
				}
			}
			sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void process(int row, int col) {
		int maxRD = Math.min((N - 1 - row), (N - col));
		int maxLD;
		for (int i = 1; i < maxRD; i++) {// 우하 횟수
			maxLD = Math.min((N - row - i), col+1);
			for (int j = 1; j < maxLD; j++) {// 좌하 횟수
				calc(row, col, i, j);
			}
		}
	}

	public static void calc(int row, int col, int RD, int LD) {
//		StringBuilder sbtemp = new StringBuilder();
		int cnt = RD * 2 + LD * 2;
		if (cnt <= maxCnt)
			return;
		boolean[] check = new boolean[101];
//		sbtemp.append('(').append(row).append(", ").append(col).append(", ").append(map[row][col]).append(')').append(", ");
		for (int i = 0; i < RD; i++) {
			row += dr_dc[0][0];
			col += dr_dc[0][1];
			int num = map[row][col];
			if (check[num])
				return;
//			sbtemp.append('(').append(row).append(", ").append(col).append(", ").append(num).append(')').append(", ");
			check[num] = true;
		}
		for (int i = 0; i < LD; i++) {
			row += dr_dc[1][0];
			col += dr_dc[1][1];
			int num = map[row][col];
			if (check[num])
				return;
//			sbtemp.append('(').append(row).append(", ").append(col).append(", ").append(num).append(')').append(", ");
			check[num] = true;
		}
		for (int i = 0; i < RD; i++) {
			row += dr_dc[2][0];
			col += dr_dc[2][1];
			int num =map[row][col];
			if (check[num])
				return;
//			sbtemp.append('(').append(row).append(", ").append(col).append(", ").append(num).append(')').append(", ");
			check[num] = true;
		}
		for (int i = 0; i < LD; i++) {
			row += dr_dc[3][0];
			col += dr_dc[3][1];
			int num = map[row][col];
			if (check[num])
				return;
//			sbtemp.append('(').append(row).append(", ").append(col).append(", ").append(num).append(')').append(", ");
			check[num] = true;
		}
		maxCnt = cnt;
//		sbtemp.append('\n').append(cnt).append('\n');
//		System.out.print(sbtemp);
	}
}
