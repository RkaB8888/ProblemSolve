import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:19,408KB, 시간:208ms
 * 
 * @author SSAFY
 *
 */
public class Main {
	static final int[][] dir = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int N, M;
	static int[][] map;
	static CCTV[] cctvs;
	static int cctvCnt, cnt0, result;

	static class CCTV {
		int i, j, serialNum, typeNum;

		public CCTV(int i, int j, int serialNum, int type) {
			this.i = i;
			this.j = j;
			this.serialNum = serialNum;
			this.typeNum = type;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctvs = new CCTV[8];
		cctvCnt = 0;
		cnt0 = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if (temp == 0) {
					cnt0++;
				} else if (temp < 6) {
					cctvs[cctvCnt] = new CCTV(i, j, cctvCnt + 10, temp);
					cctvCnt++;
				}
			}
		}
		cctvs = Arrays.copyOf(cctvs, cctvCnt);
		result = cnt0;
		if(cctvCnt>0&&cnt0>0)
			combination(0);

		System.out.println(result);

	}

	public static void combination(int depth) {
		if (depth == cctvCnt) {
			if (cnt0 < result)
				result = cnt0;
			return;
		}
		CCTV cctv = cctvs[depth];
		int row = cctv.i;
		int col = cctv.j;
		int SN = cctv.serialNum;
		int TN = cctv.typeNum;
		for (int degree = 0; degree < 4; degree++) {
			CCTVSelect(row, col, SN, TN, degree);
			combination(depth + 1);
		}
		CCTVSelect(row, col, SN, 6, -1);
	}
	public static void CCTVSelect(int row, int col, int SN, int TN, int degree) {
		switch (TN) {
		case 1:
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			break;
		case 2:
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			break;
		case 3:
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			break;
		case 4:
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1(row, col, SN, degree);
			degree++;
			degree %= 4;
			CCTV1Remove(row, col, SN, degree);
			break;
		case 5:
			CCTV1(row, col, SN, 0);
			CCTV1(row, col, SN, 1);
			CCTV1(row, col, SN, 2);
			CCTV1(row, col, SN, 3);
			break;
		case 6:
			CCTV1Remove(row, col, SN, 0);
			CCTV1Remove(row, col, SN, 1);
			CCTV1Remove(row, col, SN, 2);
			CCTV1Remove(row, col, SN, 3);
		}
	}

	public static void CCTV1(int row, int col, int SN, int degree) {
		while (true) {
			row += dir[degree][0];
			col += dir[degree][1];
			if (row < 0 || col < 0 || row >= N || col >= M || map[row][col] == SN || map[row][col] == 6)
				break;
			if (map[row][col] == 0) {
				cnt0--;
				map[row][col] = SN;
			}
		}
	}

	public static void CCTV1Remove(int row, int col, int SN, int degree) {
		while (true) {
			row += dir[degree][0];
			col += dir[degree][1];
			if (row < 0 || col < 0 || row >= N || col >= M || map[row][col] == 0 || map[row][col] == 6)
				break;
			if (map[row][col] == SN) {
				cnt0++;
				map[row][col] = 0;
			}
		}
	}
}