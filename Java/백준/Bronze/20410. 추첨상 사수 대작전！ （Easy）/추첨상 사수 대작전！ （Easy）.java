import java.io.*;
import java.util.*;

/**
 * @description
 *    - 이중 포문 완전 탐색<br>
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
	static int m, Seed, X1, X2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		m = Integer.parseInt(st.nextToken());
		Seed = Integer.parseInt(st.nextToken());
		X1 = Integer.parseInt(st.nextToken());
		X2 = Integer.parseInt(st.nextToken());
		out: for (int a = 0; a < m; a++) {
			for (int c = 0; c < m; c++) {
				if (X1 == (a * Seed + c) % m && X2 == (a * X1 + c) % m) {
					sb.append(a).append(" ").append(c);
					break out;
				}
			}
		}
		System.out.print(sb);
	}
}