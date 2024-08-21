import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:27,296kb, 시간:224ms
 * 가능한 모든 부분 집합?(3진수)을 확인하면서 최소 횟수를 구함
 */
public class Solution {
	static int D; // 두께
	static int W; // 폭
	static int K; // 몇개 연속이냐
	static boolean[][] map;
	static boolean[] A; // 해당 열 교체시 투입할 것 false
	static boolean[] B; // 해당 열 교체시 투입할 것 true
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

	private static boolean isPosible() {//바꾼 맵의 조건이 성립하는지 판단

		a: for (int j = 0; j < W; j++) {
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
					continue a;//해당 열은 더이상 볼 필요 없음
				}
			}
			return false;//해당 열에서 조건이 안맞아서 끝까지 확인했다면
		}
		return true;
	}

}