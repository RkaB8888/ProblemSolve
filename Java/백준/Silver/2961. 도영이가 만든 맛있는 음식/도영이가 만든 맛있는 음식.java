import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:11,512KB, 시간:80ms
 */
public class Main {
	static int N;
	static int[] sin;// 신맛
	static int[] ssn;// 쓴맛
	static int minDiff = Integer.MAX_VALUE;// 차이의 최소

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		sin = new int[N];
		ssn = new int[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sin[i] = Integer.parseInt(st.nextToken());
			ssn[i] = Integer.parseInt(st.nextToken());
		}

		loop(0, 0, 0);

		System.out.println(minDiff);
	}

	public static void loop(int cnt, int sinSum, int ssnSum) {
		if (cnt == N) {
			if (sinSum == 0)
				return;
			int diff = Math.abs(sinSum - ssnSum);
			if (minDiff > diff) {
				minDiff = diff;
			}
			return;
		}
		loop(cnt + 1, sinSum, ssnSum);
		if (sinSum == 0) {
			loop(cnt + 1, sin[cnt], ssnSum + ssn[cnt]);
		} else
			loop(cnt + 1, sinSum * sin[cnt], ssnSum + ssn[cnt]);

	}

}