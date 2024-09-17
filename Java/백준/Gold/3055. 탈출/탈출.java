import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 20,408 KB 시간 136 ms
 * BFS 구현 최적화
 */
public class Main {

	static int R, C, hedgehog[], drdc[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 위, 왼, 우, 하
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		int i = 0;
		out:while(i<R) {
			map[i] = br.readLine().toCharArray();
			int j = 0;
			while(j<C) {
				if (map[i][j] == 'S') {
					hedgehog = new int [] {i,j};
					i++;
					break out;
				}
				j++;
			}
			i++;
		}
		while(i<R) {
			map[i] = br.readLine().toCharArray();
			i++;
		}
		System.out.print(bfs());
	}

	public static String bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { hedgehog[0], hedgehog[1], 0 });
		visited[hedgehog[0]][hedgehog[1]] = true;

		while (!q.isEmpty()) {
			//맵 설정
			char[][] curMap = new char[R][C];
			for(int i = 0 ; i < R ; i++) {
				System.arraycopy(map[i], 0, curMap[i], 0, C);
			}
			for(int i = 0 ; i < R ; i++) {
				for(int j = 0 ; j < C ; j++) {
					if(map[i][j]=='*') {
						for(int dir = 0 ; dir<4 ; dir++) {
							int nr = i+drdc[dir][0];
							int nc = j+drdc[dir][1];
							if (nr < 0 || nc < 0 || nr >= R || nc >= C||map[nr][nc]=='X'||map[nr][nc]=='D') continue;
							curMap[nr][nc] = '*';
						}
					}
				}
			}
			map = curMap;
			
			int size = q.size();
			while(--size>=0) {
				//갈 수 있는 곳 추가
				int[] cur = q.poll();
				int r = cur[0], c = cur[1], time = cur[2], nTime = time + 1;
				for (int i = 0; i < 4; i++) {
					int nr = r + drdc[i][0];
					int nc = c + drdc[i][1];
					if (nr < 0 || nc < 0 || nr >= R || nc >= C || visited[nr][nc])
						continue;
					if (map[nr][nc] == '*'||map[nr][nc] == 'X')
						continue;
					else if (map[nr][nc] == 'D')
						return String.valueOf(nTime);
					q.add(new int[] { nr, nc, nTime });
					visited[nr][nc]= true;
				}
			}
		}
		return "KAKTUS";
	}
}