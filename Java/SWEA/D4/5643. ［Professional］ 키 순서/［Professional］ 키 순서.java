import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
				map[0][i] = -1;
			}
			result = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
			}
			for (int i = 1; i <= N; i++) {
				if (map[i][0] == -1)
					dfs1(i);
				if (map[0][i] == -1)
					dfs2(i);
				if (map[i][0] + map[0][i] == N - 1)
					result++;
			}
//			for(int i = 0 ; i <= N ; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("//////////////////////////////////");
//			for(int i = 0 ; i <= N ; i++) {
//				System.out.println(Arrays.toString(rmap[i]));
//			}
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

	public static void dfs2(int str) {
		map[0][str] = 0;
		for (int i = 1; i <= N; i++) {
			if (map[i][str] == 0)
				continue;
			if (map[0][i] == -1) {
				dfs2(i);
			}
			if (map[0][i] != 0) {
				for (int j = 1; j <= N; j++) {
					if (map[j][i] == 1)
						map[j][str] = 1;
				}
			}
			
		}
		for (int i = 1; i <= N; i++) {
			map[0][str] += map[i][str];
		}
	}
}