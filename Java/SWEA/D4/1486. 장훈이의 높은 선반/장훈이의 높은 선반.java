import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:30,516kb, 시간:289ms
 */
public class Solution {
	static int N;
	static int B;
	static int[] H;
	static int sumH;
	static int minDiff;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			H = new int[N];
			sumH = 0;
			minDiff = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			loop(0,0);

			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
		}
		System.out.println(sb);
	}
	public static void loop(int cnt, int sum) {
		if(sum>=B) {
			minDiff=Math.min(minDiff, sum-B);
			return;
		}
		if(cnt==N) {
			return;
		}
		loop(cnt+1,sum+H[cnt]);
		loop(cnt+1,sum);
	}
}
