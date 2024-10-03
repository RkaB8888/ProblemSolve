import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * BFS 구현
 * 335,660 KB 1,100 ms
 */
public class Main {
	static int N, M, K, map[][], result, drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean dp[][][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		dp = new boolean[N+1][M+1][K+1];
		for(int i = 1 ; i <= N ; i++) {
			char c[] = br.readLine().toCharArray();
			for(int j = 1 ; j <= M ; j++) {
				map[i][j] = c[j-1]-'0';
			}
		}
		System.out.println(bfs());
	}
	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {1,1,0,1});//i, j, k, 거리
		dp[1][1][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			boolean day = (cur[3]&1)==1;
			if(cur[0]==N&&cur[1]==M) return cur[3];
			for(int i = 0 ; i < 4 ; i++) {
				int nr = cur[0]+drdc[i][0];
				int nc = cur[1]+drdc[i][1];
				if(nr<=0||nc<=0||nr>N||nc>M) continue;
				if(map[nr][nc]==1) {
					if(cur[2]==K||dp[nr][nc][cur[2]+1]) continue;//벽인데 부술 수 없는 경우, 이미 지나간 경우
					if(day) {
						dp[nr][nc][cur[2]+1] = true;
						q.add(new int[] {nr,nc,cur[2]+1,cur[3]+1});
					} else {
						q.add(new int[] {cur[0],cur[1],cur[2],cur[3]+1});
					}
				} else {
					if(dp[nr][nc][cur[2]]) continue;
					dp[nr][nc][cur[2]] = true;
					q.add(new int[] {nr,nc,cur[2],cur[3]+1});
				}
			}
		}
		return -1;
	}
	
}