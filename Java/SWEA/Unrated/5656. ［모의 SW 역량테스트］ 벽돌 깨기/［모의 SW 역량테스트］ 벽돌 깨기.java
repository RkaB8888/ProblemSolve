import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, R, C, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			int[][] map = new int[R + 1][C + 1];
			for (int i = 1; i <= R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= C; j++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					if (num > 0) {
						map[0][0]++;
						map[0][j]++;
					}
				}
			}
			result = map[0][0];
			dfs(0, map);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int curMap[][]) {
		if (result == 0)
			return;
		if (curMap[0][0] == 0) {// 모든 블럭이 없음
			result = 0;
			return;
		}
		if (cnt == N) {// 모든 공을 사용
			result = Math.min(curMap[0][0], result);
			return;
		}

		for (int c = 1; c <= C; c++) {
			if (curMap[0][c] == 0)
				continue;
			int newMap[][] = shoot(c, curMap); // 해당 열에 공을 쐇을 때 결과
			dfs(cnt + 1, newMap);
		}
	}

	public static int[][] shoot(int col, int curMap[][]) {
		int newMap[][] = copyMap(curMap);
		
		for (int i = 1; i <= R; i++) {// 공이 발사됐을 때 맞을 블럭 탐색
			if (newMap[i][col] == 0)
				continue;
			int range = newMap[i][col];// 맞을 블럭
			newMap[i][col] = 0;
			newMap[0][col]--;
			newMap[0][0]--;
			if (range != 1)
				remove(newMap, i, col, range);
			break;
		}
		downSort(newMap);
		return newMap;
	}

	public static int[][] copyMap(int[][] curMap) {
		int[][] newMap = new int[R + 1][C + 1];
		for (int i = 0; i <= R; i++) {
			System.arraycopy(curMap[i], 0, newMap[i], 0, C + 1);
		}
		return newMap;
	}

	public static void downSort(int[][] map) {
		for (int j = 1; j <= C; j++) {
			int emptyRow = R; // 빈 칸을 가리키는 포인터
			for (int i = R; i > 0; i--) {
				if (map[i][j] != 0) {
					map[emptyRow][j] = map[i][j]; // 빈칸으로 블록 이동
					if (emptyRow != i)
						map[i][j] = 0; // 기존 위치를 0으로
					emptyRow--;
				}
			}
		}
	}

	public static void remove(int map[][], int r, int c, int curRange) {
		for (int i = 1; i < curRange; i++) {// 해당 범위만큼 제거
			if (c - i > 0 && map[r][c - i] != 0) {
				int nextRange = map[r][c - i];
				map[r][c - i] = 0;
				map[0][c - i]--;
				map[0][0]--;
				if (nextRange != 1)
					remove(map, r, c - i, nextRange);
			}
			if (c + i <= C && map[r][c + i] != 0) {
				int nextRange = map[r][c + i];
				map[r][c + i] = 0;
				map[0][c + i]--;
				map[0][0]--;
				if (nextRange != 1)
					remove(map, r, c + i, nextRange);
			}
			if (r - i > 0 && map[r - i][c] != 0) {
				int nextRange = map[r - i][c];
				map[r - i][c] = 0;
				map[0][c]--;
				map[0][0]--;
				if (nextRange != 1)
					remove(map, r - i, c, nextRange);
			}
			if (r + i <= R && map[r + i][c] != 0) {
				int nextRange = map[r + i][c];
				map[r + i][c] = 0;
				map[0][c]--;
				map[0][0]--;
				if (nextRange != 1)
					remove(map, r + i, c, nextRange);
			}
		}
	}
}