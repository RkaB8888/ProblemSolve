import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리:92,464KB, 시간:668ms
 * 맵에 저장하는 수치
 *	- 한번 부수고 도달한 최소 거리
 *	- 한번도 안 부수고 도달한 최소 거리
 * 이동하는 녀석은 
 *	- 벽을 부순 적이 있는지
 *	- 이동 거리
 */
public class Main {
	static class Point{
		int wasBroke, notBroke;
		boolean wall;
		public Point(int wasBroke, int notBroke, boolean wall) {
			this.wasBroke = wasBroke;
			this.notBroke = notBroke;
			this.wall = wall;
		}
		
	}
	static class Cursor{
		int i, j, dist;
		boolean broke;
		public Cursor(int i, int j, int dist,boolean broke) {
			this.i = i;
			this.j = j;
			this.dist = dist;
			this.broke = broke;
		}
		
	}
	static int N,M;
	static Point[][] map;
	static final int INF = Integer.MAX_VALUE;
	static final int[][] drdc = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Point[N][M];
		for(int i = 0 ; i < N ; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = new Point(INF,INF,c[j]=='1');
			}
		}
		
		System.out.println(bfs());
	}
	public static int bfs() {
		PriorityQueue<Cursor> pq = new PriorityQueue<Cursor>((a,b)->a.dist-b.dist);
		pq.add(new Cursor(0,0,1,false));
		map[0][0].notBroke = 1;
		map[0][0].wasBroke = 1;
		while(!pq.isEmpty()) {
			Cursor cursor = pq.poll();
			if(cursor.i==N-1&&cursor.j==M-1) return cursor.dist;
			
			for(int i = 0 ; i < 4 ; i++) {
				int r = cursor.i+drdc[i][0];
				int c = cursor.j+drdc[i][1];
				if(r<0||r>=N||c<0||c>=M) continue;
				//커서가 이미 부쉈는데 벽을 만난 경우
				//커서가 이미 부쉈는데 부순 거리가 더 클 경우
				//커서가 부순적 없는데 안부순 거리가 더 클 경우
				if(map[r][c].wall) {
					//벽을 만났는데 이미 부순 기록이 있거나,부수고 그 거리가 더 크거나 같다면
					if(cursor.broke||map[r][c].wasBroke<=cursor.dist+1) continue;
					map[r][c].wasBroke=cursor.dist+1;
					pq.add(new Cursor(r, c, cursor.dist+1,true));
				}
				else {
					//부순 기록이 있고 그 거리가 더 크거나 같은 경우 or 부순 기록이 없지만 그 거리가 더 크거나 같은 경우
					if((cursor.broke&&map[r][c].wasBroke<=cursor.dist+1)||(!cursor.broke&&map[r][c].notBroke<=cursor.dist+1)) continue;
					if(cursor.broke) {
						map[r][c].wasBroke=cursor.dist+1;
					}
					else {
						map[r][c].notBroke=cursor.dist+1;
					}
					pq.add(new Cursor(r, c, cursor.dist+1,cursor.broke));
				}
			}
		}
		return -1;
	}
}