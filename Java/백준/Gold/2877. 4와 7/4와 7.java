import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        K+=1;
        while(K>1) {
            if((K&1)==1) {
                sb.append('7');
            } else {
                sb.append('4');
            }
            K>>=1;
        }
        System.out.print(sb.reverse());
    }
}
