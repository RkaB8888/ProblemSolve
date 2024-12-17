import java.io.*;
/**
 * 구현 메모리 12,348 KB 시간 72 ms
 * 
 * @author python98
 */
public class Main {
	static final int GOAL = 6174; // 목표 값
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int num = Integer.parseInt(br.readLine()); // 숫자를 정수로 읽음
			result = 0;

			while (num != GOAL) {
				num = process(num);
				result++;
			}

			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static int process(int num) {
		// 카운팅 정렬
		int[] count = new int[10];
		for (int i = 0; i < 4; i++) {
			count[num % 10]++;
			num /= 10;
		}

		int large = 0, small = 0, multiplier = 1;
		for (int i = 9; i >= 0; i--) {
			while (count[i]-- > 0) {
				large = large * 10 + i;
				small += i * multiplier;
				multiplier *= 10;
			}
		}

		return large - small;
	}
}