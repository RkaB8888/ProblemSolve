import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:32,976kb, 시간:300ms
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
			rotate%=cnt; // 실제 회전 수 계산
			int[] arr = new int[cnt]; // 껍데기 요소를 저장함
			int i = 0, j = 0, arridx = 0;
			
			// 껍데기 요소를 배열에 반시계방향으로 저장
			while(j<m-1) arr[arridx++] = map[r][c+j++]; // 위
			while(i<n-1) arr[arridx++]=map[r+i++][c+j]; // 오른쪽
			while(j>0) arr[arridx++]=map[r+i][c+j--]; // 아래
			while(i>0) arr[arridx++]=map[r+i--][c]; // 왼쪽
			
			//저장한 배열을 시프트한 결과를 저장할 배열 생성
			int[] arrshift = new int[cnt];
			int idx1 = cnt-1, idx2 = idx1-rotate;
			int cnt2 = 0;
			
			//rotate만큼 시프트 과정
			while(cnt2<cnt) {
				arrshift[idx2--] = arr[idx1--];
				if(idx2<0) idx2+=cnt;
				if(idx1<0) idx1+=cnt;
				cnt2++;
			}
			
			//시프트 배열을 토대로 다시 맵에 저장
			arridx = 0; j = 0; i = 0;
			while(j<m-1) map[r][c+j++] = arrshift[arridx++]; // 위
			while(i<n-1) map[r+i++][c+j]=arrshift[arridx++]; // 오른쪽
			while(j>0) map[r+i][c+j--]=arrshift[arridx++]; // 아래
			while(i>0) map[r+i--][c]=arrshift[arridx++]; // 왼쪽
		}
	}

}