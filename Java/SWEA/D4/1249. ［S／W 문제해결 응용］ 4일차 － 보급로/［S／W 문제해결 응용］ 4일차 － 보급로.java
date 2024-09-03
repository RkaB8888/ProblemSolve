import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	static int N, map[][];
	static final int[] dr = { -1, 1, 0, 0 };
	static final int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String temp = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = temp.charAt(j) - '0';
				}
			}
			sb.append('#').append(tc).append(' ').append(getMinDistance(0, 0, N - 1, N - 1)).append('\n');
		}
		System.out.println(sb);
	}

	static int getMinDistance(int startI, int startJ, int endI, int endJ) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		int[][] minDistance = new int[N][N];// 시작 정점에서 자신으로의 최소 거리
		boolean[][] visited = new boolean[N][N];// 방문한 정점 관리
		final int INF = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minDistance[i][j] = INF;
			}
		}
		minDistance[startI][startJ] = 0;
		pq.add(new int[] { startI, startJ, minDistance[startI][startJ] });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[0]][cur[1]])
				continue;
			if (cur[0] == endI && cur[1] == endJ)
				return cur[2];
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minDistance[nr][nc] > cur[2] + map[nr][nc]) {
					minDistance[nr][nc] = cur[2] + map[nr][nc];
					pq.add(new int[] { nr, nc, minDistance[nr][nc] });
				}
			}
		}
		return -1;
	}

}