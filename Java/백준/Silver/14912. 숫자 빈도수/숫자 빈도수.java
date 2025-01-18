import java.io.*;
import java.util.StringTokenizer;

/**
 * @description 수학적 계산 기반으로 특정 숫자 d의 등장 횟수 계산
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int n, d, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		counting();
		System.out.print(result);
	}

	private static void counting() {
		int pow = 1;
		while (n / pow > 0) {
			int pre = n % pow;
			int cur = (n / pow) % 10;
			int next = n / (pow * 10);

			if (cur < d) {
				result += next * pow;
			} else if (cur > d) {
				result += (next + 1) * pow;
			} else {
				result += next * pow + pre + 1;

			}

			if (d == 0) {
				result -= pow;
			}

			pow *= 10;
		}
	}
}