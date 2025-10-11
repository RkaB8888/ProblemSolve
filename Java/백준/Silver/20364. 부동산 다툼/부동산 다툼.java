import java.io.*;
import java.util.*;

/**
 * @description 이 클래스에 대한 동작 설명
 * @performance 메모리: ? KB, 동작시간: ? ms
 * @author python98
 */
public class Main {
    static int N, Q;
    static int[] est;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        est = new int[N+1];
        for(int i = 1 ; i <= Q ; i++) {
            int result = 0;
            int goal = Integer.parseInt(br.readLine());
            int estCheck = goal;
            while(estCheck>1) {
                if(est[estCheck]!=0) {
                    result = estCheck;
                }
                estCheck>>=1;
            }
            if(result==0) est[goal] = i;
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}