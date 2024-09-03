import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:108,684KB, 시간:600ms 이전에 부순적이 있을 때의 최소 거리와 없을 때의 최소 거리를 따로 별도로 기록 이동하는 녀석은
 * - 벽을 부순 적이 있는지 - 이동 거리
 */
public class Main {

	static class Cursor {
		int i, j, dist;
		boolean broke;

		public Cursor(int i, int j, int dist, boolean broke) {
			this.i = i;
			this.j = j;
			this.dist = dist;
			this.broke = broke;
		}

	}

	static int N, M, map[][];
	static boolean visited[][][];
	static final int drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];// 0: 부순 적 없음, 1: 부순 적 있음
		for (int i = 0; i < N; i++) {
			char[] c = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = c[j] - '0';
			}
		}

		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<Cursor> q = new ArrayDeque<>();
		q.add(new Cursor(0, 0, 1, false));
		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			Cursor cursor = q.poll();
			int r = cursor.i, c = cursor.j, d = cursor.dist;
			boolean broke = cursor.broke;
			int brokeIdx = broke?1:0;

			visited[r][c][brokeIdx] = true;
			if (r == N - 1 && c == M - 1)
				return d;

			for (int i = 0; i < 4; i++) {
				int nr = r + drdc[i][0];
				int nc = c + drdc[i][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (map[nr][nc] == 1 && !broke && !visited[nr][nc][1]) { // 벽을 만났고, 부순 적 없음
					visited[nr][nc][1] = true;
					q.add(new Cursor(nr, nc, d + 1, true));
				} else if (map[nr][nc] == 0 && !visited[nr][nc][brokeIdx]) { // 벽이 아님
					visited[nr][nc][brokeIdx] = true;
					q.add(new Cursor(nr, nc, d + 1, broke));
				}
			}

		}
		return -1;
	}
}