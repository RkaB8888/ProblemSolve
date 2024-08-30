import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:11,976KB, 시간:76ms
 * @author SSAFY
 *
 */
public class Main {
	static final int[][] dr_dc = {{-1,0},{1,0},{0,-1},{0,1}};	//상하좌우
	static int N, M;
	static char map[][];
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i++) {
			map[i] = br.readLine().toCharArray();
		}
		System.out.println(bfs());
	}
	public static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited[0][0] = true;
		q.add(new int[]{0,0,1});
		while(!q.isEmpty()) {
			int[] curP = q.poll();
			int curX = curP[0], curY = curP[1], cnt = curP[2];
			if(curX==N-1&&curY==M-1) return cnt;
			for(int i = 0 ; i < 4 ; i++) {
				int r = curX+dr_dc[i][0];
				int c = curY+dr_dc[i][1];
				if(r<0||c<0||r>=N||c>=M||visited[r][c]||map[r][c]=='0') continue;
				visited[r][c] = true;
				q.add(new int[] {r,c,cnt+1});
			}
		}
		return 1;
	}
}