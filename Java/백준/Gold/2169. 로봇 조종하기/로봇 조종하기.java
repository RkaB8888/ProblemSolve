import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description DP + Left/Right Sweep
 * @performance 메모리: 87,524 KB, 동작시간: 1,156 ms
 */
public class Main {

    static int N, M;
    static int[] cur, up, right, left;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cur = new int[M];
        up = new int[M];
        right = new int[M];
        left = new int[M];

        st = new StringTokenizer(br.readLine());
        up[0] = cur[0] = Integer.parseInt(st.nextToken());
        for(int j = 1; j < M; j++) {
            cur[j] = Integer.parseInt(st.nextToken());
            up[j] = up[j -1] + cur[j];
        }

        for(int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                cur[j] = Integer.parseInt(st.nextToken());
            }

            left[0] = up[0] + cur[0];
            for(int j = 1 ; j < M ; j++) {
                left[j] = Math.max(left[j-1], up[j])+ cur[j];
            }

            right[M-1] = up[M-1] + cur[M-1];
            for(int j = M-2 ; j >= 0 ; j--) {
                right[j] = Math.max(right[j+1],up[j]) + cur[j];
            }

            for(int j = 0 ; j < M ; j++) {
                up[j] = Math.max(left[j],right[j]);
            }
        }

        System.out.println(up[M-1]);

    }

}
