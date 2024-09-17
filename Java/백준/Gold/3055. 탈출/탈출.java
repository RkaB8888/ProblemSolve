import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 18,548 KB 시간 192 ms
 * BFS 구현
 */
public class Main {

	static int R, C, hedgehog[], drdc[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 위, 왼, 우, 하
	static char[][] map;
	static boolean[][][] visited;
	static List<char[][]> mapList = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C][R * C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					hedgehog = new int [] {i,j};
				}
			}
		}
		mapList.add(map);
		System.out.print(bfs());
	}

	public static String bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { hedgehog[0], hedgehog[1], 0 });
		visited[hedgehog[0]][hedgehog[1]][0] = true;

		while (!q.isEmpty()) {

			int[] cur = q.poll();

			int r = cur[0], c = cur[1], time = cur[2], nTime = time + 1;

			for (int i = 0; i < 4; i++) {
				int nr = r + drdc[i][0];
				int nc = c + drdc[i][1];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc][nTime])
					continue;
				if (mapList.size() == nTime) {// 다음 지도는 준비되어 있지 않다는 뜻
					createNextMap(time);
				}
				char[][] nMap = mapList.get(nTime);
				if (nMap[nr][nc] == '*'||nMap[nr][nc] == 'X')
					continue;
				else if (nMap[nr][nc] == 'D')
					return String.valueOf(nTime);
				q.add(new int[] { nr, nc, nTime });
				visited[nr][nc][nTime] = true;
			}

		}
		return "KAKTUS";
	}

	public static void createNextMap(int curTime) {
		char[][] curMap = mapList.get(curTime);
		char[][] nextMap = new char[R][C];
		for(int i = 0 ; i < R ; i++) {
			System.arraycopy(curMap[i], 0, nextMap[i], 0, C);
		}
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(curMap[i][j]=='*') {
					for(int dir = 0 ; dir<4 ; dir++) {
						int nr = i+drdc[dir][0];
						int nc = j+drdc[dir][1];
						if (nr < 0 || nc < 0 || nr >= R || nc >= C||curMap[nr][nc]=='X'||curMap[nr][nc]=='D') continue;
						nextMap[nr][nc] = '*';
					}
				}
			}
		}
		mapList.add(nextMap);
	}
}