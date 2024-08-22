import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] Operand;
    static int[] Operator = new int[4]; // + - * /
    static int[] Permutation;
    static int max, min;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            Operand = new int[N];
            Permutation = new int[N - 1];
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            int pCnt = 0;
            for (int i = 0; i < 4; i++) {
                int count = Integer.parseInt(st.nextToken());
                for (int j = 0; j < count; j++) {
                    Permutation[pCnt++] = i;
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Operand[i] = Integer.parseInt(st.nextToken());
            }

            do {
                int result = Operand[0];
                for (int i = 0; i < N - 1; i++) {
                    result = calculate(result, Operand[i + 1], Permutation[i]);
                }
                max = Math.max(max, result);
                min = Math.min(min, result);
            } while (nextPermutation());

            sb.append('#').append(tc).append(' ').append(max - min).append('\n');
        }
        System.out.println(sb);
    }

    public static int calculate(int a, int b, int op) {
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3: return a / b;
            default: throw new IllegalArgumentException("Unknown operator");
        }
    }

    public static boolean nextPermutation() {
        int i = Permutation.length - 1;
        while (i > 0 && Permutation[i - 1] >= Permutation[i]) i--;
        if (i == 0) return false;

        int j = Permutation.length - 1;
        while (Permutation[i - 1] >= Permutation[j]) j--;
        swap(i - 1, j);

        j = Permutation.length - 1;
        while (i < j) swap(i++, j--);

        return true;
    }

    public static void swap(int a, int b) {
        int temp = Permutation[a];
        Permutation[a] = Permutation[b];
        Permutation[b] = temp;
    }
}