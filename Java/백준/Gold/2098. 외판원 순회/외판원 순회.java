import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:?KB, 시간:?ms
 */
public class Main {
	static final int INF = Integer.MAX_VALUE/2;
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
		endbit = (1 << N) - 1;
		dp = new int[1 << N][N]; // [비트마스크][마지막 방문 노드]
		for(int[] row : dp) {
			Arrays.fill(row, INF);
		}
		dp[1][0] = 0;
		for(int nextNode = 1 ; nextNode < N ; nextNode++) {
			if(adjMatrix[0][nextNode]!=0) {
				int nextMask = (1|(1<<nextNode));
				dp[nextMask][nextNode] = adjMatrix[0][nextNode];
			}
		}
		
		for(int mask = 3 ; mask < endbit ; mask++) {
			for(int lastNode = 1 ; lastNode < N ; lastNode++) {
				if((mask&(1<<lastNode))==0) continue; // 방문한 적 없음
				for(int nextNode = 1 ; nextNode < N ; nextNode++) {
					if((mask&(1<<nextNode))!=0) continue; // 방문한 적 있음
					if(adjMatrix[lastNode][nextNode]!=0) {
						int nextMask = (mask|(1<<nextNode));
						dp[nextMask][nextNode] = Math.min(dp[nextMask][nextNode], dp[mask][lastNode]+adjMatrix[lastNode][nextNode]);
					}
				}
			}
		}
		result = INF;
		for(int lastNode = 1 ; lastNode < N ; lastNode++) {
			if(adjMatrix[lastNode][0]!=0) { 
				result = Math.min(result, dp[endbit][lastNode]+adjMatrix[lastNode][0]);
			}
		}
		System.out.print(result);
	}
}