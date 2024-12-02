import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 최적화된 순열 생성 코드 메모리 및 시간 절약
 * 중복 체크 방식을 개선하여 메모리 사용을 줄이고 효율성을 높임
 *
 * @author python98
 */
public class Main {
    static int N, M, arr1[];
    static boolean[] selected;
    static StringBuilder resultBuilder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        selected = new boolean[N];
        resultBuilder = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);
        process(0, new int[M]);
        System.out.print(resultBuilder);
    }

    private static void process(int depth, int[] arr2) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                resultBuilder.append(arr2[i]).append(' ');
            }
            resultBuilder.append('\n');
            return;
        }

        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (!selected[i] && arr1[i] != prev) {
                selected[i] = true;
                arr2[depth] = arr1[i];
                process(depth + 1, arr2);
                selected[i] = false;
                prev = arr1[i];
            }
        }
    }
}