import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:32,240kb, 시간:528ms
 */
public class Main {
	static int N;// 배열 i 크기 N
	static int M;// 배열 j 크기 M
	static int R;// 회전 수
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// 배열 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
/////////////////////////////////입력 끝/////////////////////////////////////////
		//좌 상단 꼭대기를 기준으로 회전 시킬 것
		//껍데기의 갯수
		int shellCnt = Math.min(N, M)/2;
		int n = N, m = M;
		for(int i = 0 ; i < shellCnt ; i++) {
			Shell shell = new Shell(i,i,n,m);
			n-=2;m-=2;
			shell.counterClockWise(R);
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	static class Shell{
		int r;//껍데기 좌상단 i좌표
		int c;//껍데기 좌상단 j좌표
		int n;//껍데기 세로 길이
		int m;//껍데기 가로 길이
		int cnt;//껍데기 요소의 갯수
		public Shell(int r, int c, int n, int m) {
			super();
			this.r = r;
			this.c = c;
			this.n = n;
			this.m = m;
			this.cnt = 2*(n+m)-4;
		}
		public void counterClockWise(int rotate) {
			rotate%=cnt;
			while(rotate>0) {
				int temp = map[r][c];
				int i = r;
				int j = c;
				while(j<c+m-1) {
					map[i][j] = map[i][j+1];
					j++;
				}
				while(i<r+n-1) {
					map[i][j] = map[i+1][j];
					i++;
				}
				while(j>c) {
					map[i][j] = map[i][j-1];
					j--;
				}
				while(i>r) {
					map[i][j] = map[i-1][j];
					i--;
				}
				map[r+1][c] = temp;
				rotate--;
			}
		}
	}

}