import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:23,428KB, 시간:138ms
 * 
 * 완전탐색 가지치기
 * 회전 방향 정해둠, 모든 좌표 순회하며 시작점으로 사용
 * 회전 반경을 계산하여 점점 키워가며 계산
 * 이전의 들렀던 카페 수보다 회전 둘레가 작다면 가지치기
 *
 */
public class Solution {
	static int N;
	static int map[][];
	static int maxCnt;
	static final int[][] dr_dc = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };// 시계방향

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			maxCnt = -1;
			for (int i = 0, iEnd = N - 2; i < iEnd; i++) {
				for (int j = 1, jEnd = N - 1; j < jEnd; j++) {
					process(i, j);
				}
			}
			sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}

	public static void process(int row, int col) {
		int maxRD = Math.min(N - 1 - row, N - col);
		for (int i = 1; i < maxRD; i++) {// 우하 횟수
			int maxLD = Math.min(N - row - i, col+1);
			for (int j = 1; j < maxLD; j++) {// 좌하 횟수
				int cnt = i*2+j*2;
				if(cnt<=maxCnt) continue;// 이전 최대 경로보다 짧으면 가지치기
				if(calc(row, col, i, j)) {
					maxCnt = cnt;
				}
			}
		}
	}

	public static boolean calc(int row, int col, int RD, int LD) {
		boolean[] check = new boolean[101];
		int[][] directions = new int[][] { { RD, 0 }, { LD, 1 }, { RD, 2 }, { LD, 3 } };
		
		for(int[] dir : directions) {
			for (int i = 0; i < dir[0]; i++) {
				row += dr_dc[dir[1]][0];
				col += dr_dc[dir[1]][1];
				int num = map[row][col];
				if (check[num]) return false;
				check[num] = true;
			}
		}
		return true;
	}
}