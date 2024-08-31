import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:25,296kb, 시간:153ms
 *
 */
public class Solution {
	static int N;
	static int[][] map;
	static core[] cores;
	static int coreCnt;
	static int maxCore;
	static int minVal;
	static final int[][] dr_dc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class core {
		int i;
		int j;
		int num;
		boolean[] way = new boolean[4];

		public core(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}

		public boolean able() {
			int row, col;
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				row = this.i;
				col = this.j;
				while (true) {
					row += dr_dc[i][0];
					col += dr_dc[i][1];
					if (row < 0 || col < 0 || row >= N || col >= N) {
						flag = true;
						this.way[i] = true;
						break;
					}
					if (map[row][col] != 0) {
						break;
					}
				}
			}
			return flag;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			coreCnt = 0;
			for (int i = 0; i < N; i++) {// 지도 입력 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						coreCnt++;

				}
			}
			cores = new core[coreCnt];
			for (int i = 0, num = 2; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						core one = new core(i, j, num++);
						cores[num - 3] = one;
					}
				}
			}
			maxCore = 0;
			minVal = Integer.MAX_VALUE;
			cores = Arrays.copyOf(cores, coreCnt); // 실제 코어 개수에 맞게 배열 크기 조정
			dfs(0, 0, 0);
			sb.append('#').append(tc).append(' ').append(minVal).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int str, int lineSum, int successCnt) {
		int posibleC = successCnt + (coreCnt - str);
		if (posibleC < maxCore || (posibleC == maxCore && lineSum >= minVal))
			return; // 가지치기
		if (str == coreCnt) {
			if (successCnt > maxCore) {
				maxCore = successCnt;
				minVal = lineSum;
			} else if (successCnt == maxCore) {
				minVal = lineSum;
			}
			return;
		}
		for (int idx = str; idx < coreCnt; idx++) {
			core curcore = cores[idx];
			if (curcore.able()) {
				for (int dir = 0; dir < 4; dir++) {
					if (curcore.way[dir] && checkLine(curcore.i, curcore.j, dir)) {
						dfs(idx + 1, lineSum + setLine(curcore.i, curcore.j, dir, curcore.num), successCnt + 1);// 성공 후
						removeLine(curcore.i, curcore.j, dir, curcore.num);
					}
				}
			}
			dfs(idx + 1, lineSum, successCnt); // 연결 실패 시
		}

	}

	public static boolean checkLine(int row, int col, int dir) {
		while (true) {
			row += dr_dc[dir][0];
			col += dr_dc[dir][1];
			if (row < 0 || col < 0 || row >= N || col >= N) {
				return true; // 경계에 도달하면 라인 길이 반환
			}
			if (map[row][col] != 0) {
				return false; // 다른 코어나 라인에 막히면 실패
			}
		}
	}

	public static int setLine(int row, int col, int dir, int num) {
		int length = 0;
		while (true) {
			row += dr_dc[dir][0];
			col += dr_dc[dir][1];
			if (row < 0 || col < 0 || row >= N || col >= N) {
				return length; // 경계에 도달하면 라인 길이 반환
			}
			map[row][col] = num;
			length++;
		}
	}

	public static void removeLine(int row, int col, int dir, int num) {
		while (true) {
			row += dr_dc[dir][0];
			col += dr_dc[dir][1];// 다음 좌표
			if (row < 0 || col < 0 || row >= N || col >= N || map[row][col] != num) {// 경계까지 갔다면 끝까지 간 것
				return;
			}
			map[row][col] = 0;
		}
	}
}