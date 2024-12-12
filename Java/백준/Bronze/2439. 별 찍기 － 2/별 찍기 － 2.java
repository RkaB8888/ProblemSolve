import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 출력 메모리 ? KB 시간 ? ms
 * 
 * @author python98
 *
 */
public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int j = N - 1; j >= 0; j--) {
			for (int k = 0; k < j; k++) {
				sb.append(' ');
			}
			for (int k = j; k < N; k++) {
				sb.append('*');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}