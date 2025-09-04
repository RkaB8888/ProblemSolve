import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {

    static int T, N, result;
    static int[] next, cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            next = new int[N + 1];
            cnt = new int[N + 1];
            result = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                next[i] = Integer.parseInt(st.nextToken());
                cnt[next[i]]++;
            }
            for(int i = 1; i <= N; i++) {
                if(cnt[i]==0) {
                    cnt[i]=-1;
                    result++;
                    count(next[i]);
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
    static void count(int i){
        cnt[i]--;
        if(cnt[i]==0){
            cnt[i]=-1;
            result++;
            count(next[i]);
        }
    }
}
