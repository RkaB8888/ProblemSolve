import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 259,332KB 시간 2192ms
 * dp
 * goal의 진입차수의 dp값 중 가장 큰 값에 본인의 건설 시간 추가
 */
public class Main {
	static int N, K, adjMatrix[][], buildTimes[], goal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			buildTimes = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i++) {
				buildTimes[i] = Integer.parseInt(st.nextToken());
			}
			adjMatrix = new int[N+1][N+1];
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = 1;
			}
			
			for(int i = 1 ; i <= N ; i++) {
				adjMatrix[0][i] = -1;
			}
			
			goal = Integer.parseInt(br.readLine());
			dfs(goal);
			sb.append(adjMatrix[0][goal]).append('\n');
		}
		System.out.println(sb);
	}
	public static void dfs(int str) {
		if(adjMatrix[0][str]!=-1) return;
		adjMatrix[0][str] = 0;
		for(int i = 1 ; i <= N ; i++) {
			if(adjMatrix[i][str]==1) {
				dfs(i);
				if(adjMatrix[0][str]<adjMatrix[0][i]) adjMatrix[0][str]=adjMatrix[0][i];
			}
		}
		adjMatrix[0][str]+=buildTimes[str];
	}
}