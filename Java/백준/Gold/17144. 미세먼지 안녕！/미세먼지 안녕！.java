import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:?KB, 시간:?ms
 */
public class Main {
	static int R, C, T, map[][], result;
	static final int rotate[][][] = {//먼지 회전을 위해 탐색해야 하는 방향.
			{{-1,0},{0,1},{1,0},{0,-1}},
			{{1,0},{0,1},{-1,0},{0,-1}}
			};
	static int checkLen[][];
	static int airUp = -1, airDown = -1;
	static Queue<Munge> q = new ArrayDeque<>();
	static class Munge{
		int row,col,val, val5;

		public Munge(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
			this.val5 = val/5;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < C ; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
				if(val == -1) {
					if(airUp==-1) airUp = i;
					else airDown = i;
				} else if(val>0) {
					q.add(new Munge(i,j,val));
				}
			}
		}
		checkLen = new int[2][4];
		checkLen[0][0] = airUp;
		checkLen[0][1] = C-1;
		checkLen[0][2] = airUp;
		checkLen[0][3] = C-2;
		checkLen[1][0] = R-airDown-1;
		checkLen[1][1] = C-1;
		checkLen[1][2] = R-airDown-1;
		checkLen[1][3] = C-2;
		int time = 0;
		while(time<T) {
			spread();//먼지의 확산
//			System.out.println("공기청정기 윗부분 작동");
			air(0);//공기청정기 윗부분 작동
//			System.out.println("공기청정기 아랫부분 작동");
			air(1);//공기청정기 아랫부분 작동
			mungeCollect();//공기 중 먼지 Q에 담기
			time++;
		}
		while(!q.isEmpty()) {
			result+=q.poll().val;
		}
		System.out.print(result);
	}
	//공기 중 먼지 Q에 담기
	private static void mungeCollect() {
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
				if(map[i][j]>0) {
					q.add(new Munge(i, j, map[i][j]));
				}
			}
		}
	}
	//공기청정기 작동
	private static void air(int n) {
		int ur = (n==0?airUp:airDown), uc = 0;//이동할 먼지의 위치
		int r,c;
		for(int ro = 0 ; ro < 4 ; ro++) {//회전 방향
			//윗 부분
			for(int i = 0 ; i < checkLen[n][ro] ; i++) {
				r = ur; c = uc;
				ur += rotate[n][ro][0];
				uc += rotate[n][ro][1];
//				System.out.println(ur+", " +uc+", " +r+", " +c);
				shift(ur,uc,r,c);
			}
		}
	}
	//먼지 이동
	private static void shift(int curR, int curC, int nextR, int nextC) {
		if(map[curR][curC]>0) {//이동할 먼지가 있음
			if(map[nextR][nextC]!=-1) {
				map[nextR][nextC] = map[curR][curC];
			}
			map[curR][curC] = 0;
		}
	}
	//먼지의 확산
	private static void spread() {
		while(!q.isEmpty()) {
			Munge cur = q.poll();
			if(cur.val5!=0) {
				for(int i = 0 ; i < 4 ; i++) {
					int nr = cur.row + rotate[0][i][0];
					int nc = cur.col + rotate[0][i][1];
					if(nr<0||nc<0||nr>=R||nc>=C||map[nr][nc]==-1) continue;
					map[cur.row][cur.col]-=cur.val5;
					map[nr][nc]+=cur.val5;
				}
			}
		}
	}
}