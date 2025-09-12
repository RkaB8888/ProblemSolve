import java.io.*;
import java.util.*;

/**
 * @author python98
 * @description ?
 * @performance 메모리: ? KB, 동작시간: ? ms
 */
public class Main {
    static int N, M, result;
    static int[] balls, bowls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        balls = new int[N];
        bowls = new int[M];

        int total = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            balls[i] = Integer.parseInt(st.nextToken());
            total += balls[i];
        }
        int l = total/M, h = total;
        while(l<=h) {
            int mid = (l+h)>>1;
            int gn = 0, sum = 0;
            for(int i = 0 ; i < N && gn<M ; i++) {
                if(balls[i]>mid) {
                    gn = M;
                    break;
                }
                sum+=balls[i];
                if(sum>mid) {
                    sum = balls[i];
                    gn++;
                }
            }
            if(sum<=mid&&gn<M) {
                result = mid;
                h = mid-1;
            } else {
                l = mid+1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(result).append('\n');
        int cnt = 0, sum = 0, gn = 0;
        for(int i = 0 ; i < N ; i++) {
            sum+=balls[i];
            cnt++;
            if(sum>result) {
                sum = balls[i];
                bowls[gn++] = cnt-1;
                cnt = 1;
            }
        }
        bowls[gn] = cnt;
        int diff = M-gn-1;
        if(diff==0) {
            for(int c : bowls) {
                sb.append(c).append(' ');
            }
        } else {
            for(int i = 0 ; i < M ; i++) {
                if(bowls[i]==0) break;
                int c = bowls[i];
                while(c>1&&diff>0) {
                    sb.append(1).append(' ');
                    c--;
                    diff--;
                }
                sb.append(c).append(' ');
            }
        }
        System.out.print(sb);
    }

}