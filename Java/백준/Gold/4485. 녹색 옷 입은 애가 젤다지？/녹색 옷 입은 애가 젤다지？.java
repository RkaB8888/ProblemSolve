import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리:16,920KB, 시간:176ms
 */
public class Main {
	static int N, map[][],result, dr[] = {-1,1,0,0},dc[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		String s = "Problem ";
		int tc = 0;
		while(N>0) {
			tc++;
			map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(s).append(tc).append(':').append(' ').append(process()).append('\n');
			N = Integer.parseInt(br.readLine());
		}
		System.out.println(sb);
	}
	public static int process() {
		int[][] minLost = new int[N][N];
		int INF = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				minLost[i][j] = INF;
			}
		}
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)-> a[2]-b[2]);
		pq.add(new int[] {0,0,map[0][0]});
		minLost[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int c = cur[0];
			int r = cur[1];
			int l = cur[2];
			if(c==N-1&&r==N-1) return l;
			for(int i = 0 ; i < 4 ; i++) {
				int nr = c+dr[i];
				int nc = r+dc[i];
				if(nr<0||nc<0||nr>=N||nc>=N) continue;
				int nl = l + map[nr][nc];
				if(nl>=minLost[nr][nc]) continue;
				minLost[nr][nc] = nl;
				pq.add(new int[] {nr,nc,nl});
			}
		}
		return -1;
	}

}