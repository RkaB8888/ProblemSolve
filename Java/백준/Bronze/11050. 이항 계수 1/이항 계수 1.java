import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description SCC 타잔 알고리즘
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, K, result, temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        K = Math.max(K,N-K);
        result = 1;
        temp = 1;
        while(N>K) {
            result*=N;
            N--;
            result/=temp;
            temp++;
        }

        System.out.print(result);
    }
}