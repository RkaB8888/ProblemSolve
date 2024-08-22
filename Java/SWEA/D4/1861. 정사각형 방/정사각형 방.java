import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:29,344kb, 시간:249ms
 */
public class Solution {
	static int strNum;
	static int maxVal;
	static int N;
	static int[][] map;
	static int[][] count;
	static int[][] dr_dc = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			strNum = 0;
			maxVal = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			count = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (count[i][j] == -1)
						continue;
					find(i, j);
					if (maxVal < count[i][j]) {
						maxVal = count[i][j];
						strNum = map[i][j];
					}
					else if(maxVal==count[i][j]&&strNum>map[i][j]) {
						maxVal = count[i][j];
						strNum = map[i][j];
					}
				}
			}

			sb.append('#').append(t).append(' ').append(strNum).append(' ').append(maxVal).append('\n');
		}
		System.out.println(sb);
	}

	public static void find(int row, int col) {
		int r = row, sr = row;
		int c = col, sc = col;
		int preNum = map[row][col];
		if(count[r][c]==0) count[r][c]++;
		for (int i = 0; i < 4; i++) {
			r = row + dr_dc[i][0];
			c = col + dr_dc[i][1];
			if (r < 0 || c < 0 || r >= N || c >= N)
				continue;// 경계 밖
			if (map[r][c] != preNum + 1)
				continue;// 다음 숫자가 아님
			if (count[r][c] > 0) { // 시작으로 했던 곳
				count[sr][sc] += count[r][c];// 이전의 시작점이므로 횟수 옮겨 저장
				count[r][c] = -1;
				return;
			}
			preNum = map[r][c];
			count[r][c] = -1; // 거처간 곳 표시
			count[sr][sc]++;// 거처가지 않았다면
			row = r;
			col = c; // 해당 지점을 기준으로 4방 탐색 하게끔
			i=-1;//다시 사방 탐색
		}

	}

}