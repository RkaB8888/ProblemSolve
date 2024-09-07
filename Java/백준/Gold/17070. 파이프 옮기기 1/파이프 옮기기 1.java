import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:12488kb, 시간:216ms
 * 
 */
public class Main {
	static int N, result, map[][], dir[][] = {{0,1},{1,0},{1,1}};//가로, 세로, 대각선
	static class Pipe{
		int x, y, status, cnt;
		//status는 0:가로 1:세로 2:대각선
		public Pipe(int x, int y, int status) {
			this.x = x;
			this.y = y;
			this.status = status;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(result);
	}
	public static void bfs() {
		Queue<Pipe> q = new ArrayDeque<>();
		q.add(new Pipe(0,1,0));
		while(!q.isEmpty()) {
			Pipe cur = q.poll();
			if(cur.x==N-1&&cur.y==N-1) {
				result++;
				continue;
			}
			boolean flag = true;//대각선 가능한지 체크
			
			int r = cur.x+dir[0][0], c = cur.y+dir[0][1];
			if(r>=0&&r<N&&c>=0&&c<N&&map[r][c]!=1) {
				if(cur.status!=1) {
					q.add(new Pipe(r,c,0));
				}
			}
			else flag = false;
			
			r = cur.x+dir[1][0]; c = cur.y+dir[1][1];
			if(r>=0&&r<N&&c>=0&&c<N&&map[r][c]!=1) {
				if(cur.status!=0) {
					q.add(new Pipe(r,c,1));
				}
			}
			else flag = false;
			
			r = cur.x+dir[2][0]; c = cur.y+dir[2][1];
			if(flag&&map[r][c]!=1) {
				q.add(new Pipe(r,c,2));
			}
		}
	}
}