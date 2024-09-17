import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * ? KB ? ms
 * N/2개의 쌍을 결정
 */
public class Solution {
	static int N, Cnt, nums[][];
	static long result;
	static boolean isSelect[];

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
			isSelect = new boolean[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				nums[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			}
			dfs(0,0);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int str) {
		if (cnt == Cnt) {
			calculate();
			return;
		}
		for (int i = str; i < N; i++) {
			if (isSelect[i]) continue;
			isSelect[i] = true;
			dfs(cnt + 1,i+1);
			isSelect[i] = false;
		}
	}
    public static void calculate() {
        long sumX = 0, sumY = 0;

        for (int i = 0; i < N; i++) {
            if (isSelect[i]) {
                sumX += nums[i][0];
                sumY += nums[i][1];
            } else {
                sumX -= nums[i][0];
                sumY -= nums[i][1];
            }
        }

        result = Math.min(result, sumX * sumX + sumY * sumY);
    }
}
