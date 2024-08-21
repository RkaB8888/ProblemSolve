import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:22,924kb, 시간:2,575ms
 */
public class Solution {
	static int D;
	static int W;
	static int K;
	static boolean[][] map;
	static boolean[] A;
	static boolean[] B;
	static boolean flag;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new boolean[D][W];
			A = new boolean[W];
			B = new boolean[W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					B[j] = true;
					if (st.nextToken().equals("1")) {
						map[i][j] = true;
					} else {
						map[i][j] = false;
					}
				}
			} // map 설정 끝
			flag = false;
			result = Integer.MAX_VALUE;
			subSet(0, 0);
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static void subSet(int row, int cnt) {
		if(cnt>=result) return;
		if (row == D) {
			if (isPosible()) {
				if (cnt < result)
					result = cnt;
			}
			return;
		}
		//안바꿈
		subSet(row + 1, cnt);
		
		//임시 저장
		boolean[] temp = map[row];

		// A로 바꿈
		map[row] = A;
		subSet(row + 1, cnt + 1);

		// B로 바꿈
		map[row] = B;
		subSet(row + 1, cnt + 1);

		// 복구
		map[row] = temp;

	}

	private static boolean isPosible() {

		for (int j = 0; j < W; j++) {
			boolean flag = false;
			int trueCnt = 0, falseCnt = 0;
			for (int i = 0; i < D; i++) {
				if (map[i][j]) {
					trueCnt++;
					falseCnt = 0;
				} else {
					trueCnt = 0;
					falseCnt++;
				}
				if (trueCnt >= K || falseCnt >= K) {
					flag = true;
					break;
				}
			}
			if (flag) {
				continue;
			}
			return false;
		}
		return true;
	}

}