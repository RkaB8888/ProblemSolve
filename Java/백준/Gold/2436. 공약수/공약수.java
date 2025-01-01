import java.io.*;
import java.util.*;

/**
 * @description
 *    - 최대공약수 A, 최소공배수 B가 주어졌을 때 A와 B를 만족하는 두 수의 합이 최소가 되는 경우 계산.<br>
 *    - 소인수분해와 DFS 탐색을 조합하여 최적의 두 수를 찾음.
 * @performance 메모리: ? KB, 동작시간: ? ms (최적화)
 * @author python98
 */
public class Main {
    static int A, B, minSum = Integer.MAX_VALUE, resultA, resultB;
    static List<Integer> factors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        if (A == B) {
			sb.append(A).append(" ").append(B);
			System.out.print(sb);
			return;
		}

        int target = B / A;
        factors = new ArrayList<>();
        primeFactorization(target);

        findMin(A, A, 0);
        sb.append(resultA).append(" ").append(resultB);
        System.out.print(sb);
    }

    private static void primeFactorization(int n) {
        for (int i = 2; i <= n; i++) {
            int factor = 1;
            while (n % i == 0) {
                factor *= i;
                n /= i;
            }
            if (factor > 1) factors.add(factor);
        }
    }

    private static void findMin(int a, int b, int depth) {
        if (a + b >= minSum) return;
        if (depth == factors.size()) {
            minSum = a + b;
            resultA = Math.min(a, b);
            resultB = Math.max(a, b);
            return;
        }

        int factor = factors.get(depth);
        findMin(a * factor, b, depth + 1);
        findMin(a, b * factor, depth + 1);
    }
}