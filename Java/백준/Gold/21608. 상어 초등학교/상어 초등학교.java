import java.io.*;
import java.util.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	static final int[][] DIR = {{0,1},{0,-1},{1,0},{-1,0}};

	static int N;
	static int[][] map, fmap, smap, friend, pos;

	private static void set(int num){
		int r = N+1, c = N+1;
		int s[][] = new int[16][2];
		int top = 0;
		for(int i = 0 ; i < 4 ; i++) {
			int fnum = friend[num][i];
			int fr = pos[fnum][0];
			int fc = pos[fnum][1];
			if(fr==0||fc==0) {
				continue;
			}
			for(int j = 0 ; j < 4 ; j++) {
				int nfr = fr + DIR[j][0];
				int nfc = fc + DIR[j][1];
				if(nfr < 1 || nfc < 1 || nfr > N || nfc > N) continue;
				if(map[nfr][nfc]>0) continue;
				if(fmap[nfr][nfc]==0) {
					s[top][0] = nfr;
					s[top++][1] = nfc;
				}
				fmap[nfr][nfc]++;
			}
		}
		int idx = 0, fmax = -1;
		while(idx<top){
			int cr = s[idx][0];
			int cc = s[idx][1];
			idx++;
			if(fmap[cr][cc]>fmax) {
				fmax = fmap[cr][cc];
				r = cr;
				c = cc;
			} else if(fmap[cr][cc]==fmax) {
				if(smap[cr][cc]>smap[r][c]){
					r = cr; 
					c = cc;
				}else if(smap[cr][cc]==smap[r][c]) {
					if(cr < r) {
						r = cr;
						c = cc;
					} else if(cr==r) {
						if(cc<c) {
							c = cc;
						}
					}
				}
			}
			fmap[cr][cc] = 0;
		}
		if(r==N+1||c==N+1){
			int smax = -1;
			for(int i = 1 ; i <= N ; i++) {
				for(int j = 1 ; j <= N ; j++) {
					if(smap[i][j]>smax) {
						r = i;
						c = j;
						smax = smap[i][j];
					}
					if(smax == 4) break;
				}
				if(smax == 4) break;
			}
		}
		map[r][c] = num;
		smap[r][c] = -1;
		pos[num][0] = r;
		pos[num][1] = c;
		for(int i = 0 ; i < 4 ; i++) {
			int nr = r + DIR[i][0];
			int nc = c + DIR[i][1];
			if(nr < 1 || nc < 1 || nr > N || nc > N) continue;
			smap[nr][nc]--;
		}
	}

	private static int calc(){
		int sum = 0;
		int[] val = {0,1,10,100,1000};
		for(int i = 1 ; i <= N ; i++) {
			for(int j = 1 ; j <= N ; j++) {
				int num = map[i][j];
				int cnt = 0;
				for(int k = 0 ; k < 4 ; k++) {
					int ni = i + DIR[k][0];
					int nj = j + DIR[k][1];
					if(ni<1||nj<1||ni>N||nj>N) continue;
					for(int l = 0 ; l < 4 ; l++) {
						if(map[ni][nj]==friend[num][l]) {
							cnt++;
							break;
						}
					}
				}
				sum+=val[cnt];
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int total = N*N;
		map = new int[N+1][N+1];
		fmap = new int[N+1][N+1]; // 선호도 카운팅
		smap = new int[N+1][N+1]; // 빈공간 카운팅
		smap[1][N] = 2;
		smap[1][1] = 2;
		smap[N][1] = 2;
		smap[N][N] = 2;
		for(int i = 2 ; i < N ; i++) {
			smap[i][1] = 3;
			smap[i][N] = 3;
			smap[1][i] = 3;
			smap[N][i] = 3;
			for(int j = 2 ; j < N ; j++) {
				smap[i][j] = 4;
			}
		}
		friend = new int[total+1][4];
		pos = new int[total+1][2];
		for(int i = 0 ; i < total ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			friend[num][0] = Integer.parseInt(st.nextToken());
			friend[num][1] = Integer.parseInt(st.nextToken());
			friend[num][2] = Integer.parseInt(st.nextToken());
			friend[num][3] = Integer.parseInt(st.nextToken());
			set(num);
		}
		// for(int i = 0 ; i <= N ; i++) {
		// 	System.out.println(Arrays.toString(map[i]));
		// }
		System.out.print(calc());
	}
}