import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, map[][], result;

	static class Position {
		int i;
		int j;
		int time;
		int prior;

		public Position(int i, int j, int prior) {
			this.i = i;
			this.j = j;
			this.time = 2 * prior;
			this.prior = prior;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 입력 시작
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[2 * K + N][2 * K + M];
			result = 0;
			PriorityQueue<Position> pq = new PriorityQueue<Position>((a, b) -> {
				return b.prior - a.prior;
			});
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num != 0) {
						map[K + i][K + j] = num;
						Position p = new Position(K + i, K + j, num);
						pq.add(p);
						result++;
					}
				}
			} // 입력 끝
				// 처리시작
			int curTime = 0;
			while (curTime < K) {
				pq = process(pq);
				curTime++;
			} // 처리 끝
				// 출력준비
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static PriorityQueue<Position> process(PriorityQueue<Position> pq) {
		PriorityQueue<Position> pq2 = new PriorityQueue<Position>((a, b) -> {
			return b.prior - a.prior;
		});
		while (!pq.isEmpty()) {
			Position p = pq.poll();
			p.time--;
			// p.time이
			// p.prior이면 활성상태
			// p.prior-1이면 번식한 상태
			// 0이면 즉은 상태

			if (p.time == p.prior - 1) {
				// 번식
				int[][] di_dj = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
				for (int i = 0; i < 4; i++) {
					int r = p.i + di_dj[i][0];
					int c = p.j + di_dj[i][1];
					if (map[r][c] == 0) {
						map[r][c] = p.prior;
						pq2.add(new Position(r, c, p.prior));
						result++;
					}
				}
			}
			if (p.time == 0) {
				// 사망처리
				result--;
				continue;
			}
			pq2.add(p);
		}
		return pq2;
	}
}