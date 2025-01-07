import java.io.*;
import java.util.*;

/**
 * @description 위상정렬
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int N, result;
	static int[] processTime, reachTime;
	static int[][] transTime;
	static List[] tiers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		// 계급 인접리스트 생성
		tiers = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			tiers[i] = new ArrayList<Integer>();
		}
		
		// 전송시간 전처리
		transTime = new int[N + 1][N + 1];
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				transTime[i][j] = transTime[j][i] = (i - j) * (i - j);
			}
		}
		
		// 입력 처리
		processTime = new int[N + 1];
		int lastTier = 1;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tier = Integer.parseInt(st.nextToken());
			int process = Integer.parseInt(st.nextToken());
			tiers[tier].add(i);
			processTime[i] = process;
			lastTier = Math.max(lastTier, tier);
		}
		
		// 해당 번호까지의 도달 시간 계산
		reachTime = new int[N + 1];
		for (int i = 1; i < lastTier; i++) {
			List<Integer> curTier = tiers[i];
			List<Integer> nextTier = tiers[i + 1];
			for (int curNum : curTier) {
				for (int nextNum : nextTier) {
					reachTime[nextNum] = Math.max(reachTime[nextNum],
							reachTime[curNum]+transTime[curNum][nextNum] + processTime[curNum]);
				}
			}
		}
		
		// 최상위 계급의 동작 시간까지 계산
		List<Integer> last = tiers[lastTier];
		for (int num : last) {
			result = Math.max(result, reachTime[num]+processTime[num]);
		}
		
		System.out.print(result);
	}
}