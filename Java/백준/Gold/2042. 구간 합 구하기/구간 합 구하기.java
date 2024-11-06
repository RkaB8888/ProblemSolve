import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 세그먼트 트리 메모리 124,044 KB 시간 648 ms
 */
public class Main {
	static int N, M, K;
	static long[] seg;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		seg = new long[2 * N];
		for (int i = 0; i < N; i++) {
			seg[N + i] = Long.parseLong(br.readLine());
		}
		for (int i = N - 1; i > 0; i--) {
			seg[i] = seg[i * 2] + seg[i * 2 + 1];
		}
//		System.out.println(Arrays.toString(seg));
		for (int i = 0, iEnd = M + K; i < iEnd; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if (a == 1) {
				updateSeg(b - 1, c);
//				System.out.println("update : "+Arrays.toString(seg));
			} else {
				sb.append(findSeg(b - 1, (int) c - 1)).append('\n');
			}
		}
		System.out.print(sb);

	}

	private static void updateSeg(int idx, long val) {
		int strIdx = N + idx;
		long diff = val - seg[strIdx];
		while (strIdx > 0) {
			seg[strIdx] += diff;
			strIdx >>= 1;
		}
	}

	private static long findSeg(int targetStr, int targetEnd) {
		targetStr += N;
		targetEnd += N;
		long sum = 0;
		while (targetStr <= targetEnd) {
			if ((targetStr & 1) == 1)
				sum += seg[targetStr++];
			if ((targetEnd & 1) == 0)
				sum += seg[targetEnd--];
			targetStr >>= 1;
			targetEnd >>= 1;
		}
		return sum;
	}
}