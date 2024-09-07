import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 메모리 93,528 kb 실행시간 672 ms
 */
public class Solution {
	static int N, M, result;
	static int map[][], rmap[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];
			rmap = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				map[i][0] = -1;
				rmap[i][0] = -1;
			}
			result = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
				rmap[b][a] = 1;
			}
			for (int i = 1; i <= N; i++) {
				if (map[i][0] == -1)
					dfs1(i);
			}
			for(int i = 1 ; i <=N ; i++) {
				for(int j = 1 ; j <= N ; j++) {
					map[0][i]+=map[j][i];
				}
			}
			for (int i = 1; i <= N; i++) {
				if (map[i][0]+map[0][i]==N-1) result++;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs1(int str) {
		map[str][0] = 0;
		for (int i = 1; i <= N; i++) {
			if (map[str][i] == 0)
				continue;
			if (map[i][0] == -1) {
				dfs1(i);
			}
			if (map[i][0] != 0) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1)
						map[str][j] = 1;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			map[str][0] += map[str][i];
		}
	}
}