import java.io.*;
import java.util.*;

/**
 * @description BFS + 시뮬레이션
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

	private static final int[][] DIR = {{-1,0},{1,0},{0,-1},{0,1}}; // 위 아래 좌 우
	private static final int[][][] HORI_VERT = {{{0,-1},{0,0},{0,1}},{{-1,0},{0,0},{1,0}}};
	private static int N;
	private static long[] map; // 64bit N개로 비트마스킹
	private static long[][] dp; // 0:가로 1:세로
	private static int[] B, E;

	static class Pos{
		int r, c, d;
		Pos(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	private static boolean isPossible(Pos cur, int idx){
		if(idx==4) { // 회전
			if(cur.r-1<0||cur.r+1>=N||cur.c-1<0||cur.c+1>=N) return false;
			for(int i = cur.r-1 ; i <= cur.r+1 ; i++) {
				for(int j = cur.c-1 ; j <= cur.c+1 ; j++) {
					if(((map[i]>>j)&1)==1) return false;
				}
			}
			return true;
		}else {
			for(int i = 0 ; i < 3 ; i++){
				int nr = cur.r + HORI_VERT[cur.d][i][0] + DIR[idx][0]; // 막대 3개 중 위치 + 이동 방향
				int nc = cur.c + HORI_VERT[cur.d][i][1] + DIR[idx][1];
				if(nr<0||nc<0||nr>=N||nc>=N) return false;
				if(((map[nr]>>nc)&1)==1) return false;
			}
			return true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new long[N];
		dp = new long[2][N];

		int[][] b = new int[3][2];
		int[][] e = new int[3][2];
		int bCnt = 0, eCnt = 0;
		for(int i = 0 ; i < N ; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; j++) {
				long bit = 0l;
				if(input[j]=='B') {
					b[bCnt][0] = i;
					b[bCnt][1] = j;
					bCnt++;
				}else if(input[j]=='E') {
					e[eCnt][0] = i;
					e[eCnt][1] = j;
					eCnt++;
				}else {
					bit = input[j]-'0';
				}
				map[i]|=(bit<<j);
			}
		}
		B = new int[3];
		B[0] = b[1][0];
		B[1] = b[1][1];
		B[2] = b[0][0]==b[1][0] ? 0:1;
		E = new int[3];
		E[0] = e[1][0];
		E[1] = e[1][1];
		E[2] = e[0][0]==e[1][0] ? 0:1;

		Pos[] q = new Pos[10000];
		int front = 0, rear = 0;
		q[rear++] = new Pos(B[0], B[1], B[2]);
		dp[B[2]][B[0]]|=(1l<<B[1]);
		boolean flag = false;
		int result = 0;
		while(front<rear){
			int cnt = rear-front;
			while(cnt-->0){
				Pos cur = q[front++];
				if(cur.r==E[0]&&cur.c==E[1]&&cur.d==E[2]) {
					flag = true;
					break;
				}
				for(int i = 0 ; i < 5 ; i++) {
					if(isPossible(cur,i)){
						int nextR, nextC, nextD;
						if(i==4) {
							nextR = cur.r;
							nextC = cur.c;
							nextD = cur.d==0?1:0;
						} else {
							nextR = cur.r+DIR[i][0];
							nextC = cur.c+DIR[i][1];
							nextD = cur.d;
						}
						if(((dp[nextD][nextR]>>nextC)&1)==0) {
							dp[nextD][nextR]|=(1l<<nextC);
							q[rear++] = new Pos(nextR, nextC, nextD);
						}
					}
				}
			}
			if(flag){
				break;
			}
			result++;
		}
		if(!flag) result = 0;
		System.out.print(result);
	}
}