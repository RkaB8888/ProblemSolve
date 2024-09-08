import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 메모리 76,992KB 시간 236ms
 */
public class Solution {
	static int N, M, result;
	static char map[][], flag[][];
	static char[] blueLine;
	static char[] redLine;
	static char[] whiteLine;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][];
			flag = new char[N][];
			blueLine = new char[M];
			redLine = new char[M];
			whiteLine = new char[M];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < M; i++) {
				blueLine[i] = 'B';
				redLine[i] = 'R';
				whiteLine[i] = 'W';
			}
			flag[0] = whiteLine;
			flag[N - 1] = redLine;
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int restN = N - 2; // 남은 줄
			for (int i = restN; i > 0; i--) { // 파란색 갯수 i개 설정
				for (int j = restN - i; j >= 0; j--) { // 하얀색 갯수 j개 설정
					// restN-i-j가 빨간 줄 갯수
					for (int wc = 0; wc < j; wc++) {
						flag[1+wc] = whiteLine;
					}
					for (int bc = 0; bc < i; bc++) {
						flag[1+j+bc] = blueLine;
					}
					for (int rc = 0; rc < restN-i-j; rc++) {
						flag[1+j+i+rc] = redLine;
					}
					check();
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	public static void check() {
		int cnt = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j]!=flag[i][j]) {
					cnt++;
				}
				if(cnt>result) return;
			}
		}
		result = cnt;
	}

}