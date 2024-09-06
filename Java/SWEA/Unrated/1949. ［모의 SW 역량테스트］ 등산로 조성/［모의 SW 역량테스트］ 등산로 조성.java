import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:85,800kb, 시간:288ms
 * 
 */
public class Solution {
	static int N, K, dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }, result;
	static mapInfo map[][];
	static Queue<Cursor> q = new ArrayDeque<>();

	static class Cursor {
		int i, j, cnt, diggingPossible, preVal;
		boolean[][] visited;

		public Cursor(int i, int j, int cnt, int diggingPossible, int preVal, boolean[][] visited) {
			super();
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.diggingPossible = diggingPossible;
			this.preVal = preVal;
			this.visited = visited;
		}
	}

	static class mapInfo {
		int val;

		public mapInfo(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new mapInfo[N][N];
			q.clear();
			int maxVal = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int val = Integer.parseInt(st.nextToken());
					map[i][j] = new mapInfo(val);
					if (maxVal < val)
						maxVal = val;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].val == maxVal) {
						boolean[][] check = new boolean[N][N];
						check[i][j] = true;
						q.add(new Cursor(i, j, 1, 1, maxVal, check));
					}
				}
			}
			result = 0;
			bfs();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void bfs() {
		while (!q.isEmpty()) {
			Cursor cursor = q.poll();
			int x = cursor.i, y = cursor.j, cnt = cursor.cnt;
			int curVal = cursor.preVal;
			int dig = cursor.diggingPossible; // 1이 파기 가능
			for (int i = 0; i < 4; i++) {
				int r = x + dir[i][0];
				int c = y + dir[i][1];
				if (r < 0 || c < 0 || r >= N || c >= N || cursor.visited[r][c]) // 경계, 이미 다녀온 곳
					continue;
				int nextVal = map[r][c].val;
				if (nextVal < curVal) {
					boolean[][] temp = new boolean[N][N];
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < N; k++) {
							temp[j][k] = cursor.visited[j][k];
						}
					}
					temp[r][c] = true;
					q.add(new Cursor(r, c, cnt + 1, dig, nextVal, temp));
					if (result < cnt + 1)
						result = cnt + 1;
					continue;

				} else {
					if (dig == 1 && nextVal - curVal < K) {
						boolean[][] temp = new boolean[N][N];
						for (int j = 0; j < N; j++) {
							for (int k = 0; k < N; k++) {
								temp[j][k] = cursor.visited[j][k];
							}
						}
						temp[r][c] = true;
						q.add(new Cursor(r, c, cnt + 1, 0, curVal - 1, temp));
						if (result < cnt + 1)
							result = cnt + 1;
						continue;
					}
				}
			}
		}
	}

}