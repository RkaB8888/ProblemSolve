import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @description 위상정렬
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, result;
	static int[] whatIsProcessTime, whatIsTier, reachTime;
	static int[][] transTime;
	static List[] tiers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		whatIsProcessTime = new int[N + 1];
		transTime = new int[N + 1][N + 1];
		whatIsTier = new int[N + 1];
		reachTime = new int[N + 1];
		tiers = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			tiers[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				transTime[i][j] = transTime[j][i] = (i - j) * (i - j);
			}
		}
		int lastTier = 1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tier = Integer.parseInt(st.nextToken());
			int process = Integer.parseInt(st.nextToken());
			tiers[tier].add(i);
			whatIsTier[i] = tier;
			whatIsProcessTime[i] = process;
			lastTier = Math.max(lastTier, tier);
		}
		
//		for(int i = 1 ; i <= N ; i++) {
//			System.out.println(tiers[i].toString());
//		}
		
		for (int i = 1; i < lastTier; i++) {
			List<Integer> curTier = tiers[i];
			List<Integer> nextTier = tiers[i + 1];
			for (int curNum : curTier) {
				for (int nextNum : nextTier) {
					reachTime[nextNum] = Math.max(reachTime[nextNum],
							reachTime[curNum]+transTime[curNum][nextNum] + whatIsProcessTime[curNum]);
				}
			}
		}
		List<Integer> last = tiers[lastTier];
		for (int num : last) {
			result = Math.max(result, reachTime[num]+whatIsProcessTime[num]);
		}
		System.out.print(result);
	}
}