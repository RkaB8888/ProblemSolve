import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 
 * @author python98
 * BFS 구현
 * ? KB ? ms
 */
public class Main {
	static int N, M, map[][], drdc[][] = {{-1,0},{1,0},{0,-1},{0,1}}, result, group;
	static int spaceCnt[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		spaceCnt = new int[N*M];
		for(int i = 0 ; i < N ; i++) {
			char c[] = br.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = c[j]-'0';
			}
		}
		group = 2;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				result = 0;
				if(map[i][j]==1) {
					bfs(i, j);
				}
				sb.append(result);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	//사방의 빈 공간을 합하는 메서드
	private static void bfs(int row, int col) {
		result++;
		Set<Integer> check = new HashSet<Integer>();
		for(int i = 0 ; i < 4 ; i++) {
			int r = row+drdc[i][0];
			int c = col+drdc[i][1];
			if(r<0||c<0||r>=N||c>=M||map[r][c]==1) continue;
			if(map[r][c]==0) floodfill(r,c);
			if(check.contains(map[r][c])) continue;
			check.add(map[r][c]);
			result+=spaceCnt[map[r][c]];
			result%=10;
		}
	}
	private static void floodfill(int row, int col) {
		
		Queue <int[]> q = new ArrayDeque<>();
		q.add(new int[] {row,col});
		map[row][col]= group;
		int cnt = 1;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int r = cur[0]+drdc[i][0];
				int c = cur[1]+drdc[i][1];
				if(r<0||c<0||r>=N||c>=M||map[r][c]!=0) continue;
				q.add(new int[] {r,c});
				map[r][c]= group;
				cnt++;
			}
		}
		spaceCnt[group] = cnt;
		group++;
	}
	
}