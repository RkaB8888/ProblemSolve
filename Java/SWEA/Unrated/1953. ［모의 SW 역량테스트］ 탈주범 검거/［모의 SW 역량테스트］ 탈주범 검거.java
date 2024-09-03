import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:26,892KB, 시간:146ms
 *
 */
public class Solution {
	static int N, M, R, C, L;
	// 지도의 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L
    static int[][] map;
    static boolean[][] visited;
	static int checkCnt;
	static final int[][] drdc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };//상하좌우

	static final boolean[][] out = {
			{},
			{ true, true, true, true },//상하좌우
			{ true, true, false, false },//상하
			{ false, false, true, true },//좌우
			{ true, false, false, true },//상우
			{ false, true, false, true },//하우
			{ false, true, true, false },//하좌
			{ true, false, true, false }//상좌
	};//type,ways
	static final boolean[][] in = {
			{},
			{ true, true, true, true },//상하좌우
			{ true, true, false, false },//상하
			{ false, false, true, true },//좌우
			{ false, true, true, false },//하좌
			{ true, false, true, false },//상좌
			{ true, false, false, true },//상우
			{ false, true, false, true }//하우
	};//type,ways

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			checkCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append('#').append(tc).append(' ').append(checkCnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { R, C, 1 }); // {row, col, time}
		visited[R][C] = true;
		checkCnt++;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c = cur[1], time = cur[2];
			if (time == L) continue;
			
			int type = map[r][c];
			for (int i = 0; i < 4; i++) {
				if(!out[type][i]) continue;
				int nr = r + drdc[i][0];
                int nc = c + drdc[i][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;
                int nextType = map[nr][nc];
                if (!in[nextType][i]) continue;
                
                q.add(new int[] { nr, nc, time + 1 });
                visited[nr][nc] = true;
				checkCnt++;
			}
		}
	}

}