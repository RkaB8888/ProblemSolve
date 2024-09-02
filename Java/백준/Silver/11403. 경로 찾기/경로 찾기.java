import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 ?KB 시간 ?ms
 */
public class Main {
	static int N, map[][], result[][];
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		result = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, i);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int str, int cur) {
		for (int i = 0; i < N; i++) {
			if (map[cur][i] == 1 && !visited[i]) {
				result[str][i] = 1;
				visited[i] = true;
				dfs(str,i);
			}
		}
	}
}