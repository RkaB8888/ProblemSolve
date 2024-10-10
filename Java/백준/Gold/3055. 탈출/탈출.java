import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리 11,768 KB 시간 68 ms
 * BFS 구현 Q 2개 사용
 */
public class Main {

	static int R, C, drdc[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 위, 왼, 우, 하
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> waterQ = new ArrayDeque<>();
	static Queue<int[]> hedgehogQ = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					hedgehogQ.add(new int[] { i, j, 0 });
					visited[i][j] = true;
					map[i][j] = '.';
				} else if (map[i][j] == '*') {
					waterQ.add(new int[] { i, j });
				}
			}
		}
		System.out.print(bfs());
	}

	public static String bfs() {

		while (!hedgehogQ.isEmpty()) {
			// 맵 설정
			int waterSize = waterQ.size();
			while (--waterSize >= 0) {
				int[] water = waterQ.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = water[0] + drdc[dir][0];
					int nc = water[1] + drdc[dir][1];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '*' || map[nr][nc] == 'X'
							|| map[nr][nc] == 'D')
						continue;
					map[nr][nc] = '*';
					waterQ.add(new int[] { nr, nc });
				}
			}

			int hedgeSize = hedgehogQ.size();
			while (--hedgeSize >= 0) {
				// 갈 수 있는 곳 추가
				int[] cur = hedgehogQ.poll();
				int r = cur[0], c = cur[1], time = cur[2];
				for (int i = 0; i < 4; i++) {
					int nr = r + drdc[i][0];
					int nc = c + drdc[i][1];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc])
						continue;
					if (map[nr][nc] == 'D')
						return String.valueOf(time + 1);
					if (map[nr][nc] == '*' || map[nr][nc] == 'X')
						continue;
					hedgehogQ.add(new int[] { nr, nc, time + 1 });
					visited[nr][nc] = true;
				}
			}
		}
		return "KAKTUS";
	}
}