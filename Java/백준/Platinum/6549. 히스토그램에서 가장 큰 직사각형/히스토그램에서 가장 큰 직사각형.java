import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * Stack ? KB 시간 ? ms
 */
public class Main {
    static int N;
    static long result;
    static int[] H;
    static int[] stack;
    static int top = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) break;

            result = 0;
            H = new int[N];
            stack = new int[N];  // 스택 대신 배열 사용
            
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                while (top >= 0 && H[stack[top]] > H[i]) { // 스택의 top을 배열의 인덱스로 활용
                    int height = H[stack[top--]];
                    int width = (top == -1) ? i : i - stack[top] - 1;
                    result = Math.max(result, (long) height * width);
                }
                stack[++top] = i;  // push 대신 배열 인덱스를 증가시켜 직접 값 할당
            }

            while (top >= 0) {  // 스택이 비어 있을 때까지
                int height = H[stack[top--]];
                int width = (top == -1) ? N : N - stack[top] - 1;
                result = Math.max(result, (long) height * width);
            }

            sb.append(result).append('\n');
        }

        System.out.print(sb);
    }
}