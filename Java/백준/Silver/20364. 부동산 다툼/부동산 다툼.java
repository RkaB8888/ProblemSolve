import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: 45,644 KB, 동작시간: 352 ms
 */
public class Main {
    static int N, Q;
    static int[] est;

    private static int nestInt() throws IOException {
        int c, n;
        while ((n = System.in.read()) <= 32) ;
        n &= 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        N = nestInt();
        Q = nestInt();
        est = new int[N + 1];
        for (int i = 1; i <= Q; i++) {
            int result = 0;
            int goal = nestInt();
            int estCheck = goal;
            while (estCheck > 1) {
                if (est[estCheck] != 0) {
                    result = estCheck;
                }
                estCheck >>= 1;
            }
            if (result == 0) est[goal] = i;
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}