import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//2583
	static int M, N, K, cnt;
	static boolean[][] map;
	static List<Integer> m2;
	static int[][] dr_dc = {{0,1},{0,-1},{1,0},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int dlx = Integer.parseInt(st.nextToken());
			int dly = Integer.parseInt(st.nextToken());
			int urx = Integer.parseInt(st.nextToken());
			int ury = Integer.parseInt(st.nextToken());
			for(int j = dlx ; j < urx ; j++) {
				for(int k = dly ; k < ury ; k++) {
					map[j][k] = true;
				}
			}
		}
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < M ; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		m2 = new ArrayList<>();//넓이 저장
		process();
		m2.sort((a,b)->a-b);
		sb.append(cnt).append('\n');
		for(int i : m2) {
			sb.append(i).append(' ');
		}
		System.out.println(sb);
	}
	public static void process() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j]==false) {
					m2.add(counting(i,j));
					cnt++;
				}
			}
		}
	}
	public static int counting(int row, int col) {
		Queue<int[]> q = new ArrayDeque<>();
		int[] str = {row,col};
		q.add(str);
		map[row][col] = true;
		int m = 1;
		while(!q.isEmpty()) {
			int[] node = q.poll();
			int x = node[0];
			int y = node[1];
			for(int i = 0 ; i < 4 ; i++) {
				int r = x+dr_dc[i][0];
				int c = y+dr_dc[i][1];
				if(r<0||c<0||r>=N||c>=M||map[r][c]) continue;
				map[r][c] = true;
				q.add(new int[] {r,c});
				m++;
			}
		}
		return m;
	}
}