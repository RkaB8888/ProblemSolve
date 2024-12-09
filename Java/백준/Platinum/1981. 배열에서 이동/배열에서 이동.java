import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS, PQ 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 */
public class Main {
	static final int DIR[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }, INF = Integer.MAX_VALUE;
	static int N, maxVal = 0, minVal = INF, result = INF;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				minVal = Math.min(minVal, val);
				maxVal = Math.max(maxVal, val);
			}
		}

		int minDiff = 0, maxDiff = maxVal - minVal;
		while (minDiff <= maxDiff) {
			int midDiff = (minDiff + maxDiff) >> 1;
//			System.out.println("돌아감0 midDiff:"+midDiff);
			if (isPosible(midDiff)) {
				result = midDiff;
				maxDiff = midDiff - 1;
			} else {
				minDiff = midDiff + 1;
			}
		}
		System.out.print(result);
	}

	// diff 이하의 차이로 도달할 수 있는지 확인
	private static boolean isPosible(int diff) {
		for (int i = minVal; i <= maxVal - diff; i++) {
//			System.out.println("돌아감1");
			if (bfs(i, i + diff)) {
//				System.out.println("돌아감2");
				return true;
			}
		}
		return false;
	}

	private static boolean bfs(int min, int max) {
		if (map[0][0] < min || max < map[0][0])
			return false;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0 });
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;
		while (!q.isEmpty()) {
//			System.out.println("돌아감3");
			int[] cur = q.poll();
			int row = cur[0], col = cur[1];
			if (row == N - 1 && col == N - 1)
				return true;
			for (int i = 0; i < 4; i++) {
				int nr = row + DIR[i][0];
				int nc = col + DIR[i][1];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				if (map[nr][nc] < min || map[nr][nc] > max)
					continue;
				q.add(new int[] { nr, nc });
			}
		}
		return false;
	}
}