import java.io.*;
import java.util.*;

/**
 * @description BFS + 시뮬레이션 (최적화)
 * @performance 메모리: 12,328 KB, 동작시간: 76 ms
 * @author python98
 */
public class Main {

	private static int N;
	private static long[] map; // 64bit N개로 비트마스킹
	private static long[][] dp; // 0:가로 1:세로

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new long[N];
		dp = new long[2][N];

		int[] B = new int[3];
        int[] E = new int[3];
        int[][] bPos = new int[3][2];
        int[][] ePos = new int[3][2];
		int bCnt = 0, eCnt = 0;

		for(int i = 0 ; i < N ; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; j++) {
				if(input[j]=='B') {
					bPos[bCnt][0] = i;
					bPos[bCnt++][1] = j;
				}else if(input[j]=='E') {
					ePos[eCnt][0] = i;
					ePos[eCnt++][1] = j;
				}else if(input[j] == '1'){
					map[i]|=(1L<<j);
				}
			}
		}
		B[0] = bPos[1][0];
		B[1] = bPos[1][1];
		B[2] = bPos[0][0]==bPos[1][0] ? 0:1;
		E[0] = ePos[1][0];
		E[1] = ePos[1][1];
		E[2] = ePos[0][0]==ePos[1][0] ? 0:1;

		if (B[0] == E[0] && B[1] == E[1] && B[2] == E[2]) {
            System.out.print(0);
            return;
        }

		int[] q = new int[10000]; // 50은 6bit 이내, 0:방향, 1~7:c, 8~14:r 
		int front = 0, rear = 0;

		q[rear++] = (B[0]<<8) | (B[1]<<1) | B[2];
		dp[B[2]][B[0]] |=(1L << B[1]);

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int result = 0;

		while(front<rear){
			int cnt = rear-front;
			result++;

			while(cnt-- > 0){
				int cur = q[front++];
				int r = cur >> 8;
				int c = (cur >> 1) & 0x7F;
				int d = cur & 1;

				// 상하좌우 이동
				for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (d == 0) { // 가로
                        if (nr < 0 || nr >= N || nc - 1 < 0 || nc + 1 >= N) continue;
                        if ((map[nr] & (7L << (nc - 1))) != 0L) continue;
                    } else { // 세로
                        if (nr - 1 < 0 || nr + 1 >= N || nc < 0 || nc >= N) continue;
                        long mask = 1L << nc;
                        if ((map[nr - 1] & mask) != 0L || (map[nr] & mask) != 0L || (map[nr + 1] & mask) != 0L) continue;
                    }
					// 방문 체크
                    if (((dp[d][nr] >> nc) & 1L) == 0L) {
                        if (nr == E[0] && nc == E[1] && d == E[2]) {
                            System.out.print(result);
                            return;
                        }
                        dp[d][nr] |= (1L << nc);
                        q[rear++] = (nr << 8) | (nc << 1) | d;
                    }
                }

				// 회전
                if (r - 1 >= 0 && r + 1 < N && c - 1 >= 0 && c + 1 < N) {
                    long mask = 7L << (c - 1);
                    if ((map[r - 1] & mask) == 0L && (map[r] & mask) == 0L && (map[r + 1] & mask) == 0L) {
                        int nd = d ^ 1;
						// 방문 체크
                        if (((dp[nd][r] >> c) & 1L) == 0L) {
                            if (r == E[0] && c == E[1] && nd == E[2]) {
                                System.out.print(result);
                                return;
                            }
                            dp[nd][r] |= (1L << c);
                            q[rear++] = (r << 8) | (c << 1) | nd;
                        }
                    }
                }
			}
		}
		System.out.print(0);
	}
}