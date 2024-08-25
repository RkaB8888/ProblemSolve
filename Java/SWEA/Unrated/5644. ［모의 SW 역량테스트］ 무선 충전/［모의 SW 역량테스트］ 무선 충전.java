import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:24,468KB, 시간:133ms
 */
public class Solution {
	static final int[][] dr_dc = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static Position[][] map;
	static int M; // 이동시간
	static int A; // BC의 개수
	static int[] aMove; // 상 우 하 좌
	static int[] bMove;
	static int result;
	static int[] aP = new int[2];
	static int[] bP = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			map = new Position[11][11];
			for (int i = 1; i < 11; i++) {
				for (int j = 1; j < 11; j++) {
					map[i][j] = new Position();
				}
			}
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			aMove = new int[M];
			bMove = new int[M];
			aP[0] = 1;
			aP[1] = 1;
			bP[0] = 10;
			bP[1] = 10;
			result = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				setting(i, st);
			}
			calc();
			for (int i = 0; i < M; i++) {
				// 충전 계산
				move(aMove[i], bMove[i]);// 다음 좌표 계산
				calc();
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	public static void calc() {
		Position a = map[aP[0]][aP[1]];
		Position b = map[bP[0]][bP[1]];
		if (a.BC1key == b.BC1key) {
			result += a.BC1P + Math.max(a.BC2P, b.BC2P);
		} else {
			result += a.BC1P + b.BC1P;
		}
	}

	public static void move(int a, int b) {
		aP[0] += dr_dc[a][0];
		aP[1] += dr_dc[a][1];
		bP[0] += dr_dc[b][0];
		bP[1] += dr_dc[b][1];
	}

	public static void setting(int key, StringTokenizer st) {
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		map[row][col].setBC(key, p);
		for (int j = 1; j <= c; j++) {
			int mCol = col - j, pCol = col + j;
			if(mCol>0) map[row][mCol].setBC(key, p);
			if(pCol<11) map[row][pCol].setBC(key, p);
		}
		for (int i = 1; i <= c; i++) {
			int mRow = row - i, pRow = row + i;
			if (mRow > 0) {
				map[mRow][col].setBC(key, p);
				for (int j = 1; j <= c; j++) {
					if (i + j > c)
						break;
					int mCol = col - j, pCol = col + j;
					if (pCol < 11) {
						map[mRow][pCol].setBC(key, p);
					}
					if (mCol > 0) {
						map[mRow][mCol].setBC(key, p);
					}
				}
			}
			if (pRow < 11) {
				map[pRow][col].setBC(key, p);
				for (int j = 1; j <= c; j++) {
					if (i + j > c)
						break;
					int mCol = col - j, pCol = col + j;
					if (pCol < 11) {
						map[pRow][pCol].setBC(key, p);
					}
					if (mCol > 0) {
						map[pRow][mCol].setBC(key, p);
					}
				}
			}
		}
	}

	static class Position {
		int BC1key = -1;// 해당 BC 고유 번호
		int BC2key = -1;
		int BC1P;// 충전량
		int BC2P;

		public void setBC(int bCkey, int bCP) {
			if (bCP > BC1P) {
				BC2P = BC1P;
				BC2key = BC1key;
				BC1P = bCP;
				BC1key = bCkey;

			} else if (bCP > BC2P) {
				BC2P = bCP;
				BC2key = bCkey;
			}
		}

	}

}