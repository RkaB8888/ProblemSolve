import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  40,416kb 601ms
 */
public class Solution {
	static int N, result;
	static final int drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	// 블럭 1,2,3,4,5에 대한 방향에 따른 반사된 이둉방향 지정
	static final int reflectDir[][] = { // block type,drdc
			{}, { 1, 3, 0, 2 }, // 1일 때
			{ 3, 0, 1, 2 }, // 2일 때
			{ 2, 0, 3, 1 }, // 3일 때
			{ 1, 2, 3, 0 }, // 4일 때
			{ 1, 0, 3, 2 },// 5일 때
	};
	static mapInfo map[][];
	static WormHole wList[], WH[][];

	static class Ball {
		int dir, sr, sc, cr, cc, cnt;

		public Ball(int dir, int sr, int sc, int cnt) {
			this.dir = dir;
			this.sr = sr;
			this.sc = sc;
			this.cr = sr;
			this.cc = sc;
			this.cnt = cnt;
		}

	}
	static class mapInfo{
		int val;
		boolean[] dirCheck = new boolean[4];
		public mapInfo(int val) {
			this.val = val;
		}
	}
	static class WormHole {
		int r, c, nr, nc;

		public WormHole(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void warp(Ball ball) {
			ball.cr = this.nr;
			ball.cc = this.nc;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine().trim());
			map = new mapInfo[N][N];
			WH = new WormHole[N][N];
			wList = new WormHole[5];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					int val = Integer.parseInt(st.nextToken());
					map[i][j] = new mapInfo(val);
					if (val >= 6) {// 월홈을 조사함
						WH[i][j] = new WormHole(i, j);
						if (wList[val - 6] != null) {
							wList[val - 6].nr = i;
							wList[val - 6].nc = j;
							WH[i][j].nr = wList[val - 6].r;
							WH[i][j].nc = wList[val - 6].c;
						} else {
							wList[val - 6] = WH[i][j];
						}
					}
				}
			}
			dfs();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
//			System.out.println(result);
		}
		System.out.println(sb);
	}

	public static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].val != 0)
					continue;
				goStraight(i, j);
			}
		}
	}

	public static void goStraight(int row, int col) {
		for (int i = 0; i < 4; i++) {
			if(map[row][col].dirCheck[i]) continue;
			map[row][col].dirCheck[i] = true;
			Ball ball = new Ball(i, row, col, 0);
			ball.cr += drdc[ball.dir][0];
			ball.cc += drdc[ball.dir][1];
			while (true) {
				if (ball.cr < 0 || ball.cr >= N || ball.cc < 0 || ball.cc >= N || map[ball.cr][ball.cc].val == 5) {
					// 경계면 충돌은 5번 블럭 충돌과 같음
					ball.cnt *= 2;
					ball.cnt++;
					result = Math.max(result, ball.cnt);
					break;
				} else if (map[ball.cr][ball.cc].val == -1 || ball.cr == ball.sr && ball.cc == ball.sc) {
					// 블랙홀을 만나거나 원위치로 돌아온 경우
					result = Math.max(result, ball.cnt);
					break;
				} else if (map[ball.cr][ball.cc].val >= 6) {
					// 웜홀을 만나서 워프시켜 줌
					WH[ball.cr][ball.cc].warp(ball);
				} else if (map[ball.cr][ball.cc].val >= 1) {
					// 벽에 부딪혀서 방향 바꿔줌

					ball.cnt++;
					ball.dir = reflectDir[map[ball.cr][ball.cc].val][ball.dir];
				}
				// 다음 칸으로 이동
				ball.cr += drdc[ball.dir][0];
				ball.cc += drdc[ball.dir][1];
			}
		}
	}
}