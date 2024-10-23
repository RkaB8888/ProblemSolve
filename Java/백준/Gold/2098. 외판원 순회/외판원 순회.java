import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:?KB, 시간:?ms
 */
public class Main {
	static final int INF = 16*1000000;
	static int N, adjMatrix[][], dp[][], endbit, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		endbit = (1 << N)-1;
		dp = new int[(1 << N)][N];
		for(int[] row : dp) {
			Arrays.fill(row, INF);
		}
		dp[1][0] = 0;
		result = INF;
		dfs(1, 0, 0);

		System.out.print(result);
	}

	private static void dfs(int bitmask, int lastNode, int sum) {
		if(result<=sum) return;
		if (bitmask == endbit) {
			if(adjMatrix[lastNode][0]!=0) {
				result = Math.min(result, dp[endbit][lastNode]+adjMatrix[lastNode][0]);
			}
			return;
		}
		for (int i = 1; i < N; i++) {
			if (adjMatrix[lastNode][i] != 0 && (bitmask & (1 << i)) == 0) { // 방문한 적 없음
				int nextBitmask = bitmask | (1 << i);
				int nextSum = sum + adjMatrix[lastNode][i];
				if (dp[nextBitmask][i] > nextSum) {
					dp[nextBitmask][i] = nextSum;
					dfs(nextBitmask, i, nextSum);
				}
			}
		}
	}
}