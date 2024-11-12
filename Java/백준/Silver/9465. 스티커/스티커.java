import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP 메모리 139,180 KB 시간 696 ms
 */
public class Main {
    static int N;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[2][N];
            
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int[] dp = new int[3]; // [0]: 첫 행 선택, [1]: 둘째 행 선택, [2]: 선택 안함
            dp[0] = map[0][0];
            dp[1] = map[1][0];
            dp[2] = 0;
            
            for (int i = 1; i < N; i++) {
                int newDp0 = Math.max(dp[1], dp[2]) + map[0][i];
                int newDp1 = Math.max(dp[0], dp[2]) + map[1][i];
                int newDp2 = Math.max(dp[0], Math.max(dp[1], dp[2]));
                
                dp[0] = newDp0;
                dp[1] = newDp1;
                dp[2] = newDp2;
            }
            
            sb.append(Math.max(dp[0], Math.max(dp[1], dp[2]))).append('\n');
        }
        
        System.out.print(sb);
    }
}