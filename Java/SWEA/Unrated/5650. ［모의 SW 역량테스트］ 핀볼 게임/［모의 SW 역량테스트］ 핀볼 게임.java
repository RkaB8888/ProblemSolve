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

		public void warp(int[] curP) {
			curP[0] = this.nr;
			curP[1] = this.nc;
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
						int p = val-6;
						if (wList[p] != null) {
							wList[p].nr = i;
							wList[p].nc = j;
							WH[i][j].nr = wList[p].r;
							WH[i][j].nc = wList[p].c;
						} else {
							wList[p] = WH[i][j];
						}
					}
				}
			}
			dfs();
			sb.append('#').append(tc).append(' ').append(result).append('\n');
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
			int curP[] = {row,col};
			int dir = i;//공의 방향
			int cnt = 0;
			curP[0] += drdc[dir][0];
			curP[1] += drdc[dir][1];
			while (true) {
				if (curP[0] < 0 || curP[0] >= N || curP[1] < 0 || curP[1] >= N || map[curP[0]][curP[1]].val == 5) {
					// 경계면 충돌은 5번 블럭 충돌과 같음
					cnt *= 2;
					cnt++;
					result = Math.max(result, cnt);
					break;
				} else if (map[curP[0]][curP[1]].val == -1 || curP[0] == row && curP[1] == col) {
					// 블랙홀을 만나거나 원위치로 돌아온 경우
					result = Math.max(result, cnt);
					break;
				} else if (map[curP[0]][curP[1]].val >= 6) {
					// 웜홀을 만나서 워프시켜 줌
					WH[curP[0]][curP[1]].warp(curP);
				} else if (map[curP[0]][curP[1]].val >= 1) {
					// 벽에 부딪혀서 방향 바꿔줌
					cnt++;
					dir = reflectDir[map[curP[0]][curP[1]].val][dir];
				}
				// 다음 칸으로 이동
				curP[0] += drdc[dir][0];
				curP[1] += drdc[dir][1];
			}
		}
	}
}