import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// ? KB ? ms
public class Main {
	static int N, result;
	static int[][][] dp;//날짜,지각,결석
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2][3];
		dp[1][0][0] = 1;
		dp[1][1][0] = 1;
		dp[1][0][1] = 1;
		for(int i = 2 ; i <= N ; i++) {
			dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2])%1000000;
			dp[i][0][1] = dp[i-1][0][0];
			dp[i][0][2] = dp[i-1][0][1];
			dp[i][1][0] = (dp[i][0][0]+dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2])%1000000;
			dp[i][1][1] = dp[i-1][1][0];
			dp[i][1][2] = dp[i-1][1][1];
		}
		result =  (dp[N][0][0]+dp[N][0][1]+dp[N][0][2]+dp[N][1][0]+dp[N][1][1]+dp[N][1][2])%1000000;
		System.out.print(result);
	}

}