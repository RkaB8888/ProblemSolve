import java.io.*;
import java.util.*;

/**
 * @description dp + 윈도우 슬라이스
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, M, K;
	static char[][] map;
	static int[][][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		map = new char[M][];
		dp = new int[M+1][N+1][3];
		for(int i = 0 ; i < M ; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for(int i = 1 ; i <= M ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				dp[i][j][0] = dp[i-1][j][0]+dp[i][j-1][0]-dp[i-1][j-1][0];
				dp[i][j][1] = dp[i-1][j][1]+dp[i][j-1][1]-dp[i-1][j-1][1];
				dp[i][j][2] = dp[i-1][j][2]+dp[i][j-1][2]-dp[i-1][j-1][2];
				if(map[i-1][j-1]=='J') {
					dp[i][j][0]++;
				}else if(map[i-1][j-1]=='O') {
					dp[i][j][1]++;
				}else if(map[i-1][j-1]=='I') {
					dp[i][j][2]++;
				}
			}
		}
		for(int i = 0 ; i < K ; i++) {
			int x1,y1,x2,y2;
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			if(x1>x2) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
			}
			if(y1>y2) {
				int temp = y1;
				y1 = y2;
				y2 = temp;
			}
			int a, b, c;
			a = dp[x2][y2][0]-dp[x1-1][y2][0]-dp[x2][y1-1][0]+dp[x1-1][y1-1][0];
			b = dp[x2][y2][1]-dp[x1-1][y2][1]-dp[x2][y1-1][1]+dp[x1-1][y1-1][1];
			c = dp[x2][y2][2]-dp[x1-1][y2][2]-dp[x2][y1-1][2]+dp[x1-1][y1-1][2];
			sb.append(a+" "+b+" "+c+"\n");
		}
		System.out.print(sb);
	}
}