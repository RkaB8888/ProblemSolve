import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:11788kb, 시간:68ms
 * dp
 */
public class Main {
	static int N, result, map[][];
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
			int[] dpTemp;
			for (int i = 0, iEnd = N - 1; i < iEnd; i++) {
				for (int j = 2, jEnd = N - 1; j < jEnd; j++) {
					if (map[i][j] == 1)
						continue;
					boolean flag = true;
					dpTemp = dp[i][j];
					if (map[i + 1][j] == 0) {
						dp[i + 1][j][1] += dpTemp[1];
						dp[i + 1][j][1] += dpTemp[2];
					} else flag = false;
					if (map[i][j + 1] == 0) {
						dp[i][j + 1][0] += dpTemp[0];
						dp[i][j + 1][0] += dpTemp[2];
					} else flag = false;
					if (flag && map[i + 1][j + 1] == 0) {
						dp[i + 1][j + 1][2] += dpTemp[0];
						dp[i + 1][j + 1][2] += dpTemp[1];
						dp[i + 1][j + 1][2] += dpTemp[2];
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