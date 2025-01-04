import java.io.*;

/**
 * @description DP
 * @performance 메모리: ? KB, 동작 시간: ? ms
 * @author python98
 */
public class Main {
    static int T, K, N;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        dp = new int[15][15];
        for(int t = 1 ; t <= T ; t++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            if(dp[K][N] == 0) {
                dp[K][N] = getResult(K,N);
            }
            sb.append(dp[K][N]).append("\n");
        }
        System.out.print(sb);
    }

    private static int getResult(int k, int n) {
        if(k == 0) {
            return n;
        }
        int result = 0;
        for(int i = 1 ; i <= n ; i++) {
            if(dp[k-1][i]==0) {
                dp[k-1][i]=getResult(k-1,i);
            }
            result += dp[k-1][i];
        }
        return result;
    }
}