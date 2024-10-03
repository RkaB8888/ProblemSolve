import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author python98 
 * BFS 구현 ? KB ? ms
 */
public class Main {
	static int N, M, result, map[][], drdc[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int rareCnt;
	static Queue<int[]> q = new ArrayDeque<int[]>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 1)
					q.add(new int[] { i, j });
				else if(num == 0) {
					rareCnt++;
				}
			}
		}
		bfs();
		if(rareCnt!=0) {
			System.out.print(-1);
		} else {
			System.out.print(result);
		}
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			result++;
			while (--size >= 0) {
				int[] cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + drdc[i][0];
					int nc = cur[1] + drdc[i][1];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0)
						continue;
					q.add(new int[] { nr, nc });
					map[nr][nc] = 1;
					rareCnt--;
				}
			}
		}
		result--;
		return;
	}
}