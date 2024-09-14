import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 ? KB 시간 ? ms
 * 위상정렬
 */
public class Main {

	static int N, M, result[];
	static int[][] adjMatrix;
	static boolean[] isSelect;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];// 두 요소의 합이 소수가 되는 경우를 간선으로 담아두는 리스트
		result = new int[N + 1];
		isSelect = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
			adjMatrix[a][0]++;
			adjMatrix[0][b]++;
		}
		bfs();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(' ');
		}
		System.out.println(sb);
	}

	public static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[0][i] == 0) {
				q.add(i);
			}
		}
		int curTime = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			curTime++;
			while (--size >= 0) {
				int node = q.poll();
				result[node] = curTime;
				isSelect[node] = true;
				for (int i = 1; i <= N; i++) {
					if (adjMatrix[node][i] == 1) {
						adjMatrix[node][i] = 0;
						adjMatrix[node][0]--;
						adjMatrix[0][i]--;
					}
				}
			}
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[0][i] == 0 && !isSelect[i]) {
					q.add(i);
				}
			}

		}
	}
}