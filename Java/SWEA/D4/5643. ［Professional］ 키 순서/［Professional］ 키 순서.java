import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 91,304 kb 실행시간 1,765 ms
 */
public class Solution {
	static int N, M, result, adjMatrix[][],radjMatrix[][], cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작, 나보다 큰 학생 정보 행으로 유지
			radjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작, 나보다 작은 학생 정보 행으로 유지

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
				radjMatrix[b][a] = 1;
			}
			result = 0;
			for (int i = 1; i <= N; i++) {
				cnt = 0;
				DFS(i, adjMatrix, new boolean[N+1]);// i녀석보다 작은 놈의 개수
				DFS(i, radjMatrix, new boolean[N+1]);
				if (cnt == N - 1)
					result++;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void DFS(int cur,int[][] matrix ,boolean[] visited) {// 나보다 큰 녀석의 수를 찾는다.
		visited[cur] = true;
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && matrix[cur][i] != 0) {
				DFS(i, matrix,visited);
				cnt++; // 나보다 큰 대상 카운팅
			}
		}
	}
}