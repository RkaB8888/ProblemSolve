import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:51012KB, 시간:532ms
 */
public class Main {
	static int K, R, C, result = -1;
	static mapInfo map[][];
	static final int dir4[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static final int dir8[][] = {{-2,-1},{-2,1},{2,1},{2,-1},{-1,2},{1,2},{-1,-2},{1,-2}};
	
	static class mapInfo{
		int[] arrK;//남아있는 K횟수에 대한 최소 횟수 저장
		boolean isWall;

		public mapInfo(int k, int wall) {
			this.arrK = new int[k+1];
			Arrays.fill(this.arrK, Integer.MAX_VALUE);
			this.isWall = wall==1;
		}
		
	}
	
	static class Monkey{
		int i, j, k, cnt;

		public Monkey(int i, int j, int k, int cnt) {
			this.i = i;
			this.j = j;
			this.k = k;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new mapInfo[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				map[i][j] = new mapInfo(K,Integer.parseInt(st.nextToken()));
			}
		}
		bfs();
		System.out.println(result);
	}
	public static void bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		q.add(new Monkey(0,0,K,0));
		
		while(!q.isEmpty()) {
			Monkey m = q.poll();
			int x = m.i, y = m.j, k = m.k, cnt = m.cnt;
			if(x==R-1&&y==C-1) {
				result = cnt;
				return;
			}
			for(int i = 0 ; i < 4 ; i++) {
				int r = x+dir4[i][0], c = y+dir4[i][1];
				if(r<0||c<0||r>=R||c>=C||map[r][c].isWall) continue;
				if(map[r][c].arrK[k]>cnt+1) {
					map[r][c].arrK[k] = cnt+1;
					q.add(new Monkey(r, c, k, cnt+1));
				}
			}
			if(k>0) {
				for(int i = 0 ; i < 8 ; i++) {
					int r = x+dir8[i][0], c = y+dir8[i][1];
					if(r<0||c<0||r>=R||c>=C||map[r][c].isWall) continue;
					if(map[r][c].arrK[k-1]>cnt+1) {
						map[r][c].arrK[k-1] = cnt+1;
						q.add(new Monkey(r, c, k-1, cnt+1));
					}
				}
			}
		}
	}
}