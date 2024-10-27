import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모이제이션 메모리 ? KB 시간 ? ms
 * dp[길이][끝 숫자][사용한 비트]
 * @author python98
 */
public class Main {
	static final int DENUM = 1000000000;
	static int N, dp[][][], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][10][1 << 10];// 길이, 마지막 숫자, 사용된 비트
		for (int i = 1; i < 10; i++) {
			dp[1][i][1 << i] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 1, kEnd = 1 << 10; k < kEnd; k++) {
					if (j != 0) {
						dp[i + 1][j - 1][k | 1 << (j - 1)] += dp[i][j][k];
						dp[i + 1][j - 1][k | 1 << (j - 1)] %= DENUM;
					}
					if (j != 9) {
						dp[i + 1][j + 1][k | 1 << (j + 1)] += dp[i][j][k];
						dp[i + 1][j + 1][k | 1 << (j + 1)] %= DENUM;
					}
				}
			}
		}
		int endbit = (1 << 10) - 1;
		for (int i = 0; i < 10; i++) {
			result += dp[N][i][endbit];
			result %= DENUM;
		}
		System.out.print(result);
	}
}