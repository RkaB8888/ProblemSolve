import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, M, K;
	static int[][] adjMap = new int[301][301];
	static int[][] dp = new int[301][301];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < 301 ; i++) {
			Arrays.fill(dp[i],-1);
		}
		for(int i = 0 ; i < K ; i++) {
			int a, b, c;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			if(a<b){
				adjMap[a][b] = Math.max(adjMap[a][b],c);
			}
		}
		dp[1][1] = 0;
		for(int cur = 2 ; cur <= N ; cur++) {
			for(int pre = 1 ; pre < cur ; pre++) {
				for(int m = 2 ; m <= M ; m++) {
					if(adjMap[pre][cur]==0 || dp[pre][m-1]==-1) continue;
					dp[cur][m] = Math.max(dp[cur][m], dp[pre][m-1]+adjMap[pre][cur]);
				}
			}
		}
		int result = 0;
		for(int i = 2 ; i <= M ; i++) {
			result = Math.max(result, dp[N][i]);
		}
		System.out.print(result);
	}
}