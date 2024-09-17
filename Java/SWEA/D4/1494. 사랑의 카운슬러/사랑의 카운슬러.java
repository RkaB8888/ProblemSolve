import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 20,956 KB 328 ms
 * N/2개의 쌍을 결정
 */
public class Solution {
	static int N, Cnt, nums[][];
	static long result, sumX, sumY;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			Cnt = N / 2;
			nums = new int[N][];
			result = Long.MAX_VALUE;
			sumX = 0;
			sumY = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
				nums[i] = new int[] { x, y };
				sumX -= x;
				sumY -= y;
			}
			dfs(0, 0);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int str) {
		if (cnt == Cnt) {
			result = Math.min(result, sumX * sumX + sumY * sumY);
			return;
		}
		for (int i = str; i < N; i++) {
			int x = nums[i][0] * 2, y = nums[i][1] * 2;
			sumX += x;
			sumY += y;
			dfs(cnt + 1, i + 1);
			sumX -= x;
			sumY -= y;
		}
	}

}