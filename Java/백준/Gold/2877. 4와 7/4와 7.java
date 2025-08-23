import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description Binary Representation + Mapping (4/7)
 * @performance 메모리: 11,508 KB, 동작시간: 64 ms
 */
public class Main {

    static int K, msb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine())+1;
        msb = 30-Integer.numberOfLeadingZeros(K);
        StringBuilder sb = new StringBuilder(msb);

        for(int i = msb ; i >= 0 ; i--) {
            int bit = (K >> i) & 1;
            sb.append(bit == 0 ? '4' : '7');
        }
        System.out.print(sb);
    }
}
