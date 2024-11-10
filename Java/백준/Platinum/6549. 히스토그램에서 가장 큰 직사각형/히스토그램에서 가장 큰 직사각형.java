import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Stack ? KB 시간 ? ms
 */
public class Main {
	static int N, H[];
	static long result;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (true) {
			result = 0;
			stack = new Stack<Integer>();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;
			H = new int[N];
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				while (!stack.isEmpty() && H[stack.peek()] > H[i]) {
					int height = H[stack.pop()];
					int width = stack.isEmpty() ? i : i - stack.peek() - 1;
					result = Math.max(result, (long) height * width);
				}
				stack.push(i);
			}
			while (!stack.isEmpty()) {
                int height = H[stack.pop()];
                int width = stack.isEmpty() ? N : N - stack.peek() - 1;
                result = Math.max(result, (long) height * width);
            }
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
}