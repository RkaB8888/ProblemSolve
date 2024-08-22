import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:37,020KB, 시간:288ms
 */
public class Main {
	static int R, C;
	static boolean[][] map;
	static int cnt;
	static boolean flag;//생성된 경로 원복할지 결정
	static int[] dr = {-1,0,1}; //우상 우 우하
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				if (temp.charAt(j) == 'x')
					map[i][j] = true;
			}
		}
		C-=2;
		for (int i = 0; i < R; i++) {
			flag = false;
			findPipePath(i, 0);
		}
		System.out.println(cnt);

	}

	public static void findPipePath(int row, int col) {
		if(col==C) {
			cnt++;
			flag = true;
			return;
		}
		int c = col+1;
		for(int i = 0 ; i < 3 ; i++) {
			int r = row+dr[i];
			if(r<0||r>=R||map[r][c]) continue;
			map[r][c]=true;
			findPipePath(r,c);
			if(flag) return;
		}
		
	}

}