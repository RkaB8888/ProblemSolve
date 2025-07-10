import java.io.*;
import java.util.*;

/**
 * @description 구현
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {

    // N >= M 인 경우 나머지는 무조건 0
    // N < M 인 경우 나머지를 계산

    static long N, M, result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        result = 1;

        if(N >= M) {
            result = 0;
        } else {
            for(long i = 1 ; i <= N ; i++) {
                result*=i;
                result%=M;
            }
        }

        System.out.print(result);

    }

}