import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리 22,412 KB 시간 256 ms
 * DP
 * @author python98
 */
public class Main {
	static int N, matrixInfo[][], dp[][];//i~j까지의 곱 중 최소 횟수, m, n
	static final int inf = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		matrixInfo = new int[N][2];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			matrixInfo[i][0] = Integer.parseInt(st.nextToken());
			matrixInfo[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(i==j) {
					dp[i][j] = 0;
				} else {
					dp[i][j] = inf;
				}
			}
		}
		
		for(int k = 1 ; k < N ; k++) {//간격
			for(int i = 0 ; i+k < N ; i++) {//시작
				calc(i,i+k);
			}
		}
		System.out.println(dp[0][N-1]);
	}
	private static void calc(int start, int end) {
		for (int mid = start; mid < end; mid++) {
            int rows_left = matrixInfo[start][0];
            int cols_left = matrixInfo[mid][1];
            int cols_right = matrixInfo[end][1];
            
            int cost = dp[start][mid] + dp[mid + 1][end] + rows_left * cols_left * cols_right;

            dp[start][end] = Math.min(dp[start][end], cost);
		}
	}
}