import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:52,216kb, 시간:1,408ms 큐에 넣어서 한 타임마다 size로 while문 돌림 큐에서 뽑기 전에 매번 정렬시켜서
 * 마지막에 칸을 차지하면 그 방향대로 크리쳐에는 방향과 좌표, 맵에는 양이 담겨 있다
 */
public class Solution {
	static int N, M, K, map1[][], map2[][];// 한변의 길이, 격리시간, 군집 수
	static final int[][] dr_dc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static final int[] mirror = { 1, 0, 3, 2 };
	static List<creature> l1 = new ArrayList<>();
	static List<creature> l2 = new ArrayList<>();
	static int result;

	static class creature {
		int i;
		int j;
		int path;

		public creature(int i, int j, int path) {
			super();
			this.i = i;
			this.j = j;
			this.path = path;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map1 = new int[N][N];
			map2 = new int[N][N];
			l1.clear();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int path = Integer.parseInt(st.nextToken()) - 1;
				l1.add(new creature(r, c, path));
				map1[r][c] = num;
			}
			simulation();
			result = 0;
			for(creature a : l1) {
				result+=map1[a.i][a.j];
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	public static void simulation() {
		int curTime = 0;
		while (++curTime<=M&&!l1.isEmpty()) {
			l1.sort((a, b) -> map1[b.i][b.j] - map1[a.i][a.j]);
			for(creature creatureA : l1 ) {
				int amount = map1[creatureA.i][creatureA.j];
				map1[creatureA.i][creatureA.j] = 0;
				creatureA.i += dr_dc[creatureA.path][0];
				creatureA.j += dr_dc[creatureA.path][1];
				if (map2[creatureA.i][creatureA.j]>0) {// 이미 선점되어 있다면
					map2[creatureA.i][creatureA.j] += amount;
					continue;
				}
				map2[creatureA.i][creatureA.j] += amount;
				if (creatureA.i == 0 || creatureA.j == 0 || creatureA.i == N - 1 || creatureA.j == N - 1) {
					creatureA.path = mirror[creatureA.path];
					map2[creatureA.i][creatureA.j] /= 2;
				}
				if (map2[creatureA.i][creatureA.j] == 0) continue;
				l2.add(creatureA);
			}
			l1.clear(); 
			l1.addAll(l2);
			l2.clear();

			map1 = map2;
			map2 = new int[N][N];
		}
	}
}