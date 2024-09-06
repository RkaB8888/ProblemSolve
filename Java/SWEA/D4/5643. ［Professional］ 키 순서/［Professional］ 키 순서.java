import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 94,988 kb 실행시간 2,052 ms
 */
public class Solution {
	static int N, M, result, adjMatrix[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}
			result = 0;
			for (int i = 1; i <= N; i++) {
				cnt = 0;
				ltDFS(i, new boolean[N+1]);// i녀석보다 작은 놈의 개수
				gtDFS(i, new boolean[N+1]);
				if (cnt == N - 1)
					result++;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void gtDFS(int cur, boolean[] visited) {// 나보다 큰 녀석의 수를 찾는다.
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adjMatrix[cur][i] != 0) {
				gtDFS(i, visited);
				cnt++; // 나보다 큰 대상 카운팅
			}
		}
	}

	public static void ltDFS(int cur, boolean[] visited) {// 나보다 큰 녀석의 수를 찾는다.
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adjMatrix[i][cur] != 0) {
				ltDFS(i, visited);
				cnt++; // 나보다 큰 대상 카운팅
			}
		}
	}
}