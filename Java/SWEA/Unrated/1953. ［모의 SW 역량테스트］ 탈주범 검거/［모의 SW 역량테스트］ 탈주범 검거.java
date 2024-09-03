
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:24,320KB, 시간:141ms
 *
 */
public class Solution {
	static int N, M, R, C, L;
	// 지도의 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L
	static Node[][] map;// 1~7 터널, 0은 없는 곳
	static boolean[][] check;// 접근 가능한 장소 체크
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
	static class Node {
		int i, j, type, time;
		boolean check;

		public Node(int i, int j, int type) {
			super();
			this.i = i;
			this.j = j;
			this.type = type;
		}

	}

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
			map = new Node[N][M];
			check = new boolean[N][M];
			checkCnt = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int type = Integer.parseInt(st.nextToken());
					map[i][j] = new Node(i, j, type);
				}
			}
			bfs();
			sb.append('#').append(tc).append(' ').append(checkCnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		map[R][C].time = 1;
		q.add(map[R][C]);
		map[R][C].check = true;
		checkCnt++;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int r = cur.i;
			int c = cur.j;
			int type = cur.type;
			int time = cur.time;
			if (time == L)
				continue;
			for (int i = 0; i < 4; i++) {
				if(!out[type][i]) continue;
				int row = r + drdc[i][0];
				int col = c + drdc[i][1];
				if (row >= 0 && col >= 0 && row < N && col < M && !map[row][col].check && map[row][col].type != 0&&in[map[row][col].type][i]) {
					// 경계 안, 다닌 적 없음, 터널 있음, 들어갈 수 있음
					map[row][col].time = time + 1;
					q.add(map[row][col]);
					map[row][col].check = true;
					checkCnt++;
				}
			}
		}
	}

}