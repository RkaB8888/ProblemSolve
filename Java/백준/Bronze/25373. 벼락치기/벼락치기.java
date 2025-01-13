import java.io.*;

/**
 * @description 수학
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static long N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Long.parseLong(br.readLine());
		if (N > 28) {
			long a = 0, b = 0;
			N -= 28;
			a = N % 7;
			b = N / 7;
			result += 7 + b + (a == 0 ? 0 : 1);
		} else {
			int sum = 0, a = 0;
			while (sum < N) {
				a++;
				sum += a;
			}
			result = a;
		}
		System.out.print(result);
	}
}