import java.io.*;
import java.util.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

    // N이 0이라면 INSOMNIA
    // 10자리 비트 연산을 통해 각 자릿수가 등장 시 1로 변환
    // 이진 수 1111111111가 되면 반복문 종료 후 연산 결과 출력

    static int T, N, bits, result;
    static final int MAX = (1<<10)-1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            sb.append("Case #").append(i+1).append(": ");
            N = result = Integer.parseInt(br.readLine());
            bits = 0;
            if (N == 0) {
                sb.append("INSOMNIA\n");
            } else {
                getBits(result);
                while(bits != MAX) {
                    result += N;
                    getBits(result);
                }
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb);
    }
    private static void getBits(int n) {
        while(n != 0) {
            int temp = n % 10;
            bits |= (1<<temp);
            n /= 10;
        }
    }

}