import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    // 순열로 완전탐색 경우의 수 10!

    static int N;
    static int[] operand, operator;
    static int min = 1_000_000_000, max = -1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operand = new int[N];
        operator = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            operand[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        permutation(1, operand[0]);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(min);
        System.out.print(sb);
    }

    private static void permutation(int operandIdx, int result) {
        if (operandIdx == N) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operator[i] == 0) continue;
            operator[i]--;
            permutation(operandIdx + 1, operate(result, i, operand[operandIdx]));
            operator[i]++;
        }
    }

    private static int operate(int operand1, int i, int operand2) {
        switch (i) {
            case 0:
                return operand1 + operand2;
            case 1:
                return operand1 - operand2;
            case 2:
                return operand1 * operand2;
            case 3:
                return operand1 / operand2;
            default:
                return 0;
        }
    }

}