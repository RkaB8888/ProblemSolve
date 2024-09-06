import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, result, adjMatrix[][];

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
				int numDown = ltBFS(i);// i녀석보다 작은 놈의 개수
				int numUp = gtBFS(i);
				if (numUp + numDown == N - 1)
					result++;
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static int gtBFS(int start) {// 나보다 큰 녀석의 수를 찾는다.
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {

			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 큰 대상 카운팅
				}
			}
		}
		return cnt;
	}

	public static int ltBFS(int start) {// 나보다 작은 녀석의 수를 찾는다.
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {

			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[i][cur] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 큰 대상 카운팅
				}
			}
		}
		return cnt;
	}
}