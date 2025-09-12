import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, M, A; // 총인원 N명, 두부번호 M번, 주최자 번호 A번

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int m = (M>>1) + 1;
        int cur = A;
        int target;
        while((target=Integer.parseInt(br.readLine()))!=m) {
            cur += target-m;
            if(cur<1) cur+=N;
            else if(cur>N) cur-=N;
            sb.append(cur).append('\n');
        }
        sb.append(0);
        System.out.print(sb);
    }

}