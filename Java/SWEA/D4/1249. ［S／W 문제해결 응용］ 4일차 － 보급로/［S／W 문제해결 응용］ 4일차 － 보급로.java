import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	
	static int N, map[][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= TC ; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i = 0 ; i < N ; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = ch[j]-'0';
				}
			}
			System.out.println("#"+t+" "+getMinTime(0, 0, N-1, N-1));
		}
	}
	static int getMinTime(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> Integer.compare(a[2], b[2]));
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				minTime[i][j] = INF;
			}
		}
		
		minTime[sr][sc] = 0;
		pq.offer(new int[] {sr,sc,minTime[sr][sc]});
		
		while(!pq.isEmpty()) {
			
			int[] stopOver = pq.poll();
			int r = stopOver[0];
			int c = stopOver[1];
			int time = stopOver[2];
			if(visited[r][c]) continue;
			visited[r][c] = true;
			if(r==er&&c==ec)
				return time;
			for(int d = 0 ; d < 4 ; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if(nr>=0 && nc>=0 && nr<N&&nc<N&&!visited[nr][nc]
						&&minTime[nr][nc]>time+map[nr][nc]) {
					minTime[nr][nc]=time+map[nr][nc];
					pq.offer(new int[] {nr,nc,minTime[nr][nc]});
				}
			}
		}
		return -1;
	}
}