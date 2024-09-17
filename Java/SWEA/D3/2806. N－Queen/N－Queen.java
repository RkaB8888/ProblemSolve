import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * ? KB ? ms
 * 완전탐색
 */
public class Solution {
	static int N, result[] = new int[11];
	static boolean map[][], colCheck[], backSlash[], slash[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Arrays.fill(result, -1);
		result[1] = 1;
		result[2] = 0;
		result[3] = 0;
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			if(result[N]==-1) {
				result[N] = 0;
				map = new boolean[N][N];
				colCheck = new boolean[N];
				backSlash = new boolean[2*N-1];//행 열 합이 0 ~ 2N-2
				slash = new boolean[2*N-1];//행 열 차가 -N+1 ~ N-1 -> N-1을 더함
				dfs(0);
			}
			sb.append('#').append(tc).append(' ').append(result[N]).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt) {
		if (cnt == N) {
			result[N]++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(colCheck[i]||backSlash[cnt+i]||slash[cnt-i+N-1]) continue;
			colCheck[i]=true;
			backSlash[cnt+i]=true;
			slash[cnt-i+N-1]=true;
			dfs(cnt+1);
			colCheck[i]=false;
			backSlash[cnt+i]=false;
			slash[cnt-i+N-1]=false;
		}
	}

}