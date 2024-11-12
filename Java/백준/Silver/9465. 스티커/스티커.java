import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP 메모리 ? KB 시간 ? ms
 */
public class Main {
    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++) {
        	N = Integer.parseInt(br.readLine());
        	map = new int[2][N];
        	dp = new int[N][3]; //[0]:0선택, [1]:1선택, [2]:선택안함
        	for(int i = 0 ; i < 2 ; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j = 0 ; j < N ; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	dp[0][0] = map[0][0];
        	dp[0][1] = map[1][0];
        	dp[0][2] = 0;
        	for(int i = 1 ; i < N ; i++) {
        		dp[i][0] = Math.max(dp[i-1][1]+map[0][i], dp[i-1][2]+map[0][i]);
        		dp[i][1] = Math.max(dp[i-1][0]+map[1][i], dp[i-1][2]+map[1][i]);
        		dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        	}
        	sb.append(Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]))).append('\n');
        }
        System.out.print(sb);
    }
}