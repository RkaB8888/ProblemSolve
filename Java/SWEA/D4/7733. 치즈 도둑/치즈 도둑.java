import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;
/**
 * 메모리:39,960KB, 시간:299ms
 * @author SSAFY
 *
 */
public class Solution {
	static int N;
	static boolean[][] check;
	static int[][] cheese;
	static TreeSet<Integer> flavor = new TreeSet<>();
	static int result;
	static final int[][] dr_dc = {{-1,0},{1,0},{0,-1},{0,1}};//상 하 좌 우
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			flavor.clear();
			result = 1;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j++) {
					int val = Integer.parseInt(st.nextToken());
					cheese[i][j] = val;
					flavor.add(val);
				}
			}
			while(!flavor.isEmpty()) {
				int f = flavor.pollFirst();
				check = new boolean[N][N];
				checkInit(f);
				int cnt = 0;
				for(int i = 0 ; i < N ; i++) {
					for(int j = 0 ; j < N ; j++) {
						if(check[i][j]==false) {
							dfs(i,j);
							cnt++;
						}
					}
				}
				if(result<cnt) result=cnt;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int row, int col) {
		check[row][col] = true;
		for(int i = 0 ; i < 4 ; i++) {
			int r = row+dr_dc[i][0];
			int c = col+dr_dc[i][1];
			if(r<0||c<0||r>=N||c>=N||check[r][c]) continue;
			dfs(r,c);
		}
	}
	
	public static void checkInit(int f) {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(cheese[i][j]<=f) check[i][j]=true;
			}
		}
	}
}