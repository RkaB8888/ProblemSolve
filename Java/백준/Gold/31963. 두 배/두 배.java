import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 그리디 메모리 37,664 KB 시간 256 ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int[] multiplyCount = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		multiplyCount[0] = 0;

		for (int i = 1; i < N; i++) {
			int neededMultiplier = (int) Math.ceil(Math.log((double) arr[i - 1] / arr[i]) / Math.log(2));
			multiplyCount[i] = Math.max(neededMultiplier + multiplyCount[i - 1], 0);

			// 결과에 곱셈 횟수를 누적
			result += multiplyCount[i];
		}

		System.out.print(result);
	}

}