import java.io.*;
import java.util.*;

/**
 * @description Set
 * @performance 메모리: 57,100 KB, 동작시간: 500 ms
 * @author python98
 */
public class Main {
	static int N, M;
	static Set<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		set = new HashSet<>();

		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			if (set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.print(sb);
	}

}