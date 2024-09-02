import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:68332KB, 시간:280ms
 */
public class Main {
	static int N, M, map[][];
	static boolean checkMap[][];
	static int dr_dc[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static virus[] viruses;
	static int virusCnt;
	static int max;
	static int cnt0;
	static class virus {
		int i, j;

		public virus(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		checkMap = new boolean[N][M];
		viruses = new virus[10];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if (num == 2) {
					viruses[virusCnt++] = new virus(i, j);
				}
				else if(num==0) cnt0++;
			}
		}
		viruses = Arrays.copyOf(viruses, virusCnt);
		int num = N * M;
		int r1, r2, r3, c1, c2, c3;
		for (int i = 0, iEnd = num - 2; i < iEnd; i++) {
			r1 = i/M; c1 = i%M;
			if(map[r1][c1] != 0) continue;
			map[r1][c1] = 1;
			for (int j = i + 1, jEnd = num - 1; j < jEnd; j++) {
				r2 = j/M; c2 = j%M;
				if(map[r2][c2] != 0) continue;
				map[r2][c2] = 1;
				for (int k = j + 1; k < num; k++) {
					r3 = k/M; c3 = k%M;
					if(map[r3][c3] != 0) continue;
					map[r3][c3] = 1;
					spread();
					clear();
					map[r3][c3] = 0;
				}
				map[r2][c2] = 0;
			}
			map[r1][c1] = 0;
		}
		System.out.println(max-3);
	}

	public static void spread() {
		int cnt = cnt0;
		Queue<int[]> q = new ArrayDeque<>();
		for (int i = 0; i < virusCnt; i++) {
			q.add(new int[] { viruses[i].i, viruses[i].j });
		}
		while (!q.isEmpty()) {
			int[] val = q.poll();
			int r = val[0], c = val[1];
			for (int i = 0; i < 4; i++) {
				int row = r + dr_dc[i][0];
				int col = c + dr_dc[i][1];
				if (row < 0 || col < 0 || row >= N || col >= M || map[row][col] != 0)
					continue;
				map[row][col] = 3;
				cnt--;
				q.add(new int[] { row, col });
			}
		}
		if(max<cnt) max = cnt;
	}

	public static void clear() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 3)
					map[i][j] = 0;
			}
		}
	}
}