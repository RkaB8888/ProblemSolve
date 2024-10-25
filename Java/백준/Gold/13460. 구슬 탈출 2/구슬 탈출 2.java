import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DFS 구현
 * 메모리: ?KB, 시간: ?ms
 */
public class Main {
	static int R, C, result = 11, drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Hole hole;

	static class Ball {
		int[] red, blue;

		public Ball() {
		}

		public Ball(int[] red, int[] blue) {
			this.red = red;
			this.blue = blue;
		}

		public Ball(Ball ball) {
			this.red = new int[] {ball.red[0],ball.red[1]};
			this.blue = new int[] {ball.blue[0],ball.blue[1]};
		}
	}

	static class Hole {
		int r, c;
		
		public boolean check(int[] ball) {
			if(this.r==ball[0]&&this.c==ball[1]) return true;
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][];

		Ball ball = new Ball();
		hole = new Hole();
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 1, jEnd = C - 1; j < jEnd; j++) {
				if (map[i][j] == 'R') {
					ball.red = new int[] { i, j };
				} else if (map[i][j] == 'B') {
					ball.blue = new int[] { i, j };
				} else if (map[i][j] == 'O') {
					hole.r = i;
					hole.c = j;
				}
			}
		}
		dfs(0, ball, map);
		if(result==11) result = -1;
		System.out.print(result);
	}

	private static void dfs(int depth, Ball curBall, char[][] curMap) {
		if (depth >= result-1) {
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			char[][] nextMap = new char[R][C];
			for(int j = 0 ; j < R ; j++) {
				System.arraycopy(curMap[j], 0, nextMap[j], 0, C);
			}
			Ball nextBall = new Ball(curBall);
			switch (i) {
			case 0: // 더 위에 있는 구슬을 먼저 이동
				if(nextBall.blue[0]>nextBall.red[0]) {
					if(!moveBall(i, nextBall.red, 'R', 'B', nextMap) &
					!moveBall(i, nextBall.blue, 'B', 'R', nextMap)) continue;
				} else {
					if(!moveBall(i, nextBall.blue, 'B', 'R', nextMap) &
					!moveBall(i, nextBall.red, 'R', 'B', nextMap)) continue;
				}
				break;
			case 1: // 더 아래에 있는 구슬을 먼저 이동
				if(nextBall.blue[0]>nextBall.red[0]) {
					if(!moveBall(i, nextBall.blue, 'B', 'R', nextMap) &
					!moveBall(i, nextBall.red, 'R', 'B', nextMap)) continue;
				} else {
					if(!moveBall(i, nextBall.red, 'R', 'B', nextMap) &
					!moveBall(i, nextBall.blue, 'B', 'R', nextMap)) continue;
				}
				break;
			case 2: // 더 왼쪽에 있는 구슬을 먼저 이동
				if(nextBall.blue[1]>nextBall.red[1]) {
					if(!moveBall(i, nextBall.red, 'R', 'B', nextMap) &
					!moveBall(i, nextBall.blue, 'B', 'R', nextMap)) continue;
				} else {
					if(!moveBall(i, nextBall.blue, 'B', 'R', nextMap) &
					!moveBall(i, nextBall.red, 'R', 'B', nextMap)) continue;
				}
				break;
			case 3: // 더 오른쪽에 있는 구슬을 먼저 이동
				if(nextBall.blue[1]>nextBall.red[1]) {
					if(!moveBall(i, nextBall.blue, 'B', 'R', nextMap) &
					!moveBall(i, nextBall.red, 'R', 'B', nextMap)) continue;
				} else {
					if(!moveBall(i, nextBall.red, 'R', 'B', nextMap) &
					!moveBall(i, nextBall.blue, 'B', 'R', nextMap)) continue;
				}
				break;
			}
			boolean redCheck = hole.check(nextBall.red);
			boolean blueCheck = hole.check(nextBall.blue);
			if(redCheck&&!blueCheck) {
//				System.out.println(Arrays.toString(nextBall.red));
//				System.out.println(Arrays.toString(nextBall.blue));
//				System.out.println(i+", "+redCheck+", "+ blueCheck);
//				for(int t = 0 ; t < R ; t++) {
//					System.out.println(Arrays.toString(nextMap[t]));
//				}
				result = Math.min(result, depth+1);
				return;
			} else if(!redCheck&&!blueCheck) {
				dfs(depth+1, nextBall, nextMap);
			}
		}
	}

	private static boolean moveBall(int dir, int[] ball, char me, char other, char[][] map) {
		int nR = ball[0], nC = ball[1], mvCnt = 0;
//		boolean coll = false;
//		boolean goal = false;
		while(true) {
			nR += drdc[dir][0];
			nC += drdc[dir][1];
			if(map[nR][nC]=='#'||map[nR][nC]==other) {
//				coll = true;
				break;
			}
			if(map[nR][nC]=='O') {
//				goal = true;
				map[ball[0]][ball[1]] = '.';
				ball[0] = nR; ball[1] = nC;
				mvCnt++;
				break;
			}
			map[ball[0]][ball[1]] = '.';
			map[nR][nC] = me;
			ball[0] = nR; ball[1] = nC;
			mvCnt++;
		}
		if(mvCnt==0) return false;
		return true;
	}
}