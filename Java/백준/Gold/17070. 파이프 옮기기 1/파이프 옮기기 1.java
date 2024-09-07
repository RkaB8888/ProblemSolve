import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:400220kb, 시간:676ms dp사용 가로는 대각선,가로에 가능 dp[i+1][j][0] += dp[i][j][0];
 * dp[i+1][j+1][2] += dp[i][j][0]; 세로는 대각선, 세로에 가능 dp[i][j+1][1] += dp[i][j][1];
 * dp[i+1][j+1][2] += dp[i][j][1]; 대각선은 대각선, 가로, 세로 가능 dp[i+1][j][0] +=
 * dp[i][j][2]; dp[i][j+1][1] += dp[i][j][2]; dp[i+1][j+1][2] += dp[i][j][2];
 */
public class Main {
	static int N, result, map[][], dir[][] = { { 0, 1 }, { 1, 0 }, { 1, 1 } };// 가로, 세로, 대각선
	static int dp[][][];// i,j,type

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (map[0][2] == 0) {
			dp[0][2][0] = 1;
			if (map[1][2] == 0 && map[1][1] == 0) {
				dp[1][2][2] = 1;
			}
			for (int i = 0, iEnd = N - 1; i < iEnd; i++) {
				for (int j = 2, jEnd = N - 1; j < jEnd; j++) {
					if (map[i][j] == 1)
						continue;
					if (map[i + 1][j] == 0 && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0) {
						dp[i + 1][j + 1][2] += dp[i][j][0];
						dp[i + 1][j + 1][2] += dp[i][j][1];
						dp[i + 1][j + 1][2] += dp[i][j][2];
					}
					if (map[i + 1][j] == 0) {
						dp[i + 1][j][1] += dp[i][j][1];
						dp[i + 1][j][1] += dp[i][j][2];
					}
					if (map[i][j + 1] == 0) {
						dp[i][j + 1][0] += dp[i][j][0];
						dp[i][j + 1][0] += dp[i][j][2];
					}
				}
			}
			for (int i = 1, iEnd = N - 1, j = N - 1; i < iEnd; i++) {
				if (map[i][j] == 1 || map[i + 1][j] == 1)
					continue;
				dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2];
			}
			for (int j = 3, jEnd = N - 1, i = N - 1; j < jEnd; j++) {
				if (map[i][j] == 1 || map[i][j + 1] == 1)
					continue;
				dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2];
			}
		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}