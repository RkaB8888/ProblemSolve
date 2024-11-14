import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * DP 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N, result;
	static int[] DP, preDP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		preDP = new int[N];
		DP = new int[N];
		DP[0] = Integer.parseInt(br.readLine());
		preDP[0] = DP[0];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			DP[0] = preDP[0] + num;
			for (int j = 1; j < i; j++) {
				num = Integer.parseInt(st.nextToken());
				DP[j] = Math.max(preDP[j - 1] + num, preDP[j] + num);
			}
			num = Integer.parseInt(st.nextToken());
			DP[i] = preDP[i - 1] + num;
			System.arraycopy(DP, 0, preDP, 0, i + 1);
//			System.out.println(Arrays.toString(DP));
		}
		for (int i = 0; i < N; i++) {
			result = Math.max(result, DP[i]);
		}
		System.out.print(result);
	}

}